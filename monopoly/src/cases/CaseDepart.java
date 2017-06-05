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
		super("Depart", 0);
	}
	
	/**
	 * Ajoute l'argent du passage sur la case
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console();
		
		joueur.ajouterArgent(200);
		es.println(" > " + joueur.getNom() + " s'arr�te sur la case d�part: il re�oit 200� suppl�mentaire !");
		fp.afficherMessage(joueur.getNom() + " s'arr�te sur la case d�part et re�oit 400� !");
	}


	@Override
	/**
	 * Reprend la partie
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}

	
	/* ===========================
	   M�thodes abstraites de Case 
	   =========================== */
	
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

	@Override
	public String toString() {
		return "CaseDepart ["+super.toString()+"]";
	}
	
}
