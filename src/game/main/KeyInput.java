package game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.main.Main.STATE;

public class KeyInput extends KeyAdapter{
	
	
	private String temp;
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	Main game;
	public static boolean spaceforname = false;
	
	
	public KeyInput(Handler handler, Main game) {
		this.handler = handler;
		this.game = game;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	
	public void gameOverName(KeyEvent e, int key) {
		if(Menu.threeletters < 5) {
			
			temp = String.valueOf(e.getKeyChar());
			Menu.yourname = Menu.yourname.concat(temp);
			System.out.println(Menu.yourname);
			Menu.threeletters++;
		
	}
	else {
		Menu.yourname= Menu.yourname.substring(0, 3);
		temp = String.valueOf(e.getKeyChar());
		Menu.yourname = Menu.yourname.concat(temp);
		System.out.println(Menu.yourname);
	}

	
	
	
	}
	

	public void keyPressed(KeyEvent a) {
		int key = a.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) { //checks all objects and searching for PLAYER
			GameObject tempObject = handler.object.get(i);
			
			try {if(tempObject.getId() == ID.Player) {
			
				//key events for player
				
				if(key == KeyEvent.VK_W) {tempObject.setY_spd(-5); keyDown[0] = true;}
				if(key == KeyEvent.VK_A) {tempObject.setX_spd(-5); keyDown[1] = true;}
				if(key == KeyEvent.VK_S) {tempObject.setY_spd(5); keyDown[2] = true;}
				if(key == KeyEvent.VK_D) {tempObject.setX_spd(5); keyDown[3] = true;}
			}
			}
			
			catch(Exception e) {
				
			}
			
		}
		if(key == KeyEvent.VK_P) {
			if(game.gameState == STATE.Game) {
				if(Main.pause) Main.pause = false;
				else Main.pause = true;
			}
		}
		
		if(key == KeyEvent.VK_ENTER) {
			if(game.gameState == STATE.GameOver) {
				//Check to update leaderboard
				game.callLeaderboard(Menu.yourname, game.getNewScore());
				game.gameState = STATE.Menu;
			}
		}
		
		if(game.gameState == STATE.GameOver && key == KeyEvent.VK_SPACE && !spaceforname) {
			spaceforname = true;
		}
		
		if(game.gameState == STATE.GameOver && key != KeyEvent.VK_BACK_SPACE && key != KeyEvent.VK_SPACE) {
			if(spaceforname) gameOverName(a, key);
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			AudioPlayer.stopMusic();
			System.exit(1);
		}
		
	 }
	
	public void keyReleased(KeyEvent a) {
		
		int key = a.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) { //checks all objects and searching for PLAYER
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
			
				//key events for player
				
				if(key == KeyEvent.VK_W) {keyDown[0] = false;}
				if(key == KeyEvent.VK_A) {keyDown[1] = false;}
				if(key == KeyEvent.VK_S) {keyDown[2] = false;}
				if(key == KeyEvent.VK_D) {keyDown[3] = false;}
				
				if(!keyDown[0] && !keyDown[2]) tempObject.setY_spd(0);
				if(!keyDown[1] && !keyDown[3]) tempObject.setX_spd(0);
			}
			}
	
	}
	
}