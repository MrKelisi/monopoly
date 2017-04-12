package jeumonopoly;

import jeudeplateau.Case;
import jeudeplateau.Dés;
import jeudeplateau.Joueur;

public class Partie {

	private Dés des = new Dés();
	private PlateauMonopoly plateauM;
	
	public Partie(int nombreDeJoueurs) {
		plateauM = new PlateauMonopoly(nombreDeJoueurs);
	}
	
	public void demarrerLaPartie() {
		System.out.println("La partie démarre!");
		
		Joueur joueur;
		int lancé;
		Case caze;
		
		while(!plateauM.finPartie() && plateauM.getNbTours() <= 10) {
			joueur = plateauM.getJoueurActif();
			System.out.println("\nC'est au tour de " + joueur.getNom() + " (" + joueur.getArgent() + ")");
			
			if(!joueur.getEstBanqueroute()) {				
				lancé = des.lancerDes();
				System.out.println("" + joueur.getNom() + " lance les dés et obtient un " + lancé);
				
				plateauM.deplacerJoueur(joueur, lancé);
				
				caze = plateauM.getCase(joueur.getPosition());
				caze.actionCase(joueur, plateauM);
				System.out.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur la case " + joueur.getPosition() + " : " + caze.getNom());
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
