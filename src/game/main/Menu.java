package game.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


import java.awt.AlphaComposite;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import game.main.Main.STATE;

public class Menu extends MouseAdapter{
	private Main game;
	private Handler handler;
	private Spawn spawn;
	private HUD hud;
	
	private ImageLoader imageloader;
	private BufferedImage buffer;
	private Leaderboard leaderboard;
	
	
	
	public static String yourname = "";
	public static int threeletters = 1, yourscore = 0;
	

	private Rectangle play_r = new Rectangle(Main.WIDTH / 2 - 81, 360, 150, 64),
			ldr_r = new Rectangle(Main.WIDTH / 4 - 116, 360, 150, 64),
			set_r = new Rectangle(Main.WIDTH / 4 * 3 - 48, 360, 150, 64),
			quit_r = new Rectangle(Main.WIDTH / 2 - 81, 460, 150, 64),
			back_r = new Rectangle(50, 450, 150, 64),
			
			alon_r = new Rectangle(500, 170, Player.player_w, Player.player_h),
			arrow_r = new Rectangle(600, 155,64, 64),
			
			
			sound_r = new Rectangle(Main.WIDTH / 2 + 90 , 310, 64, 64);
	
	
	
	public Menu(Main game, Handler handler, Spawn spawn, HUD hud, ImageLoader imageloader,Leaderboard leaderboard) {
		this.game = game;
		this.handler = handler;
		this.spawn = spawn;
		this.hud = hud;
		this.imageloader = imageloader;
		this.leaderboard = leaderboard;
		
		spawn.spawnMenu();
		
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) menuMouse(mx, my);
		else if(game.gameState == STATE.GameOver) gameOverMouse(mx, my);
		else if(game.gameState == STATE.Leaderboard) leaderMouse(mx, my);
		else if(game.gameState == STATE.Settings) settingsMouse(mx, my);
	}
	
//	private void mouseReleased(MouseEvent e) {
//		
//	}
	
	private void gameOverMouse(int mx, int my) {
		if(mouseOver(mx, my, back_r)) {
			game.gameState = STATE.Menu;
			spawn.spawnMenu();
			if(AudioPlayer.toPlay)AudioPlayer.playMenuMusic();
		}
	}
//  Mouse over methods
	private void menuMouse(int mx, int my) {
		if(mouseOver(mx, my, play_r)) {
			game.gameState = STATE.Game;
			AudioPlayer.stopMusic();
			
			new Player(Main.WIDTH / 2-32, Main.HEIGHT / 2-32, ID.Player, handler,game ,imageloader, imageloader.img_Set);
			spawn.firstSpawn();
		}
		
		if(mouseOver(mx, my, ldr_r)) {
			game.gameState = STATE.Leaderboard;
		}
		
		if(mouseOver(mx, my, set_r)) {
			game.gameState = STATE.Settings;
			handler.clearEnemy();
		}
		
		if(mouseOver(mx, my, quit_r)) {
			AudioPlayer.stopMusic();
			System.exit(1);
		}
	}
	
	private void settingsMouse(int mx, int my) {
		if(mouseOver(mx, my, arrow_r)) {
			 if(imageloader.img_Set == player_image.Yaen) imageloader.img_Set = player_image.Robx; 
			 else if(imageloader.img_Set == player_image.Robx) imageloader.img_Set = player_image.TEMP1; 
			 else if(imageloader.img_Set == player_image.TEMP1) imageloader.img_Set = player_image.TEMP2;
			 else if(imageloader.img_Set == player_image.TEMP2) imageloader.img_Set = player_image.TEMP3;
			 else if(imageloader.img_Set == player_image.TEMP3) imageloader.img_Set = player_image.TEMP4;
			 else if(imageloader.img_Set == player_image.TEMP4) imageloader.img_Set = player_image.Yaen;// --------
		}
		
		
		if(mouseOver(mx, my, sound_r)) {
			if(AudioPlayer.toPlay) {AudioPlayer.toPlay = false; AudioPlayer.stopMusic();}
			else {AudioPlayer.toPlay = true; AudioPlayer.playMenuMusic();}
		}
		
		
		if(mouseOver(mx, my, back_r)) {
			game.gameState = STATE.Menu;
			spawn.spawnMenu();
		}
	}
	
	private void leaderMouse(int mx, int my) {
		if(mouseOver(mx, my, back_r)) {
			
			game.gameState = STATE.Menu;
		}
	}
		
	
	
	private boolean mouseOver(int mx, int my, Rectangle rect) {
		
		if (mx >= rect.x && mx <= (rect.x + rect.width) && my >= rect.y && my <= (rect.y+rect.height)) return true;
		
		return false;
	}
	
	public void tick() {
		
	}	
	
	
	
	public void render(Graphics g) {
		Font fnt = new Font("aerial", Font.BOLD, 46); //FONTS
		Font fnt2 = new Font("aerial", Font.BOLD, 20);
		Font fnt3 = new Font("aerial", Font.ITALIC, 18);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(0.9f));
		
		if (game.gameState == STATE.Menu) {
			render_menu(g, fnt, fnt2);
		}
		
		else if(game.gameState == STATE.GameOver) {
			render_GameOver(g, fnt, fnt2, fnt3);
			
		}
		
		else if(game.gameState == STATE.Settings) {
			render_settings(g, fnt, fnt2);
		}
		
		else if(game.gameState == STATE.Leaderboard) {
			render_leader(g, fnt, fnt2, fnt3);
		}
		
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}
	//All graphics methods
	private void render_menu(Graphics g, Font fnt, Font fnt2) {
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Alon's Game", Main.WIDTH / 2 - 140, 100);
		
		g.setFont(fnt2);
		
		g.setColor(Color.red);
		g.fillRect(play_r.x, play_r.y, play_r.width, play_r.height);
		g.setColor(Color.black);
		g.drawString("Play", Main.WIDTH / 2 - 28, 400);
		
		g.setColor(Color.orange);
		g.fillRect(ldr_r.x, ldr_r.y, ldr_r.width, ldr_r.height);
		g.setColor(Color.black);
		g.drawString("Leaderboard", Main.WIDTH / 4 - 103, 400);
		
		g.setColor(Color.orange);
		g.fillRect(set_r.x, set_r.y, set_r.width, set_r.height);
		g.setColor(Color.black);
		g.drawString("Settings", Main.WIDTH / 4 * 3 - 13, 400);
		
		g.setColor(Color.orange);
		g.fillRect(quit_r.x, quit_r.y, quit_r.width, quit_r.height);
		g.setColor(Color.black);
		g.drawString("Quit", Main.WIDTH / 2 - 28, 500);
	}
	
	private void render_leader(Graphics g, Font fnt, Font fnt2, Font fnt3) { //Need to make a functional leaderboard
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Leaderboard", Main.WIDTH / 2 - 150, 100);
		
		
		
		g.setFont(fnt3);
		//leaderboard content
		leaderboard.printLeaderboard(g);
		//leaderboard end
		
		g.setFont(fnt2);
		g.setColor(Color.orange);
		g.fillRect(back_r.x, back_r.y, back_r.width, back_r.height);
		g.setColor(Color.black);
		g.drawString("Back", back_r.x + 50, back_r.y + 40);

	}
	private void render_settings(Graphics g, Font fnt, Font fnt2) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Settings", Main.WIDTH / 2 - 100, 100);
		
		g.setFont(fnt2);
	
		//need to change
		g.drawString("Select image: ", Main.WIDTH / 2 - 200, 200);
		
		buffer = imageloader.loadImage(imageloader.img_Set);
		g2d.drawImage(buffer, alon_r.x, alon_r.y, null);
		buffer = imageloader.loadArrow();
		g2d.drawImage(buffer, arrow_r.x, arrow_r.y, null);
		//end need to change

		
		
		//MUTE UNMUTE
		g.setColor(Color.white);
		g.drawString("Mute / unmute: ", Main.WIDTH / 2 - 203, 350);
		if(AudioPlayer.toPlay) g.drawImage(imageloader.loadnotMute(), sound_r.x, sound_r.y, null);
		else if(!AudioPlayer.toPlay) g.drawImage(imageloader.loadMute(), sound_r.x , sound_r.y, null);
		//end mute
		
		g.setColor(Color.orange);
		g.fillRect(back_r.x, back_r.y, back_r.width, back_r.height);
		g.setColor(Color.black);
		g.drawString("Back", back_r.x + 50, back_r.y + 40);
	}
	private void render_GameOver(Graphics g, Font fnt, Font fnt2, Font fnt3) { //Need to update.
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Game Over", Main.WIDTH / 2 - 128, 100);
		
		g.setFont(fnt3);
		g.drawString("Your Score: " + hud.getScore(), Main.WIDTH / 2 - 80, 180);
		
		//Write name, and then add to scoreboard if has enough points. 
		g.drawString("Press space, then enter your name: ",  Main.WIDTH / 2 - 220, 310);
		g.drawString("Press enter to confirm. ",  Main.WIDTH / 2 - 110, 440);
		g.drawString(yourname.toUpperCase(),  Main.WIDTH / 2 + 80, 310);
		
		//END
		g.setFont(fnt2);
		g.setColor(Color.orange);
		g.fillRect(back_r.x, back_r.y, back_r.width, back_r.height);
		g.setColor(Color.black);
		g.drawString("Back", back_r.x + 50, back_r.y + 40);
	}
	
}
