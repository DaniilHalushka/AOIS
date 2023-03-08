public class SumOfIntegerValues {
    public static Object[] comparisonForSum(String binaryValue1, String binaryValue2, char check) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < binaryValue1.length(); i++) {
        char c = binaryValue1.charAt(binaryValue1.length() - 1 - i);
        if (c == binaryValue2.charAt(binaryValue2.length() - 1 - i)) {
            if (c == '1') {
                if (check == '0') {
                    result.insert(0, "0");
                    check = '1';
                } else {
                    result.insert(0, "1");
                }
            } else {
                if (check == '1') {
                    result.insert(0, "1");
                    check = '0';
                } else {
                    result.insert(0, "0");
                }
            }
        } else if (c != binaryValue2.charAt(binaryValue1.length() - 1 - i)) {
            if (check == '1') {
                result.insert(0, "0");
            } else {
                result.insert(0, "1");
            }
        }
    }
    if (binaryValue1.charAt(0) == '0' && binaryValue2.charAt(0) == '0') {
        result.insert(0, "0");
    }
    return new Object[]{
            result.toString(), check
    };
}

    public static String sumOfBinaryValues(String binaryValue1, String binaryValue2) {
        String[] checkedValues = CheckSigns.checkAreBothSignsNegative(binaryValue1, binaryValue2);
        Object[] additedBinaries = HelpFunctions.additionToBinaryString(checkedValues[0], checkedValues[1]);
        String[] signs = CheckSigns.checkBothSigns((String) additedBinaries[0], (String) additedBinaries[1]);
        Object[] pair = comparisonForSum(signs[0], signs[1], '0');
        String result = (String) pair[0];
        if ((char) pair[1] == '1') {
            additedBinaries = HelpFunctions.additionToBinaryString(result, "01");
            pair = comparisonForSum((String) additedBinaries[0], (String) additedBinaries[1], '0');
            result = (String) pair[0];
        }
        if (result.charAt(0) == '1') {
            result = '1' + result;
            result = FunctionsWithValues.makeReverseBinary(result);
        } else if (binaryValue1.charAt(0) == '1' && binaryValue2.charAt(0) == '1') {
            result = '1' + result;
        } else {
            result = '0' + result;
        }
        return result;
    }
}
