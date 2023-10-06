package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import game.main.Main.STATE;

public class Player extends GameObject{
	
	Random r;
	Handler handler;
	Main game;
	ImageLoader imageloader;
	BufferedImage play;
	public static final int player_w = 38, player_h = 38;
	
	public player_image img_Set = player_image.Yaen;


	public Player(int x, int y, ID id, Handler handler, Main game,ImageLoader imageloader, player_image img_Set) {
		super(x, y, id, handler);
		y_spd = 0;
		x_spd = 0;
		this.handler = handler;
		this.game = game;
		this.imageloader = imageloader;
		this.img_Set = img_Set;
		
	}
	

	@Override
	public void tick() {

		
		x+=x_spd;
		y+=y_spd;
		
		x = Main.clamp(x, 0, Main.WIDTH - 53);
		y = Main.clamp(y, 0, Main.HEIGHT - 75);
		collision();
		
//		handler.addObject(new Trail(x, y, player_w, player_h, 0.05f, ID.Trail, Color.green, handler));
		
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		g.setColor(Color.black);
		g2d.drawImage(imageloader.loadImage(img_Set), (int)x, (int)y, null);
	}

	private void collision() {
		for(int i = 0; i < handler.object.size() ; i++) {
		
			GameObject temp = handler.object.get(i);
			ID tempid = temp.getId();
			if(tempid == ID.BasicEnemy || tempid == ID.FastEnemy || tempid == ID.SmartEnemy 
					|| tempid == ID.BossEnemyBullet_L
					|| tempid == ID.BossEnemyBullet_C || tempid == ID.BossEnemyBullet_R){
				if(getBounds().intersects(temp.getBounds())) {
					//reduce hp once collides
					if (HUD.HEALTH > 0)HUD.HEALTH -= 2;
				}
			}
			else if(tempid == ID.BossEnemy) {
				if(getBounds().intersects(temp.getBounds())) {
					HUD.HEALTH = 0;
				}
			}
			if (HUD.HEALTH <= 0) { //GAMEOVER
				handler.object.clear();
				Menu.yourname = "";
				Menu.threeletters = 1;
				Menu.yourscore = game.getNewScore();
				KeyInput.spaceforname = false;
				game.gameState = STATE.GameOver;
			}
		}
	}
	
	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x-1, (int)y-1, player_w+1, player_h+1);
	}
	
	

}
