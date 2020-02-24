package CaseB;

public class Server extends Thread {
    private CoronavirusInspection coronavirusInspection;
    private PoissonServiceTime poissonServiceTime;
    private int queueNum;

    Server(CoronavirusInspection coronavirusInspection, int name, PoissonServiceTime poissonServiceTime){
        setName(String.valueOf(name));
        this.queueNum = name;
        this.coronavirusInspection = coronavirusInspection;
        this.poissonServiceTime = poissonServiceTime;
    }

    @Override
    public void run(){

        while(true) {
            long passengerStartTime = 0;
            while (true) {
                synchronized (Constant.queue1) {
                    if (this.queueNum == 1 && Constant.queue1.size() > 0) {
                        passengerStartTime = (long) Constant.queue1.remove();
                        break;
                    } else {
                        SynchronizedCounter.incrementServer1TimeCounter(SynchronizedCounter.getTimeCounterValue());
                    }

                    if (this.queueNum == 2 && Constant.queue1.size() > 0) {
                        passengerStartTime = (long) Constant.queue1.remove();
                        break;
                    } else {
                        SynchronizedCounter.incrementServer2TimeCounter(SynchronizedCounter.getTimeCounterValue());
                    }

                    if (this.queueNum == 3 && Constant.queue1.size() > 0) {
                        passengerStartTime = (long) Constant.queue1.remove();
                        break;
                    } else {
                        SynchronizedCounter.incrementServer3TimeCounter(SynchronizedCounter.getTimeCounterValue());
                    }
                }
                if (SynchronizedCounter.getTimeCounterValue() >= Constant.totalTime) {

                    //System.out.println(" stopped " + getName());
                    stop();
                    break;
                }
            }

            long currentTime = SynchronizedCounter.getTimeCounterValue();
            int waitTime = poissonServiceTime.next();

            if (SynchronizedCounter.getTimeCounterValue() >= Constant.totalTime) {

                //System.out.println(" stopped " + getName());
                stop();
                break;
            }

            if (this.queueNum == 1) {
                SynchronizedCounter.incrementServer1TimeCounter(waitTime);
            } else if (this.queueNum == 2) {
                SynchronizedCounter.incrementServer2TimeCounter(waitTime);
            } else if (this.queueNum == 3) {
                SynchronizedCounter.incrementServer3TimeCounter(waitTime);
            }
            SynchronizedCounter.updateTimeCounter();

            while (true) {
                if (this.queueNum == 1 && SynchronizedCounter.getTimeCounterValue() >= SynchronizedCounter.getServer1TimeCounterValue()) {
                    SynchronizedCounter.incrementNoOfPassenger();
                    SynchronizedCounter.incrementTotalResponseTime(currentTime + waitTime - passengerStartTime);
                    SynchronizedCounter.incrementTotalWaitingTime(currentTime - passengerStartTime);
                    SynchronizedCounter.incrementPassengerWaitingInQueue(Constant.queue1.size());
                    break;

                } else if (this.queueNum == 2 && SynchronizedCounter.getTimeCounterValue() >= SynchronizedCounter.getServer2TimeCounterValue()) {
                    SynchronizedCounter.incrementNoOfPassenger();
                    SynchronizedCounter.incrementTotalResponseTime(currentTime + waitTime - passengerStartTime);
                    SynchronizedCounter.incrementTotalWaitingTime(currentTime - passengerStartTime);
                    SynchronizedCounter.incrementPassengerWaitingInQueue(Constant.queue1.size());
                    break;
                } else if (this.queueNum == 3 && SynchronizedCounter.getTimeCounterValue() >= SynchronizedCounter.getServer3TimeCounterValue()) {
                    SynchronizedCounter.incrementNoOfPassenger();
                    SynchronizedCounter.incrementTotalResponseTime(currentTime + waitTime - passengerStartTime);
                    SynchronizedCounter.incrementTotalWaitingTime(currentTime - passengerStartTime);
                    SynchronizedCounter.incrementPassengerWaitingInQueue(Constant.queue1.size());
                    break;
                }
                SynchronizedCounter.updateTimeCounter();

                if (SynchronizedCounter.getTimeCounterValue() >= Constant.totalTime) {

                    //System.out.println(" stopped " + getName());
                    stop();
                    break;
                }
            }
        }
    }
}
