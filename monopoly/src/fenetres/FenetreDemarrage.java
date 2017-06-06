package fenetres;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
	private ArrayList<TextField> listeJoueurs = new ArrayList<TextField>();
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
		
		Scene scene = new Scene(root,300,190);
		stage.setScene(scene);
		
		stage.setOnHiding(new EvtQuitter());
	}
	
	/**
	 * Initialise la VBox root de la FenetreDemarrage avec une {@link ListView} de nombres de joueurs et un bouton de validation.
	 */
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(5);
		
		l_NbJoueurs = new Label("Noms des joueurs (2 minimum) :");
		root.getChildren().add(l_NbJoueurs);
		
		for(int i=0; i<4; i++) {
			listeJoueurs.add(new TextField(i<2?"Joueur"+(i+1):""));
			listeJoueurs.get(i).setPromptText("Nom du joueur "+(i+1));
			root.getChildren().add(listeJoueurs.get(i));
		}
		
		b_Valider = new Button("Valider");
		b_Valider.setOnAction(new EvtValider());
		b_Valider.setDefaultButton(true);
		root.getChildren().add(b_Valider);
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
			ArrayList<String> champs = new ArrayList<String>();
			for(int i=0; i<4; i++) {
				if(listeJoueurs.get(i).getText() != null && !listeJoueurs.get(i).getText().isEmpty())
					champs.add(listeJoueurs.get(i).getText());
			}
			if(champs.size()>=2) {
				choix = 1;
				fp.setPartie(champs.size(), champs);
				fp.getStage().show();
				stage.close();
			}
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
