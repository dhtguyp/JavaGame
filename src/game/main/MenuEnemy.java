package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class MenuEnemy extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	private Color color;
	private int dir;
	
	public MenuEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		this.handler = handler;
		x_spd = r.nextInt(5 - -5);
		y_spd = r.nextInt(5 - -5);
		if (x_spd < 3 && x_spd > -3) x_spd = 3;
		if (y_spd < 3 && y_spd > -3) y_spd = 3;		
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
		g.fillRect((int)x, (int)y, 16, 16);
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 24, 24);
	}

}

