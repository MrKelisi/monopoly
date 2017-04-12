package jeumonopoly;

import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseAllerPrison extends Case {
	
	public CaseAllerPrison() {
		super("Aller en prison");
	}
	
	public void actionCase(Joueur joueur, PlateauMonopoly plateau) {
		plateau.deplacerJoueur(joueur, 19);
		joueur.retirerArgent(500);
	}

	@Override
	public String toString() {
		return "va en prison!";
	}
	
}
