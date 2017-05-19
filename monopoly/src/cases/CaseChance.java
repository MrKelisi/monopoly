package cases;

import java.util.Random;
import jeudeplateau.Carte;
import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action d'une case chance
*@author WEBERT MORVRANGE
*/
public class CaseChance extends Case {
	
	/**
	 * Indique le nom de la case
	 */
	public CaseChance() {
		super("Chance");
	}

	@Override
	/**
	 * Non utilisée
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
	}

	@Override
	/**
	 * Permet de tirer et afficher une carte chance
	 * @see Carte
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		es.println(" > Le joueur tire une carte chance...");
		
		Carte c = fp.getPartie().getPM().tirerCarteChance();
		es.println(" [ "+c.getDesc() + " ]");
		c.actionCarte(fp.getPartie().getPM().getJoueurActif(), fp.getPartie().getPM(), fp);
		
		if(fp.getPartie().PARTIE_AUTO)
			fp.getPartie().reprendrePartie();
		else
			fp.afficherFenetreCarteChance(c.getNom(), c.getDesc());
	}

}
