package CaseC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CoronavirusInspection {

    private List<Server> servers; // List of Server Threads

    private void startService() {
        Date startDate = new Date();
        Constant.startTime = startDate.getTime()/1000;

        InputGenerator inputGenerator = new InputGenerator();
        inputGenerator.start();

        for (Server server : servers)
        {
            server.start();
        }

        try {
            inputGenerator.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Server server : servers)
        {
            try {
                server.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Constant.rho = Constant.lembda/(3* Constant.mu);

        double rho = Constant.lembda / (3 * Constant.mu);
        double prob_b = (1 - rho) * Math.pow(rho, 10.0) / (1 - Math.pow(rho, 11));

        double avg_passenger_system = 3*(rho / (1-rho) - (11) * Math.pow(rho, 11) / (1 - Math.pow(rho, 11)));
        double avg_response_time = avg_passenger_system / (Constant.lembda * (1 - prob_b));
        double avg_waiting_time = avg_response_time - 1 / Constant.mu;
        double avg_waiting_passengers = avg_waiting_time * (Constant.lembda) * (1 - prob_b);

        double avg_passenger_getting_inspected = avg_passenger_system - avg_waiting_passengers;

        System.out.println("Theoritical ");
        System.out.println("Traffic Intensity (rho)  : "+ Constant.rho );
        System.out.println("Average number of passengers getting inspected : "+ avg_passenger_getting_inspected);
        System.out.println("Average response time for passengers in getting inspected   : "+ avg_response_time);
        System.out.println("Average time for which a passenger has to wait until getting in" +
                "spected  : "+ avg_waiting_time);
        System.out.println("Average number of passengers waiting in queue before each officer  : "+ avg_waiting_passengers);

        System.out.println(" \n ");
        System.out.println(" Output :\n ");
        System.out.println("Total time  : "+ Constant.totalTime);
        System.out.println("Total Passengers arrived  : "+ (SynchronizedCounter.getNoOfPassenger() + Constant.queue1.size() + Constant.queue2.size() + Constant.queue3.size()));
        System.out.println("Total Passengers inspected  : "+ SynchronizedCounter.getNoOfPassenger());
        System.out.println("Average number of passengers getting inspected  : "+ (double) SynchronizedCounter.getTotalInspectionTime()/ Constant.totalTime);
        System.out.println("Average response time for passengers in getting inspected   : "+ (double) SynchronizedCounter.getTotalResponseTime()/ SynchronizedCounter.getNoOfPassenger());
        System.out.println("Average time for which a passenger has to wait until getting in" +
                "spected  : "+ (double) SynchronizedCounter.getTotalWaitingTime()/ SynchronizedCounter.getNoOfPassenger());
        System.out.println("Average number of passengers waiting in queue before each officer  : "+ (double) SynchronizedCounter.getPassengerWaitingInQueue()/ SynchronizedCounter.getNoOfPassenger());
    }

    private CoronavirusInspection() {
        servers = new ArrayList<>();
        for (int i = 0; i < Constant.specialOfficersCount; i++) {
            PoissonServiceTime poissonServiceTime = new PoissonServiceTime();
            Server server = new Server(this, i+1);
            servers.add(server);
        }
    }

    public static void main(String[]args) throws FileNotFoundException {
        File file = new File(Constant.fileName);
        Scanner scanner = new Scanner(file);

        Constant.totalTime = scanner.nextInt();
        Constant.lembda = scanner.nextDouble();
        Constant.mu = scanner.nextDouble();

        CoronavirusInspection coronavirusInspection = new CoronavirusInspection();

        coronavirusInspection.startService();
    }
}
