package cases;

import jeudeplateau.Carte;
import cartes.CartePayerArgent;
import cartes.CarteSortirPrison;
import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action d'une case chance
*@author WEBERT MORVRANGE
*/
public class CaseChance extends Case {
	
	/**
	 * Indique le nom de la case
	 */
	public CaseChance() {
		super("Chance", 0);
	}

	@Override
	/**
	 * Non utilisée
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
	}

	@Override
	/**
	 * Permet de tirer et afficher une carte chance
	 * @see Carte
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		es.println(" > Le joueur tire une carte chance...");
		
		Carte c = fp.getPartie().getPM().tirerCarteChance();
		es.println(" [ "+c.getDesc() + " ]");
		c.actionCarte(fp.getPartie().getPM().getJoueurActif(), fp.getPartie().getPM(), fp);
		
		if(fp.getPartie().PARTIE_AUTO)
			fp.getPartie().reprendrePartie();
		else
			fp.afficherFenetreCarteChance(c.getNom(), c.getDesc());
	}
	
	public static void main(String[] args) {
		System.out.println("TEST DE LA CLASSE : CaseChance");
		JoueurMonopoly j = new JoueurMonopoly("Yann", 0, 1000);
		PlateauMonopoly p = new PlateauMonopoly(40);
		CartePayerArgent payer = new CartePayerArgent("Amende", "Amende pour excès de vitesse : 15€.", 15);
		System.out.println(payer.toString());
		j.retirerArgent(payer.getMontant());
		p.getCase(20).setPrix(p.getCase(20).getPrix() + payer.getMontant());
		System.out.println(j.toString()); //Le joueur Yann perd 15€
		System.out.println(p.getCase(20).toString());
		
		CarteSortirPrison prison = new CarteSortirPrison("Sortie", "Vous êtes libéré de prison. \n(Cette carte doit être conservée)");
		System.out.println(prison.toString());
		j.setCarteSortiePrison(true);
		System.out.println(j.toString()); //Le joueur Yann possède la carte de sortie de prison
		
		
	}

	@Override
	public JoueurMonopoly getProprietaire() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCouleur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoyer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPrixMaison() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNbMaison() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getReponseQuestion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getPeutMettreMaison() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setProprietaire(JoueurMonopoly j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setReponseQuestion(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Case Chance !";
	}
	
}
