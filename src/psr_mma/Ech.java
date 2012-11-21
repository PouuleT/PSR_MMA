/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package psr_mma;

/**
 *
 * @author BEE Lucas
 */


import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;


public class Ech {
	static int lambda=5;
	static int mu=6;
	
    public static LinkedList<Evt> newEch() {
        LinkedList<Evt> l = new LinkedList();
        
        return l;
    }
    
    public void addNewArrivant(LinkedList<Evt> MMA) {
    	Evt.lastLanded =  Utile.newDate(Evt.lastLanded,lambda);
    	addAtTheGoodPlace(MMA, new Evt(false, Evt.lastLanded));
    }
    
    public void addAtTheGoodPlace(LinkedList<Evt> MMA, Evt temp) {
    	Iterator<Evt> pwet = MMA.iterator();
    	int i=0;
    	
    	while(pwet.hasNext() && (pwet.next().getDate() < temp.getDate())){
    		i++;
    	}
    	MMA.add(i, temp);
    }
    
    public static void main(String[] args) {
        int i=0;
        
        double oldDate = 0.0;

        LinkedList<Evt> MMA = Ech.newEch();                        // On initialise la liste chainée
        Evt temp = new Evt(false, 0.0);
        temp.toString();
        MMA.add(temp);                                  // On rajoute la première arrivée à 0 secondes

        while(i<5) {                                    // On fait la boucle pour 5 clients qui arrivent
               // On calcule la nouvelle date d'arrivée
            temp = new Evt(false,oldDate);
            temp.toString();            // On affiche le dernier élément de la liste chainée
            MMA.add(temp);              // On rajoute le nouvel évènement d'arrivée
               
            i++;
        }
        
        //MMA.toString();               // Pour afficher toute la liste chainée
        System.out.println("Fin de la boucle");
        while((Evt.lastGone < 100)||(!MMA.isEmpty())) {
        	if((MMA.getFirst().what)&&(Evt.lastGone < 100)) {
        		// créer une arrivée
        		MMA.addNewArrivant();
        	}
        	else {
        		//traiter premier élément
        		if(!MMA.getFirst().what){
        			MMA.firstPassThrough();
        			//Arrivée transformée en départ
        		}
        		else{
        			MMA.firstIsGone();
        			//Départ qui quitte la liste
        		}
        	}
        }
        
    }
    

    
    

}
