package cases;

import java.util.ArrayList;
import java.util.Random;

import exceptions.notEnoughMoneyException;
import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Cr�e l'action de la case terrain
*@author WEBERT MORVRANGE
*/

public class CaseTerrain extends Case {
	
	/**
	 * Indique le nom, le prix du terrain, la liste de ses loyers, le prix d'une maison, le nombre de maison, et la couleur que poss�de un terrain
	 * @param nom String
	 * @param montant int
	 * @param loyer ArrayList
	 * @param prixMaison int
	 * @param nbMaison int
	 * @param couleur String
	 */
	public CaseTerrain(String nom, int montant, ArrayList<Integer> loyer, int prixMaison, int nbMaison, String couleur) {
		super(nom);
		this.setPrix(montant);
		this.setCouleur(couleur);
		this.setLoyer(loyer);
		this.setPrixMaison(prixMaison);
		this.setNbMaison(nbMaison);
	}
	
	/**
	 * M�thode permettant de v�rifier un terrain : <br>
	 * <ul>
	 * <li>Si personne ne poss�de le terrain, un joueur peut l'acheter</li>
	 * <li>Si un joueur tombe sur un terrain appartenant � un autre joueur, il paye un loyer au joueur qui le poss�de</li>
	 * <li>si un joueur tombe sur un de ses terrains il ne se passe rien, mais peut acheter des maisons</li>
	 * </ul>
	 * @throws notEnoughMoneyException 
	 * @see jeudeplateau.Joueur
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) throws notEnoughMoneyException {
		
		Console es = new Console(fp);
		
		if(this.getProprietaire() == null) {
			if(getReponseQuestion()) {
				if((joueur.getArgent() - this.getPrix()) <= 0)
					throw new notEnoughMoneyException(" n'a pas assez d'argent pour acheter !");
				else{
					setProprietaire(joueur, fp);
					joueur.retirerArgent(this.getPrix());
					es.println(" > " + joueur.getNom() + " ach�te " + this.getNom() + " pour " + this.getPrix() + "�");
				}
			}
			else {
				es.println(" > " + joueur.getNom() + " d�cide de ne pas acheter ce terrain.");
			}
		}
		else if(this.getProprietaire() != joueur) {

			getPeutMettreMaison();
			String beneficiaire = "la Banque";
			
			if(!this.getProprietaire().getEstPrison()) {
				joueur.retirerArgent(getLoyer());
				if(!this.getProprietaire().getEstBanqueroute()) {
					this.getProprietaire().ajouterArgent(getLoyer());
					beneficiaire = this.getProprietaire().getNom();
				}
				es.println(" > " + joueur.getNom() + " paye un loyer de " + getLoyer() + "� � " + beneficiaire);
			}
			else {
				es.println(" > Le propri�taire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
			}
		}
		else {

			es.println(" > " + joueur.getNom() + " est sur son propre terrain");
			
			if(this.getPeutMettreMaison() && fp.getPartie().PARTIE_AUTO){
				this.ajouterMaison();
				fp.setMaison(this);
				es.println(" > " + joueur.getNom() + " poss�de d�sormais " + getNbMaison() + " maison(s) sur ce terrain.");
			}
		}
	}
	
	/**
	 * M�thode rendant le joueur propri�taire d'un terrain
	 * @param joueur String
	 * @param fp FenetrePrincipale
	 */
	public void setProprietaire(JoueurMonopoly joueur, FenetrePrincipale fp) {
		this.setProprietaire(joueur);
		fp.setMarqueurProprietaire(joueur, this);
		joueur.ajouterTerrain(this);
	}
	
	@Override
	/**
	 * M�thode permettant l'affichage d'une fen�tre pour l'achat d'un terrain, et reprenant le cours de la partie
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		
		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				setReponseQuestion(true);
			fp.getPartie().reprendrePartie();
		}
		else if(this.getProprietaire() == null)
			fp.afficherFenetreAchatTerrain();
		else
			fp.getPartie().reprendrePartie();
	}

	@Override
	public String toString() {
		return "est sur la case " + this.getNom();
	}
	
}
