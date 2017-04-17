package fenetres;

import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jeumonopoly.Partie;

public class FenetrePrincipale {
	
	private static final boolean MODE_CONSOLE = true;
	private Stage stage;
	private VBox root;
	public Random rand = new Random();
	private FenetreDemarrage fd = new FenetreDemarrage(this);
	private FenetreCarteChance fch = new FenetreCarteChance(this);
	private FenetreCarteCommunaute fco = new FenetreCarteCommunaute(this);
	private Partie partie;
	private boolean enPause = false;

	public FenetrePrincipale(Stage primaryStage) {
		//Constructeur de la classe FenetrePrincipale
		
		this.stage = primaryStage;
		
		root = new VBox();
		initRoot();
		
		Scene scene = new Scene(root,650,650);
		stage.setScene(scene);
		stage.setTitle("Monopoly");
		
		if(!MODE_CONSOLE)
			stage.show();
		
		fd.getStage().show();		
	}
	
	private void initRoot() {
		root.setSpacing(15);
		root.setStyle("-fx-background-color: #CDE6D0");
		
		Image i_plateau = new Image("images/plateau.jpg");
		ImageView iv_plateau = new ImageView(i_plateau);
		
		root.getChildren().add(iv_plateau);
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public Partie getPartie() {
		return partie;
	}
	public void setPartie(int choix) {
		partie = new Partie(choix, this);
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
	
	public void mettreEnPause() {
		this.enPause = true;
		while(enPause) {
			try {
	            Thread.sleep(500);
	        }
	        catch (InterruptedException e) {}
		}
	}
	public void retirerPause() {
		this.enPause = false;
	}
	
	
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			stage.close();
		}
	}
	
}