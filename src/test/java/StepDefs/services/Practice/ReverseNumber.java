package StepDefs.services.Practice;

import java.util.Scanner;

class ReverseNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        int reversed = 0;

        // Logic to reverse the number
        while (number != 0) {
            int digit = number % 10;         // get last digit
            reversed = reversed * 10 + digit; // shift digits and add
            number /= 10;                     // remove last digit
        }

        // Output
        System.out.println("Reversed number: " + reversed);

        scanner.close();
    }
}
