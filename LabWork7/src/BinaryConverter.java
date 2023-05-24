public class BinaryConverter {

    public static final String FORMAT = "%8s";
    public static final char OLD_CHAR = ' ';
    public static final char NEW_CHAR = '0';
    public static final int RADIX = 2;

    public static String decimalToBinary(int decimalNumber) {
        String binaryNumber = Integer.toBinaryString(decimalNumber);
        binaryNumber = String.format(FORMAT, binaryNumber).replace(OLD_CHAR, NEW_CHAR);
        return binaryNumber;
    }

    public static int binaryToDecimal(String binaryNumber) {
        return Integer.parseInt(binaryNumber, RADIX);
    }
}