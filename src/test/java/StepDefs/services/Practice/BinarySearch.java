package StepDefs.services.Practice;

public class BinarySearch {

    // Function to perform binary search
    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            // Find the middle index
            int mid = low + (high - low) / 2;

            // Check if target is present at mid
            if (arr[mid] == target) {
                return mid;  // Target found at index mid
            }
            // If target is smaller, ignore the right half
            else if (arr[mid] > target) {
                high = mid - 1;
            }
            // If target is larger, ignore the left half
            else {
                low = mid + 1;
            }
        }

        // Target not found
        return -1;
    }

    public static void main(String[] args) {
        // Example array (must be sorted)
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};

        int target = 7;
        int result = binarySearch(arr, target);

        if (result == -1) {
            System.out.println("Element not found.");
        } else {
            System.out.println("Element found at index: " + result);
        }
    }
}
