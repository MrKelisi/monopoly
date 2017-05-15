package jeudeplateau;

import java.util.ArrayList;
import fenetres.FenetrePrincipale;
import javafx.scene.shape.Polygon;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

public abstract class Case {

	private String nom;
	private String couleur;
	private int prix = 0;
	private int id = 0;
	private ArrayList<Integer> loyer = new ArrayList<Integer>();
	private int prixMaison;
	private boolean peutMettreMaison = false;
	private int nbMaison = 0;
	private JoueurMonopoly proprietaire;
	private boolean reponseQuestion = false;
	private Polygon marqueur = new Polygon();
	
	
	public Case(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	/* PARTIE COULEUR */
	
	public String getCouleur(){
		return couleur;
	}
	
	public void setCouleur(String couleur){
		this.couleur = couleur;
	}
	
	/* PARTIE SUR L ARGENT */
	
	public int getPrix() {
		return prix;
	}
	
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public int getLoyer(){
		int apayer = loyer.get(getNbMaison());
		if(proprietaire.getListeCouleur().contains(this.getCouleur()) && getNbMaison() == 0)
			apayer*=2;			// loyer double si le joueur possède tous les terrains d'une couleur, sans maison.
		
		return apayer;
	}
	
	public void setLoyer(ArrayList<Integer> loyer){
		this.loyer = loyer;
	}
	
	/* PARTIE MAISON */
	
	public int getPrixMaison(){
		return prixMaison;
	}
	
	public void setPrixMaison(int prixMaison){
		this.prixMaison = prixMaison;
	}
	
	public int getNbMaison(){
		return nbMaison;
	}
	
	public void setNbMaison(int nbMaison){
		this.nbMaison = nbMaison;
	}
	
	public boolean getPeutMettreMaison(){
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
	public void setPeutMettreMaison(boolean peutMettreMaison){
		this.peutMettreMaison = peutMettreMaison;
	}
	
	public void ajouterMaison(){
		
		setNbMaison(getNbMaison() + 1);
		proprietaire.retirerArgent(this.getPrixMaison());
		
		System.out.println("Félicitations, vous avez posé une maison !");
	}
	
	/* PARTIE JOUEUR */
	
	public JoueurMonopoly getProprietaire() {
		return proprietaire;
	}
	
	public void setProprietaire(JoueurMonopoly proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	/* PARTIE TERRAIN */

	public boolean getReponseQuestion() {
		return reponseQuestion;
	}
	
	public void setReponseQuestion(boolean acheterTerrain) {
		this.reponseQuestion = acheterTerrain;
	}
	
	/* PARTIE GRAPHIQUE (déso pas déso) */
	
	public Polygon getMarqueur(){
		return this.marqueur;
	}
	public void setMarqueur(Polygon r){
		this.marqueur = r;
	}

	/* PARTIE ABSTRAITE */ 
	public abstract void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp);
	public abstract void fenetreAction(FenetrePrincipale fp);
	
}
