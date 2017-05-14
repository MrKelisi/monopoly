package jeumonopoly;

import fenetres.FenetrePrincipale;
import io.Console;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import jeudeplateau.Case;

public class Partie {

	private PlateauMonopoly pm;
	private FenetrePrincipale fp;
	private boolean pausePartie = false;
	public final static long VITESSE_PARTIE = 1000;
	public final static boolean PARTIE_AUTO = false;
	
	public Partie(int nombreDeJoueurs, FenetrePrincipale fp) {
		this.pm = new PlateauMonopoly(nombreDeJoueurs);
		this.fp = fp;
	}
	
	public void demarrerLaPartie() {
		
		final Service<Void> partieService = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {
                    	Console es = new Console(fp);
                    	es.println("La partie démarre!");
                		
                		JoueurMonopoly joueur;
                		int lancé;
                		Case caze;
                		
                		while(!pm.finPartie() && pm.getNbTours() <= 100) {
                			
                			joueur = pm.getJoueurActif();
                			
                			if(pm.getJoueurActifID() == 0)
                				es.println("=== DEBUT DU TOUR " + pm.getNbTours() + " ===");
                				
                			es.println("C'est au tour de " + joueur.getNom() + " (possède " + joueur.getArgent() + "€)");
                			
                			if(!joueur.getEstBanqueroute()) {
                				Thread.sleep(800);
                				
                				lancé = pm.des.lancerDes();
                				
                				if(!joueur.getEstPrison()) {
                					es.println("" + joueur.getNom() + " lance les dés... [" + pm.des.getDe1() + "][" + pm.des.getDe2() + "]... et obtient un " + lancé + " !");
                					pm.deplacerJoueur(joueur, lancé);
                					fp.deplacerPion(joueur);
                					
                					caze = pm.getCase(joueur.getPosition());
                					es.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur " + caze.getNom());
                				}
                				else {
                					es.println("Le joueur est en prison.");
                					
                					caze = pm.getCase(joueur.getPosition());
                				}
            					Thread.sleep(VITESSE_PARTIE);

                				pausePartie = true;
            					caze.fenetreAction(fp);
            					
                    			while(pausePartie && !PARTIE_AUTO){ Thread.sleep(200); }
                    			
                				caze.actionCase(joueur, pm, fp);
                    			
                				es.println("" + joueur.getNom() + " possède à la fin de son tour " + joueur.getArgent() + "€");
                				System.out.println("et les terrains suivants :\n" + joueur.listTerrains());
                			}
                			else {
                				es.println("" + pm.getJoueurActif().getNom() + " est en banqueroute, il ne joue pas.");
                			}
                			
                			Thread.sleep(200);
                			fp.deplacerPion(joueur);
                			fp.refreshLabels(pm);
                			
                			pausePartie = !joueur.getEstBanqueroute();
                			while(pausePartie && !PARTIE_AUTO){ Thread.sleep(200); }
                			
                			es.println("");
                			pm.setJoueurSuivant();
                			
                		}
                		
                		es.println("=== Fin de la partie ===");
                		es.println(" Le vainqueur est " + pm.estVainqueur().getNom() + " !");
                        
                		fp.afficherVainqueur(pm);
                		
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
		return this.pm;
	}
	
	public void reprendrePartie() {
		this.pausePartie = false;
	}

	@Override
	public String toString() {
		return "Partie [plateauM=" + pm.toString() + "]";
	}
	
}
