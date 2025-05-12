package StepDefs;

import org.testng.TestNG;

import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainScheduler {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        Runnable task = () -> {
            System.out.println("Starting test execution at " + java.time.LocalDateTime.now());

            TestNG testng = new TestNG();
            testng.setTestClasses(new Class[]{RunCukesTest.class});
            testng.run();

            System.out.println("Test execution completed at " + java.time.LocalDateTime.now());
        };

        // Schedule the task to run every 1 hour, with no initial delay
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.HOURS);
    }
}
