package cases;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Carte;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

public class CaseCommunaute extends Case {

	public CaseCommunaute() {
		super("Communauté");
	}

	@Override
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
	}

	@Override
	public void fenetreAction(FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		es.println(" > Le joueur tire une carte communauté...");
		
		Carte c = fp.getPartie().getPM().tirerCarteCommunauté();
		es.println(" [ "+c.getDesc() + " ]");
		c.actionCarte(fp.getPartie().getPM().getJoueurActif(), fp.getPartie().getPM(), fp);
		
		if(fp.getPartie().PARTIE_AUTO)
			fp.getPartie().reprendrePartie();
		else
			fp.afficherFenetreCarte(false, c.getNom(), c.getDesc());
	}
	
}
