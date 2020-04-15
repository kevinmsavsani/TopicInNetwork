package CaseC;

import java.util.concurrent.atomic.AtomicLong;

public class SynchronizedCounter {

    /************ All shared variables declared as atomic variables **********/
    private static final AtomicLong noOfPassenger = new AtomicLong(0);
    private static final AtomicLong totalResponseTime = new AtomicLong(0);
    private static final AtomicLong totalWaitingTime = new AtomicLong(0);
    private static final AtomicLong passengerWaitingInQueue = new AtomicLong(0);

    private static final AtomicLong timeCounter = new AtomicLong(0);
    private static final AtomicLong server1TimeCounter = new AtomicLong(0);
    private static final AtomicLong server2TimeCounter = new AtomicLong(0);
    private static final AtomicLong server3TimeCounter = new AtomicLong(0);
    private static final AtomicLong inputTimeCounter = new AtomicLong(0);
    private static final AtomicLong totalInspectionTime = new AtomicLong(0);


    public static long getTimeCounterValue() {
        return timeCounter.get();
    }

    public static void updateTimeCounter() {
        while(true) {
            long existingValue = getTimeCounterValue();
            long newValue = getInputTimeCounterValue();
            if (Constant.queue1.size() > 0){
                newValue = Math.min(getServer1TimeCounterValue(),getInputTimeCounterValue());
            }
            long newValue1 = newValue;
            if (Constant.queue2.size() > 0){
                newValue1 = Math.min(getServer2TimeCounterValue(),newValue);
            }

            long newValue2 = newValue1;
            if (Constant.queue3.size() > 0){
                newValue2 = Math.min(getServer3TimeCounterValue(),newValue1);
            }

            if(timeCounter.compareAndSet(existingValue, newValue2)) {
                return;
            }
        }
    }

    public static long getServer1TimeCounterValue() {
        return server1TimeCounter.get();
    }

    public static void incrementServer1TimeCounter(long waitingTime) {
        while(true) {
            long existingValue = getServer1TimeCounterValue();
            long newValue = existingValue + waitingTime;

            if(server1TimeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    public static long getServer2TimeCounterValue() {
        return server2TimeCounter.get();
    }

    public static void incrementServer2TimeCounter(long waitingTime) {
        while(true) {
            long existingValue = getServer2TimeCounterValue();
            long newValue = existingValue + waitingTime;

            if(server2TimeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    public static long getServer3TimeCounterValue() {
        return server3TimeCounter.get();
    }

    public static void incrementServer3TimeCounter(long waitingTime) {
        while(true) {
            long existingValue = getServer3TimeCounterValue();
            long newValue = existingValue + waitingTime;

            if(server3TimeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    public static long getInputTimeCounterValue() {
        return inputTimeCounter.get();
    }

    public static void incrementInputTimeCounter(long waitingTime) {
        while(true) {
            long existingValue = getInputTimeCounterValue();
            long newValue = existingValue + waitingTime;

            if(inputTimeCounter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

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

    public static long getTotalInspectionTime() {
        return totalInspectionTime.get();
    }

    public static void incrementTotalInspectionTime(long passengerTime) {
        while(true) {
            long existingValue = getTotalInspectionTime();
            long newValue = existingValue + passengerTime;
            if(totalInspectionTime.compareAndSet(existingValue, newValue)) {
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

    public static void incrementPassengerWaitingInQueue(long size) {
        while(true) {
            long existingValue = getPassengerWaitingInQueue();
            long newValue = existingValue + size;

            if(passengerWaitingInQueue.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

}