package jeumonopoly;

import java.util.Random;
import fenetres.FenetrePrincipale;
import io.Console;
import jeudeplateau.Case;

public class CaseGare extends Case {

	public CaseGare(String nom) {
		super(nom);
		this.setPrix(200);
	}

	@Override
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console(fp);
		
		if(this.getProprietaire() == null) {
			if(getAcheterTerrain()) {
				setProprietaire(joueur, fp);
				joueur.retirerArgent(this.getPrix());
				es.println(" > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
			}
			else {
				es.println(" > " + joueur.getNom() + " décide de ne pas acheter cette gare.");
			}
		}
		else if(this.getProprietaire() != joueur) {
			int loyer = 50 * this.getProprietaire().getNbGares();
			String beneficiaire = "la Banque";
			
			if(!this.getProprietaire().getEstPrison()) {
				joueur.retirerArgent(loyer);
				if(!this.getProprietaire().getEstBanqueroute()) {
					this.getProprietaire().ajouterArgent(loyer);
					beneficiaire = this.getProprietaire().getNom();
				}
				es.println(" > " + joueur.getNom() + " paye un loyer de " + loyer + "€ à " + beneficiaire);
			}
			else {
				es.println(" > Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
			}
		}
		else {
			es.println(" > " + joueur.getNom() + " est dans sa propre gare.");
		}
	}
	
	public void setProprietaire(JoueurMonopoly joueur, FenetrePrincipale fp) {
		this.setProprietaire(joueur);
		fp.setMarqueurProprietaire(joueur);
		joueur.ajouterTerrain(this);
		joueur.setNbGares(joueur.getNbGares() + 1);
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
