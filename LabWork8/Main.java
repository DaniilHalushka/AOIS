import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int rows = 16;
        int columns = 16;
        int[][] matrix = new int[rows][columns];
        int[] addressOfSearching = new int[16];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = (int) (Math.random() * 2);
            }
        }

        for (int i = 0; i < 16; i++) {
            addressOfSearching[i] = (int) (Math.random() * 2);
        }

        AssociativeProcessor processor = new AssociativeProcessor(matrix);
        AssociativeProcessor.showMatrix(matrix);
        choosingTask(processor);
    }

    public static void choosingTask(AssociativeProcessor processor) {
        while (true) {
            System.out.println("Choose task: ");
            System.out.println("1 -- searching by accordance");
            System.out.println("2 -- convert to diagonal");
            System.out.println("3 -- convert to straight");
            System.out.println("4 -- logic operations");
            System.out.println("5 -- sum of digits");
            System.out.println("6 -- Read or write column");

            Scanner scanner = new Scanner(System.in);
            int task = scanner.nextInt();

            switch (task) {
                case 1 -> processor.searchingByAccordance();
                case 2 -> {
                    processor.convertToDiagonal();
                    AssociativeProcessor.showMatrix(processor.getMatrix());
                }
                case 3 -> {
                    processor.convertToStraight();
                    AssociativeProcessor.showMatrix(processor.getMatrix());
                }
                case 4 -> processor.logicOperations();
                case 5 -> processor.sumOfFields();
                case 6 -> processor.chooseReadOrWrite();
                default -> {
                    return;
                }
            }
        }
    }
}