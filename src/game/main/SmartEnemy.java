package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{

	
	private Handler handler;
	private GameObject player;
	
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		x_spd = 1;
		y_spd = 1;
		this.handler = handler;
	
		
		for (int i = 0; i < handler.object.size() ; i++) {
			if (handler.object.get(i).getId() == id.Player){
				player = handler.object.get(i);
			}
		}

		
	}
	public void tick() {
		
		int px = player.getX();
		int py = player.getY();
		
		if(x-px<0) x+= x_spd;
		else x-=x_spd;
		if(y-py<0) y+= y_spd;
		else y-=y_spd;
		
		
	
	
		
		handler.addObject(new Trail(x, y, 16, 16, 0.05f, ID.SmartEnemy, Color.PINK, handler));
	}
	public void render(Graphics g) {
		
		
		
		g.setColor(Color.PINK);
		g.fillRect((int)x, (int)y, 16, 16);
	}
	
public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}