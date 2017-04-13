package jeudeplateau;

import jeumonopoly.PlateauMonopoly;

public abstract class Case {

	String nom;
	private int prix = 0;
	private Joueur proprietaire;
	
	public Case(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getPrix() {
		return this.prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public Joueur getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}

	public abstract void actionCase(Joueur joueur, PlateauMonopoly plateau);
	
	
	
	
}
