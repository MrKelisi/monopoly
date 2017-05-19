package cartes;

import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Carte;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Type de {@link Carte} pour les payements des joueurs.<br><br>
 * &nbsp; <b>Liste des champs :</b>
 * <ul><li><b>montant</b> : int - montant � retirer au joueur.</li></ul>
 * @see Carte
 */
public class CartePayerArgent extends Carte {
	
	private int montant;
	
	/**
	 * Unique constructeur de la clase {@link CartePayerArgent}.
	 * @param titre String
	 * @param description String
	 * @param montant int
	 */
	public CartePayerArgent(String titre, String description, int montant) {
		super(titre, description);
		this.montant = montant;
	}

	/**
	 * M�thode r�alisant l'action de la carte. 
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp FenetrePrincipale
	 */
	@Override
	public void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		if(getNom().equals("Pr�sident du conseil d'administration")) {
			for(int i=0; i<plateau.getNbJoueurs(); i++) {
				if(plateau.getJoueur(i) != joueur && !plateau.getJoueur(i).getEstBanqueroute()) {
					plateau.getJoueur(i).ajouterArgent(50);
					joueur.retirerArgent(50);
				}
			}
			es.println(" > "+joueur.getNom()+" verse 50� � chaque joueur.");
		}
		else {
			joueur.retirerArgent(montant);
			es.println(" > "+joueur.getNom()+" paye "+montant+"� � la Banque");
		}
	}
}
