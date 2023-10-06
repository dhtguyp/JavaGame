package game.main;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Main game;
	private int scoreKeep = 0;
	private Random r = new Random();
	
	public Spawn(Handler handler, HUD hud, Main game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		
	}
	
	public void tick() {
		scoreKeep++;
		
		if(scoreKeep > 500) {
			scoreKeep -= 500;
			hud.setLevel(hud.getLevel() + 1);
		
			
			if(hud.getLevel() % 5 == 0 && hud.getLevel() % 10 != 0)
				new SmartEnemy(r.nextInt(Main.WIDTH / 2) + 1, r.nextInt(Main.HEIGHT / 2) + 1, ID.SmartEnemy, handler);
			else if (hud.getLevel() % 3 == 0 && hud.getLevel() % 10 != 0)
				new FastEnemy(r.nextInt(Main.WIDTH / 2) + 1, r.nextInt(Main.HEIGHT / 2) + 1, ID.FastEnemy, handler);
			else if(hud.getLevel() % 10 == 0) {
				handler.clearEnemy();
				new BossEnemy((Main.WIDTH / 2) - 40, -150, ID.BossEnemy, handler, game);
			}
			else new BasicEnemy(r.nextInt(Main.WIDTH / 2) + 1, r.nextInt(Main.HEIGHT / 2) + 1, ID.BasicEnemy, handler);
			
			
		}
	}
	public void firstSpawn() {
		handler.clearEnemy();
		hud.resetHealth();
		hud.resetScore();
		hud.resetLevel();
		this.scoreKeep = 0;
		new BasicEnemy(r.nextInt(Main.WIDTH / 2) + 1, r.nextInt(Main.HEIGHT / 2) + 1, ID.BasicEnemy, handler);
	}
	
	public void spawnMenu() {
		
		for (int i = 0; i < 15; i++) {
			new MenuEnemy(r.nextInt(Main.WIDTH / 2) + 1, r.nextInt(Main.HEIGHT / 2) + 1, ID.MenuEnemy, handler);
		}
	}
	
}
