package cases;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Cr�e l'action d'une case d�part
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
		es.println(" > " + joueur.getNom() + " s'arr�te sur la case d�part: il re�oit 200� suppl�mentaire !");
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
