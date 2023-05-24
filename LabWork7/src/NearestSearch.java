import java.util.ArrayList;
import java.util.List;

public class NearestSearch {

    public static final String FLAG_MIN = "min";
    public static final String FLAG_MAX = "max";
    public static final String OPENING_BRACKET = "(";
    public static final String CLOSING_BRACKET = ")";

    public static void nearestSearch(List<String> data, String digitToSearch) {
        List<String> higherValue = new ArrayList<>();
        List<String> lowerValue = new ArrayList<>();

        for (String value : data) {
            boolean[] comparisonResult = comparison(digitToSearch, value, value.length() - 1);
            if (comparisonResult[0]) {
                higherValue.add(value);
            } else if (comparisonResult[1]) {
                lowerValue.add(value);
            } else {
                System.out.println(value);
                return;
            }
        }

        String higherMin = findMinMax(higherValue, FLAG_MIN);
        String lowerMax = findMinMax(lowerValue, FLAG_MAX);

        printNearestValues(higherMin, lowerMax);
    }

    public static String findMinMax(List<String> arrayOfDigits, String flagForSearching) {
        if (flagForSearching.equals(FLAG_MIN)) {
            if (!arrayOfDigits.isEmpty()) {
                String minValue = arrayOfDigits.get(0);
                for (int i = 1; i < arrayOfDigits.size(); i++) {
                    boolean[] comparisonResult = comparison(minValue, arrayOfDigits.get(i), arrayOfDigits.get(i).length() - 1);
                    if (comparisonResult[1]) {
                        minValue = arrayOfDigits.get(i);
                    }
                }
                return minValue;
            } else {
                return null;
            }
        } else {
            if (!arrayOfDigits.isEmpty()) {
                String maxValue = arrayOfDigits.get(0);
                for (int i = 1; i < arrayOfDigits.size(); i++) {
                    boolean[] comparisonResult = comparison(maxValue, arrayOfDigits.get(i), arrayOfDigits.get(i).length() - 1);
                    if (comparisonResult[0]) {
                        maxValue = arrayOfDigits.get(i);
                    }
                }
                return maxValue;
            } else {
                return null;
            }
        }
    }

    private static boolean[] comparison(String argument, String word, int i) {
        boolean[] result = new boolean[2];
        boolean[] previousResult = new boolean[2];

        if (i != 0) {
            previousResult = comparison(argument, word, i - 1);
        }

        result[0] = previousResult[0] || (argument.charAt(i) == '0' && word.charAt(i) == '1' && !previousResult[1]);
        result[1] = previousResult[1] || (argument.charAt(i) == '1' && word.charAt(i) == '0' && !previousResult[0]);

        return result;
    }

    private static void printNearestValues(String higherMin, String lowerMax) {
        if (higherMin == null) {
            System.out.println("Nearest top value: not found");
            System.out.println("Nearest bottom value: " + BinaryConverter.binaryToDecimal(lowerMax) +
                    OPENING_BRACKET + lowerMax + CLOSING_BRACKET);
        } else if (lowerMax == null) {
            System.out.println("Nearest top value: " + BinaryConverter.binaryToDecimal(higherMin) +
                    OPENING_BRACKET + higherMin + CLOSING_BRACKET);
            System.out.println("Nearest bottom value: not found");
        } else {
            System.out.println("Nearest top value: " + BinaryConverter.binaryToDecimal(higherMin) +
                    OPENING_BRACKET + higherMin + CLOSING_BRACKET);
            System.out.println("Nearest bottom value: " + BinaryConverter.binaryToDecimal(lowerMax) +
                    OPENING_BRACKET + lowerMax + CLOSING_BRACKET);
        }
    }
}
