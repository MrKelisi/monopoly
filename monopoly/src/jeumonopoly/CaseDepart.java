package jeumonopoly;

import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseDepart extends Case {
	
	public CaseDepart() {
		super("Depart");
	}
	
	public void actionCase(Joueur joueur, PlateauMonopoly plateau) {
		joueur.ajouterArgent(1000);
	}

	@Override
	public String toString() {
		return "est sur la case depart : +1000!";
	}
	
}
