package CaseA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class CoronavirusInspection {

    private List<Server> servers; // List of Setup Threads

    private void startService() {

        while (SynchronizedCounter.getTimeCounterValue() <= Constant.totalTime) {
            SynchronizedCounter.updateTimeCounter();

            InputGenerator inputGenerator = new InputGenerator();
            inputGenerator.start();

            Server server1 = new Server(1);
            Server server2 = new Server(2);
            Server server3 = new Server(3);
            server1.start();
            server2.start();
            server3.start();


            try {
                inputGenerator.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                server1.join();
                server2.join();
                server3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Constant.rho = Constant.lembda/(3*Constant.mu);

        System.out.println("Theoritical \n ");
        System.out.println("Traffic Intensity (rho)  : "+ Constant.rho );
        System.out.println("Average number of passengers getting inspected (mean no. of jobs in system) : "+ Constant.rho/(1-Constant.rho));
        System.out.println("Average response time for passengers in getting inspected (mean response time)   : "+ (1/(Constant.mu - (Constant.lembda/3))));
        System.out.println("Average time for which a passenger has to wait until getting in" +
                "spected (mean waiting time)  : "+ (Constant.rho/(Constant.mu*(1-Constant.rho))));
        System.out.println("Average number of passengers waiting in queue before each officer  : "+ (Constant.rho*Constant.rho)/(1-Constant.rho));

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
