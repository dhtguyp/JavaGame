package game.main;

import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;  

//TEXT TEST:
//PLYR-5000,GUY-3500,PLYR-2500,PLYR-2000,PLYR-1600,PLYR-1300,PLYR-1000,PLYR-700,PLYR-500,PLYR-333,

public class Leaderboard {
	
	

	public String fileName, fileScoreString;
	String newName;
	int newScore = 0;
	static String filepath = "leader/text.txt";
	
	private File oldFile;
	
	FileWriter fw;
	
	private Main game;

	public Leaderboard(Main game) {
		
		oldFile = new File(filepath);
		this.game = game;
	}

	
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public int getNewScore() {
		return newScore;
	}
	public void setNewScore(int newScore) {
		this.newScore = newScore;
	}


	private static Scanner x;
	public void editRecord(String newName, int newScore) {
		if (newName == "") newName = "PLYR";
		
		String tempName = newName.toUpperCase();
		int tempScore = newScore; //10001
		boolean change = false;

		
		int fileScore;
		
		String newleader = "";
		
		File oldFile = new File(filepath);
		
		try {
			

			x = new Scanner(oldFile);
			x.useDelimiter("[,\n-]");
			int i = 1;
			
			while(i <= 10) { 
				fileName = x.next(); //GUYY
				fileScoreString = x.next();
				fileScore = Integer.parseInt(fileScoreString);
				if(newScore > fileScore) {
						change = true;
						tempName = fileName;
						tempScore = Integer.parseInt(fileScoreString);
						
						fileName = newName;
						fileScore = newScore;
						
						newName = tempName;
						newScore = tempScore;	
				}
				newleader = newleader.concat(fileName + "-" + fileScore + ",");
				i++;
			}
			
			x.close();

			FileWriter fw = new FileWriter(oldFile, false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.printf(newleader);
			
			pw.flush();
			pw.close();
			fw.close();
			bw.close();
			bw = null;

			
		}
		catch (IOException e){
			System.out.println("Editing leaderboard error - file not found.");
		}
	}
	
	public void printLeaderboard(Graphics g) {
		
		g.drawImage(game.getTrophy(), 570, 104, null);
		try {
		
			x = new Scanner(oldFile);
			x.useDelimiter("[,\n-]");
			int i = 1;
			int y_name = 140;

			while(i <= 10) { 
				fileName = x.next(); //GUYY
				fileScoreString = x.next();
				
				g.drawString(i++ + "). " +fileName, 200, y_name);
				g.drawString("..............................................", 285, y_name);
				g.drawString(fileScoreString, 520, y_name);
				
			y_name+= 30;
			}
		
		x.close();
		} catch(IOException e) {
			System.out.println("Printing leaderboard error - file not found");

		}
	
	}
	
}
