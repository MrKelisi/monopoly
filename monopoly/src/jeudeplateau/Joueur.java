package jeudeplateau;

import java.util.ArrayList;
import javafx.scene.shape.Circle;

public class Joueur {

	private String nom;
	private int argent = 1000;
	private int position = 0;
	private Circle pion = new Circle(7);
	private boolean estBanqueroute = false;
	private boolean estPrison = false;
	private boolean possedeCouleurTerrain = false;
	private int toursEnPrison = 1;
	private int nombreGaresPossedees = 0;
	private int nombreServicesPossedes = 0;
	private ArrayList <Case> terrains = new ArrayList <Case>();
	private ArrayList<String> couleur = new ArrayList <String>();
	
	public Joueur(String nom) {
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
	
	public Circle getPion() {
		return this.pion;
	}
	
	public int getNbGares() {
		return this.nombreGaresPossedees;
	}
	public void setNbGares(int nb) {
		this.nombreGaresPossedees = nb;
	}
	
	public int getNbServices() {
		return this.nombreServicesPossedes;
	}
	public void setNbServices(int nb) {
		this.nombreServicesPossedes = nb;
	}
	
	public int getArgent() {
		return this.argent;
	}
	public void ajouterArgent(int montant) {
		this.argent+=montant;
	}
	public void retirerArgent(int montant) {
		this.argent = this.argent - montant;
		if(this.argent <= 0) {
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
	
	public boolean getPossedeCouleurTerrain(){
		return this.possedeCouleurTerrain;
	}
	public void setPossedeCouleurTerrain(boolean possedeCouleurTerrain){
		this.possedeCouleurTerrain = possedeCouleurTerrain;
	}
	
	public void ajouterTerrain(Case terrain) {
		this.terrains.add(terrain);
	}
	public String listTerrains() {
		String s = "";
		for(Case t:this.terrains) {
			s+=(t.getNom()+"\n");
		}
		return s;
	}
	public ArrayList<Case> getTerrain(){
		return this.terrains;
	}
	public ArrayList<String> getListeCouleur(){
		int brun = 0;
		int turquoise = 0;
		int mauve = 0;
		int orange = 0;
		int rouge = 0;
		int jaune = 0;
		int vert = 0;
		int bleu = 0;
		for(Case t:this.getTerrain()){
			if(t.getCouleur() == "brun")
				brun += 1;
			if(t.getCouleur() == "turquoise")
				turquoise += 1;
			if(t.getCouleur() == "mauve")
			mauve += 1;
			if(t.getCouleur() == "orange")
				orange += 1;
			if(t.getCouleur() == "rouge")
				rouge += 1;
			if(t.getCouleur() == "jaune")
				jaune += 1;
			if(t.getCouleur() == "vert")
				vert += 1;
			if(t.getCouleur() == "bleu")
				bleu += 1;
		}
		if(brun == 2)
			couleur.add("brun");
		if(turquoise == 3)
			couleur.add("turquoise");
		if(mauve == 3)
			couleur.add("orange");
		if(orange == 3)
			couleur.add("orange");
		if(rouge == 3)
			couleur.add("rouge");
		if(jaune == 3)
			couleur.add("jaune");
		if(vert == 3)
			couleur.add("vert");
		if(bleu == 2)
			couleur.add("bleu");
		return this.couleur;
	}
	
}
