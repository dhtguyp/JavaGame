package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.Thread.State;
import java.util.Random;

public class Main extends Canvas implements Runnable{
	
	
	private static final long serialVersionUID = 1222757333564L;
	public static final int WIDTH = 800, HEIGHT = 600;
	private Thread thread; // gonna be only 1 thread in this game
	private boolean running = false;
	
	public static boolean pause = false;
	
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	private Leaderboard leaderboard;
	private Color color;
	ImageLoader imageloader;
	
	public enum STATE {
		Menu, Game, Leaderboard, Settings, GameOver
	};
	
	public STATE gameState = STATE.Menu;
		
	public Main() {
		
		
		
		AudioPlayer.playMenuMusic();
		leaderboard = new Leaderboard(this);
		imageloader = new ImageLoader();
		new Window(WIDTH, HEIGHT, "game", this); //Window class
		
		
		hud = new HUD();
		
		handler = new Handler();
		spawn = new Spawn(handler, hud, this);
		menu = new Menu(this, handler, spawn, hud, imageloader, leaderboard);
		
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		color = new Color(0, 0, 0);
		
	}
	
	public int getNewScore() {
		return hud.getScore();
	}
	
	public BufferedImage getTrophy() {
		return imageloader.loadTrophy();
	}
		
	public void callLeaderboard(String newName,int newScore) {
		leaderboard.editRecord(newName, newScore);
	}
	
	public synchronized void start() {
		
		thread = new Thread(this);
		running = true;
		thread.start();
		
	}
	public synchronized void stop() {
		
		try {
			thread.join();
			running = false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public void run() {	
		this.requestFocus();
		double delta = 0;
		double fps = 60.0;
		double ns = 1_000_000_000 / fps;
		int frames = 0;
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();	
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta > 1) {
				delta--;
				tick();
			}
			
			if(running) {
				render();
			}
			
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer+=1000;
				System.out.println("fps: " + frames);
				frames = 0;
				
			}	
		}
		stop();
		
		
	}
	private void tick() {
		if(!pause) {
			handler.tick();
			if (gameState == STATE.Game) {
				hud.tick();
				spawn.tick();
			}
			else if (gameState != STATE.Game) {
				menu.tick();
			}
		}
	}
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(color);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		handler.render(g);
		
		
		if(!pause) {
			if(gameState == STATE.Game) {
				g.setFont(new Font("Georgia", Font.PLAIN, 14));
				hud.render(g);
			}
			else if(gameState != STATE.Game){
				menu.render(g);
			}
			
		}
		
		else {
			g.setColor(Color.white);
			g.setFont(new Font("aerial", Font.BOLD, 40));
			g.drawString("PAUSE", 330, 100);
		}
		
		g.dispose();
		bs.show();
	}

	public static int clamp(int var, int min, int max) {
		if (var >= max) return var = max;
		else if (var <= min) return var=min;
		return var;
	}
	
	//
	//MAIN
	public static void main(String[] args) {
	
		new Main(); //Calls instructor
	
	}
}
