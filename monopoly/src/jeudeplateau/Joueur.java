package jeudeplateau;

public class Joueur {

	private String nom;
	private int argent = 1000;
	private int position = 0;
	private boolean estBanqueroute = false;
	
	public Joueur(String nom) {
		super();
		this.nom = nom;
	}
	
	public String getNom() {
		return this.nom;
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
	
	
}
