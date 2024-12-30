package StepDefs.services.Practice;

import java.time.LocalTime;

public class TimeGreeting {

    public static void main(String[] args) {
        // Get the current time
        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour(); // Get the hour part of the current time

        // Determine the greeting based on the hour
        String greeting;
        if (hour < 10) {
            greeting = "Good Morning";
        } else if (hour < 15) {
            greeting = "Good Afternoon";
        } else {
            greeting = "Good Evening";
        }

        // Display the greeting
        System.out.println(greeting);
    }
}
