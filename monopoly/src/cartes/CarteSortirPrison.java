package cartes;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Carte;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Type de {@link Carte} pour la sortie de prison. Cette carte est conserv�e par le joueur.
 * @see Carte
 */
public class CarteSortirPrison extends Carte {
	
	

	/**
	 * Unique constructeur de la clase {@link CarteSortirPrison}.
	 * @param titre String
	 * @param description String
	 */
	public CarteSortirPrison(String titre, String description) {
		super(titre, description);
	}

	/**
	 * M�thode r�alisant l'action de la carte. 
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp FenetrePrincipale
	 */
	@Override
	public void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		es.println(" > "+joueur.getNom()+" recoit la carte 'Sortir de prison' !");
		
		joueur.setCarteSortiePrison(true);
	}
	@Override
	public String toString() {
		return "CarteSortirPrison [" + super.toString() +"]";
	}
}
