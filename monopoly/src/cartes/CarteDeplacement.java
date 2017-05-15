package cartes;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Carte;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

public class CarteDeplacement extends Carte {
	
	private int position;
	private boolean deplacementRelatif;
	
	public CarteDeplacement(String titre, String description, int pos, boolean deplacementRelatif) {
		super(titre, description);
		this.position = pos;
		this.deplacementRelatif = deplacementRelatif;
	}

	@Override
	public void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		if(deplacementRelatif) //Pour les cartes "Reculez/avancez et X cases"
			plateau.deplacerJoueur(joueur, position);
		else {
			if(getNom().equals("Prison")) {
				if(joueur.getCarteSortiePrison()) {
					es.println(" > " + joueur.getNom() + " utilise sa carte et évite la prison !");
					joueur.setCarteSortiePrison(false);
					plateau.remettreCarteSortiePrisonDansPaquet();
				}
				else {
					joueur.setEstPrison(true);
					plateau.deplacerJoueur(joueur, position-joueur.getPosition());
				}
			}
			else if(position-joueur.getPosition()<0)
				plateau.deplacerJoueur(joueur, position-joueur.getPosition()+40);
			else
				plateau.deplacerJoueur(joueur, position-joueur.getPosition());
		}
		
		if(getNom().equals("Prison"))
			es.println(" > "+joueur.getNom()+" se retrouve en prison.");
		else
			es.println(" > "+joueur.getNom()+" atterit sur "+plateau.getCaseActive().getNom());
	}
}
