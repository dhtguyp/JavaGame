package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemyBullet extends GameObject{
	
	private Handler handler;
	ID tempid;
	private Random r;
	private Color color;
	
	public BossEnemyBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		this.handler = handler;
		r = new Random();
		setX_spd(x);
		y_spd = 5;
		
		int red = r.nextInt(255);
		int blue = r.nextInt(255);
		int green = r.nextInt(255);
		color = new Color(red, green, blue);
		
	}
	public void tick() {
		
		x+= x_spd;
		y+=y_spd;
		
		if(y >= Main.WIDTH) {
			handler.removeObject(this);
			
		}
		
		
		handler.addObject(new Trail(x, y, 24, 24, 0.05f, ID.Trail, color, handler));
	}
	public void render(Graphics g) {
		
		
		
		g.setColor(color);
		g.fillRect((int)x, (int)y, 24, 24);
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 24, 24);
	}
	
	@Override
	public void setX_spd(int x) {
		tempid = this.getId();
		if (tempid == id.BossEnemyBullet_C) x_spd = 0;
		else if (tempid == id.BossEnemyBullet_L) x_spd = -5;
		else if (tempid == id.BossEnemyBullet_R) x_spd = 5;
		
		this.x_spd = x_spd;
	}

}