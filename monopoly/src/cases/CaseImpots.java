package cases;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Cr�e l'action d'une case imp�t
*@author WEBERT MORVRANGE
*/
public class CaseImpots extends Case {

	/**
	 * Indique le nom ainsi que le prix � payer d'une case Imp�t
	 * @param nom String
	 * @param prix int
	 */
	public CaseImpots(String nom, int valeur) {
		super(nom, valeur);
	}
	
	/**
	 * M�thode retirant de l'argent � un joueur et l'ajoutant au Parc Gratuit
	 * @see CaseParcGratuit
	 * @see jeudeplateau.Joueur
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console();
		
		es.println(" > " + joueur.getNom() + " d�pose " + this.getPrix() + "� au Parc Gratuit.");
		fp.afficherMessage(joueur.getNom() + " d�pose " + this.getPrix() + "� au Parc Gratuit.");
		
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

	@Override
	public JoueurMonopoly getProprietaire() {
		return null;
	}

	@Override
	public String getCouleur() {
		return null;
	}

	@Override
	public int getLoyer() {
		return 0;
	}

	@Override
	public int getPrixMaison() {
		return 0;
	}

	@Override
	public int getNbMaison() {
		return 0;
	}

	@Override
	public boolean getReponseQuestion() {
		return false;
	}

	@Override
	public boolean getPeutMettreMaison() {
		return false;
	}

	@Override
	public void setProprietaire(JoueurMonopoly j) {}

	@Override
	public void setReponseQuestion(boolean b) {}
	
}
