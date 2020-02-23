import java.util.concurrent.atomic.AtomicLong;

public class SynchronizedCounter {

    /************ All shared variables declared as atomic variables **********/
    private static final AtomicLong noOfPassenger = new AtomicLong(0);
    private static final AtomicLong totalResponseTime = new AtomicLong(0);
    private static final AtomicLong totalWaitingTime = new AtomicLong(0);
    private static final AtomicLong passengerWaitingInQueue = new AtomicLong(0);


    public static long getNoOfPassenger() {
        return noOfPassenger.get();
    }

    public static void incrementNoOfPassenger() {
        while(true) {
            long existingValue = getNoOfPassenger();
            long newValue = existingValue + 1;
            if(noOfPassenger.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    public static long getTotalResponseTime() {
        return totalResponseTime.get();
    }

    public static void incrementTotalResponseTime(long passengerTime) {
        while(true) {
            long existingValue = getTotalResponseTime();
            long newValue = existingValue + passengerTime;
            if(totalResponseTime.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }


    public static long getTotalWaitingTime() {
        return totalWaitingTime.get();
    }

    public static void incrementTotalWaitingTime(long passengerTime) {
        while(true) {
            long existingValue = getTotalWaitingTime();
            long newValue = existingValue + passengerTime;

            if(totalWaitingTime.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }


    public static long getPassengerWaitingInQueue() {
        return passengerWaitingInQueue.get();
    }

    public static void incrementPassengerWaitingInQueue(int size) {
        while(true) {
            long existingValue = getPassengerWaitingInQueue();
            long newValue = existingValue + size;

            if(passengerWaitingInQueue.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

}