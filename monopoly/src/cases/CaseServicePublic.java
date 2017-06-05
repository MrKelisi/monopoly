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
		
		Console es = new Console();
		
		if(this.getProprietaire() == null) {
			if(getReponseQuestion()) {
				if(acheterTerrain(joueur, fp))
					fp.setMarqueurProprietaire(joueur, this);
			}
			else {
				es.println(" > " + joueur.getNom() + " d�cide de ne pas acheter cette compagnie.");
				fp.afficherMessage(joueur.getNom() + " d�cide de ne pas acheter cette compagnie.");
			}
		}
		
		else if(this.getProprietaire() != joueur)
			payerLoyer(joueur, fp);
			
		else {
			es.println(" > " + joueur.getNom() + " poss�de la compagnie.");
			if(fp!=null) fp.afficherMessage("Le propri�taire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
		}
	}
	
	
	public boolean acheterTerrain(JoueurMonopoly joueur, FenetrePrincipale fp) {
		if((joueur.getArgent() - this.getPrix()) <= 0) {
			System.out.println("Vous n'avez pas assez d'argent!");
			return false;
		}
		else {
			setProprietaire(joueur);
			joueur.ajouterTerrain(this);
			joueur.retirerArgent(this.getPrix());
			joueur.setNbServices(joueur.getNbServices() + 1);
			
			System.out.println(" > " + joueur.getNom() + " ach�te " + this.getNom() + " pour " + this.getPrix() + "�");
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " ach�te " + this.getNom() + " pour " + this.getPrix() + "�");
			return true;
		}
	}
	
	public void payerLoyer(JoueurMonopoly joueur, FenetrePrincipale fp) {
		String beneficiaire = "la Banque";
		
		if(!this.getProprietaire().getEstPrison()) {
			
			int loyer = fp.getPartie().getPM().des.lancerDes();
			if(fp!=null) {
				fp.effacerDes();
				fp.afficherDes(fp.getPartie().getPM());
			}
			
			if(this.getProprietaire().getNbServices() == 2) loyer*=10;
			else loyer*=4;
			
			System.out.println(" > " + joueur.getNom() + " lance les d�s... [" + fp.getPartie().getPM().des.getDe1() + "][" + fp.getPartie().getPM().des.getDe2() + "]... et obtient un " + fp.getPartie().getPM().des.getDes());
			joueur.retirerArgent(loyer);
			
			if(!this.getProprietaire().getEstBanqueroute()) {
				this.getProprietaire().ajouterArgent(loyer);
				beneficiaire = this.getProprietaire().getNom();
			}
			System.out.println(" > " + joueur.getNom() + " paye un loyer de " + loyer + "� � " + beneficiaire);
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " paye un loyer de " + loyer + "� � " + beneficiaire);
		}
		else {
			System.out.println(" > Le propri�taire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
			if(fp!=null) fp.afficherMessage("Le propri�taire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
		}
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

	
	/* ===========================
	   M�thodes abstraites de Case 
	   =========================== */
	
	@Override
	public JoueurMonopoly getProprietaire() { 
		return proprietaire; 
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
	public void setProprietaire(JoueurMonopoly j) {	
		this.proprietaire = j; 
	}

	@Override
	public void setReponseQuestion(boolean b) {	
		this.reponseQuestion = b; 
	}
	
	@Override
	public String toString() {
		return "CaseServicePublic [" + super.toString() + ", proprietaire=" + (proprietaire==null?"null":proprietaire.getNom()) + "]";
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("TEST DE LA CLASSE : CaseServicePublic");
	}

}
