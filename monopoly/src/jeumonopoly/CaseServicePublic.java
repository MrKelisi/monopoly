package jeumonopoly;

import fenetres.FenetrePrincipale;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseServicePublic extends Case {

	public CaseServicePublic(String nom) {
		// TODO Auto-generated constructor stub
		super(nom);
		this.setPrix(150);
	}

	@Override
	public void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		if(this.getProprietaire() == null) {
			this.setProprietaire(joueur);
			joueur.retirerArgent(this.getPrix());
			joueur.ajouterTerrain(this);
			joueur.setNbServices(joueur.getNbServices() + 1);
			System.out.println(" > " + joueur.getNom() + " achète la " + this.getNom() + " pour " + this.getPrix() + "€");
		}
		else if(this.getProprietaire() != joueur) {
			String beneficiaire = "la Banque";
			
			if(!this.getProprietaire().getEstPrison()) {
				
				int loyer = plateau.des.lancerDes();
				if(this.getProprietaire().getNbServices() == 2) loyer*=10;
				else loyer*=4;
				System.out.println(" > " + joueur.getNom() + " lance les dés... [" + plateau.des.getDe1() + "][" + plateau.des.getDe2() + "]... et obtient un " + plateau.des.getDes());
				
				joueur.retirerArgent(loyer);
				if(!this.getProprietaire().getEstBanqueroute()) {
					this.getProprietaire().ajouterArgent(loyer);
					beneficiaire = this.getProprietaire().getNom();
				}
				System.out.println(" > " + joueur.getNom() + " paye un loyer de " + loyer + "€ à " + beneficiaire);
			}
			else
				System.out.println(" > Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
		}
		else
			System.out.println(" > " + joueur.getNom() + " possède la compagnie.");
	}

}
