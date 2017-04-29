package jeumonopoly;

import jeudeplateau.Case;
import jeudeplateau.Joueur;
import java.util.Random;

import fenetres.FenetrePrincipale;

public class CasePrison extends Case {
	
	public CasePrison() {
		super("Prison");
	}
	
	public void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		int lanc� = plateau.des.getDes();
		int d1 = plateau.des.getDe1();
		int d2 = plateau.des.getDe2();
		int tour = joueur.getToursEnPrison();
		
		if(joueur.getEstPrison() == true){
			String rep = sortir();
			if(rep == "o"){
				System.out.println("  OUI\n" + joueur.getNom() + " d�cide de payer 50� pour sortir de prison.");
				joueur.retirerArgent(50);
				joueur.setEstPrison(false);
				joueur.setToursEnPrison(1);
				plateau.deplacerJoueur(joueur, lanc�);
			}
			else{
				if(tour > 2) {
					System.out.println("  NON\n" + joueur.getNom() + " est a son 3e tour en prison, il sort et paye 50�.");
					joueur.retirerArgent(50);
					joueur.setEstPrison(false);
					joueur.setToursEnPrison(1);
					plateau.deplacerJoueur(joueur, lanc�);
				}
				else{
					System.out.println("NON\n" + joueur.getNom() + " (tour " + tour + ") d�cide de ne pas payer et lance ses d�s...");
					if(d1 == d2){
						System.out.println("  [" + d1 + "][" + d2 + "] Gagn�! " + joueur.getNom() + " sort de prison sans payer!");
						joueur.setEstPrison(false);
						joueur.setToursEnPrison(1);
						plateau.deplacerJoueur(joueur, lanc�);
					}
					else{
						System.out.println("  [" + d1 + "][" + d2 + "] Perdu!");
					}
				}
				joueur.setToursEnPrison(tour + 1);
			}
		}
		else{
			System.out.println(" > Le joueur observe les criminels");
		}
	}

	public String sortir(){
		
		System.out.println("Voulez vous payer 50� pour sortir de prison ? ");
		
		Random rand = new Random();
		boolean rep = rand.nextBoolean();
		
		return rep ? "o": "n";
	}
	
	@Override
	public String toString() {
		return "est sur la case Prison";
	}
	
}