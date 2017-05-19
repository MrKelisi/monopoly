package cases;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Carte;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Cr�e l'action d'une case communaut�
*@author WEBERT MORVRANGE
*/

public class CaseCommunaute extends Case {

	/**
	 * Indique le nom de la case
	 */
	public CaseCommunaute() {
		super("Communaut�");
	}

	@Override
	/**
	 * Non utilis�e
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
	}

	@Override
	/**
	 * Permet de tirer et afficher une carte communaut�
	 * @see Carte
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		es.println(" > Le joueur tire une carte communaut�...");
		
		Carte c = fp.getPartie().getPM().tirerCarteCommunaut�();
		es.println(" [ "+c.getDesc() + " ]");
		c.actionCarte(fp.getPartie().getPM().getJoueurActif(), fp.getPartie().getPM(), fp);
		
		if(fp.getPartie().PARTIE_AUTO)
			fp.getPartie().reprendrePartie();
		else
			fp.afficherFenetreCarte(false, c.getNom(), c.getDesc());
	}
	
}
