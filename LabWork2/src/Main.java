import java.util.Scanner;

//Примеры вводимых выражений, их не надо удалять
//(!A | !B & C) & (!A & (C | B) | (B & (C | !B)))
//(A & (!B | (C | A))) & (!A & (C | B))
//!((!A & B) | (A & C))

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Expression: ");
        String expression = scanner.nextLine();
        String[] variableValues = MethodsForPrintInformation.makeUniqueVariables(expression.split("[&|!() ]+"));
        boolean[] result = new boolean[1 << variableValues.length];
        for (int i = 0; i < result.length; i++) {
            boolean[] values = ParseExpression.getValues(i, variableValues.length);
            result[i] = ParseExpression.parseExpression(expression, values);
        }
        MethodsForPrintInformation.printInformation(variableValues, result);
    }
}