
import java.util.Date;

public class InputGenerator extends Thread {


    @Override
    public void run() {

        Poisson poisson = new Poisson();
        long prevTime = Constant.startTime;
        long nextPassengerTime = 0;
        while(true)
        {
            Date date = new Date();
            long currentTime = (date.getTime()/1000);
            if(currentTime-prevTime >= nextPassengerTime) {
                nextPassengerTime = poisson.next();
                addPassengerToQueue(currentTime);
                prevTime = currentTime;
            }
            if (currentTime - Constant.startTime > Constant.totalTime){
                System.out.println(" stopped Passenger Input");
                stop();
                break;
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void addPassengerToQueue(long time) {
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
    }
}
