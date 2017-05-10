package jeumonopoly;

import java.util.ArrayList;
import java.util.Arrays;

import fenetres.FenetrePrincipale;
import jeudeplateau.Carte;
import jeudeplateau.Joueur;

public class CarteChance extends Carte {

	ArrayList<CarteChance> chance = new ArrayList<CarteChance>();
	
	public CarteChance(String titre, String description) {
		super(titre, description);
	}

	@Override
	public void actionCarte(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		// TODO Auto-generated method stub
		
		
	}
	
	public void setCarteChance(ArrayList<CarteChance> chance){
		this.chance = chance;
	}
	
	public void tirerCarte(){
		
	}

}
