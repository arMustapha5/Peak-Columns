import java.util.InputMismatchException;
import java.util.Scanner;

public class PeakColumnFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = 0, cols = 0;

        // Step 1: Input the dimensions of the matrix with validation (No spaces allowed)
        rows = getValidatedInput(scanner, "Enter the number of rows for Matrix A: ");
        cols = getValidatedInput(scanner, "Enter the number of columns for Matrix A: ");

        // Step 2: Initialize the matrix with input validation
        int[][] matrix = new int[rows][cols];
        System.out.println("Enter elements of Matrix A:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = getValidatedInput(scanner, "A[" + (i + 1) + "][" + (j + 1) + "]: ");
            }
        }

        // Step 3: Search for peak-columns
        System.out.println("\nPeak-columns found at:");
        boolean foundPeak = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isPeakColumn(matrix, i, j, rows, cols)) {
                    System.out.println("(" + (i + 1) + "," + (j + 1) + ") = " + matrix[i][j]);
                    foundPeak = true;
                }
            }
        }

        // Step 4: If no peak-columns found
        if (!foundPeak) {
            System.out.println("No peak-columns found.");
        }

        scanner.close();
    }

    // Method to validate input (No spaces allowed)
    private static int getValidatedInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim(); // Read input as a string and trim spaces

            // Check if input contains only digits (no spaces or other characters)
            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Invalid input. Please enter a positive integer with no spaces.");
            }
        }
    }

    // Method to check if an element is a peak-column
    private static boolean isPeakColumn(int[][] matrix, int row, int col, int rows, int cols) {
        // Check if it's the maximum in its row
        for (int j = 0; j < cols; j++) {
            if (matrix[row][j] > matrix[row][col]) {
                return false;
            }
        }

        // Check if it's the minimum in its column
        for (int i = 0; i < rows; i++) {
            if (matrix[i][col] < matrix[row][col]) {
                return false;
            }
        }

        return true; // It's a peak-column
    }
}
