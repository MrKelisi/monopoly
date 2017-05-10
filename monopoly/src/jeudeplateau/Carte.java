package jeudeplateau;

import java.util.ArrayList;

import fenetres.FenetrePrincipale;
import jeumonopoly.CarteChance;
import jeumonopoly.PlateauMonopoly;

public abstract class Carte {

	private String titre;
	private String description;
	ArrayList<CarteChance> chance = new ArrayList<CarteChance>();
	
	
	public Carte(String titre, String description) {
		this.titre = titre;
		this.description = description;
	}
	
	public abstract void actionCarte(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp);
	
}
