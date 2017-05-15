package fenetres;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FenetreSortirPrison {
	
	private FenetrePrincipale fp;
	private Stage stage;
	private HBox root;
	private Label l_Texte;
	private Button b_Oui;
	private Button b_Non;
	
	public FenetreSortirPrison(FenetrePrincipale fp) {
		
		this.fp = fp;
		
		this.stage = new Stage();
		this.stage.setTitle("Sortir de prison ?");
		this.stage.initOwner(fp.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);
		
		stage.setOnHiding(new EvtQuitter());
	}
	
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(10);
		root.setStyle("-fx-background-color: #CDE6D0; ");
		
		Image i_prison = new Image("images/prison.jpg");
		ImageView iv_prison = new ImageView(i_prison);
		root.getChildren().add(iv_prison);
		
		VBox aside = new VBox();
		aside.setSpacing(15);
		root.getChildren().add(aside);
		
		l_Texte = new Label("Voulez vous payer 50€ pour sortir de prison ?");
		aside.getChildren().add(l_Texte);

		HBox buttons_horiz = new HBox();
		buttons_horiz.setSpacing(10);
		
		b_Oui = new Button("Oui");
		b_Oui.setOnAction(new EvtOui());
		buttons_horiz.getChildren().add(b_Oui);
		
		b_Non = new Button("Non");
		b_Non.setOnAction(new EvtNon());
		buttons_horiz.getChildren().add(b_Non);

		aside.getChildren().add(buttons_horiz);
		
		root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	           if(b_Oui.isFocused())
	        	   b_Oui.fire();
	           else
	        	   b_Non.fire();
	           ev.consume(); 
	        }
	    });
	}
	
	public void afficherFenetre() {
		root = new HBox();
		initRoot();
		
		Scene scene = new Scene(root,420,130);
		stage.setScene(scene);
		stage.show();
	}
	
	public Stage getStage() {
		return stage;
	}
	
	private class EvtOui implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			stage.close();
			fp.getPartie().getPM().getCaseActive().setReponseQuestion(true);
			event.consume();
		}
	}
	
	private class EvtNon implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			stage.close();
			event.consume();
		}
	}
	
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			fp.getPartie().reprendrePartie();
			event.consume();
		}
	}
}
