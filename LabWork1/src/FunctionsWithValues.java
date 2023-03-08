import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class FunctionsWithValues {
    public static String intValueToBinary(int value) {
        StringBuilder result = new StringBuilder();
        boolean biggerThanNull = (value >= 0);

        while (abs(value) >= 1) {
            result.insert(0, (abs(value) % 2));
            value /= 2;
        }

        if (biggerThanNull) {
            result.insert(0, 0);
        } else result.insert(0, 1);

        return result.toString();
    }

    public static String makeReverseBinary(String binaryValue) {
        StringBuilder result = new StringBuilder();
        result.append(binaryValue.charAt(0));

        for (int i = 1; i < binaryValue.length(); i++) {
            if (binaryValue.charAt(i) == '1') {
                result.append("0");
            } else if (binaryValue.charAt(i) == '0') {
                result.append("1");
            } else {
                result.append(".");
            }
        }
        return result.toString();
    }

    public static int binaryValueToInt(String binaryValue) {
        int result = 0;

        for (int i = 1; i < binaryValue.length(); i++) {
            result += pow(2, binaryValue.length() - i - 1) * Character.getNumericValue(binaryValue.charAt(i));
        }

        if (binaryValue.charAt(0) == '1') {
            result = -result;
        }

        return result;
    }
}
