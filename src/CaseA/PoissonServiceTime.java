package CaseA;

import java.util.Random;

public class PoissonServiceTime {

    private Random rand = new Random();;

    public int next() {
//        double L = Math.exp(-1/ Constant.mu);
//        double p = 1.0;
//        int k = 0;
//
//        do {
//            k++;
//            p *= rand.nextDouble();
//        } while (p > L);
//
//        return k - 1;
        double p = rand.nextDouble();
        long inter_arrival_time = Math.round(Math.log(1-p) * (-1.0/Constant.mu));
        return (int) inter_arrival_time;
    }
}