package StepDefs;

import org.testng.TestNG;

import java.io.File;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MainScheduler {

    private static final Logger logger = Logger.getLogger(MainScheduler.class.getName());

    public static void main(String[] args) {
        try {
            // Ensure logs directory exists
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            // Set up logger to write to a file
            FileHandler fh = new FileHandler("logs/TestExecutionLog.log", true); // true = append mode
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setUseParentHandlers(false); // Optional: suppress console output

            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

            Runnable task = () -> {
                LocalDateTime now = LocalDateTime.now();
                logger.info("==========================================");
                logger.info("Test execution started at: " + now);

                try {
                    TestNG testng = new TestNG();
                    testng.setTestClasses(new Class[]{RunCukesTest.class});
                    testng.run();
                    logger.info("TestNG run completed successfully.");
                } catch (Exception e) {
                    logger.severe("Error during test execution: " + e.getMessage());
                    e.printStackTrace();
                }

                logger.info("Test execution finished at: " + LocalDateTime.now());
            };

            logger.info("Scheduler started. First run will begin immediately.");
            scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.HOURS);

        } catch (Exception e) {
            logger.severe("Failed to initialize scheduler: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
