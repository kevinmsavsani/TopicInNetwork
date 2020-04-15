package CaseC;

import java.util.Random;

public class InputGenerator extends Thread {


    @Override
    public void run() {

        Poisson poisson = new Poisson();
        long nextPassengerTime = 0;
        while(true)
        {
            if(SynchronizedCounter.getInputTimeCounterValue() <= SynchronizedCounter.getTimeCounterValue()) {
                nextPassengerTime = poisson.next();
                addPassengerToQueue(SynchronizedCounter.getInputTimeCounterValue());
                SynchronizedCounter.incrementInputTimeCounter(nextPassengerTime);
                SynchronizedCounter.updateTimeCounter();
            }
            if (SynchronizedCounter.getTimeCounterValue()  >= Constant.totalTime){
                stop();
                break;
            }
            SynchronizedCounter.updateTimeCounter();

        }
    }

    private void addPassengerToQueue(long time) {
        Random random = new Random();
        int randomInteger = random.nextInt(3);
        if (randomInteger == 0 && Constant.queue1.size() < 10) {
            Constant.queue1.add(time);
        } else if (randomInteger == 1 && Constant.queue2.size() < 10) {
            Constant.queue2.add(time);
        } else if (randomInteger == 2 && Constant.queue3.size() < 10) {
            Constant.queue3.add(time);
        }
    }
}
