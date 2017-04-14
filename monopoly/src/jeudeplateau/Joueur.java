package jeudeplateau;

import java.util.ArrayList;
import jeumonopoly.CaseTerrain;

public class Joueur {

	private String nom;
	private int argent = 1400;
	private int position = 0;
	private boolean estBanqueroute = false;
	private boolean estPrison = false;
	private int toursEnPrison = 1;
	private ArrayList <CaseTerrain> terrains = new ArrayList <CaseTerrain>();
	
	public Joueur(String nom) {
		super();
		this.nom = nom;
	}
	
	public String getNom() {
		return this.nom;
	}

	public int getToursEnPrison() {
		return toursEnPrison;
	}
	public void setToursEnPrison(int toursEnPrison) {
		this.toursEnPrison = toursEnPrison;
	}
	
	public int getPosition() {
		return this.position;
	}
	public void setPosition(int pos) {
		this.position = pos;
	}
	
	public int getArgent() {
		return this.argent;
	}
	public void ajouterArgent(int montant) {
		this.argent+=montant;
	}
	public void retirerArgent(int montant) {
		if(this.argent-montant > 0)
			this.argent-=montant;
		else {
			this.argent = 0;
			this.setEstBanqueroute(true);
		}
	}
	
	public boolean getEstBanqueroute() {
		return this.estBanqueroute;
	}
	public void setEstBanqueroute(boolean banqueroute) {
		this.estBanqueroute = banqueroute;
	}
	
	public boolean getEstPrison(){
		return this.estPrison;
	}
	public void setEstPrison(boolean prison){
		this.estPrison = prison;
	}
	
	public void ajouterTerrain(CaseTerrain terrain) {
		this.terrains.add(terrain);
	}
	public String listTerrains() {
		String s = "<";
		for(CaseTerrain t:this.terrains) {
			s+=(t.getNom()+", ");
		}
		s+=">";
		return s;
	}
	
}
