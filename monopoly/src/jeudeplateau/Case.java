package jeudeplateau;

import java.util.ArrayList;

import fenetres.FenetrePrincipale;
import javafx.scene.paint.Color;
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
	private int nbMaison;
	private JoueurMonopoly proprietaire;
	private boolean reponseQuestion = false;
	private Polygon r = new Polygon();
	
	
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
		int apayer;
		if(proprietaire.getListeCouleur().contains(this.getCouleur()))
			apayer = loyer.get(0) * 2;			// loyer double si le joueur possède tous les terrains d'une couleur
		else
			apayer = loyer.get(getNbMaison());
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
			for(Case c: proprietaire.getTerrain())
				if(c.getCouleur() == this.getCouleur() && c != this)
					couleur.add(c);
			
			this.peutMettreMaison = true;
			for(Case c:couleur) {
				if(!(this.getNbMaison() == c.getNbMaison() || this.getNbMaison() == c.getNbMaison() - 1))
					this.peutMettreMaison = false;
			}
		}
		else
			this.peutMettreMaison = false;
		System.out.println(peutMettreMaison);
		return this.peutMettreMaison;
	}
	
	public void setPeutMettreMaison(boolean peutMettreMaison){
		this.peutMettreMaison = peutMettreMaison;
	}
	
	public void ajouterMaison(){
		if(proprietaire.getArgent() < this.getPrixMaison())
			System.out.println("Pas assez d'argent pour acheter une maison !");
		else{
			this.setNbMaison(getNbMaison() + 1);
			proprietaire.retirerArgent(this.getPrixMaison());
			
			System.out.println("Félicitations, vous avez posé une maison");
		}
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
	
	public Polygon getPolygon(){
		return this.r;
	}
	public void setPolygon(Polygon r){
		this.r = r;
	}

	/* PARTIE ABSTRAITE */ 
	public abstract void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp);
	public abstract void fenetreAction(FenetrePrincipale fp);
	
}
