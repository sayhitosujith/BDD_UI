package StepDefs;

import org.testng.TestNG;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class TestRunnerSchedulerDaily {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Desired run time: 8:30 AM
        LocalTime targetTime = LocalTime.of(8, 30);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstRun = now.with(targetTime);

        // If the time has already passed today, schedule for tomorrow
        if (now.isAfter(firstRun)) {
            firstRun = firstRun.plusDays(1);
        }

        long initialDelay = Duration.between(now, firstRun).toMillis();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("First run scheduled at: " + firstRun.format(formatter));

        Runnable task = new Runnable() {
            @Override
            public void run() {
                LocalDateTime now = LocalDateTime.now();
                System.out.println("Running TestNG suite at: " + now.format(formatter));

                TestNG testng = new TestNG();
                testng.setTestSuites(Collections.singletonList("testng.xml"));
                testng.run();

                // Log the next run time
                LocalDateTime nextRun = now.plusDays(1).withHour(targetTime.getHour()).withMinute(targetTime.getMinute()).withSecond(0);
                System.out.println("Next run scheduled at: " + nextRun.format(formatter));
            }
        };

        // Schedule task to run daily at specified time
        scheduler.scheduleAtFixedRate(task, initialDelay, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);
    }
}
