package jeumonopoly;

import fenetres.FenetrePrincipale;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseDepart extends Case {
	
	public CaseDepart() {
		super("Depart");
	}
	
	public void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		joueur.ajouterArgent(200);
		System.out.println(" > " + joueur.getNom() + " s'arrête sur la case départ: il reçoit 200€ supplémentaire !");
	}

	@Override
	public String toString() {
		return "est sur la case depart : +1000!";
	}
	
}
