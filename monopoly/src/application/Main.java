package application;
	
import fenetres.FenetrePrincipale;
import io.Console;
import javafx.application.Application;
import javafx.stage.Stage;
import jeumonopoly.Partie;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			new FenetrePrincipale(primaryStage);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Console es = new Console();
		int nbJoueurs = 2;
		
		do {
			es.println("Nombre de joueurs (entre 2 et 4) : ");
			nbJoueurs = es.readInt();
		} while(nbJoueurs < 2 || nbJoueurs > 4);
		
		Partie P = new Partie(nbJoueurs);
		P.demarrerLaPartie();
		
		//launch(args);
		
	}
}
