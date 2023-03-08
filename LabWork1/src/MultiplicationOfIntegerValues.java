public class MultiplicationOfIntegerValues {
    static String multiplicationOfBinaryValues(String binaryValue1, String binaryValue2) {
        String result = "0";
        StringBuilder helpForResult = new StringBuilder();
        char sign = CheckSigns.checkSignForMultiplicationAndDivision(binaryValue1, binaryValue2);
        String[] help = HelpFunctions.additionToBinaryString(binaryValue1, binaryValue2);
        if ((binaryValue1.charAt(0) == '1' && binaryValue2.charAt(0) == '0') || (binaryValue1.charAt(0) == '0' && binaryValue2.charAt(0) == '1') ||
                (binaryValue1.charAt(0) == '1' && binaryValue2.charAt(0) == '1')) {
            help = HelpFunctions.deleteSighs(help[0], help[1]);
        }
        for (int i = (help[1]).length() - 1; i >= 0; i--) {
            for (int j = (help[0]).length() - 1; j >= 0; j--) {
                if (((help[0]).charAt(j) == (help[1]).charAt(i)) && ((help[1]).charAt(i) == '1')) {
                    helpForResult.insert(0, "1");
                } else {
                    helpForResult.insert(0, "0");
                }
            }
            for (int k = 0; k < help[1].length() - 1 - i; k++) {
                helpForResult.insert(helpForResult.length(), "0");
            }
            result = SumOfIntegerValues.sumOfBinaryValues("0" + result, "0" + helpForResult);
            helpForResult = new StringBuilder();
        }
        result = sign + result;
        return result;
    }
}
