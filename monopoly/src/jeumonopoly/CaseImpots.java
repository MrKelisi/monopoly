package jeumonopoly;

import fenetres.FenetrePrincipale;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseImpots extends Case {

	public CaseImpots(String nom, int prix) {
		super(nom);
		this.setPrix(prix);
	}
	
	public void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		System.out.println(" > " + joueur.getNom() + " dépose " + this.getPrix() + "€ au parc gratuit.");
		
		joueur.retirerArgent(this.getPrix());
		
		int nouveauMontantParcGratuit = plateau.getCase(20).getPrix() + this.getPrix();
		plateau.getCase(20).setPrix(nouveauMontantParcGratuit);
	}
	
}
