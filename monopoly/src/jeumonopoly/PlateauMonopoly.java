package jeumonopoly;

import java.util.Random;

import jeudeplateau.Joueur;

public class PlateauMonopoly extends jeudeplateau.Plateau {
	
	private String[] terrains = new String[] { "Rue ", "Avenue ", "Boulevard ", "Place " };
	private String[] nomTerrains = new String[] { "de Belleville", "Lecourbe",  "Vaugirard", "de Courcelles", "de la République", "de la Vilette", "de Neuilly", "du Paradis", "Mozart", "Saint-Michel", "Pigalle" };
	
	public PlateauMonopoly(int nombreDeJoueurs) {
		super(nombreDeJoueurs, 40);
		
		for(int i = 0; i < this.getNbJoueurs(); i++) {
			this.setJoueur(i, new Joueur("Joueur" + (i + 1)));
		}
		
		Random rand = new Random();
		
		for(int i = 0; i < this.getNbCases(); i++) {
			if(i == 0)
				setCase(i, new CaseDepart());
			else if(i == 9)
				setCase(i, new CasePrison());
			else if(i == 19)
				setCase(i, new CaseParcGratuit());
			else if(i == 29)
				setCase(i, new CaseAllerPrison());
			else
				setCase(i, new CaseTerrain(""+terrains[rand.nextInt(terrains.length)]+nomTerrains[rand.nextInt(nomTerrains.length)], 100+rand.nextInt(200)));
		}
	}
	
	public void deplacerJoueur(Joueur joueur, int nombreDeCases) {
		int pos = (joueur.getPosition() + nombreDeCases) % getNbCases();
		if(!joueur.getEstBanqueroute())
			joueur.setPosition(pos);
	}

	@Override
	public String toString() {
		return "PlateauMonopoly [toString()=" + super.getCase(1) + "]";
	}
	
}
