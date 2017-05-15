package fenetres;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.Partie;
import jeumonopoly.PlateauMonopoly;
import jeudeplateau.Case;

public class FenetrePrincipale {
	
	private Stage stage;
	private StackPane root;
	private Label l_ParcGratuit = new Label("0�");
	private ArrayList <Label> l_Joueurs = new ArrayList <Label>();
	private ArrayList <Label> l_ListeTerrains = new ArrayList <Label>();
	private ArrayList <Circle> l_Pions = new ArrayList <Circle>();
	private ArrayList<Label> l_Logs = new ArrayList<Label>();
	private Button tourSuivant = new Button("Finir son tour");
	private Button newPartie = new Button("Nouvelle partie");
	public Random rand = new Random();
	public Color[] Couleurs = new Color[] {Color.RED, Color.BLUE, Color.ORANGE, Color.GREEN};
	private FenetreDemarrage fd = new FenetreDemarrage(this);
	private FenetreCarteChance fch = new FenetreCarteChance(this);
	private FenetreCarteCommunaute fco = new FenetreCarteCommunaute(this);
	private FenetreAcheterTerrain fat = new FenetreAcheterTerrain(this);
	private FenetreSortirPrison fprison = new FenetreSortirPrison(this);
	private FenetreActionSurTerrain fact_ter = new FenetreActionSurTerrain(this);
	private Partie partie;

	public FenetrePrincipale(Stage primaryStage) {
		//Constructeur de la classe FenetrePrincipale
		
		this.stage = primaryStage;
		
		root = new StackPane();
		root.setOnMouseClicked(new EvtClicRoot());
		initRoot();
		
		Scene scene = new Scene(root,655,655);
		stage.setScene(scene);
		stage.setTitle("Monopoly");
		
		fd.getStage().show();
	}
	
	private void initRoot() {
		root.setStyle("-fx-background-image: url('images/plateau.png'); -fx-background-repeat: no-repeat");
		root.setAlignment(Pos.TOP_LEFT);
		
		l_ParcGratuit.setTranslateX(3);
		l_ParcGratuit.setTranslateY(68);
		root.getChildren().add(l_ParcGratuit);

		for(int i=0; i<9; i++) {
			l_Logs.add(new Label(""));
			l_Logs.get(i).setFont(Font.font("Consolas", 12));
			l_Logs.get(i).setTranslateX(100);
			l_Logs.get(i).setTranslateY(500 - i*16);
			l_Logs.get(i).setMaxWidth(460);
			l_Logs.get(i).setMaxHeight(16);
			root.getChildren().add(l_Logs.get(i));
		}

		tourSuivant.setTranslateX(473);
		tourSuivant.setTranslateY(533);
		tourSuivant.setOnAction(new EvtTourSuivant());
		tourSuivant.setDefaultButton(true);
		if(!partie.PARTIE_AUTO)
			root.getChildren().add(tourSuivant);
	}
	
	public StackPane getRoot() {
		return root;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public Circle getPionActif() {
		return l_Pions.get(partie.getPM().getJoueurActifID());
	}
	
	public Partie getPartie() {
		return partie;
	}
	public void setPartie(int nbJoueurs) {
		
		partie = new Partie(nbJoueurs, this);
		
		for(int i=0; i<nbJoueurs; i++) {
			Label l_nomJoueur = new Label(partie.getPM().getJoueur(i).getNom());
			l_nomJoueur.setTextFill(Couleurs[i]);
			l_nomJoueur.setTranslateX(95+i*120);
			l_nomJoueur.setTranslateY(100);
			root.getChildren().add(l_nomJoueur);
			
			l_Joueurs.add(new Label(""+partie.getPM().getJoueur(i).getArgent()+"�"));
			l_Joueurs.get(i).setTranslateX(95+i*120);
			l_Joueurs.get(i).setTranslateY(120);
			l_Joueurs.get(i).setFont(Font.font("Arial", 15));
			root.getChildren().add(l_Joueurs.get(i));
			
			l_ListeTerrains.add(new Label("\n"));
			l_ListeTerrains.get(i).setTranslateX(95+i*120);
			l_ListeTerrains.get(i).setTranslateY(140);
			l_ListeTerrains.get(i).setMaxWidth(110);
			root.getChildren().add(l_ListeTerrains.get(i));
			
			l_Pions.add(new Circle(7));
			l_Pions.get(i).setFill(Couleurs[i]);
			if(i<2) {
				l_Pions.get(i).setTranslateX(598 + i*15);
				l_Pions.get(i).setTranslateY(605);
			}
			else {
				l_Pions.get(i).setTranslateX(598 + (i-2)*15);
				l_Pions.get(i).setTranslateY(620);
			}
			root.getChildren().add(l_Pions.get(i));
		}
		
		refreshLabels(partie.getPM());
		partie.demarrerLaPartie();
	}
	
	public void logMessages(String msg) {
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	l_Logs.get(l_Logs.size()-1).setText("TOUR "+getPartie().getPM().getNbTours()+" : "+getPartie().getPM().getJoueurActif().getNom());
            	l_Logs.get(l_Logs.size()-1).setTextFill(Couleurs[getPartie().getPM().getJoueurActifID()]);
            	for(int i=l_Logs.size()-2; i>0; i--) {
            		l_Logs.get(i).setText(l_Logs.get(i-1).getText());
            	}
            	l_Logs.get(0).setText(msg);
            }
        });
	}
	
	public void refreshLabels(PlateauMonopoly pm) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
        		l_ParcGratuit.setText(""+pm.getCase(20).getPrix()+"�");
        		
        		for(int i=0; i<pm.getNbJoueurs(); i++) {
            		l_Joueurs.get(i).setText(""+pm.getJoueur(i).getArgent()+"� "+(pm.getJoueur(i).getCarteSortiePrison()?"[Sortie]":""));
            		l_ListeTerrains.get(i).setText(pm.getJoueur(i).getListeStringTerrains());
        		}
        		
            }
        });
	}
	
	public void afficherFenetreAchatTerrain() {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	fat.afficherFenetre();
            }
		});
	}
	
	public void afficherFenetrePrison() {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	fprison.afficherFenetre();
            }
		});
	}
	
	public void afficherFenetreCarte(boolean carteChance, String titre, String description) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
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
		});
	}
	
	public void setMarqueurProprietaire(JoueurMonopoly j, Case caze) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	caze.getMarqueur().setFill(getPionActif().getFill());
            	
            	double x = 100, y = 100;
        		int pos = j.getPosition();
        		
        		if(caze.getMarqueur().getPoints().isEmpty())
        			root.getChildren().add(caze.getMarqueur());
        		
        		if(pos > 0 && pos < 10) {
        			if(caze.getMarqueur().getPoints().isEmpty())
        				caze.getMarqueur().getPoints().addAll(new Double[] {0.,0.,0.,12.,12.,12.});
        			x = 517 - ((pos-1) * 54);
        			y = 642;
        		}
        		else if(pos > 10 && pos < 20) {
        			if(caze.getMarqueur().getPoints().isEmpty())
        				caze.getMarqueur().getPoints().addAll(new Double[] {0.,12.,12.,12.,12.,0.});
        			x = 51;
        			y = 558 - ((pos-11) * 54);
        		}
        		else if(pos > 20 && pos < 30) {
        			if(caze.getMarqueur().getPoints().isEmpty())
        				caze.getMarqueur().getPoints().addAll(new Double[] {0.,0.,0.,12.,12.,12.});
        			x = 85 + ((pos-21) * 54);
        			y = 51;
        		}
        		else if(pos > 30 && pos < 40) {
        				if(caze.getMarqueur().getPoints().isEmpty())
        			caze.getMarqueur().getPoints().addAll(new Double[] {0.,0.,12.,0.,0.,12.});
        			x = 592;
        			y = 85 + ((pos-31) * 54);
        		}
        		
        		if(pos == 15 || pos == 12)
        			x+=21;
        		else if(pos == 25 || pos == 28)
        			y+=21;
        		else if(pos == 35)
        			x-=21;
        		
        		caze.getMarqueur().setTranslateX(x);
        		caze.getMarqueur().setTranslateY(y);
            }
        });
	}
	
	public void setMaison(Case caze){
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	Polygon maison = new Polygon();
            	maison.getPoints().addAll(new Double[] {0., 11., 0., 3., 5., 0., 10., 3., 10., 11.});
            	
            	int x = -50;
            	int y = -50;
            	int pos = caze.getId();
            	
            	if(pos > 0 && pos < 10) {
        			x = 520 - ((pos-1) * 54) + (caze.getNbMaison()-1)*12;
        			y = 577;
        		}
        		else if(pos > 10 && pos < 20) {
        			x = 69;
        			y = 519 - ((pos-11) * 54) + (caze.getNbMaison()-1)*13;
        		}
        		else if(pos > 20 && pos < 30) {
        			x = 87 + ((pos-21) * 54)  + (caze.getNbMaison()-1)*12;
        			y = 69;
        		}
        		else if(pos > 30 && pos < 40) {
        			x = 576;
        			y = 87 + ((pos-31) * 54) + (caze.getNbMaison()-1)*13;
        		}
            	
            	maison.setTranslateX(x);
            	maison.setTranslateY(y);
            	root.getChildren().add(maison);
            }
		});
	}
	
	
	public void deplacerPion(JoueurMonopoly j){

		double x, y;
		int pos = j.getPosition();
		TranslateTransition tt = new TranslateTransition(Duration.millis(500), getPionActif());
		
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
			else if(j.getID() == 0 || j.getID() == 1){
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
		
		switch(j.getID()) {
		case 0: x-=8; y-=8; break;
		case 1: x+=8; y-=8; break;
		case 2: x-=8; y+=8; break;
		case 3: x+=8; y+=8; break;
		default: break;
		}
		
	    tt.setToX(x);
	    tt.setToY(y);
	    tt.play();
	}
	
	public void afficherVainqueur(PlateauMonopoly pm) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	Label vainqueur = new Label("Le vainqueur est "+pm.estVainqueur().getNom()+" !");
            	vainqueur.setTextFill(l_Pions.get(pm.estVainqueur().getID()).getFill());
            	vainqueur.setFont(Font.font("Arial", 26));
            	vainqueur.setTranslateX(145);
            	vainqueur.setTranslateY(525);
            	
        		root.getChildren().add(vainqueur);
        		
        		root.getChildren().remove(tourSuivant);
        		
        		newPartie.setTranslateX(463);
        		newPartie.setTranslateY(533);
        		newPartie.setOnAction(new EvtNewPartie());
        		root.getChildren().add(newPartie);
        		
            }
		});
	}
	
	public void resetElementsGraphiques() {
		l_ParcGratuit.setText("0�");
		l_Joueurs.clear();
		l_ListeTerrains.clear();
		l_Pions.clear();
		l_Logs.clear();
	}
	
	private class EvtTourSuivant implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			partie.reprendrePartie();
		}
	}
	private class EvtNewPartie implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			stage.close();
			resetElementsGraphiques();
			root = new StackPane();
			initRoot();
			Scene scene = new Scene(root,655,655);
			stage.setScene(scene);
			fd.getStage().show();
		}
	}
public class EvtClicRoot implements EventHandler<MouseEvent> {
		
		@Override
		public void handle(MouseEvent event) {
			
			int pos = -1;
			
			if(event.getSceneX() < 84) {
				if(event.getSceneY() < 84)
					pos = 20;
				else if(event.getSceneY() < 570)
					pos = 19 - (int)((event.getSceneY()-84)/54);
				else
					pos = 10;
			}
			else if(event.getSceneX() < 570) {
				if(event.getSceneY() < 84)
					pos = 21 + (int)((event.getSceneX()-84)/54);
				else if(event.getSceneY() >= 570)
					pos = 9 - (int)((event.getSceneX()-84)/54);
			}
			else {
				if(event.getSceneY() < 84)
					pos = 30;
				else if(event.getSceneY() < 570)
					pos = 31 + (int)((event.getSceneY()-84)/54);
				else
					pos = 0;
			}
			
			ArrayList<Integer> CasesInterdites = new ArrayList<Integer>();
			for(int i=0; i<40; i++) {
				CasesInterdites.add(i);
			}
			CasesInterdites.add(-1);
			for(Case t:getPartie().getPM().getJoueurActif().getTerrains()) {
				CasesInterdites.remove((Object)(t.getId()));
			}
			
			if(!CasesInterdites.contains(pos)) {
				fact_ter.afficherFenetre(pos);
			}
		}
	}
	
}