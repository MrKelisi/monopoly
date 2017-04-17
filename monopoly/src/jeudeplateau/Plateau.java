package jeudeplateau;

import jeudeplateau.Joueur;

public abstract class Plateau {

	private int joueurActif;
	private int nombreDeJoueurs;
	private Joueur[] joueurs;
	private int nombreDeCases = 20;
	private Case[] cases;
	private int nombreDeTours;
	public Dés des = new Dés();
	
	public Plateau(int nombreDeJoueurs, int nbCases) {
		this.nombreDeJoueurs = nombreDeJoueurs;
		this.joueurActif = 0;
		this.nombreDeCases = nbCases;
		this.nombreDeTours = 1;
		this.cases = new Case[this.nombreDeCases];
		this.joueurs = new Joueur[this.nombreDeJoueurs];
	}
	
	public Case getCase(int i) {
		return this.cases[i];
	}
	public void setCase(int i, Case caze) {
		this.cases[i] = caze;
	}
	public int getNbCases() {
		return this.nombreDeCases;
	}
	
	
	public Joueur getJoueur(int i) {
		return this.joueurs[i];
	}
	public void setJoueur(int i, Joueur joueur) {
		this.joueurs[i] = joueur;
	}
	public int getNbJoueurs() {
		return this.nombreDeJoueurs;
	}
	
	
	public int getJoueurActifID() {
		return this.joueurActif;
	}
	public Joueur getJoueurActif() {
		return this.joueurs[this.joueurActif];
	}
	public void setJoueurSuivant() {
		this.joueurActif++;
		if(this.joueurActif >= this.nombreDeJoueurs) {
			this.joueurActif = 0;
			this.nombreDeTours++;
		}
	}
	
	
	public int getNbTours() {
		return this.nombreDeTours;
	}
	
	public boolean finPartie() {
		int nbJoueursEnJeu = 0;
		for(Joueur j:joueurs) {
			if(!j.getEstBanqueroute()) nbJoueursEnJeu++;
		}
		return (nbJoueursEnJeu <= 1 && getJoueurActifID() == 0);
	}
	
	public Joueur estVainqueur() {
		int res = 0;
		for(int i=0; i<joueurs.length; i++) {
			if(getJoueur(i).getArgent() > getJoueur(res).getArgent())
				res = i;
		}
		return getJoueur(res);
	}
	
}
