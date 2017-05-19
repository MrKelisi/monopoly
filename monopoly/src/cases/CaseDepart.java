package cases;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action d'une case départ
*@author WEBERT MORVRANGE
*/

public class CaseDepart extends Case {
	
	/**
	 * Indique le nom de la case
	 */
	public CaseDepart() {
		super("Depart");
	}
	
	/**
	 * Ajoute l'argent du passage sur la case
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		joueur.ajouterArgent(200);
		es.println(" > " + joueur.getNom() + " s'arrête sur la case départ: il reçoit 200€ supplémentaire !");
	}

	@Override
	/**
	 * ToString
	 */
	public String toString() {
		return "est sur la case depart : +1000!";
	}

	@Override
	/**
	 * Reprend la partie
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}
	
}
