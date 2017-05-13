package jeumonopoly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import jeudeplateau.Case;
import jeudeplateau.Joueur;
import jeumonopoly.CarteChance;

public class PlateauMonopoly extends jeudeplateau.Plateau {

	private ArrayList<JoueurMonopoly> joueurs = new ArrayList<JoueurMonopoly>();
	private ArrayList<CarteChance> chance = new ArrayList<CarteChance>();
	private ArrayList<CarteCommunaute> communauté = new ArrayList<CarteCommunaute>();
	
	public PlateauMonopoly(int nombreDeJoueurs) {
		super(nombreDeJoueurs, 40);
		
		
		/* INITIALISATION DES JOUEURS */
		for(int i = 0; i < this.getNbJoueurs(); i++) {
			this.joueurs.add(new JoueurMonopoly("Joueur"+(i+1), i));
		}
		
		/* INITIALISATION DES CASES*/
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
		
		/* INITIALISATION DES TERRAINS */
		setCase(1, new CaseTerrain("Boulevard de Belleville", 60, new ArrayList<Integer>(Arrays.asList(2, 10, 30, 90, 160, 250)), 50, 0, "brun"));
		setCase(3, new CaseTerrain("Rue Lecourbe", 60, new ArrayList<Integer>(Arrays.asList(4, 20, 60, 180, 320, 450)), 50, 0, "brun"));
		
		setCase(6, new CaseTerrain("Rue de Vaugirard", 100, new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise"));
		setCase(8, new CaseTerrain("Rue de Courcelles", 100, new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise"));
		setCase(9, new CaseTerrain("Avenue de la République", 120, new ArrayList<Integer>(Arrays.asList(8, 40, 100, 300, 450, 600)), 50, 0, "turquoise"));
		
		setCase(11, new CaseTerrain("Boulevard la Villette", 140, new ArrayList<Integer>(Arrays.asList(10, 50, 150, 450, 625, 750)), 100, 0, "mauve"));
		setCase(13, new CaseTerrain("Avenue de Neuilly", 140, new ArrayList<Integer>(Arrays.asList(10, 50, 150, 450, 625, 750)), 100, 0, "mauve"));
		setCase(14, new CaseTerrain("Rue du Paradis", 160, new ArrayList<Integer>(Arrays.asList(12, 60, 180, 500, 700, 900)), 100, 0, "mauve"));
		
		setCase(16, new CaseTerrain("Avenue Mozart", 180, new ArrayList<Integer>(Arrays.asList(14, 70, 200, 550, 750, 950)), 100, 0, "orange"));
		setCase(18, new CaseTerrain("Boulevard Saint-Victorien", 180, new ArrayList<Integer>(Arrays.asList(14, 70, 200, 550, 750, 950)), 100, 0, "orange"));
		setCase(19, new CaseTerrain("Place Pigalle", 200, new ArrayList<Integer>(Arrays.asList(16, 80, 220, 600, 800, 1000)), 100, 0, "orange"));
		
		setCase(21, new CaseTerrain("Avenue Matignon", 220, new ArrayList<Integer>(Arrays.asList(18, 90, 250, 700, 875, 1050)), 150, 0, "rouge"));
		setCase(23, new CaseTerrain("Boulevard Malesherbes", 220, new ArrayList<Integer>(Arrays.asList(18, 90, 250, 700, 875, 1050)), 150, 0, "rouge"));
		setCase(24, new CaseTerrain("Avenue Henri-Martin", 240, new ArrayList<Integer>(Arrays.asList(20, 100, 300, 750, 925, 1100)), 150, 0, "rouge"));
		
		setCase(26, new CaseTerrain("Faubourg Saint-Honoré", 260, new ArrayList<Integer>(Arrays.asList(22, 110, 330, 800, 975, 1150)), 150, 0, "jaune"));
		setCase(27, new CaseTerrain("Place de la Bourse", 260, new ArrayList<Integer>(Arrays.asList(22, 110, 330, 800, 975, 1150)), 150, 0, "jaune"));
		setCase(29, new CaseTerrain("Rue La Fayette", 280, new ArrayList<Integer>(Arrays.asList(24, 120, 360, 850, 1025, 1200)), 150, 0, "jaune"));
		
		setCase(31, new CaseTerrain("Avenue de Breuteuil", 300, new ArrayList<Integer>(Arrays.asList(26, 130, 390, 900, 1100, 1275)), 200, 0, "vert"));
		setCase(32, new CaseTerrain("Avenue Foch", 300, new ArrayList<Integer>(Arrays.asList(26, 130, 390, 900, 1100, 1275)), 200, 0, "vert"));
		setCase(34, new CaseTerrain("Boulevard des Capucines", 320, new ArrayList<Integer>(Arrays.asList(28, 150, 450, 1000, 1200, 1400)), 200, 0, "vert"));
		
		setCase(37, new CaseTerrain("Avenue des Champs-Élysées", 350, new ArrayList<Integer>(Arrays.asList(35, 175, 500, 1100, 1300, 1500)), 200, 0, "bleu"));
		setCase(39, new CaseTerrain("Rue de la Paix", 400, new ArrayList<Integer>(Arrays.asList(50, 200, 600, 1400, 1700, 2000)), 200, 0, "bleu"));
		
		/* INITIALISATION DES CARTES */
		chance.add(new CarteChance("Amende", "Amende pour excès de vitesse, payez 10€"));
		chance.add(new CarteChance("Impot", "Réparation de la voirie : payez 40€/maison et 115€/hôtel"));
		chance.add(new CarteChance("Frais de scolarité", "Payez 150€ de frais de scolarité"));
		chance.add(new CarteChance("Prison", "Allez en prison"));
		chance.add(new CarteChance("Reparation", "Payez 25€/maison et 100€/hôtel pour des réparations"));
		chance.add(new CarteChance("Amende2", "Amende pour ivresse, payez 20€"));
		chance.add(new CarteChance("Versement", "La banque vous verse un dividende de 50€"));
		chance.add(new CarteChance("CaseDep", "Avancez jusqu'à la case départ et recevez 200€"));
		chance.add(new CarteChance("Gain", "Vos terrains vous rapportent 150€"));
		chance.add(new CarteChance("Gagne", "Vous avez gagné le prix de mots croisés ! Recevez 100€"));
		chance.add(new CarteChance("Paix", "Rendez-vous Rue de la Paix"));
		chance.add(new CarteChance("HM", "Rendez-vous à l'Avenue Henri-Martin. Si vous passez par la case départ, recevez 200€"));
		chance.add(new CarteChance("Vilette", "Avancez au Boulevard de la Vilette. Si vous passez par la case départ, recevez 200€"));
		chance.add(new CarteChance("Lyon", "Avancez à la gare de Lyon. Si vous passez par la case départ, recevez 200€"));
		chance.add(new CarteChance("Recul", "Reculez de 3 cases"));
		chance.add(new CarteChance("Sortie", "Carte pour sortir de prison (à conserver)"));
		Collections.shuffle(chance);
		
	}
	
	public void deplacerJoueur(JoueurMonopoly joueur, int nombreDeCases) {
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

	public JoueurMonopoly getJoueurActif() {
		return this.joueurs.get(getJoueurActifID());
	}
	public JoueurMonopoly getJoueur(int i) {
		return this.joueurs.get(i);
	}
	
	public Case getCaseActive() {
		return getCase(getJoueurActif().getPosition());
	}
	
	@Override
	public boolean finPartie() {
		int nbJoueursEnJeu = 0;
		for(JoueurMonopoly j:joueurs) {
			if(!j.getEstBanqueroute()) nbJoueursEnJeu++;
		}
		return (nbJoueursEnJeu <= 1);
	}

	@Override
	public Joueur estVainqueur() {
		int res = 0;
		for(int i=0; i<joueurs.size(); i++) {
			if(getJoueur(i).getArgent() > getJoueur(res).getArgent())
				res = i;
		}
		return getJoueur(res);
	}

	@Override
	public String toString() {
		return "PlateauMonopoly [toString()=" + super.getCase(1) + "]";
	}
	
}
