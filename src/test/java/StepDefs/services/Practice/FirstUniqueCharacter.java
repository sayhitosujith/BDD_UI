package StepDefs.services.Practice;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstUniqueCharacter {
    public static char firstNonRepeatingChar(String str) {
        Map<Character, Integer> countMap = new LinkedHashMap<>();
        for (char ch : str.toCharArray()) {
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return '-';
    }

    public static void main(String[] args) {
        System.out.println(firstNonRepeatingChar("sushil")); // w
    }
}
