package StepDefs.services.Practice;

public class MissingValueFinder {
    public static void main(String[] args) {
        // Example array with a missing number (5 is missing)
        int[] numbers = {1, 2, 3, 4, 6, 7, 8};

        // Find the expected sum of 1 to 8
        int n = 8;
        int expectedSum = n * (n + 1) / 2;

        // Calculate actual sum of array elements
        int actualSum = 0;
        for (int num : numbers) {
            actualSum += num;
        }

        // The difference is the missing number
        int missingNumber = expectedSum - actualSum;

        System.out.println("The missing number is: " + missingNumber);
    }
}
