package jeumonopoly;

import java.util.ArrayList;
import java.util.Arrays;

import fenetres.FenetrePrincipale;
import jeudeplateau.Case;
import jeudeplateau.Joueur;
import jeumonopoly.CarteChance;

public class CaseChance extends Case {

	
	
	public CaseChance() {
		super("Chance");
	}

	@Override
	public void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		chance = new ArrayList<CarteChance>(Arrays.asList(
				new CarteChance("Amende", "Amende pour exc�s de vitesse, payez 10�"), 
				new CarteChance("Impot", "R�paration de la voirie : payez 40�/maison et 115�/h�tel"),
				new CarteChance("Frais de scolarit�", "Payez 150� de frais de scolarit�"),
				new CarteChance("Prison", "Allez en prison"),
				new CarteChance("Reparation", "Payez 25�/maison et 100�/h�tel pour des r�parations"),
				new CarteChance("Amende2", "Amende pour ivresse, payez 20�"),
				new CarteChance("Versement", "La banque vous verse un dividende de 50�"),
				new CarteChance("CaseDep", "Avancez jusqu'� la case d�part et recevez 200�"),
				new CarteChance("Gain", "Vos terrains vous rapportent 150�"),
				new CarteChance("Gagne", "Vous avez gagn� le prix de mots crois�s ! Recevez 100�"),
				new CarteChance("Paix", "Rendez-vous Rue de la Paix"),
				new CarteChance("HM", "Rendez-vous � l'Avenue Henri-Martin. Si vous passez par la case d�part, recevez 200�"),
				new CarteChance("Vilette", "Avancez au Boulevard de la Vilette. Si vous passez par la case d�part, recevez 200�"),
				new CarteChance("Lyon", "Avancez � la gare de Lyon. Si vous passez par la case d�part, recevez 200�"),
				new CarteChance("Recul", "Reculez de 3 cases"),
				new CarteChance("Sortie", "Carte pour sortir de prison (� conserver)")));
		System.out.println(" > " + joueur.getNom() + " tire une carte chance...");
		
		
		//fp.tirerCarte(true, titre, description);
	}

}
