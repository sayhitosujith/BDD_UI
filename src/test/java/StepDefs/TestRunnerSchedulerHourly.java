package StepDefs;

import org.testng.TestNG;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class TestRunnerSchedulerHourly {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime nextRun = now.plusHours(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            System.out.println("Running TestNG suite at: " + now.format(formatter));

            TestNG testng = new TestNG();
            testng.setTestSuites(Collections.singletonList("testng.xml"));
            testng.run();

            System.out.println("Next run scheduled at: " + nextRun.format(formatter));
        };

        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.HOURS);
    }
}
