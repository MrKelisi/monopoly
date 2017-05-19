package cases;

import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

import java.util.Random;

import fenetres.FenetrePrincipale;
import io.Console;

/**
 * Cr�e l'action de la case Prison
*@author WEBERT MORVRANGE
*/
public class CasePrison extends Case {
	
	/**
	 * Indique le nom de la case
	 */
	public CasePrison() {
		super("Simple Visite");
	}
	
	/**
	 * M�thode g�rant tous les cas d'un joueur en prison : <br>
	 * <ul>
	 * <li>Si un joueur est rest� 3 tours en prison, il doit payer 50�</li>
	 * <li>Si un joueur fait un double au lanc� de d�s, il peut sortir</li>
	 * <li>Si un joueur poss�de une carte Sortie de Prison et qu'il l'utilise, il se lib�re</li>
	 * </ul>
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		int lanc� = plateau.des.getDes();
		int d1 = plateau.des.getDe1();
		int d2 = plateau.des.getDe2();
		
		if(joueur.getEstPrison() == true){
			
			es.println("Voulez vous payer 50� pour sortir de prison ? ");
			
			if(getReponseQuestion()){
				es.println("OUI : " + joueur.getNom() + " d�cide de payer 50� pour sortir de prison.");
				joueur.retirerArgent(50);
				setReponseQuestion(false);
				joueur.setEstPrison(false);
				joueur.setToursEnPrison(1);
				plateau.deplacerJoueur(joueur, lanc�);
				es.println("" + joueur.getNom() + " lance les d�s... [" + d1 + "][" + d2 + "]... et obtient un " + lanc� + " !");
				es.println("" + joueur.getNom() + " avance de " + lanc� + " cases et atterit sur " + plateau.getCaseActive().getNom());
			}
			else{
				if(joueur.getToursEnPrison() > 2) {
					es.println("NON : " + joueur.getNom() + " est a son 3e tour en prison, il sort et paye 50�.");
					joueur.retirerArgent(50);
					joueur.setEstPrison(false);
					joueur.setToursEnPrison(1);
					plateau.deplacerJoueur(joueur, lanc�);
					es.println("" + joueur.getNom() + " lance les d�s... [" + d1 + "][" + d2 + "]... et obtient un " + lanc� + " !");
					es.println("" + joueur.getNom() + " avance de " + lanc� + " cases et atterit sur " + plateau.getCaseActive().getNom());
				}
				else{
					es.println("NON : " + joueur.getNom() + " (tour " + joueur.getToursEnPrison() + ") d�cide de ne pas payer et lance ses d�s...");
					if(d1 == d2){
						es.println("  [" + d1 + "][" + d2 + "] Gagn�! " + joueur.getNom() + " sort de prison sans payer!");
						joueur.setEstPrison(false);
						joueur.setToursEnPrison(1);
						plateau.deplacerJoueur(joueur, lanc�);
					}
					else{
						es.println("  [" + d1 + "][" + d2 + "] Perdu!");
						joueur.setToursEnPrison(joueur.getToursEnPrison() + 1);
					}
				}
			}
		}
		else{
			es.println(" > Le joueur observe les criminels");
		}
		
	}
	
	@Override
	/**
	 * ToString
	 */
	public String toString() {
		return "est sur la case Prison";
	}

	@Override
	/**
	 * Reprend le cours de la partie
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		
		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				setReponseQuestion(true);
			fp.getPartie().reprendrePartie();
		}
		else if(fp.getPartie().getPM().getJoueurActif().getEstPrison())
			fp.afficherFenetrePrison();
		else
			fp.getPartie().reprendrePartie();
	}
	
}