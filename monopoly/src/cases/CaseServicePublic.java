package cases;

import java.util.Random;
import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Cr�e l'action de la case Service Public
*@author WEBERT MORVRANGE
*/

public class CaseServicePublic extends Case {

	private JoueurMonopoly proprietaire;
	private boolean reponseQuestion = false;
	
	/**
	 * Indique le nom de la case et attribut un prix
	 * @param nom String
	 */
	public CaseServicePublic(String nom) {
		super(nom, 150);
	}

	@Override
	/**
	 * M�thode g�rant l'appropriation d'un service public � un joueur <br />
	 * G�re le changement du loyer en fonction du nombre de service public poss�d� par un joueur
	 * @see Joueur
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		if(this.getProprietaire() == null) {
			if(getReponseQuestion()) {
				setProprietaire(joueur, fp);
				joueur.retirerArgent(this.getPrix());
				es.println(" > " + joueur.getNom() + " ach�te " + this.getNom() + " pour " + this.getPrix() + "�");
			}
			else {
				es.println(" > " + joueur.getNom() + " d�cide de ne pas acheter cette compagnie.");
			}
		}
		else if(this.getProprietaire() != joueur) {
			String beneficiaire = "la Banque";
			
			if(!this.getProprietaire().getEstPrison()) {
				
				int loyer = plateau.des.lancerDes();
				if(this.getProprietaire().getNbServices() == 2) loyer*=10;
				else loyer*=4;
				es.println(" > " + joueur.getNom() + " lance les d�s... [" + plateau.des.getDe1() + "][" + plateau.des.getDe2() + "]... et obtient un " + plateau.des.getDes());
				
				joueur.retirerArgent(loyer);
				if(!this.getProprietaire().getEstBanqueroute()) {
					this.getProprietaire().ajouterArgent(loyer);
					beneficiaire = this.getProprietaire().getNom();
				}
				es.println(" > " + joueur.getNom() + " paye un loyer de " + loyer + "� � " + beneficiaire);
			}
			else {
				es.println(" > Le propri�taire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
			}
		}
		else
			es.println(" > " + joueur.getNom() + " poss�de la compagnie.");
	}
	
	public void setProprietaire(JoueurMonopoly joueur, FenetrePrincipale fp) {
		proprietaire = joueur;
		joueur.ajouterTerrain(this);
		joueur.setNbServices(joueur.getNbServices() + 1);
		fp.setMarqueurProprietaire(joueur, this);
	}

	@Override
	/**
	 * Affiche une fen�tre pour l'achat de la case et reprend le cours de la partie
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
		return 0;
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
	
	public static void main(String[] args) {
		
		System.out.println("coucou2");
	}

}
