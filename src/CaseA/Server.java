package CaseA;

public class Server extends Thread {
    private int queueNum;

    Server(int name){
        setName(String.valueOf(name));
        this.queueNum = name;
    }

    @Override
    public void run(){

        PoissonServiceTime poissonServiceTime = new PoissonServiceTime();

        long currentTime = SynchronizedCounter.getTimeCounterValue();
        int waitTime = poissonServiceTime.next();

        //System.out.println(currentTime+"    "+waitTime +"  "+this.queueNum);
        if (this.queueNum == 1 && SynchronizedCounter.getTimeCounterValue() == SynchronizedCounter.getServer1TimeCounterValue() && Constant.queue1.size() > 0) {
            SynchronizedCounter.incrementNoOfPassenger();
            long passengerStartTime = (long) Constant.queue1.remove();
            SynchronizedCounter.incrementTotalResponseTime(currentTime + waitTime - passengerStartTime);
            SynchronizedCounter.incrementTotalWaitingTime(currentTime - passengerStartTime);
            SynchronizedCounter.incrementPassengerWaitingInQueue(Constant.queue1.size());
            SynchronizedCounter.incrementServer1TimeCounter(waitTime);
        } else if (this.queueNum == 2 && SynchronizedCounter.getTimeCounterValue() == SynchronizedCounter.getServer2TimeCounterValue() && Constant.queue2.size() > 0) {
            SynchronizedCounter.incrementNoOfPassenger();
            long passengerStartTime = (long) Constant.queue2.remove();
            SynchronizedCounter.incrementTotalResponseTime(currentTime + waitTime - passengerStartTime);
            SynchronizedCounter.incrementTotalWaitingTime(currentTime - passengerStartTime);
            SynchronizedCounter.incrementPassengerWaitingInQueue(Constant.queue2.size());
            SynchronizedCounter.incrementServer2TimeCounter(waitTime);
        } else if (this.queueNum == 3 && SynchronizedCounter.getTimeCounterValue() == SynchronizedCounter.getServer3TimeCounterValue() && Constant.queue3.size() > 0) {
            SynchronizedCounter.incrementNoOfPassenger();
            long passengerStartTime = (long) Constant.queue3.remove();
            SynchronizedCounter.incrementTotalResponseTime(currentTime + waitTime - passengerStartTime);
            SynchronizedCounter.incrementTotalWaitingTime(currentTime - passengerStartTime);
            SynchronizedCounter.incrementPassengerWaitingInQueue(Constant.queue3.size());
            SynchronizedCounter.incrementServer3TimeCounter(waitTime);
        }
    }
}
