package cases;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Carte;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action d'une case communauté
*@author WEBERT MORVRANGE
*/

public class CaseCommunaute extends Case {

	/**
	 * Indique le nom de la case
	 */
	public CaseCommunaute() {
		super("Communauté");
	}

	@Override
	/**
	 * Non utilisée
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
	}

	@Override
	/**
	 * Permet de tirer et afficher une carte communauté
	 * @see Carte
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		es.println(" > Le joueur tire une carte communauté...");
		
		Carte c = fp.getPartie().getPM().tirerCarteCommunauté();
		es.println(" [ "+c.getDesc() + " ]");
		c.actionCarte(fp.getPartie().getPM().getJoueurActif(), fp.getPartie().getPM(), fp);
		
		if(fp.getPartie().PARTIE_AUTO)
			fp.getPartie().reprendrePartie();
		else
			fp.afficherFenetreCarte(false, c.getNom(), c.getDesc());
	}
	
}
