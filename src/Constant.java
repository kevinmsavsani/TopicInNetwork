import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Constant {
    public static String fileName = "src/input.txt";

    public static long startTime;

    public static int specialOfficersCount = 3;
    public static int totalTime;
    public static double lembda;
    public static double mu;
    public static double rho;


    public static BlockingQueue queue1 = new ArrayBlockingQueue(100000);
    public static BlockingQueue queue2 = new ArrayBlockingQueue(100000);
    public static BlockingQueue queue3 = new ArrayBlockingQueue(100000);

    public static int queueNumber = 1;

}
