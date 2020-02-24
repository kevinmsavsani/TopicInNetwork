package CaseB;

import java.util.Random;

public class Poisson {

    private Random rand;

    /** Creates a variable with a given mean. */
    public Poisson() {
        rand = new Random();
    }

    public int next() {
        double L = Math.exp(-1/ Constant.lembda);
        double p = 1.0;
        int k = 0;

        do {
            k++;
            p *= rand.nextDouble();
        } while (p > L);

        return k - 1;
    }
}