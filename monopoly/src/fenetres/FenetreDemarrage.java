package fenetres;

import javafx.collections.FXCollections;
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

/**
 * Fenêtre à afficher au démarrage d'une nouvelle {@link jeumonopoly.Partie}, permettant de sélectionner le nombre de joueur.
 * @see FenetrePrincipale
 */
public class FenetreDemarrage {

	private FenetrePrincipale fp;
	private Stage stage;
	private VBox root;
	private Label l_NbJoueurs;
	private ListView<String> l_Nombre;
	private Button b_Valider;
	private int choix = 0;
	
	/**
	 * Unique constructeur de la classe {@link FenetreDemarrage}, prenant en paramètre la {@link FenetrePrincipale} fp.
	 * @param fp FenetrePrincipale
	 * @see FenetrePrincipale
	 */
	public FenetreDemarrage(FenetrePrincipale fp) {
		
		this.fp = fp;
		
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
	
	/**
	 * Initialise la VBox root de la FenetreDemarrage avec une {@link ListView} de nombres de joueurs et un bouton de validation.
	 */
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
		
		l_Nombre.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	        	b_Valider.fire();
	        }
	    });
	}
	
	/**
	 * Renvoie la Stage de la fenêtre de démarrage.
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * Évènement qui récupère dans la {@link ListView} le nombre de joueurs désiré et lance une partie avec ce nombre.
	 * @see FenetrePrincipale
	 */
	private class EvtValider implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			choix = Integer.parseInt(l_Nombre.getFocusModel().getFocusedItem());
			fp.setPartie(choix);
			fp.getStage().show();
			stage.close();
			event.consume();
		}
	}
	
	/**
	 * Évènement qui ferme la fenêtre de démarrage et arrête le programme si l'on a pas cliqué sur le bouton Valider.
	 */
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			if(choix == 0)
				System.exit(0);
			event.consume();
		}
	}
}
