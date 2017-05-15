package cartes;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Carte;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

public class CartePayerArgent extends Carte {
	
	private int montant;
	
	public CartePayerArgent(String titre, String description, int montant) {
		super(titre, description);
		this.montant = montant;
		
	}

	@Override
	public void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		if(getNom().equals("Président du conseil d'administration")) {
			for(int i=0; i<plateau.getNbJoueurs(); i++) {
				if(plateau.getJoueur(i) != joueur && !plateau.getJoueur(i).getEstBanqueroute()) {
					plateau.getJoueur(i).ajouterArgent(50);
					joueur.retirerArgent(50);
				}
			}
			es.println(" > "+joueur.getNom()+" verse 50€ à chaque joueur.");
		}
		else {
			joueur.retirerArgent(montant);
			es.println(" > "+joueur.getNom()+" paye "+montant+"€ à la Banque");
		}
	}
}
