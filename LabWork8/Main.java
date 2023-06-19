import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AssociativeProcessor associativeProcessor = new AssociativeProcessor(16);
        int[][] words = associativeProcessor.generateWords(16);

        System.out.println("Words");
        for (int i = 0; i < 16; i++) {
            associativeProcessor.makeEntry(words[i], i);
        }
        associativeProcessor.showTable();

        System.out.println("\n***********************************************");
        System.out.println("\nChosen element (the first word): " + Arrays.toString(associativeProcessor.takeWord(0)));
        System.out.println("\n***********************************************");
        System.out.println("\nTable with diagonalizing:");
        associativeProcessor.showDiagonalTable();
        System.out.println("\n***********************************************\n");
        System.out.println("\nStandard table:");
        associativeProcessor.showTable();

        System.out.println("\n***********************************************\n");
        System.out.println("Logic functions");
        System.out.println("F0: " + Arrays.toString(associativeProcessor.F0()));
        System.out.print("F5: ");
        associativeProcessor.F5(associativeProcessor.memoryTable[1]);
        System.out.println("F10: " + Arrays.toString(associativeProcessor.F10(associativeProcessor.memoryTable[2])));
        System.out.println("F15: " + Arrays.toString(associativeProcessor.F15()));

        int[] searchWord = new int[]{0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0};
        System.out.println("\n***********************************************\n");
        System.out.println("Match search for " + Arrays.toString(searchWord) + ":");
        int[][] closestPatterns = associativeProcessor.searchNearestPattern(searchWord);
        for (int[] pattern : closestPatterns) {
            System.out.println(Arrays.toString(pattern));
        }
        System.out.println("\n***********************************************\n");
        System.out.println("Sum of elements");
        int[] maskForSum = new int[]{1, 0, 0};
        Map<Integer, int[]> arithmeticResults = associativeProcessor.arithmeticsLogic(maskForSum);
        for (Map.Entry<Integer, int[]> entry : arithmeticResults.entrySet()) {
            System.out.println(entry.getKey() + ": " + Arrays.toString(entry.getValue()));
        }
    }
}