
package psr_mma;

/**
 *
 * @author BEE Lucas
 */

public class Utile {
    public static double newDate(double oldDate, int psy){
        return oldDate + ((-1)*Math.log(1-Math.random()))/psy;
    }
}
