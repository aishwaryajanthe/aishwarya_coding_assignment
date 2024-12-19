import java.util.Scanner;
import java.math.BigDecimal;
import java.math.MathContext;

public class FactorialCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Prompt the user to enter a number
            System.out.print("Enter a non-negative integer to calculate its factorial: ");
            String input = scanner.nextLine().trim();

            // Check if the input is empty
            if (input.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty. Please enter a non-negative integer.");
            }

            // Check if the input is numeric
            if (!input.matches("\\d+")) {
                throw new IllegalArgumentException("Input must be a valid non-negative integer.");
            }

            int n = Integer.parseInt(input);

            // Check if the input is negative (this should not happen due to regex, but is extra safety)
            if (n < 0) {
                throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
            }

            if (n > 10000) {
                // Use Stirling's approximation for very large numbers
                BigDecimal approximation = stirlingApproximation(n);
                System.out.println("The approximate factorial of " + n + " is: " + approximation);
            } else {
                // Calculate the factorial exactly for smaller numbers
                BigDecimal factorial = calculateFactorial(n);
                System.out.println("The factorial of " + n + " is: " + factorial);
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Invalid input. Please enter a valid non-negative integer.");
        } finally {
            scanner.close();
        }
    }

    // Method to calculate the factorial using BigDecimal
    private static BigDecimal calculateFactorial(int n) {
        BigDecimal result = BigDecimal.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }
        return result;
    }

    // Method to calculate Stirling's approximation for large factorials
    private static BigDecimal stirlingApproximation(int n) {
        MathContext mc = new MathContext(10); // Set precision
        BigDecimal pi = BigDecimal.valueOf(Math.PI);
        BigDecimal e = BigDecimal.valueOf(Math.E);

        BigDecimal nBig = BigDecimal.valueOf(n);
        BigDecimal sqrtTerm = BigDecimal.valueOf(2 * Math.PI * n).sqrt(mc);
        BigDecimal baseTerm = nBig.pow(n).divide(e.pow(n), mc);

        return sqrtTerm.multiply(baseTerm, mc);
    }
}
