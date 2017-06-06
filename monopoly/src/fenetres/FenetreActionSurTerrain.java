package fenetres;

import cases.CaseTerrain;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Fen�tre � afficher lorqu'on clic sur une {@link CaseTerrain}.<br><br>
	 * ** <b>Liste d'actions r�alisable : </b>
	 * <ul><li>Poser une maison</li>
	 * <li>Revendre une maison</li>
	 * <li>Revendre le terrain</li></ul>
 * @see FenetrePrincipale
 */
public class FenetreActionSurTerrain {
	
	private FenetrePrincipale fp;
	private Stage stage;
	private HBox root;
	private int position;
	private Label l_Texte;
	private Label l_TexteErreur;
	private Button b_Poser;
	private Button b_Revendre;
	
	/**
	 * Unique constructeur de la classe {@link FenetreActionSurTerrain}, prenant en param�tre la {@link FenetrePrincipale} fp.
	 * @param fp FenetrePrincipale
	 * @see FenetrePrincipale
	 */
	public FenetreActionSurTerrain(FenetrePrincipale fp) {
		
		this.fp = fp;
		
		this.stage = new Stage();
		this.stage.setTitle("Action sur le terrain :");
		this.stage.initOwner(fp.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);
		
		stage.setOnHiding(new EvtQuitter());
	}
	
	/**
	 * Initialise la HBox root de la FenetreActionSurTerrain avec une image, un label et des boutons. 
	 */
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(10);
		root.setStyle("-fx-background-color: #CDE6D0; ");
		
		Image i_terrain;
		
		switch(position) {
		case 5: i_terrain = new Image("images/gare.jpg"); break;
		case 15: i_terrain = new Image("images/gare.jpg"); break;
		case 25: i_terrain = new Image("images/gare.jpg"); break;
		case 35: i_terrain = new Image("images/gare.jpg"); break;
		case 28: i_terrain = new Image("images/eau.jpg"); break;
		case 12: i_terrain = new Image("images/elec.jpg"); break;
		default: {
			String couleur = fp.getPartie().getPM().getCase(position).getCouleur();
			i_terrain = new Image("images/m_"+couleur+".jpg");
		}; break;
		}
		
		ImageView iv_terrain = new ImageView(i_terrain);
		root.getChildren().add(iv_terrain);
		
		VBox aside = new VBox();
		aside.setSpacing(15);
		root.getChildren().add(aside);
		
		l_Texte = new Label("Que voulez-vous faire sur le terrain "+ fp.getPartie().getPM().getCase(position).getNom() +" ?");
		aside.getChildren().add(l_Texte);

		HBox buttons_horiz = new HBox();
		buttons_horiz.setSpacing(10);
		
		b_Poser = new Button("Poser une maison ("+fp.getPartie().getPM().getCase(position).getPrixMaison()+"�)");
		b_Poser.setOnAction(new EvtPoser());
		if(position != 5 && position != 15 && position != 25 && position != 35 && position != 12 && position != 28)
			buttons_horiz.getChildren().add(b_Poser);
		
		b_Revendre = new Button("Revendre le terrain");
		b_Revendre.setOnAction(new EvtRevendre());
		buttons_horiz.getChildren().add(b_Revendre);

		aside.getChildren().add(buttons_horiz);
		
		l_TexteErreur = new Label("");
		l_TexteErreur.setTextFill(Color.RED);
		aside.getChildren().add(l_TexteErreur);
		
		root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	           if(b_Poser.isFocused())
	        	   b_Poser.fire();
	           else
	        	   b_Revendre.fire();
	           ev.consume(); 
	        }
	    });
	}
	
	/**
	 * Affiche la fen�tre en r�initialisant la HBox root � chaque appel.
	 * @param pos int
	 */
	public void afficherFenetre(int pos) {
		position = pos;
		root = new HBox();
		initRoot();
		
		Scene scene = new Scene(root,470,130);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Renvoie la Stage de la fen�tre.
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * �v�nement qui pose une maison dans la fen�tre principale et l'ajoute dans la Case.
	 */
	private class EvtPoser implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			CaseTerrain c = (CaseTerrain) fp.getPartie().getPM().getCase(position);
			if(c.getPeutMettreMaison()) {
				c.ajouterMaison(fp);
				fp.setMaison(c);
				stage.close();
			}
			else l_TexteErreur.setText("Impossible de placer une maison ici.");
			event.consume();
		}
	}
	
	/**
	 * �v�nement qui revend le terrain du joueur.
	 */
	private class EvtRevendre implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			int prixRevente = fp.getPartie().getPM().getCase(position).getPrix() + fp.getPartie().getPM().getCase(position).getNbMaison()*fp.getPartie().getPM().getCase(position).getPrixMaison();
			fp.afficherMessage(fp.getPartie().getPM().getCase(position).getProprietaire().getNom() + " revend " + fp.getPartie().getPM().getCase(position).getNom() + " pour " + prixRevente + "�");
			fp.getPartie().getPM().getJoueurActif().getListeTerrains().remove(fp.getPartie().getPM().getCase(position));
			fp.getPartie().getPM().getCase(position).setProprietaire(null);
			fp.getPartie().getPM().getCase(position).getMarqueur().setFill(Color.TRANSPARENT);
			fp.getPartie().getPM().getJoueurActif().getListeCouleur();
			
			for(int i=0; i<5; i++) {
				fp.getPartie().getPM().getCase(position).maisons.get(i).setFill(Color.TRANSPARENT);
			}
			fp.getPartie().getPM().getJoueurActif().ajouterArgent(prixRevente);
			stage.close();
			event.consume();
		}
	}
	
	/**
	 * �v�nement qui ferme la fen�tre et rafraichit les labels.
	 */
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			fp.refreshLabels(fp.getPartie().getPM());
			event.consume();
		}
	}
	
}
