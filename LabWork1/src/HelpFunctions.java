public class HelpFunctions {
    public static boolean maxValueLength(String binaryValue1, String binaryValue2) {
        return binaryValue1.length() > binaryValue2.length();
    }

    public static String[] additionToBinaryString(String binaryValue1, String binaryValue2) {
        char sign1 = binaryValue1.charAt(0), sign2 = binaryValue2.charAt(0);
        StringBuilder binaryValue1Builder = new StringBuilder(binaryValue1);
        StringBuilder binaryValue2Builder = new StringBuilder(binaryValue2);

        binaryValue1Builder.delete(0, 1);
        binaryValue2Builder.delete(0, 1);
        if (HelpFunctions.maxValueLength(binaryValue1, binaryValue2)) {
            while (binaryValue2Builder.length() != binaryValue1Builder.length()) {
                binaryValue2Builder.insert(0, "0");
            }
            binaryValue2 = String.valueOf(sign2) + binaryValue2Builder;
        } else {
            while ((binaryValue1Builder.toString()).length() != binaryValue2Builder.length()) {
                binaryValue1Builder.insert(0, "0");
            }
            binaryValue1 = String.valueOf(sign1) + binaryValue1Builder;
        }
        return new String[]{
                binaryValue1, binaryValue2
        };
    }
    public static String[] deleteSighs(String binaryValue1, String binaryValue2){
        StringBuilder binaryValue1Builder = new StringBuilder(binaryValue1).delete(0, 1);
        StringBuilder binaryValue2Builder = new StringBuilder(binaryValue2).delete(0, 1);
        return new String[]{
                binaryValue1Builder.toString(), binaryValue2Builder.toString()
        };
    }


}
