import java.util.List;

public class UtilMethodsForPNCF {
    private static final String OR_OPERATOR = " | ";
    private static final String AND_OPERATOR = " & ";
    private static final String LEFT_BRACKET = "(";
    private static final String RIGHT_BRACKET = ")";
    private static final String NEGATION_SYMBOL = "!";
    public static final String COMMA = ", ";

    private static String buildPNCFExpression(String[] variableValues, List<Boolean> utilListOfBooleans) {
        StringBuilder PNCF = new StringBuilder();
        for (int i = 0; i < variableValues.length; i++) {
            if (!utilListOfBooleans.get(i)) {
                PNCF.append(variableValues[i]);
            } else {
                PNCF.append(NEGATION_SYMBOL).append(variableValues[i]);
            }
            if (i != variableValues.length - 1) {
                PNCF.append(OR_OPERATOR);
            }
        }
        return PNCF.toString();
    }

    public static String PNCF(String[] variableValues, List<List<Boolean>> tableOfTruth, boolean[] result) {
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (!result[i]) {
                if (resultBuilder.length() > 0) {
                    resultBuilder.append(AND_OPERATOR);
                }
                resultBuilder.append(LEFT_BRACKET);
                resultBuilder.append(buildPNCFExpression(variableValues, tableOfTruth.get(i)));
                resultBuilder.append(RIGHT_BRACKET);
            }
        }
        return resultBuilder.toString();
    }

    public static String convertPNCFToNumberVariant(boolean[] result) {
        StringBuilder resultString = new StringBuilder(AND_OPERATOR + LEFT_BRACKET);
        for (int i = 0; i < result.length; i++) {
            if (!result[i]) {
                if (resultString.length() > 3) {
                    resultString.append(COMMA);
                }
                resultString.append(i);
            }
        }
        resultString.append(RIGHT_BRACKET);
        return resultString.toString();
    }
}
