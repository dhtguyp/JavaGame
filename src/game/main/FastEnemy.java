package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class FastEnemy extends GameObject{
	private Random r;
	private Handler handler;
	private Color color;
	
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		this.handler = handler;
		r = new Random();
		x_spd = 2;
		y_spd = 8;
		
		int red = r.nextInt(255);
		int blue = r.nextInt(255);
		int green = r.nextInt(255);
		color = new Color(red, green, blue);
		
		
	}
	public void tick() {
		
		x+= x_spd;
		y+=y_spd;
		
		if(y <= 0 || y >= Main.HEIGHT - 50) y_spd *= -1;
		if(x <= 0 || x >= Main.WIDTH - 28) x_spd *= -1;
		
		handler.addObject(new Trail(x, y, 24, 24, 0.05f, ID.Trail, color, handler));
	}
	public void render(Graphics g) {
		
		
		
		g.setColor(color);
		g.fillRect((int)x, (int)y, 24, 24);
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 24, 24);
	}

}
