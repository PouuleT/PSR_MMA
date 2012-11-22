/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package psr_mma;

/**
 *
 * @author BEE Lucas
 */

public class Stats {
	static int withoutWaitCount=0;
	static int withWaitCount = 0;
	static double duration=0.0;
	
	public static void afficheTheorique() {
		System.out.println("--------------------");
		System.out.println("RESULTATS THEORIQUES");
		System.out.println("--------------------");
		if(Ech.lambda<Ech.mu)
			System.out.println("lambda<mu : file stable ");
		else
			System.out.println("lambda>mu : file instable ");
		double ro = (double)Ech.lambda/Ech.mu;
		System.out.println("ro (lambda/mu) = "+ro);
		System.out.println("nombre de clients attendus (lambda x duree) = "+Ech.lambda*Ech.duree);
		System.out.println("Prob de service sans attente (1 - ro) = "+(1-ro)); 
		System.out.println("Prob file occupee (ro) = "+ro); 
		System.out.println("Debit (lambda) = "+Ech.lambda);
		System.out.println("Esp nb clients (ro/1-ro) = "+ro/(1-ro));
		System.out.println("Temps moyen de sejour (1/mu(1-ro)) = "+(double)1.0/Ech.mu*(1.0-ro)); 
	}
	public static void afficheSimulation() {
		System.out.println("-------------------- ");
		System.out.println("RESULTATS SIMULATION ");
		System.out.println("-------------------- ");
		System.out.println("Nombre total de clients = "+Evt.globalId);
		System.out.println("Proportion clients sans attente = "+(double)withoutWaitCount/Evt.globalId);
		System.out.println("Proportion clients avec attente = "+(double)withWaitCount/Evt.globalId); 
		System.out.println("Debit = "+(double)Evt.globalId/Ech.duree);
		System.out.println("Nb moyen de clients dans systeme = ???"); 
		System.out.println("Temps moyen de sejour = "+(double)duration/Evt.globalId);
	}
	
	public static void withoutWait() {
		withoutWaitCount++;
	}
	
	public static void withWait() {
		withWaitCount++;
	}
	
	public static void averageDuration(Evt temp){
		duration = duration + temp.dtime-temp.atime;
	}

}
