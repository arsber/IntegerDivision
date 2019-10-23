package ua.arsber.foxminded.integerdivision;

public class DivisionCalculator {
    public String makeDivision(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor cannot be 0");
        }
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        if (dividend < divisor) {
            return buildLiteOutput(dividend, divisor);
        }
        StringBuilder result = new StringBuilder();
        result = doSubDivision(divisor, dividend);
        buildFullOutput(dividend, divisor, result);
        return result.toString();
    }

    private StringBuilder doSubDivision(int divisor, int dividend) {
        StringBuilder subDivision = new StringBuilder();
        StringBuilder subQuotient = new StringBuilder();
        String[] dividendDigits = String.valueOf(dividend).split("");
        int residue = 0;
        int subQuotientNumber;
        for (int i = 0; i < dividendDigits.length; i++) {
            subQuotient.append(dividendDigits[i]);
            subQuotientNumber = Integer.parseInt(subQuotient.toString());
            if (subQuotientNumber >= divisor) {
                residue = subQuotientNumber % divisor;
                int multiplyResult = subQuotientNumber / divisor * divisor;
                subQuotient.replace(0, subQuotient.length(), String.valueOf(residue));
                addSubDivisionLines(i, subQuotientNumber, multiplyResult, subDivision);
            }
            if (i == dividendDigits.length - 1) {
                addResidueLine(i, residue, subDivision);
            }
        }
        return subDivision;
    }

    private void addSubDivisionLines(int i, int subQuotientNumber, int multiplyResult, StringBuilder result) {
        String subQuotient = String.format("%" + (i + 2) + "s", "_" + String.valueOf(subQuotientNumber));
        result.append(subQuotient).append("\n");
        String multiply = String.format("%" + (i + 2) + "s", multiplyResult);
        result.append(multiply).append("\n");
        int tab = subQuotient.length() - countDigits(multiplyResult);
        String separator = " ".repeat(tab) + "-".repeat(countDigits(multiplyResult));
        result.append(separator).append("\n");
    }

    private void addResidueLine(int i, int residue, StringBuilder result) {
        String residueLine = String.format("%" + (i + 2) + "s", String.valueOf(residue));
        result.append(residueLine).append("\n");
    }

    private String buildLiteOutput(int dividend, int divisor) {
        StringBuilder result = new StringBuilder();
        int tab = countDigits(dividend);
        result.append(dividend).append("|").append(divisor).append("\n");
        result.append(" ".repeat(tab)).append("|-\n");
        result.append(" ".repeat(tab)).append("|0\n");
        return result.toString();
    }

    private void buildFullOutput(int dividend, int divisor, StringBuilder subDivision) {
        int quotient = dividend / divisor;
        int[] stringEndIndex = findStringsEnd(subDivision);
        int tab = countDigits(dividend) + 1 - stringEndIndex[0];
        subDivision.insert(stringEndIndex[2], " ".repeat(tab) + "|" + quotient);
        subDivision.insert(stringEndIndex[1], " ".repeat(tab) + "|" + "-".repeat(String.valueOf(quotient).length()));
        subDivision.replace(1, stringEndIndex[0], String.valueOf(dividend) + "|" + divisor);
    }

    private int[] findStringsEnd(StringBuilder source) {
        int[] index = new int[3];
        for (int i = 0, j = 0; j < 3; i++) {
            if (source.charAt(i) == '\n') {
                index[j] = i;
                j++;
            }
        }
        return index;
    }

    private int countDigits(int number) {
        if (number == 0) {
            return 1;
        }
        int amountOfDigits = 0;
        while (number > 0) {
            number = number / 10;
            amountOfDigits++;
        }
        return amountOfDigits;
    }
}
