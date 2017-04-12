package jeumonopoly;

import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseParcGratuit extends Case {
	
	public CaseParcGratuit() {
		super("Parc Gratuit");
	}

	public void actionCase(Joueur joueur, PlateauMonopoly plateau) {
		joueur.ajouterArgent(100);
	}

	@Override
	public String toString() {
		return "est sur la case parc gratuit";
	}
	
}
