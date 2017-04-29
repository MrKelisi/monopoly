package jeumonopoly;

import fenetres.FenetrePrincipale;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.control.Label;
import jeudeplateau.Case;
import jeudeplateau.Joueur;

public class Partie {

	private PlateauMonopoly plateauM;
	private FenetrePrincipale fp;
	private Label l_PG = new Label("0 €");
	
	public Partie(int nombreDeJoueurs, FenetrePrincipale fp) {
		this.plateauM = new PlateauMonopoly(nombreDeJoueurs);
		this.fp = fp;
	}
	
	public void demarrerLaPartie() {
		
		final Service<Void> partieService = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {
                    	System.out.println("La partie démarre!");
                		
                		Joueur joueur;
                		int lancé;
                		Case caze;
                		
                		while(!plateauM.finPartie() && plateauM.getNbTours() <= 100) {
                			
                			joueur = plateauM.getJoueurActif();
                			
                			if(plateauM.getJoueurActifID() == 0)
                				System.out.println("\n==================\n DEBUT DU TOUR " + plateauM.getNbTours() + "\n==================");
                				
                			System.out.println("\nC'est au tour de " + joueur.getNom() + " (possède " + joueur.getArgent() + "€)");
                			
                			if(!joueur.getEstBanqueroute()) {
                				Thread.sleep(800);
                				
                				lancé = plateauM.des.lancerDes();
                				
                				if(!joueur.getEstPrison()) {
                					System.out.println("" + joueur.getNom() + " lance les dés... [" + plateauM.des.getDe1() + "][" + plateauM.des.getDe2() + "]... et obtient un " + lancé + " !");
                					plateauM.deplacerJoueur(joueur, lancé);
                					fp.deplacerPion(plateauM);
                					Thread.sleep(1000);
                					
                					caze = plateauM.getCase(joueur.getPosition());
                					System.out.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur la case " + joueur.getPosition() + " : " + caze.getNom());
                				}
                				else {
                					System.out.println("Le joueur est en prison.");
                					
                					caze = plateauM.getCase(joueur.getPosition());
                				}
                				
                				caze.actionCase(joueur, plateauM, fp);
                				System.out.println("" + joueur.getNom() + " possède à la fin de son tour " + joueur.getArgent() + "€ et les terrains suivants :\n" + joueur.listTerrains());
                			}
                			else {
                				System.out.println("" + plateauM.getJoueurActif().getNom() + " est en banqueroute, il ne joue pas.");
                			}

                			fp.deplacerPion(plateauM);
                			fp.refreshLabels(plateauM);
                			plateauM.setJoueurSuivant();
                			
                		}
                		
                		System.out.println("\n============================\n Fin de la partie");
                		System.out.println(" Le vainqueur est " + plateauM.estVainqueur().getNom() + " !\n============================");
                        
                		fp.afficherVainqueur(plateauM);
                		
                		return null;
                    }
                };
            }
        };
        /*
        partieService.stateProperty().addListener(new ChangeListener<Worker.State>() {

            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) {
                switch (newValue) {
                    default: break;
                }
            }
        });
        */
        partieService.start();
	}
	
	public PlateauMonopoly getPM() {
		return this.plateauM;
	}
	
	public Label getLabelPG() {
		return this.l_PG;
	}
	public void setLabelPG(String s) {
		this.l_PG.setText(s);
	}

	@Override
	public String toString() {
		return "Partie [plateauM=" + plateauM.toString() + "]";
	}
	
}
