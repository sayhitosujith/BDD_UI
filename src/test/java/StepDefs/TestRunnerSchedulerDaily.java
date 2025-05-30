package StepDefs;

import org.testng.TestNG;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class TestRunnerSchedulerDaily {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Desired run time: 9:00 AM
        LocalTime targetTime = LocalTime.of(8, 30);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstRun = now.with(targetTime);

        // If the time has already passed today, schedule for tomorrow
        if (now.isAfter(firstRun)) {
            firstRun = firstRun.plusDays(1);
        }

        long initialDelay = Duration.between(now, firstRun).toMillis();

        Runnable task = () -> {
            System.out.println("Running TestNG suite at: " + java.time.LocalDateTime.now());

            TestNG testng = new TestNG();
            testng.setTestSuites(Collections.singletonList("testng.xml"));
            testng.run();
        };

        // Schedule task to run daily at specified time
        scheduler.scheduleAtFixedRate(task, initialDelay, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);
    }
}
