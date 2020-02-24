package CaseA;

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
                //System.out.println(" stopped Passenger Input");
                stop();
                break;
            }
            SynchronizedCounter.updateTimeCounter();

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
