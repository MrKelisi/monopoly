package jeumonopoly;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;

public class CaseCommunaute extends Case {

	public CaseCommunaute() {
		super("Communauté");
	}

	@Override
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		String titre = "Titre";
		String description = "Description";
		es.println(" > " + joueur.getNom() + " tire une carte communauté...");
		
		//fp.tirerCarte(false, titre, description);
	}

	@Override
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}
	
}
