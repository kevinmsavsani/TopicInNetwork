import java.util.Random;

public class Poisson {

    private Random rand;

    /** Creates a variable with a given mean. */
    public Poisson() {
        rand = new Random();
    }

    public int next() {
        double L = Math.exp(-Constant.lembda);
        double p = 1.0;
        int k = 0;

        do {
            k++;
            p *= rand.nextDouble();
        } while (p > L);

        return k - 1;
    }

    private static final int SIMULATION_TIME_LENGHT = 25;
    
    private static int runSimulation(Poisson poisson) {
        int arrivedPassengers = 0;

        /*We want to check the probability of 1 customer to arrive at each
        single second. The default k value is 1, so we dont need to set it.*/
        for(int second = 0; second < SIMULATION_TIME_LENGHT; second++){
            arrivedPassengers += poisson.next();
        }

        System.out.printf(
                "\t%d passengers arrived in %d seconds\n", arrivedPassengers, SIMULATION_TIME_LENGHT);
        System.out.printf("\tArrival rate: %.2f passengers per second. Passengers interarrival time: %.2f seconds in average\n",
                Constant.lembda, 1/Constant.lembda);

        return arrivedPassengers;
    }

    public static void main(String args[]){
        int passengersArrivedInAllSimulations = 0;
        Poisson poisson = null;
        for(int i = 0; i < Constant.totalTime; i++){
            poisson = new Poisson();
            System.out.printf("Simulation number %d\n", i);
            passengersArrivedInAllSimulations += runSimulation(poisson);
        }

        double mean = passengersArrivedInAllSimulations/(double)Constant.totalTime;
        System.out.printf("\nArrived passengers average after %d simulations: %.2f\n",
                Constant.totalTime, mean);
        if(poisson != null){
            System.out.printf(
                    "%.2f passengers expected by each %d seconds of simulation with interarrival time of %.2f seconds\n",
                    Constant.lembda*SIMULATION_TIME_LENGHT, SIMULATION_TIME_LENGHT,
                    (1/Constant.lembda));
        }
    }
}