package cartes;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Carte;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

public class CarteSortirPrison extends Carte {
	
	public CarteSortirPrison(String titre, String description) {
		super(titre, description);
		
	}

	@Override
	public void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		es.println(" > "+joueur.getNom()+" recoit la carte 'Sortir de prison' !");
		
		joueur.setCarteSortiePrison(true);
	}
}
