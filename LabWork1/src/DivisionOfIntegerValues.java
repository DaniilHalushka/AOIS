public class DivisionOfIntegerValues {
    static String divisionOfBinaryValues(String binaryValue1, String binaryValue2) {
        int result = 0, intValue1 = Math.abs(FunctionsWithValues.binaryValueToInt(binaryValue1)), int_value2 = Math.abs(FunctionsWithValues.binaryValueToInt(binaryValue2));
        char sign = CheckSigns.checkSignForMultiplicationAndDivision(binaryValue1, binaryValue2);
        StringBuilder binaryValue1Builder = new StringBuilder(binaryValue1).delete(0, 1).insert(0, "0");
        StringBuilder binaryValue2Builder = new StringBuilder(binaryValue2).delete(0, 1).insert(0, "0");
        while (intValue1 >= int_value2) {
            binaryValue1Builder = new StringBuilder(DifferenceOfIntegerValues.differenceOfBinaryValues(binaryValue1Builder.toString(), binaryValue2Builder.toString()));
            intValue1 = FunctionsWithValues.binaryValueToInt(binaryValue1Builder.toString());
            result++;
        }
        if (sign == '1') {
            result = -result;
        }
        return Integer.toString(result);
    }
}
