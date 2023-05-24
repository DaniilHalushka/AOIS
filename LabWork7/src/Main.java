import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Integer> arrayOfDigits = new ArrayList<>() {{
            add(156);
            add(16);
            add(59);
            add(247);
            add(89);
            add(73);
            add(186);
            add(6);
            add(38);
            add(81);
        }};

        List<String> arrayOfBinaryDigits = new ArrayList<>();
        for (int digit : arrayOfDigits) {
            String binaryNumber = BinaryConverter.decimalToBinary(digit);
            arrayOfBinaryDigits.add(binaryNumber);
        }
        System.out.println(arrayOfBinaryDigits);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("If you want to search by match - press 1");
            System.out.println("If you want to search by nearest top (bottom) meaning - press 2");
            System.out.println("If you want to exit - press 0");

            System.out.print("Enter variant: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Enter the number: ");
                    int numberToSearch = Integer.parseInt(scanner.nextLine());
                    String digitToSearch = BinaryConverter.decimalToBinary(numberToSearch);
                    System.out.println("You entered: " + BinaryConverter.binaryToDecimal(digitToSearch)
                            + "(" + digitToSearch + ")");
                    SearchByMatch.searchByMatch(arrayOfBinaryDigits, digitToSearch);
                }
                case "2" -> {
                    System.out.print("Enter the number: ");
                    int numberToSearch = Integer.parseInt(scanner.nextLine());
                    String digitToSearch = BinaryConverter.decimalToBinary(numberToSearch);
                    NearestSearch.nearestSearch(arrayOfBinaryDigits, digitToSearch);
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("Incorrect input");
            }
        }
    }
}