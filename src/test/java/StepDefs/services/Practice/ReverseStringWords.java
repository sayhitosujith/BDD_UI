package StepDefs.services.Practice;

public class ReverseStringWords {
    public static String reversedWords(String str) {
        String[] words = str.split("");

        //initialise the string builder to store the result
        StringBuilder reversedString = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            reversedString.append(words[i]);
            if (i != 0) {
            }
        }

        return
                reversedString.toString();
    }


    public static void main(String[] ars) {
        String input = "Tim is great";

        String result = reversedWords(input);
        System.out.println("Reversed:" + result);
    }
}

