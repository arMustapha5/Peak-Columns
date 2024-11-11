import java.util.Scanner;

public class PeakColumnFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = 0, cols = 0;

        // Step 1: Input the dimensions of the matrix with validation (Allow spaces or commas)
        int[] dimensions = getValidatedDimensions(scanner, "Enter the dimensions (rows, columns) for Matrix A: ");
        rows = dimensions[0];
        cols = dimensions[1];

        // Step 2: Initialize the matrix with input validation for matrix elements
        int[][] matrix = new int[rows][cols];
        System.out.println("Enter elements of Matrix A (row by row, separated by spaces or commas):");
        for (int i = 0; i < rows; i++) {
            int[] rowValues = getRowValues(scanner, cols, "Row " + (i + 1) + ": ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rowValues[j];
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

    // Method to validate matrix dimensions input (Allows space or comma as a separator)
    private static int[] getValidatedDimensions(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Split input by space or comma
            String[] parts = input.split("[,\\s]+");

            if (parts.length == 2 && parts[0].matches("\\d+") && parts[1].matches("\\d+")) {
                int rows = Integer.parseInt(parts[0]);
                int cols = Integer.parseInt(parts[1]);
                return new int[]{rows, cols};
            } else {
                System.out.println("Invalid input. Please enter two integers separated by a space or comma.");
            }
        }
    }

    // Method to get row values (Allows space or comma as a separator)
    private static int[] getRowValues(Scanner scanner, int expectedCols, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Split input by space or comma
            String[] parts = input.split("[,\\s]+");

            if (parts.length == expectedCols) {
                int[] rowValues = new int[expectedCols];
                try {
                    for (int i = 0; i < expectedCols; i++) {
                        rowValues[i] = Integer.parseInt(parts[i]);
                    }
                    return rowValues;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter " + expectedCols + " integers separated by spaces or commas.");
                }
            } else {
                System.out.println("Invalid input. Please enter exactly " + expectedCols + " integers.");
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
