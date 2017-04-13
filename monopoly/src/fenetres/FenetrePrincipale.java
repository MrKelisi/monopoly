package fenetres;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetrePrincipale {
	
	private Stage stage;
	private VBox window;
	private VBox root;

	public FenetrePrincipale(Stage stage) {
		//Constructeur de la classe FenetrePrincipale
		
		this.stage = stage;
		
		window = new VBox();
		initWindow();

		Scene scene = new Scene(window,600,600);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setTitle("Monopoly");
		
		stage.setScene(scene);
		stage.show();
	}
	
	private void initWindow() {
		
		window.setSpacing(15);
	}
	
}