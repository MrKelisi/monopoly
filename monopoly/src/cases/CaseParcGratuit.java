package cases;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action de la case Parc Gratuit
*@author WEBERT MORVRANGE
*/
public class CaseParcGratuit extends Case {
	
	/**
	 * Indique le nom de la case et initialise sa valeur
	 */
	public CaseParcGratuit() {
		super("Parc Gratuit");
		this.setPrix(0);
	}

	/**
	 * Méthode permettant à un joueur de récupérer l'argent de la case Parc Gratuit, initialisant celle-ci à 0
	 * @see jeudeplateau.Joueur
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		es.println(" > " + joueur.getNom() + " ramasse " + this.getPrix() + "€ du parc gratuit !");
		joueur.ajouterArgent(this.getPrix());
		this.setPrix(0);
	}

	@Override
	/**
	 * ToString
	 */
	public String toString() {
		return "est sur la case parc gratuit";
	}

	@Override
	/**
	 * Reprend le cours de la partie
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}
	
}
