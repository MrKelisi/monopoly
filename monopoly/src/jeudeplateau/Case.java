package jeudeplateau;

import jeumonopoly.PlateauMonopoly;

public abstract class Case {

	String nom;
	
	public Case(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public abstract void actionCase(Joueur joueur, PlateauMonopoly plateau);
	
}
