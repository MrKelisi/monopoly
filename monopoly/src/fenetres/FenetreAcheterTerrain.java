package fenetres;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FenetreAcheterTerrain {
	
	private FenetrePrincipale fp;
	private Stage stage;
	private VBox root;
	private Label l_Texte;
	private Button b_Oui;
	private Button b_Non;
	
	public FenetreAcheterTerrain(FenetrePrincipale fp) {
		
		this.fp = fp;
		
		this.stage = new Stage();
		this.stage.setTitle("Acheter ce terrain ?");
		this.stage.initOwner(fp.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);
		
		stage.setOnHiding(new EvtFenNon());
	}
	
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(5);
		
		l_Texte = new Label("Voulez vous acheter " + fp.getPartie().getPM().getCaseActive().getNom() + " pour " + fp.getPartie().getPM().getCaseActive().getPrix() + "€ ?");
		root.getChildren().add(l_Texte);

		HBox buttons_horiz = new HBox();
		buttons_horiz.setSpacing(10);
		
		b_Oui = new Button("Oui");
		b_Oui.setOnAction(new EvtOui());
		buttons_horiz.getChildren().add(b_Oui);
		
		b_Non = new Button("Non");
		b_Non.setOnAction(new EvtNon());
		buttons_horiz.getChildren().add(b_Non);

		root.getChildren().add(buttons_horiz);
	}
	
	public void afficherFenetre() {
		root = new VBox();
		initRoot();
		
		Scene scene = new Scene(root,400,80);
		stage.setScene(scene);
		stage.show();
	}
	
	public Stage getStage() {
		return stage;
	}
	
	private class EvtOui implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			fp.getPartie().getPM().getCase(fp.getPartie().getPM().getJoueurActif().getPosition()).setAcheterTerrain(true);
			stage.close();
		}
	}
	
	private class EvtNon implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			stage.close();
		}
	}
	
	private class EvtFenNon implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			fp.getPartie().reprendrePartie();
		}
	}
}
