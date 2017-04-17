package jeumonopoly;

import fenetres.FenetrePrincipale;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseChance extends Case {

	public CaseChance() {
		super("Chance");
	}

	@Override
	public void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		String titre = "Titre";
		String description = "Description";
		System.out.println(" > " + joueur.getNom() + " tire une carte chance...");
		
		//fp.tirerCarte(true, titre, description);
	}

}
