package cases;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action pour aller en prison
*@author WEBERT MORVRANGE
*@see Case
*/
public class CaseAllerPrison extends Case {
	
	/**
	 * Indique le nom de la case
	 */
	public CaseAllerPrison() {
		super("Aller en prison");
	}
	
	/**
	 * Méthode permettant de gérer l'action quand le joueur tombe sur la case AllerPrison
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		if(joueur.getCarteSortiePrison()) {
			es.println(" > " + joueur.getNom() + " utilise sa carte et évite la prison !");
			joueur.setCarteSortiePrison(false);
			plateau.remettreCarteSortiePrisonDansPaquet();
		}
		else {
			joueur.setEstPrison(true);
			joueur.setPosition(10);
			es.println(" > " + joueur.getNom() + " est envoyé en prison!");
		}
	}

	@Override
	/**
	 * ToString
	 */
	public String toString() {
		return "va en prison!";
	}

	@Override
	/**
	 * Méthode permettant de reprendre la partie
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}
	
}
