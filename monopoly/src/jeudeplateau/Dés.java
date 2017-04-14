package jeudeplateau;

import java.util.Random;

public class Dés {
	
	private int de1;
	private int de2;
	private Random rand = new Random();
	
	public int getDes() {
		return (this.de1 + this.de2);
	}
	
	public int getDe1(){
		return this.de1;
	}
	public int getDe2(){
		return this.de2;
	}
	
	public int lancerDes() {
		this.de1 = 1+this.rand.nextInt(6);
		this.de2 = 1+this.rand.nextInt(6);
		return getDes();
	}

}