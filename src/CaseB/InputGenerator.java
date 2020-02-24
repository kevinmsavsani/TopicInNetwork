package CaseB;

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
        Constant.queue1.add(time);
    }
}
