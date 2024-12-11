package dz.wasstyle.aoc.dayfour;

import dz.wasstyle.aoc.Day;
import dz.wasstyle.aoc.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CeresSearch implements Day {

    private static String FILE_PATH = "day4_input.txt";

    /**
     * Storing each line from a file in a strings array list
     * @param file The file to read
     * @return An array list where each element corresponds to a line from the given file
     */
    private static ArrayList<String> fileToArray(File file) {
        Main.sendDebugMessage("-------------------------fileToArray()----------------------------------");
        Main.sendDebugMessage("Converting file to array...");
        ArrayList<String> arrayList = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("File unfounded");
            return null;
        }
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            arrayList.add(line);
            Main.sendDetails("Added line " + line);
        }
        Main.sendDebugMessage("Converted file to array");
        Main.sendDebugMessage("-------------------------fileToArray()----------------------------------");
        return arrayList;
    }

    private static int xmasCountPartTwo(ArrayList<String> text) {
        int count = 0;
        int rows = text.toArray().length;
        if (rows == 0) return 0;
        int cols = text.get(0).length();

        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = text.get(i).toLowerCase().toCharArray();
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                count += checkDiagonalMAS(grid, row, col);
            }
        }

        return count;
    }
    private static int checkDiagonalMAS(char[][] grid, int row, int col) {
        int count = 0;

        if (row - 1 >= 0 && row + 1 < grid.length &&
                col - 1 >= 0 && col + 1 < grid[row].length) {

            boolean condOne = (grid[row][col] == 'a' &&
                    ((grid[row - 1][col - 1] == 'm' && grid[row + 1][col + 1] == 's') ||
                            (grid[row - 1][col - 1] == 's' && grid[row + 1][col + 1] == 'm')));

            boolean condTwo = (grid[row][col] == 'a' &&
                    ((grid[row + 1][col - 1] == 'm' && grid[row - 1][col + 1] == 's') ||
                            (grid[row + 1][col - 1] == 's' && grid[row - 1][col + 1] == 'm')));

            if (condOne && condTwo) {
                count++;
            }
        }

        return count;
    }


    private static int xmasCount(ArrayList<String> text) {
        int count = 0;
        int rows = text.toArray().length;
        if (rows == 0) return 0;
        int cols = text.get(0).length();

        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = text.get(i).toLowerCase().toCharArray();
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                count += checkHorizontal(grid, row, col);
                count += checkVertical(grid, row, col);
                count += checkDiagonalUp(grid, row, col);
                count += checkDiagonalDown(grid, row, col);
            }
        }

        return count;
    }

    private static int checkHorizontal(char[][] grid, int row, int col) {
        int count = 0;
        if (col + 3 < grid[row].length &&
                grid[row][col] == 'x' &&
                grid[row][col + 1] == 'm' &&
                grid[row][col + 2] == 'a' &&
                grid[row][col + 3] == 's') {
            count++;
        }
        if (col - 3 >= 0 &&
                grid[row][col] == 'x' &&
                grid[row][col - 1] == 'm' &&
                grid[row][col - 2] == 'a' &&
                grid[row][col - 3] == 's') {
            count++;
        }
        return count;
    }

    private static int checkVertical(char[][] grid, int row, int col) {
        int count = 0;
        if (row + 3 < grid.length &&
                grid[row][col] == 'x' &&
                grid[row + 1][col] == 'm' &&
                grid[row + 2][col] == 'a' &&
                grid[row + 3][col] == 's') {
            count++;
        }
        if (row - 3 >= 0 &&
                grid[row][col] == 'x' &&
                grid[row - 1][col] == 'm' &&
                grid[row - 2][col] == 'a' &&
                grid[row - 3][col] == 's') {
            count++;
        }
        return count;
    }


    private static int checkDiagonalUp(char[][] grid, int row, int col) {
        int count = 0;
        if (row - 3 >= 0 && col + 3 < grid[row].length &&
                grid[row][col] == 'x' &&
                grid[row - 1][col + 1] == 'm' &&
                grid[row - 2][col + 2] == 'a' &&
                grid[row - 3][col + 3] == 's') {
            count++;
        }
        if (row + 3 < grid.length && col - 3 >= 0 &&
                grid[row][col] == 'x' &&
                grid[row + 1][col - 1] == 'm' &&
                grid[row + 2][col - 2] == 'a' &&
                grid[row + 3][col - 3] == 's') {
            count++;
        }
        return count;
    }

    private static int checkDiagonalDown(char[][] grid, int row, int col) {
        int count = 0;
        if (row - 3 >= 0 && col - 3 >= 0 &&
                grid[row][col] == 'x' &&
                grid[row - 1][col - 1] == 'm' &&
                grid[row - 2][col - 2] == 'a' &&
                grid[row - 3][col - 3] == 's') {
            count++;
        }
        if (row + 3 < grid.length && col + 3 < grid[row].length &&
                grid[row][col] == 'x' &&
                grid[row + 1][col + 1] == 'm' &&
                grid[row + 2][col + 2] == 'a' &&
                grid[row + 3][col + 3] == 's') {
            count++;
        }
        return count;
    }

    @Override
    public void execute() {

        File file = Main.getResource(FILE_PATH);
        ArrayList<String> inputs = fileToArray(file);
        System.out.println("[AOC DAY 4 P1] XMAS Count: " + xmasCount(inputs));
        System.out.println("[AOC DAY 4 P2] X-MAS Count: " + xmasCountPartTwo(inputs));
    }
}
