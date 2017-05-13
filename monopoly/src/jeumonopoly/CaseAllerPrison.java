package jeumonopoly;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;

public class CaseAllerPrison extends Case {
	
	public CaseAllerPrison() {
		super("Aller en prison");
	}
	
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		joueur.setEstPrison(true);
		joueur.setPosition(10);
		es.println(" > " + joueur.getNom() + " est envoyé en prison!");
	}

	@Override
	public String toString() {
		return "va en prison!";
	}

	@Override
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}
	
}
