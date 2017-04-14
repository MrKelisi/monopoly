package fenetres;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FenetreCarteCommunaute {

	private FenetrePrincipale fp;
	private Stage stage;
	private HBox root;
	private VBox carte;
	private Button b_Ok;
	private Label l_Titre;
	private Label l_Description;
	private String[] Titres = new String[] {"Vol de passeport", "Retour à la case départ", "Tombée de neige"};
	private String[] Descriptions = new String[] {"Allez à l'ambassade sans passez par la case \ndépart. Vous devrez payez 50€ pour faire refaire \nun nouveau passeport et restez bloqué tout un \ntour à l'ambassade, sauf si vous faites un 6 au dé.", "Et touchez 100€", "Retournez à l'aéroport"};
	
	public FenetreCarteCommunaute(FenetrePrincipale f) {
		
		this.fp = f;
		
		this.stage = new Stage();
		this.stage.setTitle("Carte Communauté");
		this.stage.initOwner(fp.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);

		int choix = fp.rand.nextInt(3);
		l_Titre = new Label(Titres[choix]);
		l_Description = new Label(Descriptions[choix]);
		
		root = new HBox();
		initRoot();
		
		Scene scene = new Scene(root,440,200);
		stage.setScene(scene);
		
		stage.setOnHiding(new EvtQuitter());
	}
	
	private void initRoot() {
		root.setFillHeight(true);
		root.setAlignment(Pos.CENTER_LEFT);
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(15);
		root.setStyle("-fx-background-color: #CDE6D0; -fx-border-style: dashed; -fx-border-width: 3px; -fx-border-color: blue");
		
		Image i_communaute = new Image("images/communaute.jpg");
		ImageView iv_communaute = new ImageView(i_communaute);
		root.getChildren().add(iv_communaute);
		
		this.carte = new VBox();
		carte.setFillWidth(true);
		
		l_Titre.setStyle("-fx-font-size: 20px");
		carte.getChildren().add(l_Titre);
		l_Description.setStyle("-fx-padding: 10px 0px");
		carte.getChildren().add(l_Description);
		
		b_Ok = new Button("OK");
		b_Ok.setOnAction(new EvtValider());
		carte.getChildren().add(b_Ok);
		
		root.getChildren().add(carte);
	}
	
	public Stage getStage() {
		return stage;
	}
	
	private class EvtValider implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			stage.close();
		}
	}
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			stage.close();
		}
	}
}
