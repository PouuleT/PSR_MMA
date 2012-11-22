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
	static int lambda=6;
	static int mu=5;
	
    public static LinkedList<Evt> newEch() {
        LinkedList<Evt> l = new LinkedList();
        
        return l;
    }
    
    public static void addNewArrivant(LinkedList<Evt> MMA) {
    	//System.out.println("Un nouveau paquet arrive");
    	Evt.lastLanded =  Utile.newDate(Evt.lastLanded,lambda);
    	
    	while(!MMA.isEmpty() && MMA.getFirst().what && MMA.getFirst().getDate() < Evt.lastLanded)
    		firstIsGone(MMA);
    	
    	addAtTheGoodPlace(MMA, new Evt(false, Evt.lastLanded));
    }
    
    public static void addAtTheGoodPlace(LinkedList<Evt> MMA, Evt temp) {
    	Iterator<Evt> pwet = MMA.iterator();
    	int i=0;
    	
    	while(pwet.hasNext() && (pwet.next().getDate() < temp.getDate())){
    		i++;
    	}
    	MMA.add(i, temp);
    }
    
    public static void firstPassThrough(LinkedList<Evt> MMA){
    	//System.out.println("On fait passer le paquet");
    	Evt temp = MMA.getFirst();
    	//System.out.println("Avant la suppression");
    	//MMA.toString();
    	MMA.removeFirst();
    	//System.out.println("Après la suppression");
    	//MMA.toString();
    	//MMA.
    	//System.out.print("1 - ");
    	temp.toString();
    	
    	temp.dtime = Utile.newDate(Math.max(Evt.lastGone,temp.atime), Ech.mu);	// On calcule la nouvelle date de départ
    	Evt.lastGone = temp.dtime;							// On met à jour la date du dernier départ
    	temp.what = true;									// On transforme l'arrivée en départ
    	
    	addAtTheGoodPlace(MMA, temp);						// On replace le paquet au bon endroit
    	
    }
    
    public static void test() {
    	LinkedList<Evt> MMA = Ech.newEch();
    	
    	MMA.add(new Evt(false, 0.0));
    	//MMA.add(new Evt(false, 1.0));
    	//MMA.add(new Evt(false, 2.0));
    	//addAtTheGoodPlace(MMA, new Evt(false, 1.3));
    	
    	
    	MMA.toString();
    	firstPassThrough(MMA);
    	MMA.toString();
    	firstIsGone(MMA);
    	MMA.toString();
    	System.out.close();
    }
    
    public static void firstIsGone(LinkedList<Evt> MMA){
    	//System.out.println("Le paquet est passé");
    	MMA.getFirst().toString();
    	MMA.removeFirst();
    }
    
    public static void traiteFirst(LinkedList<Evt> MMA) {

		//traiter premier élément
		if(!MMA.getFirst().what){	// Si c'est une arrivée
			//System.out.println("cas 2.1");
			firstPassThrough(MMA);	// On l'a transforme en départ
		}
		else{						// Si c'est un départ
			//System.out.println("cas 2.2");
			firstIsGone(MMA);		// On l'enlève de la liste chainée
		}
    }
    
    public static void main(String[] args) {
        int i=0;
        
        double oldDate = 0.0;

        //test();
        
        LinkedList<Evt> MMA = Ech.newEch();                        // On initialise la liste chainée
        Evt temp = new Evt(false, 0.0);
        
        //MMA.toString();               // Pour afficher toute la liste chainée
        
        while((Evt.lastLanded < 1000)||(!MMA.isEmpty())) {
        	if(MMA.isEmpty()) {
        		//System.out.println("cas 0");
        		addNewArrivant(MMA);
        	}
        	else if((MMA.getFirst().what)&&(Evt.lastLanded < 1000)) {
        		// créer une arrivée
        		//System.out.println("cas 1");
        		addNewArrivant(MMA);
        	}
        	else {
        		traiteFirst(MMA);
        	}
        /*	System.out.println("Nouvelle boucle");
        	MMA.toString();
        	try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
        }
        
    }
    

    
    

}
