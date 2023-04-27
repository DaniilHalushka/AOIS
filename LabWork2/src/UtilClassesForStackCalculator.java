import java.util.LinkedList;

public class UtilClassesForStackCalculator {
    private static final int PRIORITY_NOT = 3;
    private static final int PRIORITY_AND = 2;
    private static final int PRIORITY_OR = 1;

    private static final char OPERATOR_NOT = '!';
    private static final char OPERATOR_AND = '&';
    private static final char OPERATOR_OR = '|';
    private static final char LEFT_BRACKET = '(';
    private static final char RIGHT_BRACKET = ')';

    public static int priorityInExpression(char charValue) {
        return switch (charValue) {
            case OPERATOR_NOT -> PRIORITY_NOT;
            case OPERATOR_AND -> PRIORITY_AND;
            case OPERATOR_OR -> PRIORITY_OR;
            default -> 0;
        };
    }

    public static boolean isOperatorInExpression(char charValue) {
        return charValue == OPERATOR_AND || charValue == OPERATOR_OR || charValue == OPERATOR_NOT;
    }

    private static String extractFromLinkedList(LinkedList<Character> stack) {
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    public static String buildReversePolishNotation(String expression) {
        StringBuilder RPN = new StringBuilder();
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            char charValue = expression.charAt(i);
            if (charValue == ' ') {
                continue;
            }
            if (charValue == LEFT_BRACKET) {
                stack.push(charValue);
                continue;
            }
            if (charValue == RIGHT_BRACKET) {
                while (!stack.isEmpty() && stack.peek() != LEFT_BRACKET) {
                    RPN.append(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }
            if (isOperatorInExpression(charValue)) {
                while (!stack.isEmpty() && priorityInExpression(stack.peek()) >= priorityInExpression(charValue)) {
                    RPN.append(stack.pop());
                }
                stack.push(charValue);
                continue;
            }
            RPN.append(charValue);
        }
        RPN.append(extractFromLinkedList(stack));
        return RPN.toString();
    }
}
