package fenetres;

import java.util.ArrayList;
import java.util.Random;
import cases.CaseTerrain;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

/**
 * Fen�tre javafx principale pour l'affichage du jeu de Monopoly. 
 */
public class FenetrePrincipale {
	
	private Stage stage;
	private StackPane root;
	private Label l_ParcGratuit = new Label("0�");
	private ArrayList <Label> l_Joueurs = new ArrayList <Label>();
	private ArrayList <Label> l_ListeTerrains = new ArrayList <Label>();
	private ArrayList <Circle> l_Pions = new ArrayList <Circle>();
	private ArrayList<Label> l_Logs = new ArrayList<Label>();
	private ArrayList<Image> imageDes = new ArrayList<Image>();
	private ArrayList<ImageView> Des = new ArrayList<ImageView>();
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

	/**
	 * Unique constructeur de la classe {@link FenetrePrincipale}, prenant en param�tre la Stage principale pass�e par le main. 
	 * Le constructeur n'affiche pas la Stage au d�marrage, mais une instance de {@link FenetreDemarrage} pour choisir un nombre de joueur avant l'affichage.
	 * @param primaryStage Stage
	 * @see application.Main 
	 * @see FenetreDemarrage
	 */
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
	
	/**
	 * Initialise la StackPane root de la FenetrePrincipale avec les images, les labels et les boutons ad�quates au Monopoly.
	 */
	private void initRoot() {
		imageDes.add(new Image("images/de1.jpg"));
		imageDes.add(new Image("images/de2.jpg"));
		imageDes.add(new Image("images/de3.jpg"));
		imageDes.add(new Image("images/de4.jpg"));
		imageDes.add(new Image("images/de5.jpg"));
		imageDes.add(new Image("images/de6.jpg"));
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
	
	/**
	 * Renvoie la StackPane root de {@link FenetrePrincipale}.
	 * @return root StackPane
	 */
	public StackPane getRoot() {
		return root;
	}
	
	/**
	 * Renvoie la Stage stage de {@link FenetrePrincipale}.
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * Renvoie le {@link Circle} pion du joueur actif dans la {@link Partie}.
	 * @return pion Circle
	 * @see Partie
	 */
	public Circle getPionActif() {
		return l_Pions.get(partie.getPM().getJoueurActifID());
	}
	
	/**
	 * Renvoie la {@link Partie} partie de la {@link FenetrePrincipale}.
	 * @return partie {@link Partie}
	 */
	public Partie getPartie() {
		return partie;
	}
	
	/**
	 * M�thode permettant de lancer une partie. Elle instanciera une nouvelle {@link Partie} avec le bon nombre de joueurs 
	 * et se chargera de placer les �l�ments graphiques tels que : <br>les noms des joueurs, l'argent qu'ils poss�dent, 
	 * la liste de leurs terrains et les pions.
	 * @param nbJoueurs int
	 */
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
	
	/**
	 * Se charge de rafraichir les labels de logs dans la fen�tre. Elle re�oit un String msg en param�tre, remonte les anciens logs 
	 * et place le nouveau message � la fin des logs.
	 * @param msg String
	 */
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
	
	/**
	 * Cette m�thode est appel� � chaque fois qu'un rafrichissement des labels est n�cessaire. Elle va chercher les informations dans
	 * les champs de la partie pour mettre � jours les labels.
	 * @param pm PlateauMonopoly
	 * @see PlateauMonopoly
	 */
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
	
	/**
	 * Affiche la fen�tre {@link FenetreAcheterTerrain}.
	 * @see FenetreAcheterTerrain
	 */
	public void afficherFenetreAchatTerrain() {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	fat.afficherFenetre();
            }
		});
	}
	
	/**
	 * Affiche la fen�tre {@link FenetreSortirPrison}.
	 * @see FenetreSortirPrison
	 */
	public void afficherFenetrePrison() {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	fprison.afficherFenetre();
            }
		});
	}
	
	/**
	 * Affiche la fen�tre {@link FenetreCarteChance}. <br>
	 * Les param�tres String titre et String description pass�s seront utilis�s dans la fen�tre pour indiquer qu'elle carte on a tir�.
	 * @param titre String
	 * @param description String
	 * @see FenetreCarteChance 
	 */
	public void afficherFenetreCarteChance(String titre, String description) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
				fch.setTitre(titre);
				fch.setDescription(description);
				fch.afficherCarte();
            }
		});
	}
	
	/**
	 * Affiche la fen�tre {@link FenetreCarteCommunaute}. <br>
	 * Les param�tres String titre et String description pass�s seront utilis�s dans la fen�tre pour indiquer qu'elle carte on a tir�.
	 * @param titre String
	 * @param description String
	 * @see FenetreCarteCommunaute
	 */
	public void afficherFenetreCarteCommunaut�(String titre, String description) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
				fco.setTitre(titre);
				fco.setDescription(description);
				fco.afficherCarte();
            }
		});
	}
	
	/**
	 * M�thode pla�ant un marqueur d�signant le propri�taire du terrain quand le joueur ach�te le terrain.
	 * @param joueur JoueurMonopoly
	 * @param caze Case
	 * @see JoueurMonopoly
	 * @see Case
	 */
	public void setMarqueurProprietaire(JoueurMonopoly joueur, Case caze) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	caze.getMarqueur().setFill(getPionActif().getFill());
            	
            	double x = 100, y = 100;
        		int pos = joueur.getPosition();
        		
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
	
	/**
	 * M�thode ajoutant un {@link Polygon} maison dans la fen�tre principale en fonction de la {@link Case} pass�e en param�tre.
	 * @param caze Case
	 * @see Case
	 */
	public void setMaison(Case caze){
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	caze.maisons.get(caze.getNbMaison()).setFill(Color.BLACK);
            	
            	int x = -50;
            	int y = -50;
            	int pos = caze.getId();
            	
            	if(caze.getMarqueur().getPoints().isEmpty())
            		root.getChildren().add(caze.maisons.get(caze.getNbMaison()));
            	
            	caze.maisons.get(caze.getNbMaison()).getPoints().addAll(new Double[] {0., 11., 0., 3., 5., 0., 10., 3., 10., 11.});
            	
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
            	
            	caze.maisons.get(caze.getNbMaison()).setTranslateX(x);
            	caze.maisons.get(caze.getNbMaison()).setTranslateY(y);
            	
            }
		});
	}
	
	public void afficherDes(PlateauMonopoly p){
		Platform.runLater(new Runnable() {
            @Override public void run() {
        Des.clear();
		Des.add(new ImageView(imageDes.get(p.des.getDe1()-1)));
		Des.add(new ImageView(imageDes.get(p.des.getDe2()-1)));
		Des.get(0).setTranslateX(200);
		Des.get(0).setTranslateY(300);
		Des.get(1).setTranslateX(300);
		Des.get(1).setTranslateY(300);
		
		root.getChildren().add(Des.get(0));
		root.getChildren().add(Des.get(1));
        }});
	}
	
	/**
	 * D�place le pion du joueur actif en fonction de la position sur le plateau de joueur pass� en param�tre.
	 * @param joueur JoueurMonopoly
	 * @see JoueurMonopoly
	 */
	public void deplacerPion(JoueurMonopoly joueur){

		double x, y;
		int pos = joueur.getPosition();
		TranslateTransition tt = new TranslateTransition(Duration.millis(500), getPionActif());
		
		if(joueur.getEstBanqueroute()) {
			x = 103;
			y = 538;
		}
		else if(pos == 0) {
			x = 604;
			y = 604;
		}
		else if(pos == 10) {
			if(joueur.getEstPrison()) {
				x = 47;
				y = 598;
			}
			else if(joueur.getID() == 0 || joueur.getID() == 1){
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
		
		switch(joueur.getID()) {
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
	
	/**
	 * Affiche le vainqueur de la partie. Ajoute �galement le bouton newPartie � la fen�tre princiaple.
	 * @param pm PlateauMonopoly
	 * @see PlateauMonopoly
	 */
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
	
	/**
	 * R�initialise les �l�ments graphiques de la fen�tre tels que les labels, les pions et les logs.
	 */
	public void resetElementsGraphiques() {
		l_ParcGratuit.setText("0�");
		l_Joueurs.clear();
		l_ListeTerrains.clear();
		l_Pions.clear();
		l_Logs.clear();
	}
	
	/**
	 * �v�nement lorque l'on appuie sur le bouton tourSuivant : la partie reprend.
	 */
	private class EvtTourSuivant implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			partie.reprendrePartie();
		}
	}
	/**
	 * �v�nement lorque l'on appuie sur le bouton newPartie : la fen�tre principale se ferme, les �l�ments graphiques sont
	 * r�initialis�s, la StackPane root est red�finie et on r�affiche la fen�tre de d�marrage.
	 * @see FenetreDemarrage
	 */
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
	/**
	 * �v�nement lorqu'on clic dans la StackPane root : 
	 * en fonction des coordonn�es du pointeurs, on peux obtenir la position de la case vis�e. <br>
	 * Si cette position est une position valide (c�d que l'on clic sur une {@link CaseTerrain} qui appartient au joueur dont
	 * c'est le tour), alors on peut d�clencher l'affichage d'une {@link FenetreAcheterTerrain} avec en param�tre la position cliqu�e.
	 * @see CaseTerrain
	 * @see FenetreAcheterTerrain
	 */
	private class EvtClicRoot implements EventHandler<MouseEvent> {
		
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