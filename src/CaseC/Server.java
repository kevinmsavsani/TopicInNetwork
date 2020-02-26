package CaseC;

public class Server extends Thread {
    private CoronavirusInspection coronavirusInspection;
    private int queueNum;

    Server(CoronavirusInspection coronavirusInspection, int name){
        setName(String.valueOf(name));
        this.queueNum = name;
        this.coronavirusInspection = coronavirusInspection;
    }

    @Override
    public void run(){
        PoissonServiceTime poissonServiceTime = new PoissonServiceTime();
        while(true) {
            while (true) {
                if (this.queueNum == 1 && Constant.queue1.size() > 0) {
                    break;
                } else if(this.queueNum == 1 ){
                    SynchronizedCounter.incrementServer1TimeCounter(SynchronizedCounter.getInputTimeCounterValue()-SynchronizedCounter.getServer1TimeCounterValue());
                }

                if (this.queueNum == 2 && Constant.queue2.size() > 0) {
                    break;
                }  else  if(this.queueNum == 2 ) {
                    SynchronizedCounter.incrementServer2TimeCounter(SynchronizedCounter.getInputTimeCounterValue()-SynchronizedCounter.getServer2TimeCounterValue());
                }

                if (this.queueNum == 3 && Constant.queue3.size() > 0) {
                    break;
                } else  if(this.queueNum == 3 ){
                    SynchronizedCounter.incrementServer3TimeCounter(SynchronizedCounter.getInputTimeCounterValue()-SynchronizedCounter.getServer3TimeCounterValue());
                }

                if (SynchronizedCounter.getTimeCounterValue() >= Constant.totalTime) {

                    //System.out.println(" stopped " + getName());
                    stop();
                    break;
                }
                SynchronizedCounter.updateTimeCounter();
            }

            long currentTime = SynchronizedCounter.getTimeCounterValue();
            int waitTime = poissonServiceTime.next();

            //System.out.println(currentTime+"    "+waitTime +"  "+this.queueNum);

            if (SynchronizedCounter.getTimeCounterValue() >= Constant.totalTime) {

                //System.out.println(" stopped " + getName());
                stop();
                break;
            }

            if (this.queueNum == 1 && Constant.queue1.size() > 0) {
                SynchronizedCounter.incrementServer1TimeCounter(waitTime);
            } else if (this.queueNum == 2 && Constant.queue2.size() > 0) {
                SynchronizedCounter.incrementServer2TimeCounter(waitTime);
            } else if (this.queueNum == 3 && Constant.queue3.size() > 0) {
                SynchronizedCounter.incrementServer3TimeCounter(waitTime);
            }
            SynchronizedCounter.updateTimeCounter();

            while (true) {
                if (this.queueNum == 1 && SynchronizedCounter.getTimeCounterValue() >= SynchronizedCounter.getServer1TimeCounterValue()) {
                    SynchronizedCounter.incrementNoOfPassenger();
                    long passengerStartTime = (long) Constant.queue1.remove();
                    SynchronizedCounter.incrementTotalResponseTime(currentTime + waitTime - passengerStartTime);
                    SynchronizedCounter.incrementTotalWaitingTime(currentTime - passengerStartTime);
                    SynchronizedCounter.incrementPassengerWaitingInQueue(Constant.queue1.size());
                    break;

                } else if (this.queueNum == 2 && SynchronizedCounter.getTimeCounterValue() >= SynchronizedCounter.getServer2TimeCounterValue()) {
                    SynchronizedCounter.incrementNoOfPassenger();
                    long passengerStartTime = (long) Constant.queue2.remove();
                    SynchronizedCounter.incrementTotalResponseTime(currentTime + waitTime - passengerStartTime);
                    SynchronizedCounter.incrementTotalWaitingTime(currentTime - passengerStartTime);
                    SynchronizedCounter.incrementPassengerWaitingInQueue(Constant.queue2.size());
                    break;
                } else if (this.queueNum == 3 && SynchronizedCounter.getTimeCounterValue() >= SynchronizedCounter.getServer3TimeCounterValue()) {
                    SynchronizedCounter.incrementNoOfPassenger();
                    long passengerStartTime = (long) Constant.queue3.remove();
                    SynchronizedCounter.incrementTotalResponseTime(currentTime + waitTime - passengerStartTime);
                    SynchronizedCounter.incrementTotalWaitingTime(currentTime - passengerStartTime);
                    SynchronizedCounter.incrementPassengerWaitingInQueue(Constant.queue3.size());
                    break;
                }

                if (SynchronizedCounter.getTimeCounterValue() >= Constant.totalTime) {

                    //System.out.println(" stopped " + getName());
                    stop();
                    break;
                }
            }
        }
    }
}
