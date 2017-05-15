package cartes;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Carte;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

public class CarteRecevoirArgent extends Carte {
	
	private int montant;
	
	public CarteRecevoirArgent(String titre, String description, int montant) {
		super(titre, description);
		this.montant = montant;
		
	}

	@Override
	public void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		if(getNom().equals("Anniversaire")) {
			for(int i=0; i<plateau.getNbJoueurs(); i++) {
				if(plateau.getJoueur(i) != joueur && !plateau.getJoueur(i).getEstBanqueroute()) {
					plateau.getJoueur(i).retirerArgent(10);
					joueur.ajouterArgent(10);
				}
			}
			es.println(" > "+joueur.getNom()+" reçoit 10€ de chaque joueur.");
		}
		else {
			joueur.ajouterArgent(montant);
			es.println(" > "+joueur.getNom()+" reçoit "+montant+"€ de la Banque");
		}
	}
}
