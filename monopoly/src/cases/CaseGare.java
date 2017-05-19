package cases;

import java.util.Random;
import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action d'une case gare
*@author WEBERT MORVRANGE
*/

public class CaseGare extends Case {

	/**
	 * Indique le nom et ajoute le prix d'une gare
	 * @param nom String
	 */
	public CaseGare(String nom) {
		super(nom);
		this.setPrix(200);
	}

	@Override
	/**
	 * Méthode gérant l'appropriation d'une gare à un joueur <br />
	 * Gère le changement du loyer en fonction du nombre de gare possédé par un joueur
	 * @see Joueur
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		if(this.getProprietaire() == null) {
			if(getReponseQuestion()) {
				setProprietaire(joueur, fp);
				joueur.retirerArgent(this.getPrix());
				es.println(" > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
			}
			else {
				es.println(" > " + joueur.getNom() + " décide de ne pas acheter cette gare.");
			}
		}
		else if(this.getProprietaire() != joueur) {
			int loyer = 50 * this.getProprietaire().getNbGares();
			String beneficiaire = "la Banque";
			
			if(!this.getProprietaire().getEstPrison()) {
				joueur.retirerArgent(loyer);
				if(!this.getProprietaire().getEstBanqueroute()) {
					this.getProprietaire().ajouterArgent(loyer);
					beneficiaire = this.getProprietaire().getNom();
				}
				es.println(" > " + joueur.getNom() + " paye un loyer de " + loyer + "€ à " + beneficiaire);
			}
			else {
				es.println(" > Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
			}
		}
		else {
			es.println(" > " + joueur.getNom() + " est dans sa propre gare.");
		}
	}
	
	/**
	 * Rend le joueur propriétaire d'une gare
	 * @param joueur JoueurMonopoly
	 * @param fp FenetrePrincipale
	 * @see jeudeplateau.Joueur
	 */
	public void setProprietaire(JoueurMonopoly joueur, FenetrePrincipale fp) {
		this.setProprietaire(joueur);
		fp.setMarqueurProprietaire(joueur, this);
		joueur.ajouterTerrain(this);
		joueur.setNbGares(joueur.getNbGares() + 1);
	}

	@Override
	/**
	 * Affiche une fenêtre d'achat de terrain
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		
		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				setReponseQuestion(true);
			fp.getPartie().reprendrePartie();
		}
		else if(this.getProprietaire() == null)
			fp.afficherFenetreAchatTerrain();
		else
			fp.getPartie().reprendrePartie();
	}

}
