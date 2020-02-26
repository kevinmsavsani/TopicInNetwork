package CaseC;

import java.util.Random;

public class PoissonServiceTime {

    private Random rand = new Random();;

    public int next() {
        double p = rand.nextDouble();
        long inter_arrival_time = Math.round(Math.log(1-p) * (-1.0/Constant.mu));
        return (int) inter_arrival_time;
    }
}