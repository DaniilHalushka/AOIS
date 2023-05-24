import java.util.*;
import java.util.stream.Collectors;

public class UtilMethodsForMinimisation {
    private static final List<String> VARIABLES_IN_THE_HEADER = new ArrayList<>(Arrays.asList("!B !C", "!B C", "B C", "B !C"));
    private static final List<String> VARIABLES_IN_THE_LEFT_PART = new ArrayList<>(Arrays.asList("!A", "A"));
    public static final String OPENING_BRACKET = "(";
    public static final String CLOSED_BRACKET = ")";
    public static final String FORMAT = "%20s";
    public static final String IS_EXIST = "X";
    public static final String IS_NOT_EXIST = "--";



    private static List<String> separateFormula(String startedFormula, boolean isANDOperator) {
        String operator = isANDOperator ? "&" : "\\|";
        return List.of(startedFormula.split(operator));
    }

    private static String inverseFormula(String startedFormula) {
        return (startedFormula.charAt(0) == '!') ? startedFormula.substring(1) : "!" + startedFormula;
    }


    private static String findIntersection(String stringValue1, String stringValue2, boolean isANDOperator) {
        char stringOperator = isANDOperator ? '&' : '|';
        List<String> firstString = separateFormula(stringValue1, isANDOperator);
        List<String> secondString = separateFormula(stringValue2, isANDOperator);
        StringBuilder result = new StringBuilder();
        int counter = 0;

        for (String startString : firstString) {
            counter += checkStringMatch(startString, secondString, result, stringOperator);
            if (counter == firstString.size() - 1) {
                break;
            }
        }

        return formatResult(result, stringOperator);
    }

    private static List<String> separateFormulaWithBrackets(String startedFormula, boolean isANDOperator) {
        List<String> result = new ArrayList<>();
        String operator = isANDOperator ? "\\|" : "&";
        String[] fragmentsArray = startedFormula.split(operator);
        if (fragmentsArray.length > 1) {
            for (String fragment : fragmentsArray) {
                result.add(fragment.substring(1, fragment.length() - 1));
            }
        } else if (fragmentsArray.length == 1) {
            result.add(startedFormula);
        }
        return result;
    }

    private static String formatResult(StringBuilder result, char stringOperator) {
        if (result.length() == 0 || result.indexOf(String.valueOf(stringOperator)) == -1) {
            return "";
        } else {
            return OPENING_BRACKET + result + CLOSED_BRACKET;
        }
    }
    private static int checkStringMatch(String startString, List<String> secondString, StringBuilder result, char stringOperator) {
        int counter = 0;

        for (String nextString : secondString) {
            if (startString.equals(nextString)) {
                counter++;
                if (result.length() == 0) {
                    result.append(startString);
                } else {
                    result.append(stringOperator).append(startString);
                }
            }
        }

        return counter;
    }

    private static int checkExcess(List<String> functionalParts, int index, boolean isANDOperator) {
        List<List<String>> matrixOfConstituents = createMatrixOfConstituents(functionalParts, isANDOperator);
        List<String> tempPart = matrixOfConstituents.get(index);
        return findMatchingIndex(matrixOfConstituents, tempPart);
    }

    private static String glueParts(String startedFormula, boolean isANDOperator) {
        char utilReverseOperator = isANDOperator ? '|' : '&';
        StringBuilder result = new StringBuilder();

        List<String> partsOfFormula = separateFormulaWithBrackets(startedFormula, isANDOperator);

        for (int i = 0; i < partsOfFormula.size(); i++) {
            for (int j = i + 1; j < partsOfFormula.size(); j++) {
                String intersection = findIntersection(partsOfFormula.get(i), partsOfFormula.get(j), isANDOperator);
                if (!intersection.equals("")) {
                    if (result.length() == 0) {
                        result.append(intersection);
                    } else {
                        result.append(utilReverseOperator).append(intersection);
                    }
                }
            }
            partsOfFormula.remove(i);
            i--;
        }
        return result.toString();
    }

    private static int findMatchingIndex(List<List<String>> matrixOfConstituents, List<String> tempPart) {
        int result = -1;
        for (int i = 0; i < matrixOfConstituents.size(); i++) {
            List<String> currentPart = matrixOfConstituents.get(i);
            if (isExcessPart(currentPart, tempPart)) {
                result = i;
                break;
            }
        }
        return result;
    }

    private static List<List<String>> createMatrixOfConstituents(List<String> functionalParts, boolean isANDOperator) {
        List<List<String>> matrixOfConstituents = new ArrayList<>();
        for (String functionalPart : functionalParts) {
            List<String> constituents = separateFormula(functionalPart, isANDOperator);
            matrixOfConstituents.add(constituents);
        }
        return matrixOfConstituents;
    }

    private static int countMatchingElements(List<String> list1, List<String> list2) {
        int count = 0;
        for (String item : list1) {
            if (list2.contains(item)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isExcessPart(List<String> currentPart, List<String> tempPart) {
        int matchingCount = countMatchingElements(currentPart, tempPart);
        return matchingCount == tempPart.size() - 1 && isReversed(currentPart, tempPart);
    }

    private static List<String> unitedString(List<String> stringValue1, List<String> stringValue2) {
        return stringValue2.stream()
                .filter(stringValue1::contains)
                .collect(Collectors.toList());
    }

    private static boolean isReversed(List<String> stringValue1, List<String> stringValue2) {
        for (var string : stringValue2) {
            if (stringValue1.contains(inverseFormula(string))) {
                return true;
            }
        }
        return false;
    }



    private static String transformNormalFormToTDF(String initialString, boolean isANDOperator) {
        List<String> utilTokens = separateFormulaWithBrackets(initialString, isANDOperator);
        StringBuilder result = new StringBuilder();
        String utilStringOperator = isANDOperator ? "&" : "\\|";
        String utilReversedOperator = !isANDOperator ? "&" : "|";
        for (int i = 0; i < utilTokens.size(); i++) {
            String uniqueValue = "";
            if (checkExcess(utilTokens, i, isANDOperator) != -1) {
                List<String> tokenList = Arrays.asList(utilTokens.get(i).split(utilStringOperator));
                List<String> checkedTokenList = Arrays.asList(utilTokens.get(checkExcess(utilTokens, i, isANDOperator)).split(utilStringOperator));
                String unitedFormula = transformListToNormalForm(unitedString(tokenList, checkedTokenList), isANDOperator);
                uniqueValue = uniqueValue + OPENING_BRACKET + unitedFormula + CLOSED_BRACKET;
                utilTokens.remove(checkExcess(utilTokens, i, isANDOperator));
                utilTokens.remove(i);
                i--;
            } else uniqueValue = uniqueValue + OPENING_BRACKET + utilTokens.get(i) + CLOSED_BRACKET;
            if (result.toString().equals("")) {
                result.append(uniqueValue);
            } else {
                if (result.indexOf(uniqueValue) == -1) {
                    result.append(utilReversedOperator).append(uniqueValue);
                }
            }
        }
        return result.toString();
    }

    private static String transformListToNormalForm(List<String> list, boolean isANDOperator) {
        String utilReversedOperator = !isANDOperator ? "&" : "|";
        StringBuilder result = new StringBuilder();
        for (var element : list) {
            if (result.toString().equals(""))
                result.append(element);
            else {
                result.append(utilReversedOperator).append(element);
            }
        }
        return result.toString();
    }

    private static boolean existence(String formula, String inputValue, boolean isANDOperator) {
        String utilReverseOperator = isANDOperator ? "\\|" : "&";
        String[] tokensInImplications = formula.split(utilReverseOperator);
        List<String> tokensInConstituents = Arrays.asList(inputValue.substring(1, inputValue.length() - 1).split(utilReverseOperator));
        for (var formulaInImplications : tokensInImplications) {
            if (!tokensInConstituents.contains(formulaInImplications)) {
                return false;
            }
        }
        return true;
    }

    private static void printValues(String PNF, boolean isANDOperator) {
        System.out.format(FORMAT, "----------");
        var existenceConstituents = separateFormula(PNF, !isANDOperator);
        for (var constituent : existenceConstituents) {
            System.out.format(FORMAT, constituent);
        }

        var existenceImplications = separateFormula(glueParts(PNF, isANDOperator), !isANDOperator);

        List<List<Integer>> transformConstituentsToImplications = new ArrayList<>();

        for (String implications : existenceImplications) {
            System.out.format("\n%20s", implications);
            List<Integer> isImplicationCorrect = new ArrayList<>();
            for (int indexOfConstituents = 0; indexOfConstituents < existenceConstituents.size(); indexOfConstituents++) {
                if (existence(implications.substring(1, implications.length() - 1), existenceConstituents.get(indexOfConstituents), !isANDOperator)) {
                    System.out.format(FORMAT, IS_EXIST);
                    isImplicationCorrect.add(indexOfConstituents);
                } else System.out.format(FORMAT, IS_NOT_EXIST);
            }
            transformConstituentsToImplications.add(isImplicationCorrect);
        }

        List<Integer> result = new ArrayList<>();

        Map<Integer, Boolean> areConstituentsSwitched = createNegativeHashMap(existenceConstituents);

        for (Map.Entry<Integer, Boolean> constituent : areConstituentsSwitched.entrySet()) {
            if (getImplicationsOfConstituents(constituent.getKey(), transformConstituentsToImplications).size() == 1) {
                makeTrueConstituent(areConstituentsSwitched, transformConstituentsToImplications.get(getImplicationsOfConstituents(constituent.getKey(), transformConstituentsToImplications).get(0)));
                result.add(getImplicationsOfConstituents(constituent.getKey(), transformConstituentsToImplications).get(0));
            }
        }


        while (!isFinalTable(areConstituentsSwitched)) {
            int optimumSelection = selectOptimalOption(areConstituentsSwitched, transformConstituentsToImplications);
            makeTrueConstituent(areConstituentsSwitched, transformConstituentsToImplications.get(optimumSelection));
            result.add(optimumSelection);
        }

        printResult(existenceImplications, result, isANDOperator);
    }



    private static Map<Integer, Integer> calculateNotChangedImplications(List<Integer> notChangedConstituents, List<List<Integer>> convertToImplications) {
        Map<Integer, Integer> notChangedImplications = new HashMap<>();

        for (int index = 0; index < convertToImplications.size(); index++) {
            List<Integer> implication = convertToImplications.get(index);
            int intersectionSize = calculateIntersectionSize(notChangedConstituents, implication);
            notChangedImplications.put(index, intersectionSize);
        }

        return notChangedImplications;
    }

    private static int selectOptimalOption(Map<Integer, Boolean> changedConstituents, List<List<Integer>> convertToImplications) {
        List<Integer> notChangedConstituents = new ArrayList<>();

        for (Map.Entry<Integer, Boolean> constituent : changedConstituents.entrySet()) {
            if (!constituent.getValue()) {
                notChangedConstituents.add(constituent.getKey());
            }
        }

        Map<Integer, Integer> notChangedImplications = calculateNotChangedImplications(notChangedConstituents, convertToImplications);

        return findMaxValue(notChangedImplications);
    }

    private static int calculateIntersectionSize(List<Integer> list1, List<Integer> list2) {
        List<Integer> intersection = new ArrayList<>(list1);
        intersection.retainAll(list2);
        return intersection.size();
    }

    private static int findMaxValue(Map<Integer, Integer> map) {
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (value > max) {
                max = value;
                maxIndex = entry.getKey();
            }
        }

        return maxIndex;
    }

    private static Map<Integer, Boolean> createNegativeHashMap(List<String> listOfConstituents) {
        Map<Integer, Boolean> createdMap = new HashMap<>();
        for (int i = 0; i < listOfConstituents.size(); i++) {
            createdMap.put(i, false);
        }
        return createdMap;
    }

    private static List<Integer> getImplicationsOfConstituents(int constituent, List<List<Integer>> constituents) {
        List<Integer> implications = new ArrayList<>();
        for (int i = 0; i < constituents.size(); i++) {
            if (constituents.get(i).contains(constituent)) {
                implications.add(i);
            }
        }
        return implications;
    }

    private static Map<Integer, Boolean> makeTrueConstituent(Map<Integer, Boolean> transformMap, List<Integer> numberValues) {
        for (var value : numberValues) {
            transformMap.put(value, true);
        }
        return transformMap;
    }

    private static boolean isFinalTable(Map<Integer, Boolean> changedConstituents) {
        for (Map.Entry<Integer, Boolean> entry : changedConstituents.entrySet()) {
            if (!entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    private static void printResult(List<String> listOfImplications, List<Integer> source, boolean isANDOperator) {
        String operator = isANDOperator ? "&" : "|";
        StringBuilder result = new StringBuilder();

        for (int value : source) {
            if (result.length() > 0) {
                result.append(operator);
            }
            result.append(listOfImplications.get(value));
        }

        String normalForm = transformNormalFormToTDF(result.toString(), isANDOperator);
        System.out.println("\n" + result);
        System.out.println("The result of minimisation: " + normalForm);
    }

    private static void printTopVariables() {
        for (String variable : VARIABLES_IN_THE_HEADER) {
            System.out.printf(FORMAT, variable);
        }
    }

    private static String getKarnaughValues(String[] values) {
        StringBuilder value = new StringBuilder();
        int halfLength = values.length / 2;

        for (int i = 0; i < halfLength; i++) {
            value.append(values[i]);
        }
        value.append("\\");
        for (int i = halfLength; i < values.length; i++) {
            value.append(values[i]);
        }

        return value.toString();
    }

    private static List<String> Karnaugh(List<List<Boolean>> terms) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < terms.size(); i++) {
            for (int start = 0; start < terms.get(i).size(); start++) {
                if (start != terms.get(i).size() - 1) {
                    processInnerLoop(terms, i, start, result);
                } else {
                    processLastIteration(terms, i, start, result);
                }
            }
        }
        makeUnique(result);
        return result;
    }

    private static void minimizationOfKarnaugh(String[] values, boolean[] arrayOfBooleanResults) {
        String variables = getKarnaughValues(values);
        System.out.printf(FORMAT, variables);
        printTopVariables();
        List<List<Boolean>> parts = createKarnaughMap(arrayOfBooleanResults);

        for (int i = 0; i < VARIABLES_IN_THE_LEFT_PART.size(); i++) {
            System.out.println();
            System.out.printf(FORMAT, VARIABLES_IN_THE_LEFT_PART.get(i));
            for (Boolean booleanValue : parts.get(i)) {
                System.out.printf(FORMAT, UtilOperationsWithExpression.booleanValueToInt(booleanValue));
            }
        }
        printKarnaugh(parts);
    }

    private static void processInnerLoop(List<List<Boolean>> terms, int i, int start, List<String> result) {
        for (int finish = terms.get(i).size() - 1; finish >= start; finish--) {
            if (checkRowInTable(terms.get(i), start, finish - start)) {
                if (i != terms.size() - 1 && checkRowInTable(terms.get(i + 1), start, finish - start)) {
                    processUnion(terms, start, finish, i, i + 1, result);
                } else {
                    if (start - finish != 0) {
                        processAtomTerms(start, finish, i, result);
                    }
                }
            }
        }
    }

    private static void processLastIteration(List<List<Boolean>> terms, int i, int start, List<String> result) {
        if (terms.get(i).get(start)) {
            if (i != terms.size() - 1 && !terms.get(i + 1).get(start)) {
                processCounter(terms, start, i, result);
            } else {
                if (i != terms.size() - 1 && terms.get(i + 1).get(start)) {
                    processUniqueVariables(terms, start, i, result);
                }
            }
        } else {
            if (i != terms.size() - 1 && !terms.get(i + 1).get(start)) {
                processUniqueTerms(start, i, result);
            }
        }
    }

    private static void processUnion(List<List<Boolean>> terms, int start, int finish, int i, int j, List<String> result) {
        if (!terms.get(i).get(start) && !terms.get(i).get(terms.get(i).size() - 1) && !terms.get(j).get(start) && !terms.get(j).get(terms.get(i).size() - 1)) {
            var atomTopTerms = receiveAtomTermsArray(start, finish - start, i);
            var atomDownTerms = receiveAtomTermsArray(start, finish - start, j);
            var union = unitedString(atomDownTerms, atomTopTerms);
            result.add(OPENING_BRACKET + transformListToNormalForm(union, false) + CLOSED_BRACKET);
        }
    }

    private static void processAtomTerms(int start, int finish, int i, List<String> result) {
        var atomTerms = receiveAtomTermsArray(start, finish - start, i);
        result.add(OPENING_BRACKET + transformListToNormalForm(atomTerms, false) + CLOSED_BRACKET);
    }

    private static void processCounter(List<List<Boolean>> terms, int start, int i, List<String> result) {
        int counter = 0;
        for (int finish = terms.get(i).size() - 1; finish >= 0; finish--) {
            if (checkRowInTable(terms.get(i), 0, finish - 1)) {
                var atomTerms = receiveAtomTermsArray(0, finish - 1, i);
                var buffer = receiveAtomTermsArray(terms.get(i).size() - 1, 0, i);
                var union = unitedString(atomTerms, buffer);
                result.add(OPENING_BRACKET + transformListToNormalForm(union, false) + CLOSED_BRACKET);
                counter++;
            }
        }
        if (counter == 0) {
            if (checkRowInTable(terms.get(i), start, 0) && i != terms.size() - 1 && checkRowInTable(terms.get(i + 1), start, 0)) {
                var atomTopTerms = receiveAtomTermsArray(start, 0, i);
                var atomDownTerms = receiveAtomTermsArray(start, 0, i + 1);
                var union = unitedString(atomDownTerms, atomTopTerms);
                result.add(OPENING_BRACKET + transformListToNormalForm(union, false) + CLOSED_BRACKET);
            }
        }
    }

    private static void processUniqueVariables(List<List<Boolean>> variables, int start, int i, List<String> result) {
        var atomTopVars = receiveAtomTermsArray(start, 0, i);
        var atomDownVars = receiveAtomTermsArray(start, 0, i + 1);
        var uniqueTerms = unitedString(atomDownVars, atomTopVars);
        for (int finish = 0; finish < variables.get(i).size() - 1; finish++) {
            var topVars = receiveAtomTermsArray(0, finish, i);
            var downVars = receiveAtomTermsArray(0, finish, i + 1);
            var union = unitedString(topVars, downVars);
            result.add(OPENING_BRACKET + transformListToNormalForm(unitedString(uniqueTerms, union), false) + CLOSED_BRACKET);
        }
    }

    private static void processUniqueTerms(int start, int i, List<String> result) {
        var atomTerms = receiveAtomTermsArray(start, 0, i);
        result.add(OPENING_BRACKET + transformListToNormalForm(atomTerms, false) + CLOSED_BRACKET);
    }


    private static List<String> receiveAtomTermsArray(int start, int count, int nextTerm) {
        List<List<String>> termsArray = new ArrayList<>();
        for (int i = start; i < start + count + 1; i++) {
            var buffer = new ArrayList<>(Arrays.asList(VARIABLES_IN_THE_HEADER.get(i).split(" ")));
            buffer.add(VARIABLES_IN_THE_LEFT_PART.get(nextTerm));
            termsArray.add(buffer);
        }
        List<String> result = new ArrayList<>(termsArray.get(0));
        for (int i = 1; i < termsArray.size(); i++) {
            result = unitedString(result, termsArray.get(i));
        }
        return result;
    }

    private static List<String> makeUnique(List<String> list) {
        Set<String> set = new HashSet<>(list);
        List<String> uniqueList = new ArrayList<>(set);
        uniqueList.remove(null);
        uniqueList.remove("()");
        return uniqueList;
    }

    private static boolean checkRowInTable(List<Boolean> listOfBooleans, int start, int count) {
        if (isPowerOfTwo(count + 1)) {
            for (int i = start; i < start + count + 1; i++) {
                if (!listOfBooleans.get(i)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private static boolean isPowerOfTwo(double a) {
        double variable = Math.log(a) / Math.log(2);
        return (int) variable == variable;
    }

    private static List<List<Boolean>> createKarnaughMap(boolean[] results) {
        List<List<Boolean>> result = new ArrayList<>();
        int counter = 0;
        for (int i = 1; i < 3; i++) {
            List<Boolean> buffer = new ArrayList<>();
            int halfLength = results.length / 2;
            while (counter < halfLength * i) {
                if (halfLength * i - counter == 2) {
                    buffer.add(results[counter + 1]);
                } else if (halfLength * i - counter == 1) {
                    buffer.add(results[counter - 1]);
                } else
                    buffer.add(results[counter]);
                counter++;
            }
            result.add(buffer);
        }
        return result;
    }

    private static void printKarnaugh(List<List<Boolean>> terms) {
        List<String> result = Karnaugh(terms);
        printKarnaughResults(result);
    }

    private static void printKarnaughResults(List<String> results) {
        StringBuilder result = new StringBuilder();
        String utilReverseOperator = "|";
        for (var value : results) {
            if (result.length() == 0) {
                result.append(value);
            } else {
                result.append(utilReverseOperator).append(value);
            }
        }
        var resultOfMinimisation = transformNormalFormToTDF(result.toString(), true);
        System.out.println("\nThe result of minimization: " + resultOfMinimisation);
    }

    public static boolean printMinimizeResults(String[] variables, List<List<Boolean>> table, boolean[] result) {
        String NDF;
        String defaultPNDF = UtilMethodsForPNDF.PNDF(variables, table, result).replaceAll(" ", "");
        String defaultPNCF = UtilMethodsForPNCF.PNCF(variables, table, result).replaceAll(" ", "");
        String TNDF;
        String NCF;
        String TNCF;
        NDF = glueParts(defaultPNDF, true);
        TNDF = transformNormalFormToTDF(NDF, true);
        NCF = glueParts(defaultPNCF, false);
        TNCF = transformNormalFormToTDF(NCF, false);

        System.out.println("Calculation method:");
        System.out.println("Started function in PNDF: " + defaultPNDF);

        System.out.println("NDF: " + NDF);
        System.out.println("Dead-end NDF: " + TNDF);
        System.out.println("Started function in PNCF: " + defaultPNCF);
        System.out.println("NCF: " + NCF);
        System.out.println("Dead-end NCF: " + TNCF);

        System.out.println("\n\nCalculation-table method\nPNDF: " + defaultPNDF + "\n");
        printValues(defaultPNDF, true);

        System.out.println("\n\nCalculation-table method\nPNCF: " + defaultPNCF + "\n");
        printValues(defaultPNCF, false);

        System.out.println("\nMethod of Karnaugh map");
        minimizationOfKarnaugh(variables, result);
        return true;
    }
}
