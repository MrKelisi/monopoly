package jeumonopoly;

import java.util.Random;

import fenetres.FenetrePrincipale;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseTerrain extends Case {
	
	public CaseTerrain(String nom, int montant) {
		super(nom);
		this.setPrix(montant);
	}
	
	public void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		if(this.getProprietaire() == null) {
			Random rand = new Random();
			if(rand.nextBoolean()) {
				this.setProprietaire(joueur);
				joueur.retirerArgent(this.getPrix());
				joueur.ajouterTerrain(this);
				System.out.println(" > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
			}
			else
				System.out.println(" > " + joueur.getNom() + " décide de ne pas acheter ce terrain.");
		}
		else if(this.getProprietaire() != joueur) {
			double loyer_d = this.getPrix() * 0.4 / 10;
			Math.ceil(loyer_d);
			int loyer = (int) loyer_d * 10;
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
			System.out.println(" > " + joueur.getNom() + " est sur son propre terrain");
	}
	
	@Override
	public String toString() {
		return "est sur la case " + this.getNom();
	}
	
}
