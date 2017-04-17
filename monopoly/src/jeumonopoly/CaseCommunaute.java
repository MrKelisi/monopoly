package jeumonopoly;

import fenetres.FenetrePrincipale;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseCommunaute extends Case {

	public CaseCommunaute() {
		super("Communauté");
	}

	@Override
	public void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		String titre = "Titre";
		String description = "Description";
		System.out.println(" > " + joueur.getNom() + " tire une carte communauté...");
		
		//fp.tirerCarte(false, titre, description);
	}

}
