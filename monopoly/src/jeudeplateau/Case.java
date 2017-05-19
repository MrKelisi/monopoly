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
 * D�finit les cases du Monopoly
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
	 * D�finit le nom de la case
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
	 * D�finit l'identifiant de la case
	 * @return id
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Met � jour l'identifiant de la case
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
	 * D�finit la couleur
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
	 * D�finit le prix de la case
	 * @param prix int
	 */
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	/**
	 * Renvoie le loyer du terrain en fonction du nombre de maisons pos�es sur ledit terrain
	 * @return apayer
	 */
	public int getLoyer(){
		int apayer = loyer.get(getNbMaison());
		if(proprietaire.getListeCouleur().contains(this.getCouleur()) && getNbMaison() == 0)
			apayer*=2;			// loyer double si le joueur poss�de tous les terrains d'une couleur, sans maison.
		
		return apayer;
	}
	
	/**
	 * D�finit une liste de loyer
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
	 * D�finit le prix d'une maison
	 * @param prixMaison int
	 */
	public void setPrixMaison(int prixMaison){
		this.prixMaison = prixMaison;
	}
	
	/**
	 * Renvoie le nombre de maisons pos�es sur un terrain
	 * @return nbMaison
	 */
	public int getNbMaison(){
		return nbMaison;
	}
	
	/**
	 * Met � jour le nombre de maisons pos�es sur un terrain
	 * @param nbMaison int
	 */
	public void setNbMaison(int nbMaison){
		this.nbMaison = nbMaison;
	}
	
	/**
	 * Permet de savoir si un joueur peut poser une maison ou non, il peut si : <br>
	 * <ul>
	 * <li> Il poss�de tous les terrains d'une m�me couleur;</li>
	 * <li>Le nombre de maisons sur chaque terrain doit �tre identique pour en rajouter (cad 1 maison sur chaque terrain pour pouvoir en poser une deuxi�me);</li>
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
	 * Met � jour si le joueur peut poser une maison ou non
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
		
		System.out.println("F�licitations, vous avez pos� une maison !");
	}
	
	/* PARTIE JOUEUR */
	
	/**
	 * Renvoie le joueur propri�taire d'une case
	 * @return proprietaire
	 */
	public JoueurMonopoly getProprietaire() {
		return proprietaire;
	}
	
	/**
	 * D�finit le propri�taire d'une case
	 * @param proprietaire JoueurMonopoly
	 */
	public void setProprietaire(JoueurMonopoly proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	/* PARTIE TERRAIN */

	/**
	 * Renvoie la r�ponse � une question (Pour l'achat d'un terrain par exemple)
	 * @return reponseQuestion
	 */
	public boolean getReponseQuestion() {
		return reponseQuestion;
	}
	
	/**
	 * D�finit la r�ponse � une question
	 * @param reponse boolean
	 */
	public void setReponseQuestion(boolean reponse) {
		this.reponseQuestion = reponse;
	}
	
	/* PARTIE GRAPHIQUE (d�so pas d�so) */
	
	/**
	 * Renvoie le marqueur de possession d'une case d'un joueur
	 * @return marqueur
	 */
	public Polygon getMarqueur(){
		return this.marqueur;
	}
	
	/**
	 * D�finit un marqueur de possession d'une case
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
