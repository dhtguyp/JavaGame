package game.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ImageLoader {
	

	public player_image img_Set = player_image.Yaen;
	private BufferedImage buffer;
	private String path;
	
	private String robxPath = "/smile.jpg";

	private String yaenPath = "/yaen.png";
	

	
	
	
	private String trophyPath = "/trophy.jpg";
	private String mutePath = "/mute.png";
	private String notmutePath = "/notemute.png";
	private String arrowPath = "/arrow.png";
	public BufferedImage loadImage(player_image img) {
		
		switch(img) {
		case Yaen: path = robxPath; break; 
		case Robx: path = robxPath; break;
		case TEMP1: path = yaenPath; break; // Removed unecessary images for now.
		case TEMP2: path = robxPath; break;
		case TEMP3: path = yaenPath; break;
		case TEMP4: path = robxPath; break;
		
		
		}

		return loadImageActual(path);
	}
	
	
	public BufferedImage loadImageActual(String path) {
		
		
		try {
			buffer =  ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return buffer;
	}
		

	public BufferedImage loadTrophy() {
		return loadImageActual(trophyPath);
	}
	
	public BufferedImage loadMute() {
		return loadImageActual(mutePath);
	}
	
	public BufferedImage loadnotMute() {
		return loadImageActual(notmutePath);
	}
	
	public BufferedImage loadArrow() {
		return loadImageActual(arrowPath);
	}
	

}
