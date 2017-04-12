package jeumonopoly;

import jeudeplateau.Case;
import jeudeplateau.D�s;
import jeudeplateau.Joueur;

public class Partie {

	private D�s des = new D�s();
	private PlateauMonopoly plateauM;
	
	public Partie(int nombreDeJoueurs) {
		plateauM = new PlateauMonopoly(nombreDeJoueurs);
	}
	
	public void demarrerLaPartie() {
		System.out.println("La partie d�marre!");
		
		Joueur joueur;
		int lanc�;
		Case caze;
		
		while(!plateauM.finPartie() && plateauM.getNbTours() <= 10) {
			joueur = plateauM.getJoueurActif();
			System.out.println("\nC'est au tour de " + joueur.getNom() + " (" + joueur.getArgent() + ")");
			
			if(!joueur.getEstBanqueroute()) {				
				lanc� = des.lancerDes();
				System.out.println("" + joueur.getNom() + " lance les d�s et obtient un " + lanc�);
				
				plateauM.deplacerJoueur(joueur, lanc�);
				
				caze = plateauM.getCase(joueur.getPosition());
				caze.actionCase(joueur, plateauM);
				System.out.println("" + joueur.getNom() + " avance de " + lanc� + " cases et atterit sur la case " + joueur.getPosition() + " : " + caze.getNom());
			}
			else
				System.out.println("" + plateauM.getJoueurActif().getNom() + " est banqueroute, il ne joue pas.");
			
			plateauM.setJoueurSuivant();
		}
		System.out.println("Fin de la partie");
	}

	@Override
	public String toString() {
		return "Partie [des=" + des.getDes() + ", plateauM=" + plateauM.toString() + "]";
	}
	
}
