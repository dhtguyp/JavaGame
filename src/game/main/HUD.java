package game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD { //Heads up Display

	public static int HEALTH = 100;
	private int greenValue = 255;
	private int score = 0, level = 1;
	
	
	
	public void tick() {
		

		
		Main.clamp(HEALTH, 0, 100);
//		Main.clamp(greenValue, 0, 255);
		
		greenValue = HEALTH * 2 + 55;
		score += 1;
		
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 30);
		
		g.setColor(Color.getHSBColor( (1f * HEALTH) / 360, 1f, 1f));
		
		
		g.fillRect(15, 15, HEALTH * 2, 30);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 30);
		g.drawString(("Score: "+  score), 17, 64);
		g.drawString(("Level:  "+  level), 17, 78);
	}

	//GET SET
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void resetHealth() {
		HEALTH = 100;
	}
	public void resetScore() {
		score = 0;
	}
	public void resetLevel() {
		level = 1;
	}
	
}
