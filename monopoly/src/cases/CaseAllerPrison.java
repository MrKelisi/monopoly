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
	
	private boolean reponseQuestion = false;
	
	/**
	 * Indique le nom de la case
	 */
	public CaseAllerPrison() {
		super("Aller en prison", 0);
	}
	
	/**
	 * Méthode permettant de gérer l'action quand le joueur tombe sur la case AllerPrison
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console();
		
		if(joueur.getCarteSortiePrison()) {
			es.println(" > " + joueur.getNom() + " utilise sa carte et évite la prison !");
			fp.afficherMessage(joueur.getNom() + " utilise sa carte et évite la prison !");
			joueur.setCarteSortiePrison(false);
			plateau.remettreCarteSortiePrisonDansPaquet();
		}
		else {
			joueur.setEstPrison(true);
			joueur.setPosition(10);
			es.println(" > " + joueur.getNom() + " est envoyé en prison!");
			fp.afficherMessage(joueur.getNom() + " est envoyé en prison!");
		}
	}

	@Override
	/**
	 * Méthode permettant de reprendre la partie
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}

	
	/* ===========================
	   Méthodes abstraites de Case 
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
		return reponseQuestion;
	}

	@Override
	public boolean getPeutMettreMaison() {
		return false;
	}

	@Override
	public void setProprietaire(JoueurMonopoly j) {}

	@Override
	public void setReponseQuestion(boolean b) {
		this.reponseQuestion = b;
	}
	
	@Override
	public String toString() {
		return "CaseAllerPrison [" + super.toString() + "]";
	}
	
}
