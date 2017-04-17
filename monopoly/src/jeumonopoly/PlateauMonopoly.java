package jeumonopoly;

import java.util.Random;

import jeudeplateau.Joueur;

public class PlateauMonopoly extends jeudeplateau.Plateau {
	
	//private String[] terrains = new String[] { "Rue ", "Avenue ", "Boulevard ", "Place " };
	//private String[] nomTerrains = new String[] { "de Belleville", "Lecourbe",  "Vaugirard", "de Courcelles", "de la R�publique", "de la Vilette", "de Neuilly", "du Paradis", "Mozart", "Saint-Michel", "Pigalle" };
	
	public PlateauMonopoly(int nombreDeJoueurs) {
		super(nombreDeJoueurs, 40);
		
		for(int i = 0; i < this.getNbJoueurs(); i++) {
			this.setJoueur(i, new Joueur("Joueur" + (i + 1)));
		}
		
		Random rand = new Random();
		
		for(int i = 0; i < this.getNbCases(); i++) {
			if(i == 0)
				setCase(i, new CaseDepart());
			else if(i == 10)
				setCase(i, new CasePrison());
			else if(i == 20)
				setCase(i, new CaseParcGratuit());
			else if(i == 30)
				setCase(i, new CaseAllerPrison());
			else if(i == 5)
				setCase(i, new CaseGare("Gare Montparnasse"));
			else if(i == 15)
				setCase(i, new CaseGare("Gare de Lyon"));
			else if(i == 25)
				setCase(i, new CaseGare("Gare du Nord"));
			else if(i == 35)
				setCase(i, new CaseGare("Gare Saint-Lazare"));
			else if(i == 4)
				setCase(i, new CaseImpots("Impots sur le revenu", 200));
			else if(i == 38)
				setCase(i, new CaseImpots("Taxe de Luxe", 100));
			else if(i == 12)
				setCase(i, new CaseServicePublic("Compagnie d'�l�ctricit�"));
			else if(i == 28)
				setCase(i, new CaseServicePublic("Compagnie d'eau"));
			else if(i == 7 || i == 22 || i == 36)
				setCase(i, new CaseChance());
			else if(i == 2 || i == 17 || i == 33)
				setCase(i, new CaseCommunaute());
			else
				setCase(i, new CaseTerrain("Terrain"+i, 100+rand.nextInt(20)*10));
				//setCase(i, new CaseTerrain(""+terrains[rand.nextInt(terrains.length)]+nomTerrains[rand.nextInt(nomTerrains.length)], 100+rand.nextInt(20)*10));
		}
	}
	
	public void deplacerJoueur(Joueur joueur, int nombreDeCases) {
		int pos;
		
		if((joueur.getPosition() + nombreDeCases) >= getNbCases()) {
			pos = (joueur.getPosition() + nombreDeCases) % getNbCases();
			System.out.println(" > " + joueur.getNom() + " passe par la case d�part et gagn� 200� !");
			joueur.ajouterArgent(200);
		}
		else
			pos = joueur.getPosition() + nombreDeCases;
		
		if(!joueur.getEstBanqueroute()) {
			joueur.setPosition(pos);
		}
	}

	@Override
	public String toString() {
		return "PlateauMonopoly [toString()=" + super.getCase(1) + "]";
	}
	
}
