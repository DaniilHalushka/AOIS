import java.util.List;

public class UtilClassesForPNDF {
    private static final String AND_OPERATOR = " & ";
    private static final String OR_OPERATOR = " | ";
    private static final String OPENING_BRACKET = "(";
    private static final String CLOSING_BRACKET = ")";
    private static final String NUMBER_VARIANT_PREFIX = "|(";
    private static final String NUMBER_VARIANT_SUFFIX = ")";
    public static final String COMMA = ", ";

    public static String buildPNDFExpression(String[] variableValues, List<Boolean> utilListOfBooleans) {
        StringBuilder PNDFBuilder = new StringBuilder();
        for (int i = 0; i < variableValues.length; i++) {
            if (i != 0) {
                PNDFBuilder.append(AND_OPERATOR);
            }
            PNDFBuilder.append(utilListOfBooleans.get(i) ? variableValues[i] : "!" + variableValues[i]);
        }
        return PNDFBuilder.toString();
    }

    public static String PNDF(String[] variableValues, List<List<Boolean>> tableOfTruth, boolean[] result) {
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (result[i]) {
                if (resultBuilder.length() > 0) {
                    resultBuilder.append(OR_OPERATOR);
                }
                resultBuilder.append(OPENING_BRACKET)
                        .append(buildPNDFExpression(variableValues, tableOfTruth.get(i)))
                        .append(CLOSING_BRACKET);
            }
        }
        return resultBuilder.toString();
    }

    public static String convertPNDFToNumberVariant(boolean[] result) {
        StringBuilder resultString = new StringBuilder(NUMBER_VARIANT_PREFIX);
        for (int i = 0; i < result.length; i++) {
            if (result[i]) {
                if (resultString.length() > 3) {
                    resultString.append(COMMA);
                }
                resultString.append(i);
            }
        }
        return resultString.append(NUMBER_VARIANT_SUFFIX).toString();
    }
}