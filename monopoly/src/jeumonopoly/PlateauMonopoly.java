package jeumonopoly;

import javafx.scene.paint.Color;

import jeudeplateau.Joueur;

public class PlateauMonopoly extends jeudeplateau.Plateau {
	
	public PlateauMonopoly(int nombreDeJoueurs) {
		super(nombreDeJoueurs, 40);
		
		Color[] Couleurs = new Color[] {Color.RED, Color.BLUE, Color.ORANGE, Color.GREEN};
		
		for(int i = 0; i < this.getNbJoueurs(); i++) {
			this.setJoueur(i, new Joueur("Joueur" + (i + 1)));
			this.getJoueur(i).getPion().setFill(Couleurs[i]);
		}
		
		setCase(0, new CaseDepart());
		setCase(2, new CaseCommunaute());
		setCase(4, new CaseImpots("Impots sur le revenu", 200));
		setCase(5, new CaseGare("Gare Montparnasse"));
		setCase(7, new CaseChance());
		setCase(10, new CasePrison());
		setCase(12, new CaseServicePublic("Compagnie d'électricité"));
		setCase(15, new CaseGare("Gare de Lyon"));
		setCase(17, new CaseCommunaute());
		setCase(20, new CaseParcGratuit());
		setCase(22, new CaseChance());
		setCase(25, new CaseGare("Gare du Nord"));
		setCase(28, new CaseServicePublic("Compagnie des eaux"));
		setCase(30, new CaseAllerPrison());
		setCase(33, new CaseCommunaute());
		setCase(35, new CaseGare("Gare Saint-Lazare"));
		setCase(36, new CaseChance());
		setCase(38, new CaseImpots("Taxe de Luxe", 100));
		
		setCase(1, new CaseTerrain("Boulevard de Belleville", 60, 30, "brun"));
		setCase(3, new CaseTerrain("Rue Lecourbe", 60, 30, "brun"));
		
		setCase(6, new CaseTerrain("Rue de Vaugirard", 100, 50, "turquoise"));
		setCase(8, new CaseTerrain("Rue de Courcelles", 100, 50, "turquoise"));
		setCase(9, new CaseTerrain("Avenue de la République", 120, 60, "turquoise"));
		
		setCase(11, new CaseTerrain("Boulevard la Villette", 140, 70, "mauve"));
		setCase(13, new CaseTerrain("Avenue de Neuilly", 140, 70, "mauve"));
		setCase(14, new CaseTerrain("Rue du Paradis", 160, 80, "mauve"));
		
		setCase(16, new CaseTerrain("Avenue Mozart", 180, 90, "orange"));
		setCase(18, new CaseTerrain("Boulevard Saint-Victorien", 180, 90, "orange"));
		setCase(19, new CaseTerrain("Place Pigalle", 200, 100, "orange"));
		
		setCase(21, new CaseTerrain("Avenue Matignon", 220, 110, "rouge"));
		setCase(23, new CaseTerrain("Boulevard Malesherbes", 220, 110, "rouge"));
		setCase(24, new CaseTerrain("Avenue Henri-Martin", 240, 120, "rouge"));
		
		setCase(26, new CaseTerrain("Faubourg Saint-Honoté", 260, 130, "jaune"));
		setCase(27, new CaseTerrain("Place de la Bourse", 260, 130, "jaune"));
		setCase(29, new CaseTerrain("Rue La Fayette", 280, 140, "jaune"));
		
		setCase(31, new CaseTerrain("Avenue de Breuteuil", 300, 150, "vert"));
		setCase(32, new CaseTerrain("Avenue Foch", 300, 150, "vert"));
		setCase(34, new CaseTerrain("Boulevard des Capucines", 320, 160, "vert"));
		
		setCase(37, new CaseTerrain("Avenue des Champs-Élysées", 350, 175, "bleu"));
		setCase(39, new CaseTerrain("Rue de la Paix", 400, 200, "bleu"));
	}
	
	public void deplacerJoueur(Joueur joueur, int nombreDeCases) {
		int pos;
		
		if((joueur.getPosition() + nombreDeCases) >= getNbCases()) {
			pos = (joueur.getPosition() + nombreDeCases) % getNbCases();
			System.out.println(" > " + joueur.getNom() + " passe par la case départ et gagné 200€ !");
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
