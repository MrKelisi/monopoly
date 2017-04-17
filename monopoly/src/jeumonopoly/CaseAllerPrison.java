package jeumonopoly;

import fenetres.FenetrePrincipale;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseAllerPrison extends Case {
	
	public CaseAllerPrison() {
		super("Aller en prison");
	}
	
	public void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		joueur.setEstPrison(true);
		joueur.setPosition(10);
		System.out.println(" > " + joueur.getNom() + " est envoyé en prison!");
	}

	@Override
	public String toString() {
		return "va en prison!";
	}
	
}
