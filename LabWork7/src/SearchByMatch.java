import java.util.ArrayList;
import java.util.List;

public class SearchByMatch {

    public static final String OPENING_BRACKET = "(";
    public static final String CLOSING_BRACKET = ")";
    public static final String FLAG_MAX = "max";

    public static void searchByMatch(List<String> listOfDigits, String searchArgument) {
        int[] counters = new int[listOfDigits.size()];

        for (int i = 0; i < searchArgument.length(); i++) {
            for (int j = 0; j < listOfDigits.size(); j++) {
                if (listOfDigits.get(j).charAt(i) == searchArgument.charAt(i)) {
                    counters[j]++;
                }
            }
        }

        int maxCount = -1;
        for (int counter : counters) {
            if (counter > maxCount) {
                maxCount = counter;
            }
        }

        List<String> sameNumbers = new ArrayList<>();
        for (int i = 0; i < counters.length; i++) {
            if (counters[i] == maxCount) {
                sameNumbers.add(listOfDigits.get(i));
            }
        }

        String result = NearestSearch.findMinMax(sameNumbers, FLAG_MAX);
        System.out.println("Result: " + BinaryConverter.binaryToDecimal(result) + OPENING_BRACKET + result + CLOSING_BRACKET);
    }
}