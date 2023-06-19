import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AssociativeProcessor {
    private final int sizeOfTable;
    int[][] memoryTable;

    public AssociativeProcessor(int size) {
        this.sizeOfTable = size;
        this.memoryTable = new int[16][16];
        for (int i = 0; i < this.sizeOfTable; i++) {
            this.memoryTable[i] = Arrays.copyOf(this.memoryTable[i], 16);
        }
    }

    private int[] wordMove(int index, int[] word) {
        int movingPart = word.length - index;
        int[] firstPart = Arrays.copyOfRange(word, 0, movingPart);
        int[] secondPart = Arrays.copyOfRange(word, movingPart, word.length);

        int[] movedWord = new int[word.length];
        System.arraycopy(secondPart, 0, movedWord, 0, secondPart.length);
        System.arraycopy(firstPart, 0, movedWord, secondPart.length, firstPart.length);

        return movedWord;
    }

    public void makeEntry(int[] word, int index) {
        int[] movedWord = wordMove(index, word);
        for (int i = 0; i < memoryTable.length; i++) {
            memoryTable[i][index] = movedWord[i];
        }
    }

    public int[][] generateWords(int size) {
        int[][] matrixOfWords = new int[size][size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrixOfWords[i][j] = random.nextInt(2);
            }
        }
        return matrixOfWords;
    }

    int[] takeWord(int index) {
        int[] movedWord = new int[memoryTable.length];
        for (int i = 0; i < memoryTable.length; i++) {
            movedWord[i] = memoryTable[i][index];
        }

        int[] chosenWord = new int[memoryTable.length];
        System.arraycopy(movedWord, index, chosenWord, 0, movedWord.length - index);
        System.arraycopy(movedWord, 0, chosenWord, movedWord.length - index, index);

        return chosenWord;
    }

    public int[][] obtainWord() {
        int[][] word = new int[sizeOfTable][sizeOfTable];
        for (int i = 0; i < sizeOfTable; i++) {
            word[i] = takeWord(i);
        }

        return word;
    }

    public Map<Integer, int[]> arithmeticsLogic(int[] enterValue) {
        List<int[]> results = new ArrayList<>();
        int[][] listOfAllWords = memoryTable;

        List<int[]> suitableWords = new ArrayList<>();
        for (int[] word : listOfAllWords) {
            if (Arrays.equals(Arrays.copyOfRange(word, 0, 3), enterValue)) {
                suitableWords.add(word);
            }
        }

        for (int[] word : suitableWords) {
            int[] A = Arrays.copyOfRange(word, 3, 7);
            int[] B = Arrays.copyOfRange(word, 7, 11);
            int[] sum = unionOfTheParts(A, B);
            int[] result = Arrays.copyOf(word, 11 + sum.length);
            System.arraycopy(sum, 0, result, 11, sum.length);
            results.add(result);
        }

        Map<Integer, int[]> finalResults = new HashMap<>();
        for (int i = 0; i < results.size(); i++) {
            finalResults.put(i, results.get(i));
        }

        return finalResults;
    }

    private int[] unionOfTheParts(int[] firstWord, int[] secondWord) {
        int[] firstElement = Arrays.copyOf(firstWord, firstWord.length);
        int[] secondElement = Arrays.copyOf(secondWord, secondWord.length);
        int[] result = new int[Math.max(firstElement.length, secondElement.length)];
        int utilElement = 0;

        int resultIndex = result.length - 1;
        while (firstElement.length > 0 && secondElement.length > 0) {
            int firstDigit = firstElement[firstElement.length - 1];
            int secondDigit = secondElement[secondElement.length - 1];
            int utilResult = firstDigit ^ secondDigit ^ utilElement;
            utilElement = (firstDigit & secondDigit) | ((firstDigit ^ secondDigit) & utilElement);
            result[resultIndex] = utilResult;
            firstElement = Arrays.copyOf(firstElement, firstElement.length - 1);
            secondElement = Arrays.copyOf(secondElement, secondElement.length - 1);
            resultIndex--;
        }

        result = Arrays.copyOf(result, result.length + 1);
        System.arraycopy(result, 0, result, 1, result.length - 1);
        result[0] = utilElement;


        return result;
    }

    public int[][] searchNearestPattern(int[] pattern) {
        List<Integer> differenceList = new ArrayList<>();
        for (int[] word : memoryTable) {
            int utilDifference = 0;
            for (int i = 0; i < sizeOfTable; i++) {
                utilDifference += word[i] != pattern[i] ? 1 : 0;
            }
            differenceList.add(utilDifference);
        }
        int minimalDifference = Integer.MAX_VALUE;
        for (int difference : differenceList) {
            minimalDifference = Math.min(minimalDifference, difference);
        }
        List<int[]> matchingItems = new ArrayList<>();
        for (int i = 0; i < differenceList.size(); i++) {
            if (differenceList.get(i) == minimalDifference) {
                matchingItems.add(memoryTable[i]);
            }
        }
        int[][] result = new int[matchingItems.size()][sizeOfTable];
        for (int i = 0; i < matchingItems.size(); i++) {
            result[i] = matchingItems.get(i);
        }
        return result;
    }

    public void showTable() {
        for (int[] ints : memoryTable) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public void showDiagonalTable() {
        int[][] diagonalTable = obtainWord();
        for (int[] ints : diagonalTable) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public int[] F0() {
        return new int[]{0, 0, 0, 0};
    }

    public void F5(int[] argument) {
        System.out.println(Arrays.toString(argument));
    }

    public int[] F10(int[] argument) {
        int[] result = new int[argument.length];
        for (int i = 0; i < argument.length; i++) {
            result[i] = argument[i] == 0 ? 1 : 0;
        }
        return result;
    }

    public int[] F15() {
        return new int[]{1, 1, 1, 1};
    }
}