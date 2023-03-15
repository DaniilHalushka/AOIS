
public class CheckSigns {
    public static String[] checkAreBothSignsNegative(String binaryValue1, String binaryValue2) {
        if (binaryValue1.charAt(0) == '1' && binaryValue2.charAt(0) == '1') {
            StringBuilder binaryValue1Builder = new StringBuilder(binaryValue1);
            StringBuilder binaryValue2Builder = new StringBuilder(binaryValue2);

            binaryValue1Builder.delete(0, 1);
            binaryValue2Builder.delete(0, 1);

            binaryValue1Builder.insert(0, 0);
            binaryValue2Builder.insert(0, 0);

            binaryValue1 = String.valueOf(binaryValue1Builder);
            binaryValue2 = String.valueOf(binaryValue2Builder);
        }
        return new String[]{
                binaryValue1, binaryValue2
        };
    }

    public static String[] checkBothSigns(String binaryValue1, String binaryValue2) {
        if (binaryValue1.charAt(0) == '1') {
            binaryValue1 = FunctionsWithValues.makeReverseBinary(binaryValue1);
        }
        if (binaryValue2.charAt(0) == '1') {
            binaryValue2 = FunctionsWithValues.makeReverseBinary(binaryValue2);
        }
        return new String[]{
                binaryValue1, binaryValue2
        };
    }

    public static String checkSignForDifference(String value) {
        StringBuilder bufferValue = new StringBuilder(value);
        if (bufferValue.charAt(0) == '0') {
            bufferValue.delete(0, 1);
            bufferValue.insert(0, '1');
        } else {
            bufferValue.delete(0, 1);
            bufferValue.insert(0, '0');
        }
        value = String.valueOf(bufferValue);
        return value;
    }

    static char checkSignForMultiplicationAndDivision(String binaryValue1, String binaryValue2) {
        char result = '0';
        if ((binaryValue1.charAt(0) == '1' && binaryValue2.charAt(0) != '1') || (binaryValue1.charAt(0) != '1' && binaryValue2.charAt(0) == '1')) {
            result = '1';
        }
        return result;
    }
}
