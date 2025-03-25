package StepDefs.services.Practice;

public class MissingNumber {
    public static int findMissingNumber(int[] arr, int n) {
        int sum = n * (n + 1) / 2;
        for (int num : arr) sum -= num;
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,5,7,8,9};
        System.out.println("Missing Number: " + findMissingNumber(arr, 5)); // 4
    }
}
