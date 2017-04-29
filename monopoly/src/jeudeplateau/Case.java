package jeudeplateau;

import fenetres.FenetrePrincipale;
import javafx.scene.paint.Color;
import jeumonopoly.PlateauMonopoly;

public abstract class Case {

	private String nom;
	private String couleur;
	private int prix = 0;
	private int loyer;
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
		return loyer;
	}
	public void setLoyer(int loyer){
		this.loyer = loyer;
	}
	
	public Joueur getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	
	public abstract void actionCase(Joueur joueur, PlateauMonopoly plateau, FenetrePrincipale fp);
	
}
