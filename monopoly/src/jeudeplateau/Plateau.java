package jeudeplateau;

import java.util.ArrayList;

public abstract class Plateau {

	private int joueurActifID = 0;
	private int nombreDeJoueurs = 2;
	private int nombreDeCases = 20;
	private int nombreDeTours = 1;
	private ArrayList<Case> cases = new ArrayList<Case>();
	public D�s des = new D�s();
	
	public Plateau(int nombreDeJoueurs, int nbCases) {
		this.nombreDeJoueurs = nombreDeJoueurs;
		this.nombreDeCases = nbCases;
		for(int i=0; i<nombreDeCases; i++) {
			cases.add(null);
		}
	}
	
	public Case getCase(int i) {
		return this.cases.get(i);
	}
	public void setCase(int i, Case caze) {
		this.cases.set(i, caze);
	}
	public int getNbCases() {
		return this.nombreDeCases;
	}
	
	
	public int getNbJoueurs() {
		return this.nombreDeJoueurs;
	}
	
	
	public int getJoueurActifID() {
		return this.joueurActifID;
	}
	public void setJoueurSuivant() {
		this.joueurActifID++;
		if(this.joueurActifID >= this.nombreDeJoueurs) {
			this.joueurActifID = 0;
			this.nombreDeTours++;
		}
	}
	
	public int getNbTours() {
		return this.nombreDeTours;
	}
	
	public abstract boolean finPartie();
	public abstract Joueur estVainqueur();
	
}
