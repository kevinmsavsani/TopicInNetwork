package CaseA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoronavirusInspection {

    private List<Server> servers; // List of Server Threads

    private void startService() {

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

        Constant.rho = Constant.lembda/(3*Constant.mu);

        double avg_response_time = 3.0 / (3 * Constant.mu - Constant.lembda);
        double avg_waiting_time = Constant.lembda /(Constant.mu * (3 * Constant.mu - Constant.lembda));
        double avg_waiting_passengers = avg_waiting_time * (Constant.lembda);
        double avg_passenger_system = avg_response_time * (Constant.lembda);
        double avg_passenger_getting_inspected = avg_passenger_system - avg_waiting_passengers;

        System.out.println("Theoritical \n ");
        System.out.println("Traffic Intensity (rho)  : "+ Constant.rho );
        System.out.println("Average number of passengers getting inspected : "+ avg_passenger_getting_inspected);
        System.out.println("Average response time for passengers in getting inspected   : "+ avg_response_time);
        System.out.println("Average time for which a passenger has to wait until getting in" +
                "spected  : "+ avg_waiting_time);
        System.out.println("Average number of passengers waiting in queue before each officer  : "+ avg_waiting_passengers);

        System.out.println(" \n ");
        System.out.println(" \n ");
        System.out.println("Total time  : "+Constant.totalTime);
        System.out.println("Total Passengers arrived  : "+ (SynchronizedCounter.getNoOfPassenger() + Constant.queue1.size() +Constant.queue2.size() + Constant.queue3.size()));
        System.out.println("Total Passengers inspected  : "+ SynchronizedCounter.getNoOfPassenger());
        System.out.println("Average number of passengers getting inspected  : "+ (double) SynchronizedCounter.getNoOfPassenger()/Constant.totalTime);
        System.out.println("Average response time for passengers in getting inspected   : "+ (double) SynchronizedCounter.getTotalResponseTime()/ SynchronizedCounter.getNoOfPassenger());
        System.out.println("Average time for which a passenger has to wait until getting in" +
                "spected  : "+ (double) SynchronizedCounter.getTotalWaitingTime()/ SynchronizedCounter.getNoOfPassenger());
        System.out.println("Average number of passengers waiting in queue before each officer  : "+ (double) SynchronizedCounter.getPassengerWaitingInQueue()/ SynchronizedCounter.getNoOfPassenger());
    }

    private CoronavirusInspection() {
        servers = new ArrayList<>();
        for (int i = 0; i < Constant.specialOfficersCount; i++) {
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
