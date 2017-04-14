package jeumonopoly;

import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class Partie {

	private PlateauMonopoly plateauM;
	
	public Partie(int nombreDeJoueurs) {
		plateauM = new PlateauMonopoly(nombreDeJoueurs);
	}
	
	public void demarrerLaPartie() {
		System.out.println("La partie d�marre!");
		
		Joueur joueur;
		int lanc�;
		Case caze;
		
		while(!plateauM.finPartie() && plateauM.getNbTours() <= 20) {
			
			if(plateauM.getJoueurActifID() == 0) {
				System.out.println("\n==================\n DEBUT DU TOUR " + plateauM.getNbTours() + "\n==================");
			}
			
			joueur = plateauM.getJoueurActif();
			System.out.println("\nC'est au tour de " + joueur.getNom() + " (poss�de " + joueur.getArgent() + "�)");
			
			if(!joueur.getEstBanqueroute()) {		
				
				lanc� = plateauM.des.lancerDes();
				
				if(!joueur.getEstPrison()) {
					System.out.println("" + joueur.getNom() + " lance les d�s... [" + plateauM.des.getDe1() + "][" + plateauM.des.getDe2() + "] ...et obtient un " + lanc� + " !");
					plateauM.deplacerJoueur(joueur, lanc�);
				}
				else
					System.out.println("Le joueur est en prison.");
				
				caze = plateauM.getCase(joueur.getPosition());
				if(!joueur.getEstPrison())
					System.out.println("" + joueur.getNom() + " avance de " + lanc� + " cases et atterit sur la case " + joueur.getPosition() + " : " + caze.getNom());
				
				caze.actionCase(joueur, plateauM);
				System.out.println("" + joueur.getNom() + " poss�de � la fin de son tour " + joueur.getArgent() + "� et les terrains suivants :\n\t" + joueur.listTerrains());
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
