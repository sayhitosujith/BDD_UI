package StepDefs.services.Practice;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class RemoveDuplicates {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 2, 3, 4, 4, 5};
        LinkedHashSet<Integer> set = new LinkedHashSet<>(Arrays.asList(arr));
        System.out.println(set);
    }
}
