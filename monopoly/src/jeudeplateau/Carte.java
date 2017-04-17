package jeudeplateau;

import fenetres.FenetrePrincipale;
import jeumonopoly.PlateauMonopoly;

public abstract class Carte {

	private String titre;
	private String description;
	
	public Carte(String titre, String description) {
		this.titre = titre;
		this.description = description;
	}
	
	public abstract void actionCarte(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp);
	
}
