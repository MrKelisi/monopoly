package jeumonopoly;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;

public class CaseChance extends Case {

	
	
	public CaseChance() {
		super("Chance");
	}

	@Override
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		es.println(" > " + joueur.getNom() + " tire une carte chance...");
		
		/*fp.tirerCarte(true, "Titre", "Description");*/
	}

	@Override
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}

}
