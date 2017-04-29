package jeumonopoly;

import java.util.Random;

import fenetres.FenetrePrincipale;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseTerrain extends Case {
	
	public CaseTerrain(String nom, int montant, int loyer, String couleur) {
		super(nom);
		this.setPrix(montant);
		this.setCouleur(couleur);
		this.setLoyer(loyer);
	}
	
	public void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		if(this.getProprietaire() == null) {
			Random rand = new Random();
			if(rand.nextBoolean()) {
				this.setProprietaire(joueur);
				fp.setMarqueurProprietaire(joueur);
				joueur.retirerArgent(this.getPrix());
				joueur.ajouterTerrain(this);
				System.out.println(" > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
			}
			else
				System.out.println(" > " + joueur.getNom() + " décide de ne pas acheter ce terrain.");
		}
		else if(this.getProprietaire() != joueur) {
			String beneficiaire = "la Banque";
			
			if(!this.getProprietaire().getEstPrison()) {
				joueur.retirerArgent(getLoyer());
				if(!this.getProprietaire().getEstBanqueroute()) {
					this.getProprietaire().ajouterArgent(getLoyer());
					beneficiaire = this.getProprietaire().getNom();
				}
				System.out.println(" > " + joueur.getNom() + " paye un loyer de " + getLoyer() + "€ à " + beneficiaire);
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
