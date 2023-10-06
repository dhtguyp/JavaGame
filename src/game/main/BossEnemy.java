package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BossEnemy extends GameObject{
	
	private Handler handler;
	private Main game;
	private int timer = 1300;

	
	public BossEnemy(int x, int y, ID id, Handler handler,Main game) {
		super(x, y, id, handler);
		
		this.handler = handler;
		this.game = game;
		
		x_spd = 0; // 
		y_spd = 2;
		
	}
	public void tick() {
		
		x+= x_spd;
		y+= y_spd;
			
		switch (timer) {
		case(1200): y_spd = 0; break;
		case(1180): x_spd = 3; break;
		case(600): x_spd = 7; break;
		case(90): x_spd = 0; break;
		case(0): y_spd = -4; break;
		
		}
		
		if(timer % 40 == 0 && timer < 1180 && timer >= 90) {
			new BossEnemyBullet(x+40, y+80, ID.BossEnemyBullet_L, handler);
			new BossEnemyBullet(x+40, y+80, ID.BossEnemyBullet_C, handler);
			new BossEnemyBullet(x+40, y+80, ID.BossEnemyBullet_R, handler);
		}

		
		if(y < -150) handler.removeObject(this);
		
		 if (timer > 560)timer--;
		 else timer-=2;
		 
		 
		 
		 
		if(x <= 0 || x >= Main.WIDTH - 70) x_spd *= -1;
	
		
//		handler.addObject(new Trail(x, y, 60, 60, 0.05f, ID.Trail, Color.blue, handler));
	}
	public void render(Graphics g) {
		
		
		
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 80, 80);
	}
	
public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 80, 80);
	}

}
