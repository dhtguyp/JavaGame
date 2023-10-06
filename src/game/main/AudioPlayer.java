package game.main;

import java.io.*;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;

import javax.sound.sampled.FloatControl;

import javax.sound.sampled.LineUnavailableException;

import javax.sound.sampled.UnsupportedAudioFileException;



public class AudioPlayer {
	
	public static boolean toPlay = true;
	private static Clip play;
	public static void playMenuMusic(){

		try {

			AudioInputStream menuSound = AudioSystem.getAudioInputStream(new File("res/music/life.wav"));

			play = AudioSystem.getClip();

			play.open(menuSound);

			FloatControl volume = (FloatControl) play.getControl(FloatControl.Type.MASTER_GAIN);

			volume.setValue(0.5f);

			play.loop(Clip.LOOP_CONTINUOUSLY);
			
		}		
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e){

			e.printStackTrace();

		}

	}
	
	public static void playSound(){

		try {
			AudioInputStream menuSound = AudioSystem.getAudioInputStream(new File("res/music/woosh.wav")); //change to sound path
			play = AudioSystem.getClip();
			play.open(menuSound);
			FloatControl volume = (FloatControl) play.getControl(FloatControl.Type.MASTER_GAIN);
			volume.setValue(0.5f);
		}		
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e){

			e.printStackTrace();

		}

	}
	public static void stopMusic(){

		play.close();

	}

}
