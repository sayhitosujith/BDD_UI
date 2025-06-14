package StepDefs;

import org.testng.TestNG;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class TestRunnerSchedulerDaily {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4); // 4 times a day

        List<LocalTime> runTimes = Arrays.asList(
                LocalTime.of(8, 30),
                LocalTime.of(9, 30),
                LocalTime.of(10, 30),
                LocalTime.of(11, 30),
                LocalTime.of(13, 30)
        );

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (LocalTime targetTime : runTimes) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime firstRun = now.withHour(targetTime.getHour())
                    .withMinute(targetTime.getMinute())
                    .withSecond(0)
                    .withNano(0);

            if (now.isAfter(firstRun)) {
                firstRun = firstRun.plusDays(1);
            }

            long initialDelay = Duration.between(now, firstRun).toMillis();
            long period = TimeUnit.DAYS.toMillis(1);

            System.out.println("Run for " + targetTime + " scheduled at: " + firstRun.format(formatter));

            Runnable task = () -> {
                LocalDateTime currentRunTime = LocalDateTime.now();
                System.out.println("Running TestNG suite at: " + currentRunTime.format(formatter));

                TestNG testng = new TestNG();
                testng.setTestSuites(Collections.singletonList("testng.xml"));
                testng.run();

                LocalDateTime nextRun = currentRunTime.plusDays(1)
                        .withHour(targetTime.getHour())
                        .withMinute(targetTime.getMinute())
                        .withSecond(0)
                        .withNano(0);

                System.out.println("Next run for " + targetTime + " scheduled at: " + nextRun.format(formatter));
            };

            scheduler.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.MILLISECONDS);
        }
    }
}
