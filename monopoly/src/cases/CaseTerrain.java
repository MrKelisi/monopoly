package cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
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

	private JoueurMonopoly proprietaire;
	private ArrayList<Integer> loyer = new ArrayList<Integer>();
	private String couleur;
	private int prixMaison;
	private int nbMaison = 0;
	private boolean peutMettreMaison = false;
	private boolean reponseQuestion = false;
	
	/**
	 * Indique le nom, le prix du terrain, la liste de ses loyers, le prix d'une maison, le nombre de maison, et la couleur que poss�de un terrain
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
	
	@SuppressWarnings({ "unused", "static-access" })
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console();
		
		if(this.getProprietaire() == null) {
			if(getReponseQuestion()) {
				if(acheterTerrain(joueur, fp))
					fp.setMarqueurProprietaire(joueur, this);
			}
			else {
				es.println(" > " + joueur.getNom() + " d�cide de ne pas acheter ce terrain.");
				fp.afficherMessage(joueur.getNom() + " d�cide de ne pas acheter ce terrain.");
			}
		}
		
		else if(this.getProprietaire() != joueur)
			payerLoyer(joueur, fp);
		
		else {
			es.println(" > " + joueur.getNom() + " est sur son propre terrain");
			fp.afficherMessage(joueur.getNom() + " est sur son propre terrain");
			
			if(this.getPeutMettreMaison() && fp.getPartie().PARTIE_AUTO) {
				this.ajouterMaison(fp);
				fp.setMaison(this);
				es.println(" > " + joueur.getNom() + " poss�de d�sormais " + getNbMaison() + " maison" + (getNbMaison()>0?"s":"") + " sur ce terrain.");
			}
		}
	}
	
	
	public boolean acheterTerrain(JoueurMonopoly joueur, FenetrePrincipale fp) {
		if((joueur.getArgent() - this.getPrix()) <= 0) {
			System.out.println("Vous n'avez pas assez d'argent!");
			return false;
		}
		else {
			setProprietaire(joueur);
			joueur.ajouterTerrain(this);
			joueur.retirerArgent(this.getPrix());
			System.out.println(" > " + joueur.getNom() + " ach�te " + this.getNom() + " pour " + this.getPrix() + "�");
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " ach�te " + this.getNom() + " pour " + this.getPrix() + "�");
			return true;
		}
	}
	
	public void payerLoyer(JoueurMonopoly joueur, FenetrePrincipale fp) {
		String beneficiaire = "la Banque";
		
		if(!this.getProprietaire().getEstPrison()) {
			joueur.retirerArgent(getLoyer());
			if(!this.getProprietaire().getEstBanqueroute()) {
				this.getProprietaire().ajouterArgent(getLoyer());
				beneficiaire = this.getProprietaire().getNom();
			}
			System.out.println(" > " + joueur.getNom() + " paye un loyer de " + getLoyer() + "� � " + beneficiaire);
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " paye un loyer de " + getLoyer() + "� � " + beneficiaire);
		}
		else {
			System.out.println(" > Le propri�taire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
			if(fp!=null) fp.afficherMessage("Le propri�taire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
		}
	}
	
	/**
	 * Permet l'ajout d'une maison sur un terrain
	 */
	public void ajouterMaison(FenetrePrincipale fp){
		
		nbMaison++;
		proprietaire.retirerArgent(this.getPrixMaison());
		
		System.out.println(" > " + proprietaire.getNom() + " a pos� une maison sur "+getNom()+" !");
		if(fp!=null) fp.afficherMessage(" > " + proprietaire.getNom() + " a pos� une maison sur "+getNom()+" !");
	}
	
	/**
	 * M�thode permettant l'affichage d'une fen�tre pour l'achat d'un terrain, et reprenant le cours de la partie
	 */
	@SuppressWarnings("static-access")
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
	
	
	/* ===========================
	   M�thodes abstraites de Case 
	   =========================== */
	
	public boolean getPeutMettreMaison() {
		if(proprietaire.getListeCouleur().contains(this.getCouleur())){

			ArrayList<Case> couleur = new ArrayList<Case>();
			for(Case c: proprietaire.getListeTerrains())
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
	public int getLoyer() {
		int aPayer = loyer.get(getNbMaison());
		if(proprietaire.getListeCouleur().contains(this.getCouleur()) && getNbMaison() == 0)
			aPayer*=2; // loyer double si le joueur poss�de tous les terrains d'une couleur mais sans maison.
		
		return aPayer;
	}

	@Override
	public String getCouleur() {
		return couleur;
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
	}

	@Override
	public void setReponseQuestion(boolean b) {
		this.reponseQuestion = b;
	}
	
	@Override
	public String toString() {
		return "CaseTerrain ["+ super.toString() +", proprietaire=" + (proprietaire==null?"null":proprietaire.getNom()) + ", couleur=" + couleur + ", loyer=" + loyer
				+ ", prixMaison=" + prixMaison + ", peutMettreMaison=" + peutMettreMaison + ", nbMaison=" + nbMaison + "]";
	}

	public static void main(String[] args) {
		
		Console es = new Console();
		
		es.println("TEST DE LA CLASSE : CaseTerrain\n");
		
		CaseTerrain c = new CaseTerrain("Avenue de la R�publique", 120, new ArrayList<Integer>(Arrays.asList(8, 40, 100, 300, 450, 600)), 50, 0, "turquoise");
		JoueurMonopoly j1 = new JoueurMonopoly("Yann", 0, 1000);
		JoueurMonopoly j2 = new JoueurMonopoly("Benoit", 1, 1000);
		
		es.println(c.toString() + "\n");
		es.println(j1.toString() + "\n");
		es.println(j2.toString() + "\n");
		
		c.acheterTerrain(j1, null);
		
		es.println("== Propri�taire de " + c.getNom() + " : "+ c.getProprietaire().getNom());
		es.println("== Nombre de maisons : "+ c.getNbMaison() + "");
		
		c.payerLoyer(j2, null);
		
		es.println("");
		c.ajouterMaison(null);
		c.ajouterMaison(null);
		c.ajouterMaison(null);
		es.println("== Nombre de maisons : "+ c.getNbMaison() + "");
		
		c.payerLoyer(j2, null);
		
		es.println("\n" + c.toString() + "\n");
		es.println(j1.toString() + "\n");
		es.println(j2.toString());
	}
	
}
