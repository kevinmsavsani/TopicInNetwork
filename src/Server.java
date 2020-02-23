import java.util.Date;

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

        while(true)
        {
            while(true){
                if(this.queueNum == 1 && Constant.queue1.size()>0){
                    break;
                }
                else if(this.queueNum == 2 && Constant.queue2.size()>0){
                    break;
                }
                else if(this.queueNum == 3 && Constant.queue3.size()>0){
                    break;
                }
            }

            Date date = new Date();
            long currentTime = (date.getTime()/1000);

            int waitTime = poissonServiceTime.next();

            if (currentTime+waitTime - Constant.startTime > Constant.totalTime){
                stop();
                break;
            }

            long time = (date.getTime()/1000);
            while (currentTime + waitTime > time){
                Date checkDate = new Date();
                time = (checkDate.getTime()/1000);
            }

            if(this.queueNum == 1){
                SynchronizedCounter.incrementNoOfPassenger();
                long passengerTime = (long) Constant.queue1.remove();
                SynchronizedCounter.incrementTotalResponseTime(currentTime+waitTime-passengerTime);
                SynchronizedCounter.incrementTotalWaitingTime(currentTime-passengerTime);
                SynchronizedCounter.incrementPassengerWaitingInQueue(Constant.queue1.size());

            }
            else if(this.queueNum == 2){
                SynchronizedCounter.incrementNoOfPassenger();
                long passengerTime = (long) Constant.queue2.remove();
                SynchronizedCounter.incrementTotalResponseTime(currentTime+waitTime-passengerTime);
                SynchronizedCounter.incrementTotalWaitingTime(currentTime-passengerTime);
                SynchronizedCounter.incrementPassengerWaitingInQueue(Constant.queue2.size());
            }
            else if(this.queueNum == 3){
                SynchronizedCounter.incrementNoOfPassenger();
                long passengerTime = (long) Constant.queue3.remove();
                SynchronizedCounter.incrementTotalResponseTime(currentTime+waitTime-passengerTime);
                SynchronizedCounter.incrementTotalWaitingTime(currentTime-passengerTime);
                SynchronizedCounter.incrementPassengerWaitingInQueue(Constant.queue3.size());
            }
        }
    }
}
