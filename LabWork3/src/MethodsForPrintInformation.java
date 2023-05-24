import java.util.ArrayList;
import java.util.List;

public class MethodsForPrintInformation {
    private static final List<List<Boolean>> TABLE_OF_TRUTH = new ArrayList<>();
    private static final String COLUMN_FORMAT = "%10s";
    private static final String HEADER_RESULT = "Result";
    private static final String PNDF_TITLE = "PNDF:\n";
    private static final String NUMBER_FORM_OF_PNDF_TITLE = "Number form of PNDF:\n";
    private static final String PNCF_TITLE = "PNCF:\n";
    private static final String NUMBER_FORM_OF_PNCF_TITLE = "Number form of PNCF:\n";
    private static final String INDEX_FORM_TITLE = "Index form:\n";

    public static String[] makeUniqueVariables(String[] arrayString) {
        List<String> uniqueVariables = new ArrayList<>();
        for (var string : arrayString) {
            if (!uniqueVariables.contains(string) && !string.equals("")) {
                uniqueVariables.add(string);
            }
        }
        String[] result = new String[uniqueVariables.size()];
        uniqueVariables.toArray(result);
        return result;
    }

    public static void printInformation(String[] variableValues, boolean[] result) {
        for (String variable : variableValues) {
            System.out.format(COLUMN_FORMAT, variable);
        }
        System.out.format(COLUMN_FORMAT + "%n", HEADER_RESULT);
        for (int i = 0; i < result.length; i++) {
            boolean[] values = ParseExpression.getValues(i, variableValues.length);
            List<Boolean> buffer = new ArrayList<>();
            for (boolean value : values) {
                buffer.add(value);
                System.out.format(COLUMN_FORMAT, value);
            }
            TABLE_OF_TRUTH.add(buffer);
            System.out.format(COLUMN_FORMAT + "%n", result[i]);
        }
        System.out.println(PNDF_TITLE + UtilMethodsForPNDF.PNDF(variableValues, TABLE_OF_TRUTH, result));
        System.out.println(NUMBER_FORM_OF_PNDF_TITLE + UtilMethodsForPNDF.convertPNDFToNumberVariant(result));
        System.out.println(PNCF_TITLE + UtilMethodsForPNCF.PNCF(variableValues, TABLE_OF_TRUTH, result));
        System.out.println(NUMBER_FORM_OF_PNCF_TITLE + UtilMethodsForPNCF.convertPNCFToNumberVariant(result));
        System.out.println(INDEX_FORM_TITLE + UtilOperationsWithExpression.convertExpressionToIndexForm(result));
        System.out.println(UtilMethodsForMinimisation.printMinimizeResults(variableValues, TABLE_OF_TRUTH, result));
    }
}
