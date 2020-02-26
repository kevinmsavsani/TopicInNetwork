package CaseC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CoronavirusInspection {

    private List<Server> servers; // List of Setup Threads

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

        System.out.println("Theoritical \n ");
        System.out.println("Traffic Intensity (rho)  : "+ Constant.rho );
        System.out.println("Average number of passengers getting inspected (mean no. of jobs in system) : "+ Constant.rho/(1- Constant.rho));
        System.out.println("Average response time for passengers in getting inspected (mean response time)   : "+ (1/(Constant.mu - (Constant.lembda/3))));
        System.out.println("Average time for which a passenger has to wait until getting in" +
                "spected (mean waiting time)  : "+ (Constant.rho/(Constant.mu*(1- Constant.rho))));
        System.out.println("Average number of passengers waiting in queue before each officer  : "+ (Constant.rho* Constant.rho)/(1- Constant.rho));

        System.out.println(" \n ");
        System.out.println(" \n ");
        System.out.println("Total time  : "+ Constant.totalTime);
        System.out.println("Total Passengers arrived  : "+ (SynchronizedCounter.getNoOfPassenger() + Constant.queue1.size() + Constant.queue2.size() + Constant.queue3.size()));
        System.out.println("Total Passengers inspected  : "+ SynchronizedCounter.getNoOfPassenger());
        System.out.println("Average number of passengers getting inspected  : "+ (double) SynchronizedCounter.getNoOfPassenger()/ Constant.totalTime);
        System.out.println("Average response time for passengers in getting inspected   : "+ (double) SynchronizedCounter.getTotalResponseTime()/ SynchronizedCounter.getNoOfPassenger());
        System.out.println("Average time for which a passenger has to wait until getting in" +
                "spected  : "+ (double) SynchronizedCounter.getTotalWaitingTime()/ SynchronizedCounter.getNoOfPassenger());
        System.out.println("Average number of passengers waiting in queue before each officer  : "+ (double) SynchronizedCounter.getPassengerWaitingInQueue()/ SynchronizedCounter.getNoOfPassenger());
    }

    private CoronavirusInspection() {
        servers = new ArrayList<>();
        for (int i = 0; i < Constant.specialOfficersCount; i++) {
            PoissonServiceTime poissonServiceTime = new PoissonServiceTime();
            Server server = new Server(this, i+1,poissonServiceTime);
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
