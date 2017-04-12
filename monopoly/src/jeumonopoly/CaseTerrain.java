package jeumonopoly;

import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class CaseTerrain extends Case {
	
	public CaseTerrain(String nom, int montant) {
		super(nom);
	}
	
	public void actionCase(Joueur joueur, PlateauMonopoly plateau) {
		
	}

	@Override
	public String toString() {
		return "est sur la case " + this.getNom();
	}
	
}
