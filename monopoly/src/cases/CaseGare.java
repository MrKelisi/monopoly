package cases;

import java.util.ArrayList;
import java.util.Random;
import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;
import jeudeplateau.Joueur;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action d'une case gare
*@author WEBERT MORVRANGE
*/

public class CaseGare extends Case {

	private JoueurMonopoly proprietaire;
	private boolean reponseQuestion = false;
	
	/**
	 * Indique le nom et ajoute le prix d'une gare
	 * @param nom String
	 */
	public CaseGare(String nom) {
		super(nom, 200);
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
			int loyer = getLoyer();
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
		proprietaire = joueur;
		joueur.ajouterTerrain(this);
		joueur.setNbGares(joueur.getNbGares() + 1);
		fp.setMarqueurProprietaire(joueur, this);
	}

	@Override
	/**
	 * Affiche une fenêtre d'achat de terrain
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		
		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				reponseQuestion = true;
			fp.getPartie().reprendrePartie();
		}
		else if(this.getProprietaire() == null)
			fp.afficherFenetreAchatTerrain();
		else
			fp.getPartie().reprendrePartie();
	}

	@Override
	public JoueurMonopoly getProprietaire() {
		// TODO Auto-generated method stub
		return proprietaire;
	}

	@Override
	public String getCouleur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoyer() {
		// TODO Auto-generated method stub
		return proprietaire != null ? 50 * this.getProprietaire().getNbGares() : 200;
	}

	@Override
	public int getPrixMaison() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNbMaison() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getReponseQuestion() {
		// TODO Auto-generated method stub
		return reponseQuestion;
	}

	@Override
	public boolean getPeutMettreMaison() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setProprietaire(JoueurMonopoly j) {
		// TODO Auto-generated method stub
		this.proprietaire = j;
	}

	@Override
	public void setReponseQuestion(boolean b) {
		// TODO Auto-generated method stub
		this.reponseQuestion = b;
	}

}
