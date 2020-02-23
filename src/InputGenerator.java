
import java.util.Date;

public class InputGenerator extends Thread {

    private static int runSimulation(Poisson poisson) {
        int arrivedPassengers = 0;

        arrivedPassengers = poisson.next();

        return arrivedPassengers;
    }

    @Override
    public void run() {
        Date startDate = new Date();
        Constant.startTime = startDate.getTime()/1000;
        long timeDiff = 0;
        while(true)
        {
            Date date = new Date();
            long currentTime = (date.getTime()/1000);
            if(currentTime-Constant.startTime >= timeDiff) {
                Poisson poisson = new Poisson();
                int arrivedPassenger = runSimulation(poisson);
                addPassengerToQueue(arrivedPassenger,currentTime);
                timeDiff++;
            }
            if (currentTime - Constant.startTime > Constant.totalTime){
                stop();
                break;
            }
        }
    }

    private void addPassengerToQueue(int arrivedPassenger, long time) {
        while(arrivedPassenger > 0) {
            if (Constant.queueNumber == 1) {
                Constant.queue1.add(time);
                Constant.queueNumber = 2;
            } else if (Constant.queueNumber == 2) {
                Constant.queue2.add(time);
                Constant.queueNumber = 3
                ;
            } else if (Constant.queueNumber == 3) {
                Constant.queue3.add(time);
                Constant.queueNumber = 1;
            }
            arrivedPassenger--;
        }
    }
}
