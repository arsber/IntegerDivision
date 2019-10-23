package ua.arsber.foxminded.integerdivision;

public class Application {
    public static void main(String[] args) {
        DivisionCalculator calculator = new DivisionCalculator();
        int dividend = 123;
        int divisor = 5;
        String s = calculator.makeDivision(dividend, divisor);
        System.out.println(s);
    }
}
