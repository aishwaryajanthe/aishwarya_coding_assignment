public class StringToIntegerConverter {
    public static void main(String[] args) {
        // Input string
        String inputString = "$180,240/y";

        // Remove non-numeric characters
        String numericString = inputString.replaceAll("[^0-9]", "");

        // Convert the numeric string to an integer
        int outputInteger = Integer.parseInt(numericString);

        // Print the result
        System.out.println("Output Integer = " + outputInteger);
    }
}
