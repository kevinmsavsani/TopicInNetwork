package CaseC;

import java.util.Random;

public class Poisson {

    private Random rand;

    /** Creates a variable with a given mean. */
    public Poisson() {
        rand = new Random();
    }

    public int next() {
        double p = rand.nextDouble();
        long inter_arrival_time = Math.round(Math.log(1-p) * (-1.0/Constant.lembda));
        return (int) inter_arrival_time;
    }
}