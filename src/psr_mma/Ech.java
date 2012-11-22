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
import java.util.Iterator;


public class Ech {
	static int lambda=5;
	static int mu=6;
	static int duree=1000;
	
    public static LinkedList<Evt> newEch() {
        LinkedList<Evt> l = new LinkedList<Evt>();
        
        return l;
    }
    
    public static void addNewArrivant(LinkedList<Evt> MMA) {
    	//System.out.println("Un nouveau paquet arrive");
    	Evt.lastLanded =  Utile.newDate(Evt.lastLanded,lambda);
    	
/*    	if(!MMA.isEmpty())
    		Stats.withoutWait();
    	else
    		Stats.withWait();
  */  	
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

    	MMA.removeFirst();

    	temp.toString();
    	if(Math.max(Evt.lastGone,temp.atime)==temp.atime)
    		Stats.withoutWait();
    	else
    		Stats.withWait();
    	temp.dtime = Utile.newDate(Math.max(Evt.lastGone,temp.atime), Ech.mu);	// On calcule la nouvelle date de départ
    	Evt.lastGone = temp.dtime;							// On met à jour la date du dernier départ
    	temp.what = true;									// On transforme l'arrivée en départ
    	
    	addAtTheGoodPlace(MMA, temp);						// On replace le paquet au bon endroit
    	
    }
    
    public static void test() {
    	LinkedList<Evt> MMA = Ech.newEch();
    	
    	MMA.add(new Evt(false, 0.0));

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
    	Stats.averageDuration(MMA.getFirst());
    	MMA.removeFirst();
    }
    
    public static void traiteFirst(LinkedList<Evt> MMA) {

		//traiter premier élément
		if(!MMA.getFirst().what){	// Si c'est une arrivée
			firstPassThrough(MMA);	// On l'a transforme en départ
		}
		else{						// Si c'est un départ
			firstIsGone(MMA);		// On l'enlève de la liste chainée
		}
    }
    
    public static void main(String[] args) {

        LinkedList<Evt> MMA = Ech.newEch();                        // On initialise la liste chainée
        
        while((Evt.lastLanded < duree)||(!MMA.isEmpty())) {
        	if(MMA.isEmpty()) {
        		
        		addNewArrivant(MMA);
        	}
        	else if((MMA.getFirst().what)&&(Evt.lastLanded < duree)) {
        		
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
        Stats.afficheTheorique();
        Stats.afficheSimulation();
    }
    

    
    

}
