import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AssociativeProcessor {
    private static final int BIT_SIZE = 16;
    private int[][] matrix;
    private boolean diagonal;

    public AssociativeProcessor(int[][] data) {
        this.matrix = data;
        this.diagonal = false;
    }

    public void searchingByAccordance() {
        Map<String, Integer> tickOfMatching = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your example data: ");
        String exampleOfData = scanner.nextLine();
        for (char charValue : exampleOfData.toCharArray()) {
            if (charValue != '0' && charValue != '1') {
                System.out.println("Wrong example data!");
                return;
            }
        }
        if (exampleOfData.length() > BIT_SIZE) {
            exampleOfData = exampleOfData.substring(0, BIT_SIZE);
        } else if (exampleOfData.length() < BIT_SIZE) {
            exampleOfData = String.format("%" + BIT_SIZE + "s", exampleOfData).replace(' ', '0');
        }
        System.out.println("Our data for matching: " + exampleOfData);
        int[][] copyOfMatrix = Arrays.copyOf(matrix, matrix.length);
        boolean diagonalDataValues = diagonal;
        if (diagonalDataValues) {
            convertToStraight();
        }
        for (int numberValue = 0; numberValue < matrix.length; numberValue++) {
            tickOfMatching.put(Arrays.toString(matrix[numberValue]), recurseAlgorithmForAccordance(copyOfMatrix[numberValue], exampleOfData, numberValue));
        }
        if (diagonalDataValues) {
            convertToDiagonal();
        }
        System.out.println("Strings with the highest matching digits:");
        tickOfMatching.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .skip(Math.max(0, tickOfMatching.size() - 3))
                .forEach(entry -> System.out.println(entry.getKey()));
    }


    public void convertToStraight() {
        int[][] copyOfMatrix = Arrays.copyOf(matrix, matrix.length);
        matrix = new int[copyOfMatrix.length][];
        for (int numberValue = 0; numberValue < copyOfMatrix.length; numberValue++) {
            matrix[numberValue] = new int[copyOfMatrix[numberValue].length];
            System.arraycopy(copyOfMatrix[numberValue], numberValue, matrix[numberValue], 0, copyOfMatrix[numberValue].length - numberValue);
            System.arraycopy(copyOfMatrix[numberValue], 0, matrix[numberValue], copyOfMatrix[numberValue].length - numberValue, numberValue);
        }
        diagonal = false;
    }

    public void convertToDiagonal() {
        int[][] copyOfMatrix = Arrays.copyOf(matrix, matrix.length);
        matrix = new int[copyOfMatrix.length][];
        for (int numberValue = 0; numberValue < copyOfMatrix.length; numberValue++) {
            matrix[numberValue] = new int[copyOfMatrix[numberValue].length];
            System.arraycopy(copyOfMatrix[numberValue], copyOfMatrix[numberValue].length - numberValue - 1, matrix[numberValue], 0, numberValue + 1);
            System.arraycopy(copyOfMatrix[numberValue], 0, matrix[numberValue], numberValue + 1, copyOfMatrix[numberValue].length - numberValue - 1);
        }
        diagonal = true;
    }


    public void logicOperations() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose type of operation:");
        System.out.println("1 -- constant 1");
        System.out.println("2 -- constant 0");
        System.out.println("3 -- New argument");
        System.out.println("4 -- Negative of new argument");
        int typeOfOperation = scanner.nextInt();
        System.out.print("Choose column: ");
        int numberOfColumn = scanner.nextInt();

        switch (typeOfOperation) {
            case 1 -> {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][numberOfColumn] = 1;
                }
            }
            case 2 -> {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][numberOfColumn] = 0;
                }
            }
            case 3 -> positiveArgument(numberOfColumn);
            case 4 -> negativeArgument(numberOfColumn);
        }

        int columns = matrix[0].length;
        for (int[] ints : matrix) {
            for (int j = 0; j < columns; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    private void positiveArgument(int numberOfColumn) {
        boolean diagonalData = diagonal;
        if (diagonalData) {
            convertToStraight();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input argument: ");
        String argument = scanner.nextLine();
        if (argument.length() > BIT_SIZE) {
            argument = argument.substring(0, BIT_SIZE);
        } else if (argument.length() < BIT_SIZE) {
            argument = String.format("%" + BIT_SIZE + "s", argument).replace(' ', '0');
        }
        char[] argumentChars = argument.toCharArray();
        for (int numberValue = 0; numberValue < matrix[numberOfColumn].length; numberValue++) {
            matrix[numberOfColumn][numberValue] = Character.getNumericValue(argumentChars[numberValue]);
        }
        if (diagonalData) {
            convertToDiagonal();
        }
    }

    private void negativeArgument(int numberOfColumn) {
        boolean diagonalData = diagonal;
        if (diagonalData) {
            convertToStraight();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input argument: ");
        String argument = scanner.nextLine();
        if (argument.length() > BIT_SIZE) {
            argument = argument.substring(0, BIT_SIZE);
        } else if (argument.length() < BIT_SIZE) {
            argument = String.format("%" + BIT_SIZE + "s", argument).replace(' ', '0');
        }
        argument = argument.replace("1", "2").replace("0", "1").replace("2", "0");
        char[] argumentChars = argument.toCharArray();
        for (int numberValue = 0; numberValue < matrix[numberOfColumn].length; numberValue++) {
            matrix[numberOfColumn][numberValue] = Character.getNumericValue(argumentChars[numberValue]);
        }
        if (diagonalData) {
            convertToDiagonal();
        }
    }

    private static int[] sumOfNumbers(int[] num1, int[] num2) {
        int[] sum = new int[num1.length];
        int utilValue = 0;
        for (int i = num1.length - 1; i >= 0; i--) {
            if (num1[i] + num2[i] == 1 && utilValue == 0) {
                sum[i] = 1;
            } else if (num1[i] + num2[i] == 1 && utilValue > 0) {
                sum[i] = 0;
            } else if (num1[i] + num2[i] == 2 && utilValue > 0) {
                sum[i] = 1;
            } else if (num1[i] + num2[i] == 0 && utilValue > 0) {
                sum[i] = 1;
                utilValue -= 1;
            } else if (num1[i] + num2[i] == 0 && utilValue == 0) {
                sum[i] = 0;
            } else if (num1[i] + num2[i] == 2 && utilValue == 0) {
                sum[i] = 0;
                utilValue += 1;
            }
        }
        if (utilValue > 0) {
            sum = Arrays.copyOf(sum, sum.length + 1);
            System.arraycopy(sum, 0, sum, 1, sum.length - 1);
            sum[0] = 1;
        } else if (utilValue == 0) {
            sum = Arrays.copyOf(sum, sum.length + 1);
            System.arraycopy(sum, 0, sum, 1, sum.length - 1);
            sum[0] = 0;
        }
        return sum;
    }

    public void sumOfFields() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your example data: ");
        String exampleData = scanner.nextLine().substring(0, 3);
        boolean diagonalData = diagonal;
        if (diagonalData) {
            convertToStraight();
        }
        for (int[] ints : matrix) {
            if (Arrays.toString(ints).replace(", ", "").substring(1, 4).equals(exampleData)) {
                int[] sum = sumOfNumbers(Arrays.copyOfRange(ints, 3, 7), Arrays.copyOfRange(ints, 7, 11));
                System.arraycopy(sum, 0, ints, 11, sum.length);
            }
        }
        if (diagonalData) {
            convertToDiagonal();
        }
        int columns = matrix[0].length;

        for (int[] ints : matrix) {
            for (int j = 0; j < columns; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private int recurseAlgorithmForAccordance(int[] matrixString, String data, int numberOfString) {
        int tick = 0;
        int positionOfBreak = 0;
        if (!diagonal) {
            for (int digit = 0; digit < data.length(); digit++) {
                if (data.charAt(digit) == Character.forDigit(matrixString[digit], 10)) {
                    tick++;
                }
            }
        }
        if (diagonal) {
            for (int digit = 0; digit < data.substring(numberOfString).length(); digit++) {
                positionOfBreak = digit;
                if (data.charAt(digit + numberOfString) == Character.forDigit(matrixString[digit], 10)) {
                    tick++;
                }
            }
            for (int digit = 0; digit < data.substring(0, numberOfString).length(); digit++) {
                if (data.charAt(digit) == Character.forDigit(matrixString[positionOfBreak + digit], 10)) {
                    tick++;
                }
            }
        }
        return tick;
    }

    public void chooseReadOrWrite() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 -- Read column");
        System.out.println("2 -- Read word");
        System.out.println("3 -- Write column");
        System.out.println("4 -- Write word");
        int typeOfOperation = scanner.nextInt();
        switch (typeOfOperation) {
            case 1 -> readWord();
            case 2 -> readColumn();
            case 3 -> writeWord();
            case 4 -> writeColumn();
        }
    }

    private void readWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input word position: ");
        int wordPosition = scanner.nextInt();
        int[] word = new int[matrix.length];
        for (int numberValue = 0; numberValue < matrix.length; numberValue++) {
            word[numberValue] = matrix[numberValue][wordPosition];
        }
        System.out.println(Arrays.toString(word));
    }

    private void readColumn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input column number: ");
        int columnNumber = scanner.nextInt();
        int[] column = matrix[columnNumber];
        System.out.println(Arrays.toString(column));
    }

    private void writeWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input word position: ");
        int wordPosition = scanner.nextInt();
        int[] word = new int[matrix.length];
        System.out.print("Input word: ");
        String wordString = scanner.next();
        if (wordString.length() != matrix.length) {
            System.out.println("Invalid word length!");
            return;
        }
        for (int numberValue = 0; numberValue < matrix.length; numberValue++) {
            word[numberValue] = Character.getNumericValue(wordString.charAt(numberValue));
        }
        for (int numberValue = 0; numberValue < matrix.length; numberValue++) {
            matrix[numberValue][wordPosition] = word[numberValue];
        }
        int columns = matrix[0].length;

        for (int[] ints : matrix) {
            for (int j = 0; j < columns; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    private void writeColumn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input column number: ");
        int columnNumber = scanner.nextInt();
        int[] column = new int[matrix.length];
        System.out.print("Input column data: ");
        String columnData = scanner.next();
        if (columnData.length() != matrix.length) {
            System.out.println("Invalid column data length!");
            return;
        }
        for (int numberValue = 0; numberValue < column.length; numberValue++) {
            column[numberValue] = Character.getNumericValue(columnData.charAt(numberValue));
        }
        matrix[columnNumber] = column;
        int columns = matrix[0].length;

        for (int[] ints : matrix) {
            for (int j = 0; j < columns; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void showMatrix(int[][] matrix) {
        int columns = matrix[0].length;

        for (int[] ints : matrix) {
            for (int j = 0; j < columns; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[][] getMatrix() {
        return matrix;
    }
}