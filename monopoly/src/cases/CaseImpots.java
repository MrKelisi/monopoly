package cases;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action d'une case impôt
*@author WEBERT MORVRANGE
*/
public class CaseImpots extends Case {

	/**
	 * Indique le nom ainsi que le prix à payer d'une case Impôt
	 * @param nom String
	 * @param prix int
	 */
	public CaseImpots(String nom, int prix) {
		super(nom);
		this.setPrix(prix);
	}
	
	/**
	 * Méthode retirant de l'argent à un joueur et l'ajoutant au Parc Gratuit
	 * @see CaseParcGratuit
	 * @see jeudeplateau.Joueur
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		es.println(" > " + joueur.getNom() + " dépose " + this.getPrix() + "€ au parc gratuit.");
		
		joueur.retirerArgent(this.getPrix());
		
		int nouveauMontantParcGratuit = plateau.getCase(20).getPrix() + this.getPrix();
		plateau.getCase(20).setPrix(nouveauMontantParcGratuit);
	}

	@Override
	/**
	 * Reprend le cours de la partie
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}
	
}
