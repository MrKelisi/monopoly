package jeumonopoly;

import java.util.Random;
import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;

public class CaseServicePublic extends Case {

	public CaseServicePublic(String nom) {
		super(nom);
		this.setPrix(150);
	}

	@Override
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		if(this.getProprietaire() == null) {
			if(getAcheterTerrain()) {
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
		this.setProprietaire(joueur);
		fp.setMarqueurProprietaire(joueur);
		joueur.ajouterTerrain(this);
		joueur.setNbServices(joueur.getNbServices() + 1);
	}

	@Override
	public void fenetreAction(FenetrePrincipale fp) {
		
		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				setAcheterTerrain(true);
			fp.getPartie().reprendrePartie();
		}
		else if(this.getProprietaire() == null)
			fp.afficherFenetreAchatTerrain();
		else
			fp.getPartie().reprendrePartie();
	}

}
