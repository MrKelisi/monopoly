package jeudeplateau;

import fenetres.FenetrePrincipale;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

public abstract class Carte {

	private String titre;
	private String description;
	
	
	public Carte(String titre, String description) {
		this.titre = titre;
		this.description = description;
	}
	
	public String getNom() {
		return this.titre;
	}
	public String getDesc() {
		return this.description;
	}
	
	public abstract void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp);
	
}
