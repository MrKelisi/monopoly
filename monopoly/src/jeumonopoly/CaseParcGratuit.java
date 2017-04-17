package jeumonopoly;

import fenetres.FenetrePrincipale;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseParcGratuit extends Case {
	
	public CaseParcGratuit() {
		super("Parc Gratuit");
		this.setPrix(0);
	}

	public void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		System.out.println(" > " + joueur.getNom() + " ramasse " + this.getPrix() + "€ du parc gratuit !");
		joueur.ajouterArgent(this.getPrix());
		this.setPrix(0);
	}

	@Override
	public String toString() {
		return "est sur la case parc gratuit";
	}
	
}
