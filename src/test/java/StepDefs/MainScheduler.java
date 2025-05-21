package StepDefs;

import org.testng.TestNG;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MainScheduler {

    private static final Logger logger = Logger.getLogger(MainScheduler.class.getName());
    private static ScheduledExecutorService scheduler;

    public static void main(String[] args) {
        try {
            setupLogger();
            setupScheduler();

            logger.info("Scheduler started. First test run will begin immediately.");
            scheduler.scheduleAtFixedRate(MainScheduler::runTests, 0, 1, TimeUnit.HOURS);

            // Shutdown hook to gracefully terminate scheduler
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("Shutdown initiated. Terminating scheduler...");
                scheduler.shutdown();
                try {
                    if (!scheduler.awaitTermination(10, TimeUnit.SECONDS)) {
                        logger.warning("Scheduler did not terminate in the allotted time.");
                        scheduler.shutdownNow();
                    }
                    logger.info("Scheduler shutdown complete.");
                } catch (InterruptedException e) {
                    logger.severe("Shutdown interrupted: " + e.getMessage());
                    scheduler.shutdownNow();
                }
            }));

        } catch (Exception e) {
            logger.severe("Failed to initialize scheduler: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void setupLogger() throws IOException {
        File logDir = new File("logs");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        FileHandler fileHandler = new FileHandler("logs/TestExecutionLog.log", true); // Append mode
        fileHandler.setFormatter(new SimpleFormatter());

        // Remove existing handlers if any
        if (logger.getHandlers().length == 0) {
            logger.addHandler(fileHandler);
        }

        logger.setUseParentHandlers(false); // Suppress console output
    }

    private static void setupScheduler() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    private static void runTests() {
        LocalDateTime startTime = LocalDateTime.now();
        logger.info("==========================================");
        logger.info("Test execution started at: " + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        try {
            TestNG testng = new TestNG();
            testng.setTestClasses(new Class[]{RunCukesTest.class});
            testng.run();
            logger.info("TestNG execution completed successfully.");
        } catch (Exception e) {
            logger.severe("Error during test execution: " + e.getMessage());
            e.printStackTrace();
        }

        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);

        logger.info("Test execution finished at: " + endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        logger.info(String.format("Total Execution Time: %02d:%02d:%02d (hh:mm:ss)",
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart()));
    }
}
