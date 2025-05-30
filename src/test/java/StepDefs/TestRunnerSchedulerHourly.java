package StepDefs;

import org.testng.TestNG;

import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class TestRunnerSchedulerHourly {

    public static void main(String[] args) {
        // Create a scheduled executor with one thread
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Define the task to run TestNG suite
        Runnable task = () -> {
            System.out.println("Running TestNG suite at: " + java.time.LocalDateTime.now());

            TestNG testng = new TestNG();
            testng.setTestSuites(Collections.singletonList("testng.xml"));
            testng.run();
        };
        // Schedule the task to run every hour with initial delay of 0
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.HOURS);
    }
}