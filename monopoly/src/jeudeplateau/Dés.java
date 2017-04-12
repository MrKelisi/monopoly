package jeudeplateau;

import java.util.Random;

public class Dés {
	
	private int des;
	
	public int getDes() {
		return this.des;
	}
	
	public int lancerDes() {
		Random rand = new Random();
		int de1 = 1+rand.nextInt(6);
		int de2 = 1+rand.nextInt(6);
		
		this.des = de1+de2;
		return this.des;
	}
	
}