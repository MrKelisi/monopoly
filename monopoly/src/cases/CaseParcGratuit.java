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
		super("Parc Gratuit", 0);
	}

	/**
	 * Méthode permettant à un joueur de récupérer l'argent de la case Parc Gratuit, réinitialisant ensuite celle-ci à 0
	 * @see jeudeplateau.Joueur
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console();
		
		es.println(" > " + joueur.getNom() + " ramasse " + this.getPrix() + "€ du Parc Gratuit !");
		fp.afficherMessage(joueur.getNom() + " ramasse " + this.getPrix() + "€ du Parc Gratuit !");
		joueur.ajouterArgent(this.getPrix());
		this.setPrix(0);
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
	
	@Override
	public String toString() {
		return "est sur la case parc gratuit , argent disponible :" + this.getPrix() + " €";
	}
	
}
