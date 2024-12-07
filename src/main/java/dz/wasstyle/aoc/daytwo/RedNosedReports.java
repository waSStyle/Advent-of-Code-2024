package dz.wasstyle.aoc.daytwo;

import dz.wasstyle.aoc.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RedNosedReports {

    private static String FILE_PATH = "day2_input.txt";

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

    /**
     * Converts a string sequence to an arraylist of integers
     * @param string The string to slice into arraylist of integers
     * @return The integer's arraylist
     */
    private static ArrayList<Integer> stringSequenceToIntegerArrayList(String string) {
        Main.sendDetails("-------------------------stringSequenceToIntegerArrayList()----------------------------------");
        Main.sendDetails("Starting to converting string to an arraylist");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(String element: string.split(" ")) {
            try {
                arrayList.add(Integer.parseInt(element));
            } catch(NumberFormatException e) {
                System.err.println("Invalid file format should be integers");
                return null;
            }
        }
        Main.sendDetails("Converted to an arraylist");
        Main.sendDetails("-------------------------stringSequenceToIntegerArrayList()----------------------------------");
        return arrayList;
    }

    /**
     * Converts an arraylist of numerical strings to an ArrayList of integers than storing this last in a ArrayList
     * @param arrayList the strings array list to convert
     * @return The head ArrayList that contains ArrayLists of sliced strings
     */
    private static ArrayList<ArrayList<Integer>> arrayToSequences(ArrayList<String> arrayList) {
        Main.sendDebugMessage("-------------------------sequencesToArray()----------------------------------");
        Main.sendDebugMessage("Converting arraylist to sequences...");
        ArrayList<ArrayList<Integer>> arraylists = new ArrayList<>();
        for(String element: arrayList) {
            arraylists.add(stringSequenceToIntegerArrayList(element));
        }
        Main.sendDebugMessage("Converted arraylist to sequences");
        Main.sendDebugMessage("-------------------------sequencesToArray()----------------------------------");
        return arraylists;
    }

    /**
     * Checks if an integers arraylist is monotone
     * @param arrayList the array list that has to be checked
     * @return Whether the array list is monotone
     */
    private static boolean isMontone(ArrayList<Integer> arrayList) {
        Main.sendDetails("-------------------------isMonotone()----------------------------------");
        Main.sendDetails("Checking if sequence is monotone");
        if(isDecreasing(arrayList) || isIncreasing(arrayList)) return true;
        Main.sendDetails("-------------------------isMonotone()----------------------------------");
        return false;
    }

    /**
     * Checks if an integers arraylist is sorted increasingly
     * @param arrayList the array list that has to be checked
     * @return Whether the array list is increasing
     */
    private static boolean isIncreasing(ArrayList<Integer> arrayList) {
        Main.sendDetails("-------------------------isIncreasing()----------------------------------");
        for(int i = 0; i < arrayList.toArray().length - 1; i++) {
            if(!(arrayList.get(i) <= arrayList.get(i+1))) return false;
        }
        Main.sendDetails("-------------------------isIncreasing()----------------------------------");
        return true;
    }

    /**
     * Checks if an integers arraylist is sorted decreasingly
     * @param arrayList the array list that has to be checked
     * @return Whether the array list is decreasing
     */
    private static boolean isDecreasing(ArrayList<Integer> arrayList) {
        Main.sendDetails("-------------------------isDecreasing()----------------------------------");
        for(int i = 0; i < arrayList.toArray().length - 1; i++) {
            if(!(arrayList.get(i) >= arrayList.get(i+1))) return false;
        }
        Main.sendDetails("-------------------------isDecreasing()----------------------------------");
        return true;
    }

    /**
     * Verifies if the distance between each element of an integers array list is between 1 and 3
     * @param arrayList The array list that has to be checked
     * @return Whether the distance is valid or no
     */
    private static boolean isDistanceValid(ArrayList<Integer> arrayList) {
        Main.sendDetails("-------------------------isDistanceValid()----------------------------------");
        for(int i = 0; i < arrayList.toArray().length - 1; i++) {
            int distance = Math.abs(arrayList.get(i) - arrayList.get(i+1));
            if(!(distance >= 1 && distance <= 3)) return false;
        }
        Main.sendDetails("-------------------------isDistanceValid()----------------------------------");
        return true;
    }

    /**
     * Checks if the sequences completes the two conditions
     * @param arrayList The array list that has to be checked
     * @return Whether the sequence is safe or no
     */
    private static boolean isSequenceSafe(ArrayList<Integer> arrayList) {
        Main.sendDetails("-------------------------isSequenceSafe()----------------------------------");
        if(isMontone(arrayList) && isDistanceValid(arrayList)) return true;
        Main.sendDetails("-------------------------isSequenceSafe()----------------------------------");
        return false;
    }

    /**
     * Checks if the sequences completes the two conditions with removing only one element from the sequence
     * @param arrayList The array list that has to be checked
     * @return Whether the sequence is safe or no
     */
    private static boolean isSequenceSafePD(ArrayList<Integer> arrayList) {
        Main.sendDetails("-------------------------isSequenceSafePD()----------------------------------");
        for(int i = 0; i < arrayList.toArray().length; i++) {
            int e = arrayList.get(i);
            arrayList.remove(i);
            if(isMontone(arrayList) && isDistanceValid(arrayList)) return true;
            arrayList.add(i, e);
        }
        Main.sendDetails("-------------------------isSequenceSafePD()----------------------------------");
        return false;
    }

    /**
     * Counts the number of safe sequences in the ArrayList
     * @param arrayList The head array list of sequences to check
     * @return the count of safe sequences found in the array lists
     */
    private static int safeSequencesCount(ArrayList<ArrayList<Integer>> arrayList) {
        Main.sendDebugMessage("-------------------------safeSequencesCount()----------------------------------");
        Main.sendDebugMessage("Starting counting safe sequences...");
        int stacker = 0;
        for(ArrayList<Integer> sequence : arrayList) {
            if(isSequenceSafe(sequence) || isSequenceSafePD(sequence)) stacker++;
        }
        Main.sendDebugMessage("Finalized counting of safe sequences");
        Main.sendDebugMessage("-------------------------safeSequencesCount()----------------------------------");
        return stacker;
    }

    public static void execute() {

        File file = Main.getResource(FILE_PATH);
        ArrayList<String> inputs = fileToArray(file);
        ArrayList<ArrayList<Integer>> sequences = arrayToSequences(inputs);
        System.out.println("[AOC DAY 2] Safe sequences count: " + safeSequencesCount(sequences));

    }

}
