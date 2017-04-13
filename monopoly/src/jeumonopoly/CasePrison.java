package jeumonopoly;

import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CasePrison extends Case {
	
	public CasePrison() {
		super("Prison");
	}
	
	public void actionCase(Joueur joueur, PlateauMonopoly plateau) {
		
	}

	@Override
	public String toString() {
		return "est sur la case Prison";
	}
	
}
