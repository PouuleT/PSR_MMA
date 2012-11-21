/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package psr_mma;

import java.util.List;

/**
 *
 * @author BEE Lucas
 */

public class Evt {

    boolean what; // What = 0 => Arrivée ; What = 1 => Départ
    int id;
    double atime;
    double dtime;
    static int globalId=0;
	static double lastGone=0.0;
	static double lastLanded=0.0;

    @Override
    public String toString(){
        String txt;
        if(this.what == false)
            txt= "Date = "+this.atime+"\tArrivee\tclient #"+this.id;
        else
            txt="Date = "+this.dtime+"\tDepart\tclient #"+this.id+"arrive a t="+this.atime;

        System.out.println(txt);
        return txt;
    }
    
    public double getDate() {
    	if(this.what)
    		return this.dtime;
    	else
    		return this.atime;
    }

    public Evt(boolean what, double atime) {
        globalId++;
        this.atime=atime;
        this.dtime=dtime;
        this.id=globalId;
        this.what=what;
    }
    

}
