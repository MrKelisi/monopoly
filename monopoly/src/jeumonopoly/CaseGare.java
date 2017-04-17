package jeumonopoly;

import fenetres.FenetrePrincipale;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseGare extends Case {

	public CaseGare(String nom) {
		super(nom);
		this.setPrix(200);
	}

	@Override
	public void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		if(this.getProprietaire() == null) {
			this.setProprietaire(joueur);
			joueur.retirerArgent(this.getPrix());
			joueur.ajouterTerrain(this);
			joueur.setNbGares(joueur.getNbGares() + 1);
			System.out.println(" > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
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
				System.out.println(" > " + joueur.getNom() + " paye un loyer de " + loyer + "€ à " + beneficiaire);
			}
			else
				System.out.println(" > Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
		}
		else
			System.out.println(" > " + joueur.getNom() + " est dans sa propre gare.");
	}

}
