package jeumonopoly;

import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseTerrain extends Case {
	
	public CaseTerrain(String nom, int montant) {
		super(nom);
		this.setPrix(montant);
	}
	
	public void actionCase(Joueur joueur, PlateauMonopoly plateau) {
		if(this.getProprietaire() == null) {
			this.setProprietaire(joueur);
			joueur.retirerArgent(this.getPrix());
			joueur.ajouterTerrain(this);
			System.out.println("  > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
		}
		else if(this.getProprietaire().getNom() != joueur.getNom()) {
			int loyer = 35;
			String payerA = "la Banque";
			
			if(!this.getProprietaire().getEstPrison()) {
				joueur.retirerArgent(loyer);
				if(!this.getProprietaire().getEstBanqueroute()) {
					this.getProprietaire().ajouterArgent(loyer);
					payerA = this.getProprietaire().getNom();
				}
				System.out.println("  > " + joueur.getNom() + " paye un loyer de " + loyer + "€ à " + payerA);
			}
			else
				System.out.println("  > Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
		}
		else
			System.out.println("  > " + joueur.getNom() + " est sur son propre terrain");
	}
	
	@Override
	public String toString() {
		return "est sur la case " + this.getNom();
	}
	
}
