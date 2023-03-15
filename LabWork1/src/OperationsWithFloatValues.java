import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

public class OperationsWithFloatValues {


    public static String addNumbersToBegin(String needNumber, int countOfNumbers) {
        StringBuilder needNumberBuilder = new StringBuilder(needNumber);
        for (var i = 0; i < countOfNumbers; i++) {
            needNumberBuilder.insert(0, 0);
        }
        needNumber = needNumberBuilder.toString();
        return needNumber;
    }

    public static String addNumbersToEnd(String needNumber, int countOfNumbers) {
        needNumber = needNumber + "0".repeat(Math.max(0, countOfNumbers));
        return needNumber;
    }

    public static String[] additionToBegin(String binaryValue1, String binaryValue2) {
        String[] pair = new String[2];
        if (binaryValue1.length() > binaryValue2.length()) {
            pair[0] = binaryValue1;
            pair[1] = addNumbersToBegin(binaryValue2, binaryValue1.length() - binaryValue2.length());
        } else {
            pair[1] = binaryValue2;
            pair[0] = addNumbersToBegin(binaryValue1, binaryValue2.length() - binaryValue1.length());
        }
        return pair;
    }

    public static String[] additionToEnd(String binaryValue1, String binaryValue2) {
        String[] pair = new String[2];
        if (binaryValue1.length() > binaryValue2.length()) {
            pair[0] = binaryValue1;
            pair[1] = addNumbersToEnd(binaryValue2, binaryValue1.length() - binaryValue2.length());
        } else {
            pair[1] = binaryValue2;
            pair[0] = addNumbersToEnd(binaryValue1, binaryValue2.length() - binaryValue1.length());
        }
        return pair;
    }

    public static String floatToBinary(float floatNumber) {
        String integerValue = String.valueOf(floatNumber).split("\\.")[0];
        float help = floatNumber - parseInt(integerValue);
        integerValue = String.valueOf(parseInt(FunctionsWithValues.intValueToBinary(parseInt(String.valueOf(floatNumber).split("\\.")[0]))));
        StringBuilder result = new StringBuilder(integerValue + ".");
        int counter = -1;
        while (help > 0) {
            if (help - pow(2, counter) >= 0) {
                result.append(1);
                help -= pow(2, counter);
            } else {
                result.append(0);
            }
            counter--;
        }
        return result.toString();
    }

    public static String floatSum(float number1, float number2) {
        String integerValue1 = floatToBinary(number1).split("\\.")[0];
        String floatValue1 = floatToBinary(number1).split("\\.")[1];

        String integerValue2 = floatToBinary(number2).split("\\.")[0];
        String floatValue2 = floatToBinary(number2).split("\\.")[1];

        integerValue1 = additionToBegin(integerValue1, integerValue2)[0];
        integerValue2 = additionToBegin(integerValue1, integerValue2)[1];

        floatValue1 = additionToEnd(floatValue1, floatValue2)[0];
        floatValue2 = additionToEnd(floatValue1, floatValue2)[1];

        int pointPosition = floatValue1.length();
        String binaryValue1 = integerValue1 + floatValue1;
        String binaryValue2 = integerValue2 + floatValue2;

        String result = UtilFunctionsForFloat.helpSum(binaryValue1, binaryValue2);
        result = result.substring(0, result.length() - pointPosition) + "." + result.substring(result.length() - pointPosition);
        return result;
    }
}
