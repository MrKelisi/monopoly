package jeudeplateau;

public abstract class Joueur {

	private String nom;
	private int id;
	private int position = 0;
	
	public Joueur(String nom, int id) {
		setNom(nom);
		setID(id);
	}
	
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		if(nom == null || nom.isEmpty())
			throw new IllegalArgumentException("Le nom ne peux pas être vide ou null");
		this.nom = nom;
	}
	
	public int getID() {
		return this.id;
	}
	public void setID(int id) {
		this.id = id;
	}
	
	public int getPosition() {
		return this.position;
	}
	public void setPosition(int pos) {
		this.position = pos;
	}
	
}
