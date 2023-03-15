public class DifferenceOfIntegerValues {

    public static String differenceOfBinaryValues(String binaryValue1, String binaryValue2) {
        String result;
        UtilFunctions.additionToBinaryString(binaryValue1, binaryValue2);
        if (binaryValue1.equals(binaryValue2)) {
            return "0";
        }
        binaryValue2 = CheckSigns.checkSignForDifference(binaryValue2);
        result = SumOfIntegerValues.sumOfBinaryValues(binaryValue1, binaryValue2);
        return result;
    }
}
