package fenetres;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import jeudeplateau.Joueur;
import jeumonopoly.Partie;
import jeumonopoly.PlateauMonopoly;

public class FenetrePrincipale {
	
	private Stage stage;
	private StackPane root;
	private Label l_ParcGratuit = new Label("0€");
	private ArrayList <Label> l_Joueurs = new ArrayList <Label>();
	private ArrayList <Label> l_ListeTerrains = new ArrayList <Label>();
	public Random rand = new Random();
	private FenetreDemarrage fd = new FenetreDemarrage(this);
	private FenetreCarteChance fch = new FenetreCarteChance(this);
	private FenetreCarteCommunaute fco = new FenetreCarteCommunaute(this);
	private Partie partie;

	public FenetrePrincipale(Stage primaryStage) {
		//Constructeur de la classe FenetrePrincipale
		
		this.stage = primaryStage;
		
		root = new StackPane();
		initRoot();
		
		Scene scene = new Scene(root,655,655);
		stage.setScene(scene);
		stage.setTitle("Monopoly");
		
		fd.getStage().show();
	}
	
	private void initRoot() {
		root.setStyle("-fx-background-image: url('images/plateau.png'); -fx-background-repeat: no-repeat");
		root.setAlignment(Pos.TOP_LEFT);
		root.getChildren().add(l_ParcGratuit);
		l_ParcGratuit.setTranslateX(3);
		l_ParcGratuit.setTranslateY(68);
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public Partie getPartie() {
		return partie;
	}
	public void setPartie(int nbJoueurs) {
		partie = new Partie(nbJoueurs, this);
		setDepartPions(partie.getPM());
		
		Color[] Couleurs = new Color[] {Color.RED, Color.BLUE, Color.ORANGE, Color.GREEN};
		for(int i=0; i<nbJoueurs; i++) {
			Label l_nomJoueur = new Label(partie.getPM().getJoueur(i).getNom());
			l_nomJoueur.setTextFill(Couleurs[i]);
			l_nomJoueur.setTranslateX(95+i*120);
			l_nomJoueur.setTranslateY(100);
			root.getChildren().add(l_nomJoueur);
			
			l_Joueurs.add(new Label(""+partie.getPM().getJoueur(i).getArgent()+"€"));
			l_Joueurs.get(i).setTranslateX(95+i*120);
			l_Joueurs.get(i).setTranslateY(120);
			l_Joueurs.get(i).setFont(Font.font("Arial", 15));
			root.getChildren().add(l_Joueurs.get(i));
			
			l_ListeTerrains.add(new Label("\n"));
			l_ListeTerrains.get(i).setTranslateX(95+i*120);
			l_ListeTerrains.get(i).setTranslateY(140);
			l_ListeTerrains.get(i).setMaxWidth(110);
			root.getChildren().add(l_ListeTerrains.get(i));
		}
		
	}
	public void refreshLabels(PlateauMonopoly pm) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
        		l_ParcGratuit.setText(""+pm.getCase(20).getPrix()+"€");
        		
        		for(int i=0; i<pm.getNbJoueurs(); i++) {
            		l_Joueurs.get(i).setText(""+pm.getJoueur(i).getArgent()+"€");
            		l_ListeTerrains.get(i).setText(pm.getJoueur(i).listTerrains());
        		}
        		
            }
        });
	}
	
	public void tirerCarte(boolean carteChance, String titre, String description) {
		if(carteChance) {
			fch.setTitre(titre);
			fch.setDescription(description);
			fch.afficherCarte();
		}
		else {
			fco.setTitre(titre);
			fco.setDescription(description);
			fco.afficherCarte();
		}
	}
	
	public StackPane getRoot() {
		return root;
	}
	
	public void setDepartPions(PlateauMonopoly pm) {
		for(int i=0; i< 2; i++) {
			pm.getJoueur(i).getPion().setTranslateX(598 + i*15);
			pm.getJoueur(i).getPion().setTranslateY(605);
			root.getChildren().add(pm.getJoueur(i).getPion());
		}
		for(int i=2; i< pm.getNbJoueurs(); i++) {
			pm.getJoueur(i).getPion().setTranslateX(598 + (i-2)*15);
			pm.getJoueur(i).getPion().setTranslateY(620);
			root.getChildren().add(pm.getJoueur(i).getPion());
		}
		
	}
	
	public void setMarqueurProprietaire(Joueur j) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
        		//Rectangle r = new Rectangle(8,8,j.getPion().getFill());
            	Polygon r = new Polygon();
            	r.setFill(j.getPion().getFill());
            	
            	double x = 100, y = 100;
        		int pos = j.getPosition();
        		
        		if(pos > 0 && pos < 10) {
                	r.getPoints().addAll(new Double[] {0.,0.,0.,12.,12.,12.});
        			x = 517 - ((pos-1) * 54);
        			y = 642;
        		}
        		else if(pos > 10 && pos < 20) {
                	r.getPoints().addAll(new Double[] {0.,12.,12.,12.,12.,0.});
        			x = 51;
        			y = 558 - ((pos-11) * 54);
        		}
        		else if(pos > 20 && pos < 30) {
                	r.getPoints().addAll(new Double[] {0.,0.,0.,12.,12.,12.});
        			x = 85 + ((pos-21) * 54);
        			y = 51;
        		}
        		else if(pos > 30 && pos < 40) {
                	r.getPoints().addAll(new Double[] {0.,0.,12.,0.,0.,12.});
        			x = 592;
        			y = 85 + ((pos-31) * 54);
        		}
        		
        		if(pos == 15 || pos == 12)
        			x+=21;
        		else if(pos == 25 || pos == 28)
        			y+=21;
        		else if(pos == 35)
        			x-=21;
        		
        		r.setTranslateX(x);
        		r.setTranslateY(y);
        		root.getChildren().add(r);
            }
        });
	}
	
	public void deplacerPion(PlateauMonopoly pm){
		
		Joueur j = pm.getJoueurActif();
		int idJoueur = pm.getJoueurActifID();
		
		double x, y;
		int pos = j.getPosition();
		TranslateTransition tt = new TranslateTransition(Duration.millis(500), j.getPion());
		
		if(j.getEstBanqueroute()) {
			x = 103;
			y = 538;
		}
		else if(pos == 0) {
			x = 604;
			y = 604;
		}
		else if(pos == 10) {
			if(j.getEstPrison()) {
				x = 47;
				y = 598;
			}
			else if(idJoueur == 0 || idJoueur == 1){
				x = 16;
				y = 644;
			}
			else /* idJoueur == 2 ou 3*/ {
				x = 48;
				y = 628;
			}
		}
		else if(pos == 20) {
			x = 30;
			y = 34;
		}
		else if(pos == 30) {
			x = 604;
			y = 34;
		}
		else if(pos > 0 && pos < 10) {
			x = 537 - ((pos-1) * 54);
			y = 620;
		}
		else if(pos > 10 && pos < 20) {
			x = 30;
			y = 538 - ((pos-11) * 54);
		}
		else if(pos > 20 && pos < 30) {
			x = 104 + ((pos-21) * 54);
			y = 30;
		}
		else if(pos > 30 && pos < 40) {
			x = 612;
			y = 106 + ((pos-31) * 54);
		}
		else {
			x = -50;
			y = -50;
		}
		
		if	    (idJoueur==0) { x-=8;	y-=8; }
		else if (idJoueur==1) { x+=8; 	y-=8; }
		else if (idJoueur==2) { x-=8; 	y+=8; }
		else			   { x+=8; 	y+=8; }
		
	    tt.setToX(x);
	    tt.setToY(y);
	    tt.play();
	    
	}
	
	public void afficherVainqueur(PlateauMonopoly pm) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	Label vainqueur = new Label("Le vainqueur est "+pm.estVainqueur().getNom()+" !");
            	vainqueur.setTextFill(pm.estVainqueur().getPion().getFill());
            	vainqueur.setFont(Font.font("Arial", 30));
            	vainqueur.setTranslateX(150);
            	vainqueur.setTranslateY(440);
            	
        		root.getChildren().add(vainqueur);
            }
		});
	}
	
}