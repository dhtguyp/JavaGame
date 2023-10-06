package game.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {//every object extends this class
	
	protected int x, y;
	protected ID id;
	protected int x_spd, y_spd;
	private Handler handler;
	
	public GameObject(int x, int y, ID id, Handler handler) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		handler.addObject(this);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	
	//Getters and Setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getX_spd() {
		return x_spd;
	}

	public void setX_spd(int x_spd) {
		this.x_spd = x_spd;
	}

	public int getY_spd() {
		return y_spd;
	}

	public void setY_spd(int y_spd) {
		this.y_spd = y_spd;
	}
	
	
	
}
