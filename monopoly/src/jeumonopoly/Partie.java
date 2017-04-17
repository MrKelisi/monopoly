package jeumonopoly;

import fenetres.FenetrePrincipale;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class Partie {

	private PlateauMonopoly plateauM;
	private FenetrePrincipale fp;
	
	public Partie(int nombreDeJoueurs, FenetrePrincipale fp) {
		this.plateauM = new PlateauMonopoly(nombreDeJoueurs);
		this.fp = fp;
	}
	
	public void demarrerLaPartie() {
		System.out.println("La partie démarre!");
		
		Joueur joueur;
		int lancé;
		Case caze;
		
		while(!plateauM.finPartie() && plateauM.getNbTours() <= 20) {
			
			if(plateauM.getJoueurActifID() == 0) {
				System.out.println("\n==================\n DEBUT DU TOUR " + plateauM.getNbTours() + "\n==================");
			}
			
			joueur = plateauM.getJoueurActif();
			System.out.println("\nC'est au tour de " + joueur.getNom() + " (possède " + joueur.getArgent() + "€)");
			
			if(!joueur.getEstBanqueroute()) {		
				
				lancé = plateauM.des.lancerDes();
				
				if(!joueur.getEstPrison()) {
					System.out.println("" + joueur.getNom() + " lance les dés... [" + plateauM.des.getDe1() + "][" + plateauM.des.getDe2() + "]... et obtient un " + lancé + " !");
					plateauM.deplacerJoueur(joueur, lancé);
					
					caze = plateauM.getCase(joueur.getPosition());
					System.out.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur la case " + joueur.getPosition() + " : " + caze.getNom());
				}
				else {
					System.out.println("Le joueur est en prison.");
					caze = plateauM.getCase(joueur.getPosition());
				}
				
				caze.actionCase(joueur, plateauM, fp);
				System.out.println("" + joueur.getNom() + " possède à la fin de son tour " + joueur.getArgent() + "€ et les terrains suivants :\n\t" + joueur.listTerrains());
			}
			else
				System.out.println("" + plateauM.getJoueurActif().getNom() + " est en banqueroute, il ne joue pas.");
			
			plateauM.setJoueurSuivant();
		}
		System.out.println("\n============================\n Fin de la partie");
		System.out.println(" Le vainqueur est " + plateauM.estVainqueur().getNom() + " !\n============================");
	}

	@Override
	public String toString() {
		return "Partie [plateauM=" + plateauM.toString() + "]";
	}
	
}
