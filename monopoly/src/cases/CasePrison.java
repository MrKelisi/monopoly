package cases;

import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

import java.util.Random;

import fenetres.FenetrePrincipale;
import io.Console;

public class CasePrison extends Case {
	
	public CasePrison() {
		super("Simple Visite");
	}
	
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		int lancé = plateau.des.getDes();
		int d1 = plateau.des.getDe1();
		int d2 = plateau.des.getDe2();
		
		if(joueur.getEstPrison() == true){
			
			es.println("Voulez vous payer 50€ pour sortir de prison ? ");
			
			if(getReponseQuestion()){
				es.println("OUI : " + joueur.getNom() + " décide de payer 50€ pour sortir de prison.");
				joueur.retirerArgent(50);
				setReponseQuestion(false);
				joueur.setEstPrison(false);
				joueur.setToursEnPrison(1);
				plateau.deplacerJoueur(joueur, lancé);
				es.println("" + joueur.getNom() + " lance les dés... [" + d1 + "][" + d2 + "]... et obtient un " + lancé + " !");
				es.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur " + plateau.getCaseActive().getNom());
			}
			else{
				if(joueur.getToursEnPrison() > 2) {
					es.println("NON : " + joueur.getNom() + " est a son 3e tour en prison, il sort et paye 50€.");
					joueur.retirerArgent(50);
					joueur.setEstPrison(false);
					joueur.setToursEnPrison(1);
					plateau.deplacerJoueur(joueur, lancé);
					es.println("" + joueur.getNom() + " lance les dés... [" + d1 + "][" + d2 + "]... et obtient un " + lancé + " !");
					es.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur " + plateau.getCaseActive().getNom());
				}
				else{
					es.println("NON : " + joueur.getNom() + " (tour " + joueur.getToursEnPrison() + ") décide de ne pas payer et lance ses dés...");
					if(d1 == d2){
						es.println("  [" + d1 + "][" + d2 + "] Gagné! " + joueur.getNom() + " sort de prison sans payer!");
						joueur.setEstPrison(false);
						joueur.setToursEnPrison(1);
						plateau.deplacerJoueur(joueur, lancé);
					}
					else{
						es.println("  [" + d1 + "][" + d2 + "] Perdu!");
						joueur.setToursEnPrison(joueur.getToursEnPrison() + 1);
					}
				}
			}
		}
		else{
			es.println(" > Le joueur observe les criminels");
		}
		
	}
	
	@Override
	public String toString() {
		return "est sur la case Prison";
	}

	@Override
	public void fenetreAction(FenetrePrincipale fp) {
		
		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				setReponseQuestion(true);
			fp.getPartie().reprendrePartie();
		}
		else if(fp.getPartie().getPM().getJoueurActif().getEstPrison())
			fp.afficherFenetrePrison();
		else
			fp.getPartie().reprendrePartie();
	}
	
}