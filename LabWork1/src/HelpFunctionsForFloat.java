import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

public class HelpFunctionsForFloat {
    private static final int COUNT_OF_BYTES = 16;
    private static boolean HELP_BOOLEAN = false;

    public static String helpView(String binaryView) {
        StringBuilder binaryViewBuilder = new StringBuilder(binaryView);
        while (binaryViewBuilder.length() < COUNT_OF_BYTES) {
            binaryViewBuilder.insert(0, 0);
        }
        binaryView = binaryViewBuilder.toString();
        return binaryView;
    }

    public static char additionValues(char digit1, char digit2) {
        char result;
        int sumOfValues = parseInt(String.valueOf(digit1)) + parseInt(String.valueOf(digit2));
        if (HELP_BOOLEAN) {
            sumOfValues++;
        }
        if (sumOfValues < 2) {
            result = (char) (sumOfValues + '0');
            HELP_BOOLEAN = false;
        } else {
            HELP_BOOLEAN = true;
            result = (char) (sumOfValues - 2 + '0');
        }

        return result;
    }

    public static String specialView(String binaryValue) {
        StringBuilder binaryValueBuilder = new StringBuilder(binaryValue);
        while (binaryValueBuilder.length() < COUNT_OF_BYTES) {
            binaryValueBuilder.insert(0, 0);
        }
        binaryValue = binaryValueBuilder.toString();
        return binaryValue;
    }

    public static String helpSum(String binaryValue1, String binaryValue2) {
        StringBuilder result = new StringBuilder();
        binaryValue1 = specialView(binaryValue1);
        binaryValue2 = specialView(binaryValue2);
        for (int i = binaryValue1.length() - 1; i >= 0; i--) {
            result.insert(0, additionValues(binaryValue1.charAt(i), binaryValue2.charAt(i)));
        }
        HELP_BOOLEAN = false;
        return result.toString();
    }

    public static float binaryValueToFloat(String binaryValue) {
        float result = 0;
        for (int i = 0; i < binaryValue.length(); i++) {
            result = result + parseInt(String.valueOf(binaryValue.charAt(i))) * (float) pow(2, - (i + 1));
        }
        return result;
    }


}
