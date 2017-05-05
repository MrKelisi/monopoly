package jeudeplateau;

import java.util.ArrayList;

import fenetres.FenetrePrincipale;
import javafx.scene.paint.Color;
import jeumonopoly.PlateauMonopoly;

public abstract class Case {

	private String nom;
	private String couleur;
	private int prix = 0;
	private ArrayList<Integer> loyer = new ArrayList<Integer>();
	private int prixMaison;
	private boolean peutMettreMaison = false;
	private int nbMaison;
	private Joueur proprietaire;
	
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
		return this.loyer.get(this.getNbMaison());
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
		return this.nbMaison;
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
	
	public Joueur getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Joueur proprietaire) {
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
	
	public abstract void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp);
	
}
