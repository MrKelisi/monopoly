package jeudeplateau;

import java.util.ArrayList;

import fenetres.FenetrePrincipale;
import javafx.scene.paint.Color;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

public abstract class Case {

	private String nom;
	private String couleur;
	private int prix = 0;
	private ArrayList<Integer> loyer = new ArrayList<Integer>();
	private int prixMaison;
	private boolean peutMettreMaison = false;
	private int nbMaison;
	private JoueurMonopoly proprietaire;
	private boolean reponseQuestion = false;

	public Case(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}

	public String getCouleur(){
		return couleur;
	}
	public void setCouleur(String couleur){
		this.couleur = couleur;
	}
	
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public int getLoyer(){
		return loyer.get(getNbMaison());
	}
	public void setLoyer(ArrayList<Integer> loyer){
		this.loyer = loyer;
	}
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
		if(proprietaire.getListeCouleur().contains(this.getCouleur()))
				this.peutMettreMaison = true;
		return this.peutMettreMaison;
	}
	public void setPeutMettreMaison(boolean peutMettreMaison){
		this.peutMettreMaison = peutMettreMaison;
	}
	
	public JoueurMonopoly getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(JoueurMonopoly proprietaire) {
		this.proprietaire = proprietaire;
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

	public boolean getReponseQuestion() {
		return reponseQuestion;
	}
	public void setReponseQuestion(boolean acheterTerrain) {
		this.reponseQuestion = acheterTerrain;
	}

	public abstract void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp);
	
	public abstract void fenetreAction(FenetrePrincipale fp);
	
}
