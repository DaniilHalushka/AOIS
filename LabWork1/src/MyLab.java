import java.util.Scanner;
import java.util.Locale;

public class MyLab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ENGLISH);

        System.out.println("Enter the first integer value:");
        int intValue1 = scanner.nextInt();
        System.out.println("Enter the second integer value:");
        int intValue2 = scanner.nextInt();
        System.out.println("-----------\n");

        System.out.println("Enter the first float value:");
        float floatValue1 = scanner.nextFloat();
        System.out.println("Enter the second float value:");
        float floatValue2 = scanner.nextFloat();
        System.out.println("-----------\n");

        System.out.println("Operations with integer values:" + "\n\n" +
                "Variant 1: the first value = " + intValue1 + ", the second value = " + intValue2);
        System.out.println("The first value in binary = " + FunctionsWithValues.intValueToBinary(intValue1) + ", the second value in binary = " +
                FunctionsWithValues.intValueToBinary(intValue2));
        System.out.println("Sum of values = " +
                FunctionsWithValues.binaryValueToInt(SumOfIntegerValues.sumOfBinaryValues(FunctionsWithValues.intValueToBinary(intValue1),
                        (FunctionsWithValues.intValueToBinary(intValue2)))));
        System.out.println("Difference of values = " +
                FunctionsWithValues.binaryValueToInt(DifferenceOfIntegerValues.differenceOfBinaryValues(FunctionsWithValues.intValueToBinary(intValue1),
                        (FunctionsWithValues.intValueToBinary(intValue2)))));
        System.out.println("Multiplication of values = " +
                FunctionsWithValues.binaryValueToInt(MultiplicationOfIntegerValues.multiplicationOfBinaryValues(FunctionsWithValues.intValueToBinary(intValue1),
                        (FunctionsWithValues.intValueToBinary(intValue2)))));
        System.out.println("Division of values (without remainder, integer value) = " +
                DivisionOfIntegerValues.divisionOfBinaryValues(FunctionsWithValues.intValueToBinary(intValue1), (FunctionsWithValues.intValueToBinary(intValue2))));

        System.out.println("\nVariant 2: the first value = " + (-intValue1) + ", the second value = " + intValue2);
        System.out.println("The first value in binary = " + FunctionsWithValues.intValueToBinary(-intValue1) + ", the second value in binary = " +
                FunctionsWithValues.intValueToBinary(intValue2));
        System.out.println("Sum of values = " +
                FunctionsWithValues.binaryValueToInt(SumOfIntegerValues.sumOfBinaryValues(FunctionsWithValues.intValueToBinary(-intValue1),
                        (FunctionsWithValues.intValueToBinary(intValue2)))));
        System.out.println("Difference of values = " +
                FunctionsWithValues.binaryValueToInt(DifferenceOfIntegerValues.differenceOfBinaryValues(FunctionsWithValues.intValueToBinary(-intValue1),
                        (FunctionsWithValues.intValueToBinary(intValue2)))));
        System.out.println("Multiplication of values = " +
                FunctionsWithValues.binaryValueToInt(MultiplicationOfIntegerValues.multiplicationOfBinaryValues(FunctionsWithValues.intValueToBinary(-intValue1),
                        (FunctionsWithValues.intValueToBinary(intValue2)))));
        System.out.println("Division of values (without remainder, integer value) = " +
                DivisionOfIntegerValues.divisionOfBinaryValues(FunctionsWithValues.intValueToBinary(-intValue1), (FunctionsWithValues.intValueToBinary(intValue2))));

        System.out.println("\nVariant 3: the first value = " + intValue1 + ", the second value = " + (-intValue2));
        System.out.println("The first value in binary = " + FunctionsWithValues.intValueToBinary(intValue1) + ", the second value in binary = " +
                FunctionsWithValues.intValueToBinary(-intValue2));
        System.out.println("Sum of values = " +
                FunctionsWithValues.binaryValueToInt(SumOfIntegerValues.sumOfBinaryValues(FunctionsWithValues.intValueToBinary(intValue1),
                        (FunctionsWithValues.intValueToBinary(-intValue2)))));
        System.out.println("Difference of values = " +
                FunctionsWithValues.binaryValueToInt(DifferenceOfIntegerValues.differenceOfBinaryValues(FunctionsWithValues.intValueToBinary(intValue1),
                        (FunctionsWithValues.intValueToBinary(-intValue2)))));
        System.out.println("Multiplication of values = " +
                FunctionsWithValues.binaryValueToInt(MultiplicationOfIntegerValues.multiplicationOfBinaryValues(FunctionsWithValues.intValueToBinary(intValue1),
                        (FunctionsWithValues.intValueToBinary(-intValue2)))));
        System.out.println("Division of values (without remainder, integer value) = " +
                DivisionOfIntegerValues.divisionOfBinaryValues(FunctionsWithValues.intValueToBinary(intValue1), (FunctionsWithValues.intValueToBinary(-intValue2))));

        System.out.println("\nVariant 4: the first value = " + (-intValue1) + ", the second value = " + (-intValue2));
        System.out.println("The first value in binary = " + FunctionsWithValues.intValueToBinary(-intValue1) + ", the second value in binary = " +
                FunctionsWithValues.intValueToBinary(-intValue2));
        System.out.println("Sum of values = " +
                FunctionsWithValues.binaryValueToInt(SumOfIntegerValues.sumOfBinaryValues(FunctionsWithValues.intValueToBinary(-intValue1),
                        (FunctionsWithValues.intValueToBinary(-intValue2)))));
        System.out.println("Difference of values = " +
                FunctionsWithValues.binaryValueToInt(DifferenceOfIntegerValues.differenceOfBinaryValues(FunctionsWithValues.intValueToBinary(-intValue1),
                        (FunctionsWithValues.intValueToBinary(-intValue2)))));
        System.out.println("Multiplication of values = " +
                FunctionsWithValues.binaryValueToInt(MultiplicationOfIntegerValues.multiplicationOfBinaryValues(FunctionsWithValues.intValueToBinary(-intValue1),
                        (FunctionsWithValues.intValueToBinary(-intValue2)))));
        System.out.println("Division of values (without remainder, integer value) = " +
                DivisionOfIntegerValues.divisionOfBinaryValues(FunctionsWithValues.intValueToBinary(-intValue1), (FunctionsWithValues.intValueToBinary(-intValue2))));
        System.out.println("-----------\n");


        System.out.println("\nThe first value in binary = " + OperationsWithFloatValues.floatToBinary(floatValue1) + ", the second value in binary = " +
                OperationsWithFloatValues.floatToBinary(floatValue2));
        System.out.println("Sum of float values as a floating point number = " + OperationsWithFloatValues.floatSum(floatValue1, floatValue2) + " or " + (floatValue1 + floatValue2));

    }
}
