package cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import exceptions.notEnoughMoneyException;
import fenetres.FenetrePrincipale;
import io.Console;
import javafx.scene.shape.Polygon;
import jeudeplateau.Case;
import jeudeplateau.Joueur;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action de la case terrain
*@author WEBERT MORVRANGE
*/
	

public class CaseTerrain extends Case {

	private JoueurMonopoly proprietaire;
	private String couleur;
	private ArrayList<Integer> loyer = new ArrayList<Integer>();
	private int prixMaison;
	private boolean peutMettreMaison = false;
	private int nbMaison = 0;
	private boolean reponseQuestion = false;
	
	/**
	 * Indique le nom, le prix du terrain, la liste de ses loyers, le prix d'une maison, le nombre de maison, et la couleur que possède un terrain
	 * @param nom String
	 * @param montant int
	 * @param loyer ArrayList
	 * @param prixMaison int
	 * @param nbMaison int
	 * @param couleur String
	 */
	public CaseTerrain(String nom, int valeur, ArrayList<Integer> loyer, int prixMaison, int nbMaison, String couleur) {
		super(nom, valeur);
		this.couleur = couleur;
		this.loyer = loyer;
		this.prixMaison = prixMaison;
		this.nbMaison = nbMaison;
	}
	
	/**
	 * Méthode permettant de vérifier un terrain : <br>
	 * <ul>
	 * <li>Si personne ne possède le terrain, un joueur peut l'acheter</li>
	 * <li>Si un joueur tombe sur un terrain appartenant à un autre joueur, il paye un loyer au joueur qui le possède</li>
	 * <li>si un joueur tombe sur un de ses terrains il ne se passe rien, mais peut acheter des maisons</li>
	 * </ul>
	 * @throws notEnoughMoneyException 
	 * @see jeudeplateau.Joueur
	 * @see Case
	 */
	
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		if(this.getProprietaire() == null) {
			if(getReponseQuestion())
				if(acheterTerrain(joueur, es))
					fp.setMarqueurProprietaire(joueur, this);
			else
				es.println(" > " + joueur.getNom() + " décide de ne pas acheter ce terrain.");
		}
		
		else if(this.getProprietaire() != joueur)
			payerLoyer(joueur, es);
		
		else {
			es.println(" > " + joueur.getNom() + " est sur son propre terrain");
			
			if(this.getPeutMettreMaison() && fp.getPartie().PARTIE_AUTO){
				this.ajouterMaison();
				fp.setMaison(this);
				es.println(" > " + joueur.getNom() + " possède désormais " + getNbMaison() + " maison(s) sur ce terrain.");
			}
		}
	}
	
	/**
	 * Méthode rendant le joueur propriétaire d'un terrain
	 * @param joueur String
	 * @param fp FenetrePrincipale
	 */
	
	public boolean acheterTerrain(JoueurMonopoly joueur, Console es) {
		if((joueur.getArgent() - this.getPrix()) <= 0) {
			es.println("Vous n'avez pas assez d'argent!");
			return false;
		}
		else {
			setProprietaire(joueur);
			joueur.retirerArgent(this.getPrix());
			es.println(" > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
			return true;
		}
	}
	
	public void payerLoyer(JoueurMonopoly joueur, Console es) {
		String beneficiaire = "la Banque";
		
		if(!this.getProprietaire().getEstPrison()) {
			joueur.retirerArgent(getLoyer());
			if(!this.getProprietaire().getEstBanqueroute()) {
				this.getProprietaire().ajouterArgent(getLoyer());
				beneficiaire = this.getProprietaire().getNom();
			}
			es.println(" > " + joueur.getNom() + " paye un loyer de " + getLoyer() + "€ à " + beneficiaire);
		}
		else {
			es.println(" > Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
		}
	}
	
	/**
	 * Permet l'ajout d'une maison sur un terrain
	 */
	public void ajouterMaison(){
		
		nbMaison++;
		proprietaire.retirerArgent(this.getPrixMaison());
		
		System.out.println("Félicitations, vous avez posé une maison !");
	}
	
	/**
	 * Méthode permettant l'affichage d'une fenêtre pour l'achat d'un terrain, et reprenant le cours de la partie
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		
		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				reponseQuestion = true;
			fp.getPartie().reprendrePartie();
		}
		else if(this.getProprietaire() == null)
			fp.afficherFenetreAchatTerrain();
		else
			fp.getPartie().reprendrePartie();
	}
	
	public boolean getPeutMettreMaison() {
		if(proprietaire.getListeCouleur().contains(this.getCouleur())){

			ArrayList<Case> couleur = new ArrayList<Case>();
			for(Case c: proprietaire.getTerrains())
				if(c.getCouleur() == this.getCouleur() && c != this)
					couleur.add(c);
			
			this.peutMettreMaison = true;
			for(Case c:couleur) {
				if(!(this.getNbMaison() == c.getNbMaison() || this.getNbMaison() == c.getNbMaison() - 1))
					this.peutMettreMaison = false;
			}
			
			if(proprietaire.getArgent() < this.getPrixMaison()) {
				this.peutMettreMaison = false;
				System.out.println("Vous n'avez pas assez d'argent pour acheter une maison !");
			}
			if(getNbMaison() == 4) {
				this.peutMettreMaison = false;
				System.out.println("Le quota de maisons est atteint !");
			}
		}
		else
			this.peutMettreMaison = false;
		
		return this.peutMettreMaison;
	}

	@Override
	public String getCouleur() {
		return couleur;
	}

	@Override
	public int getLoyer() {
		int apayer = loyer.get(getNbMaison());
		if(proprietaire.getListeCouleur().contains(this.getCouleur()) && getNbMaison() == 0)
			apayer*=2; // loyer double si le joueur possède tous les terrains d'une couleur, sans maison.
		
		return apayer;
	}

	@Override
	public int getPrixMaison() {
		return prixMaison;
	}

	@Override
	public int getNbMaison() {
		return nbMaison;
	}

	@Override
	public JoueurMonopoly getProprietaire() {
		return proprietaire;
	}

	@Override
	public boolean getReponseQuestion() {
		return reponseQuestion;
	}

	@Override
	public void setProprietaire(JoueurMonopoly j) {
		this.proprietaire = j;
		j.ajouterTerrain(this);
	}

	@Override
	public void setReponseQuestion(boolean b) {
		this.reponseQuestion = b;
	}
	
	@Override
	public String toString() {
		return "CaseTerrain ["+ super.toString() +", proprietaire=" + proprietaire + ", couleur=" + couleur + ", loyer=" + loyer
				+ ", prixMaison=" + prixMaison + ", peutMettreMaison=" + peutMettreMaison + ", nbMaison=" + nbMaison + "]";
	}

	public static void main(String[] args) {
		
		Console es = new Console(null);
		
		System.out.println("TEST DE LA CLASSE : CaseTerrain");
		
		CaseTerrain c = new CaseTerrain("Avenue de la République", 120, new ArrayList<Integer>(Arrays.asList(8, 40, 100, 300, 450, 600)), 50, 0, "turquoise");
		System.out.println(c.toString() + "\n");
		
		JoueurMonopoly j1 = new JoueurMonopoly("Yann", 0, 1000);
		JoueurMonopoly j2 = new JoueurMonopoly("Benoit", 1, 1000);
		
		c.acheterTerrain(j1, es);
		
		System.out.println("Propriétaire : "+ c.getProprietaire().getNom() + "\n");
		
		c.ajouterMaison();
		System.out.println("Nombre de maisons : "+ c.getNbMaison());
		c.ajouterMaison();
		System.out.println("Nombre de maisons : "+ c.getNbMaison() + "\n");
		
		c.payerLoyer(j2, es);
		
		System.out.println(j1.toString()+"\n");
		System.out.println(j2.toString());
		
	}
	
}
