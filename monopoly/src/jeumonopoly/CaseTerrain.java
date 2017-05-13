package jeumonopoly;

import java.util.ArrayList;
import java.util.Random;
import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;

public class CaseTerrain extends Case {
	
	public CaseTerrain(String nom, int montant, ArrayList<Integer> loyer, int prixMaison, int nbMaison, String couleur) {
		super(nom);
		this.setPrix(montant);
		this.setCouleur(couleur);
		this.setLoyer(loyer);
		this.setPrixMaison(prixMaison);
		this.setNbMaison(nbMaison);
	}
	
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		if(this.getProprietaire() == null) {
			if(getAcheterTerrain()) {
				setProprietaire(joueur, fp);
				joueur.retirerArgent(this.getPrix());
				es.println(" > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
			}
			else {
				es.println(" > " + joueur.getNom() + " décide de ne pas acheter ce terrain.");
			}
		}
		else if(this.getProprietaire() != joueur) {
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
		else {
			es.println(" > " + joueur.getNom() + " est sur son propre terrain");
			
			if(this.getPeutMettreMaison()){
				this.ajouterMaison(); 
				es.println(" > " + joueur.getNom() + " pose une maison et possede desormais " + getNbMaison() + " maison sur ce terrain.");
			}
			else {
				es.println(" > " + joueur.getNom() + " ne peut pas acheter de maison");
			}
		}
	}
	
	public void setProprietaire(JoueurMonopoly joueur, FenetrePrincipale fp) {
		this.setProprietaire(joueur);
		fp.setMarqueurProprietaire(joueur);
		joueur.ajouterTerrain(this);
	}
	
	@Override
	public String toString() {
		return "est sur la case " + this.getNom();
	}
	
	@Override
	public void fenetreAction(FenetrePrincipale fp) {
		
		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				setAcheterTerrain(true);
			fp.getPartie().reprendrePartie();
		}
		else if(this.getProprietaire() == null)
			fp.afficherFenetreAchatTerrain();
		else
			fp.getPartie().reprendrePartie();
	}
	
}
