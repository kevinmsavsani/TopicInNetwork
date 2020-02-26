package CaseC;

import java.util.Random;

public class PoissonServiceTime {

    private Random rand = new Random();;

    public int next() {
        double L = Math.exp(-1/ Constant.mu);
        double p = 1.0;
        int k = 0;

        do {
            k++;
            p *= rand.nextDouble();
        } while (p > L);

        return k - 1;
    }
}