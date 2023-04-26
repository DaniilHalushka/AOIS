public class UtilOperationsWithExpression {
    private static final char OR_OPERATOR = '|';
    private static final char AND_OPERATOR = '&';
    private static final char NOT_OPERATOR = '!';

    public static boolean utilMethodToInversion(boolean variableValue, char operatorInExpression) {
        return operatorInExpression == NOT_OPERATOR && !variableValue;
    }

    private static boolean chooseNeedOperation(boolean variableValue1, boolean variableValue2, char operatorInExpression) {
        boolean result = false;
        switch (operatorInExpression) {
            case OR_OPERATOR -> {
                result = variableValue1 || variableValue2;
            }
            case AND_OPERATOR -> {
                result = variableValue1 && variableValue2;
            }
        }
        return result;
    }

    public static int binaryValueToInt(String binaryValue) {
        int result = 0;

        for (int i = 0; i < binaryValue.length(); i++) {
            int symbolInInteger = Character.getNumericValue(binaryValue.charAt(i));
            result += symbolInInteger * Math.pow(2, i);
        }

        return result;
    }

    public static boolean utilMethodForChoosingOperation(boolean variableValue1, boolean variableValue2, char operatorInExpression) {
        return chooseNeedOperation(variableValue1, variableValue2, operatorInExpression);
    }

    private static int booleanValueToInt(boolean booleanValue) {
        return booleanValue ? 1 : 0;
    }

    public static int convertExpressionToIndexForm(boolean[] arrayOfBooleanResults) {
        StringBuilder binaryValueBuilder = new StringBuilder();
        for (int i = arrayOfBooleanResults.length - 1; i >= 0; i--) {
            binaryValueBuilder.append(booleanValueToInt(arrayOfBooleanResults[i]));
        }
        return binaryValueToInt(binaryValueBuilder.toString());
    }
}
