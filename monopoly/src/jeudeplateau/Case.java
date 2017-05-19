package jeudeplateau;

import java.util.ArrayList;

import cases.CaseAllerPrison;
import cases.CaseChance;
import cases.CaseCommunaute;
import cases.CaseDepart;
import cases.CaseGare;
import cases.CaseImpots;
import cases.CaseParcGratuit;
import cases.CasePrison;
import cases.CaseServicePublic;
import cases.CaseTerrain;
import exceptions.notEnoughMoneyException;
import fenetres.FenetrePrincipale;
import javafx.scene.shape.Polygon;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Définit les cases du Monopoly
*@author WEBERT MORVRANGE
*/

public abstract class Case {

	private String nom;
	private String couleur;
	private int prix = 0;
	private int id = 0;
	private ArrayList<Integer> loyer = new ArrayList<Integer>();
	private int prixMaison;
	private boolean peutMettreMaison = false;
	private int nbMaison = 0;
	private boolean reponseQuestion = false;
	private Polygon marqueur = new Polygon();
	
	/**
	 * @see JoueurMonopoly
	 */
	private JoueurMonopoly proprietaire;
	
	/**
	 * Définit le nom de la case
	 * @param nom String
	 */
	public Case(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Renvoie le nom de la case
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Définit l'identifiant de la case
	 * @return id
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Met à jour l'identifiant de la case
	 * @param id int
	 */
	public void setId(int id){
		this.id = id;
	}
	/* PARTIE COULEUR */
	
	/**
	 * Renvoie la couleur de la case
	 * @return couleur
	 */
	public String getCouleur(){
		return couleur;
	}
	
	/**
	 * Définit la couleur
	 * @param couleur String
	 */
	public void setCouleur(String couleur){
		this.couleur = couleur;
	}
	
	/* PARTIE SUR L ARGENT */
	
	/**
	 * Renvoie le prix de la case
	 * @return prix
	 */
	public int getPrix() {
		return prix;
	}
	
	/**
	 * Définit le prix de la case
	 * @param prix int
	 */
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	/**
	 * Renvoie le loyer du terrain en fonction du nombre de maisons posées sur ledit terrain
	 * @return apayer
	 */
	public int getLoyer(){
		int apayer = loyer.get(getNbMaison());
		if(proprietaire.getListeCouleur().contains(this.getCouleur()) && getNbMaison() == 0)
			apayer*=2;			// loyer double si le joueur possède tous les terrains d'une couleur, sans maison.
		
		return apayer;
	}
	
	/**
	 * Définit une liste de loyer
	 * @param loyer int
	 */
	public void setLoyer(ArrayList<Integer> loyer){
		this.loyer = loyer;
	}
	
	/* PARTIE MAISON */
	
	/**
	 * Renvoie le prix d'une maison
	 * @return prixMaison
	 */
	public int getPrixMaison(){
		return prixMaison;
	}
	
	/**
	 * Définit le prix d'une maison
	 * @param prixMaison int
	 */
	public void setPrixMaison(int prixMaison){
		this.prixMaison = prixMaison;
	}
	
	/**
	 * Renvoie le nombre de maisons posées sur un terrain
	 * @return nbMaison
	 */
	public int getNbMaison(){
		return nbMaison;
	}
	
	/**
	 * Met à jour le nombre de maisons posées sur un terrain
	 * @param nbMaison int
	 */
	public void setNbMaison(int nbMaison){
		this.nbMaison = nbMaison;
	}
	
	/**
	 * Permet de savoir si un joueur peut poser une maison ou non, il peut si : <br>
	 * <ul>
	 * <li> Il possède tous les terrains d'une même couleur;</li>
	 * <li>Le nombre de maisons sur chaque terrain doit être identique pour en rajouter (cad 1 maison sur chaque terrain pour pouvoir en poser une deuxième);</li>
	 * </ul>
	 * @return boolean
	 * @see JoueurMonopoly
	 */
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
	
	/**
	 * Met à jour si le joueur peut poser une maison ou non
	 * @param peutMettreMaison boolean
	 */
	public void setPeutMettreMaison(boolean peutMettreMaison){
		this.peutMettreMaison = peutMettreMaison;
	}
	
	/**
	 * Permet l'ajout d'une maison sur un terrain
	 */
	public void ajouterMaison(){
		
		setNbMaison(getNbMaison() + 1);
		proprietaire.retirerArgent(this.getPrixMaison());
		
		System.out.println("Félicitations, vous avez posé une maison !");
	}
	
	/* PARTIE JOUEUR */
	
	/**
	 * Renvoie le joueur propriétaire d'une case
	 * @return proprietaire
	 */
	public JoueurMonopoly getProprietaire() {
		return proprietaire;
	}
	
	/**
	 * Définit le propriétaire d'une case
	 * @param proprietaire JoueurMonopoly
	 */
	public void setProprietaire(JoueurMonopoly proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	/* PARTIE TERRAIN */

	/**
	 * Renvoie la réponse à une question (Pour l'achat d'un terrain par exemple)
	 * @return reponseQuestion
	 */
	public boolean getReponseQuestion() {
		return reponseQuestion;
	}
	
	/**
	 * Définit la réponse à une question
	 * @param reponse boolean
	 */
	public void setReponseQuestion(boolean reponse) {
		this.reponseQuestion = reponse;
	}
	
	/* PARTIE GRAPHIQUE (déso pas déso) */
	
	/**
	 * Renvoie le marqueur de possession d'une case d'un joueur
	 * @return marqueur
	 */
	public Polygon getMarqueur(){
		return this.marqueur;
	}
	
	/**
	 * Définit un marqueur de possession d'une case
	 * @param r Polygon
	 */
	public void setMarqueur(Polygon r){
		this.marqueur = r;
	}

	/* PARTIE ABSTRAITE */ 
	/**
	 * Action de la case lorsqu'un joueur tombe dessus
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp FenetrePrincipale
	 * @throws notEnoughMoneyException 
	 * @see CaseDepart
	 * @see CaseCommunaute
	 * @see CaseImpots
	 * @see CaseGare
	 * @see CaseChance
	 * @see CasePrison
	 * @see CaseServicePublic
	 * @see CaseParcGratuit
	 * @see CaseAllerPrison
	 * @see CaseTerrain
	 */
	public abstract void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) throws notEnoughMoneyException;
	public abstract void fenetreAction(FenetrePrincipale fp);
	
}
