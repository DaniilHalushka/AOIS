import java.util.LinkedList;

public class ParseExpression {
    private static final String ERROR_MESSAGE = "Wrong value (not 0 or 1)";
    private static final char FIRST_VARIABLE_CHAR = 'A';
    private static final char OR_OPERATOR_CHAR = '|';
    private static final char AND_OPERATOR_CHAR = '&';
    private static final char NOT_OPERATOR_CHAR = '!';

    private static boolean intValueToBoolean(int intValue) {
        if ((intValue == 0) || (intValue == 1)) {
            return intValue != 0;
        } else {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private static String replaceVariablesInExpression(String expressionString, boolean[] booleanValues) {
        for (int i = 0; i < booleanValues.length; i++) {
            String string;
            if (booleanValues[i]) {
                string = "1";
            } else {
                string = "0";
            }
            expressionString = expressionString.replace(Character.toString((char) (FIRST_VARIABLE_CHAR + i)), string);
        }
        return expressionString;
    }

    public static boolean parseExpression(String expressionString, boolean[] booleanValues) {
        expressionString = UtilMethodsForStackCalculator.buildReversePolishNotation(expressionString);
        expressionString = replaceVariablesInExpression(expressionString, booleanValues);
        LinkedList<Boolean> RPNToBoolean = new LinkedList<>();
        int counter = 0;
        while (counter < expressionString.length()) {
            if (utilMethodForBinaryOperator(expressionString.charAt(counter))) {
                Boolean operand2 = RPNToBoolean.removeFirst();
                Boolean operand1 = RPNToBoolean.removeFirst();
                RPNToBoolean.addFirst(UtilOperationsWithExpression.utilMethodForChoosingOperation(operand1, operand2, expressionString.charAt(counter)));
            } else {
                if (utilMethodForUnaryOperator(expressionString.charAt(counter))) {
                    Boolean operand = RPNToBoolean.removeFirst();
                    RPNToBoolean.addFirst(UtilOperationsWithExpression.utilMethodToInversion(operand, expressionString.charAt(counter)));
                } else {
                    RPNToBoolean.addFirst(intValueToBoolean(Integer.parseInt(String.valueOf(expressionString.charAt(counter)))));
                }
            }
            counter++;
        }
        return RPNToBoolean.removeFirst();
    }

    private static boolean utilMethodForBinaryOperator(char operatorValue) {
        return operatorValue == OR_OPERATOR_CHAR || operatorValue == AND_OPERATOR_CHAR;
    }

    private static boolean utilMethodForUnaryOperator(char operatorValue) {
        return operatorValue == NOT_OPERATOR_CHAR;
    }

    public static boolean[] getValues(int n, int length) {
        boolean[] values = new boolean[length];
        for (int i = 0; i < length; i++) {
            values[length - i - 1] = (n & (1 << i)) != 0;
        }
        return values;
    }
}
