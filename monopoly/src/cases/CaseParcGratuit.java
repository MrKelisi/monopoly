package cases;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

public class CaseParcGratuit extends Case {
	
	public CaseParcGratuit() {
		super("Parc Gratuit");
		this.setPrix(0);
	}

	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		es.println(" > " + joueur.getNom() + " ramasse " + this.getPrix() + "€ du parc gratuit !");
		joueur.ajouterArgent(this.getPrix());
		this.setPrix(0);
	}

	@Override
	public String toString() {
		return "est sur la case parc gratuit";
	}

	@Override
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}
	
}
