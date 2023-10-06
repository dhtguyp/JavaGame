package game.main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler { //update and render all objects
	
	
	ArrayList<GameObject> object = new ArrayList<GameObject>();
	
	public void tick() {
		for(int i = 0; i < object.size() ; i++) {
				GameObject tempObject = object.get(i);
				
				tempObject.tick();
			
	}

}
	public void render(Graphics g) {
		for(int j = 0; j < object.size() ; j++) {

			GameObject tempObject = object.get(j);
			
			tempObject.render(g);
}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearEnemy() {
		for(int k = 0; k < object.size(); k++) {
			GameObject tempObject = object.get(k);
			
			if (tempObject.getId() == ID.Player) continue;
			this.removeObject(tempObject);
			k--;
			
		}
	}
}
