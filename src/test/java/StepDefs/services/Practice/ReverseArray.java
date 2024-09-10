package StepDefs.services.Practice;

class ReverseArray {
    public static void main(String[] args) {
        // Initialize the array
        char[] array = {'a', 'b', 'c'};

        // Print the original array
        System.out.println("Original array:");
        printArray(array);

        // Reverse the array
        reverseArray(array);

        // Print the reversed array
        System.out.println("Reversed array:");
        printArray(array);
    }

    // Method to reverse the array
    public static void reverseArray(char[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            // Swap the elements
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            // Move towards the center
            left++;
            right--;
        }
    }

    // Method to print the array
    public static void printArray(char[] array) {
        for (char c : array) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
