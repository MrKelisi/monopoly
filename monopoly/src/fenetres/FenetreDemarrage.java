package fenetres;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FenetreDemarrage {

	private FenetrePrincipale fp;
	private Stage stage;
	private VBox root;
	private Label l_NbJoueurs;
	private ListView<String> l_Nombre;
	private Button b_Valider;
	private int choix = 0;
	
	public FenetreDemarrage(FenetrePrincipale f) {
		
		this.fp = f;
		
		this.stage = new Stage();
		this.stage.setTitle("Monopoly");
		this.stage.initOwner(fp.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);
		
		root = new VBox();
		initRoot();
		
		Scene scene = new Scene(root,300,160);
		stage.setScene(scene);
		
		stage.setOnHiding(new EvtQuitter());
	}
	
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(5);
		
		l_NbJoueurs = new Label("Nombre de joueurs :");
		root.getChildren().add(l_NbJoueurs);
		
		l_Nombre = new ListView<String>();
		l_Nombre.setItems(FXCollections.observableArrayList("2", "3", "4"));
		l_Nombre.getSelectionModel().select(0);
		root.getChildren().add(l_Nombre);
		
		b_Valider = new Button("Valider");
		b_Valider.setOnAction(new EvtValider());
		root.getChildren().add(b_Valider);
	}
	
	public Stage getStage() {
		return stage;
	}
	
	private class EvtValider implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			choix = Integer.parseInt(l_Nombre.getFocusModel().getFocusedItem());
			fp.getStage().show();
			fp.setPartie(choix);
			fp.getPartie().demarrerLaPartie();
			stage.close();
		}
	}
	
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			if(choix == 0)
				System.exit(0);
		}
	}
}
