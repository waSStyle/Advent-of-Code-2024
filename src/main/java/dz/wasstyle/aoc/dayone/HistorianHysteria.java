package dz.wasstyle.aoc.dayone;

import dz.wasstyle.aoc.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HistorianHysteria {

    private static String FILE_PATH = "day1_input.txt";


    /**
     * Storing each line from a file in a strings array list
     * @param file The file to read
     * @return An array list where each element corresponds to a line from the given file
     */
    private static ArrayList<String> fileToArray(File file) {

        Main.sendDebugMessage("-------------------------fileToArray()----------------------------------");
        Main.sendDebugMessage("Converting input file to an array list...");

        ArrayList<String> arraylist = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Input file unfound");
            return arraylist;
        }

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for(String sequence: line.split("   ")) {
                arraylist.add(sequence);
                Main.sendDetails("Added " + sequence + " to the array");
            }
        }

        Main.sendDebugMessage("File successfully converted to an array");
        Main.sendDebugMessage("-------------------------fileToArray()----------------------------------");
        return arraylist;

    }

    /**
     * Storing each element of a string arraylist as an integer in an arraylist of integer itself in an arraylist, in the first one if the element's index in the string arraylist is odd and the second if it's even
     * @param stringArray The string array to convert to two integer array lists
     * @return An array list of two integers array lists each containing int sequences
     */
    private static ArrayList<ArrayList<Integer>> stringToIntegerArray(ArrayList<String> stringArray) {
        Main.sendDebugMessage("-------------------------stringToIntegerArray()----------------------------------");
        Main.sendDebugMessage("Converting strings array to integers array");
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        arrayList.add(new ArrayList<Integer>());
        arrayList.add(new ArrayList<Integer>());
        for(int i=0;i<stringArray.toArray().length;i++)
        {
            int sequence;
            try {
                sequence = Integer.parseInt(stringArray.get(i));
            } catch (NumberFormatException e) {
                System.err.println("Invalid file format");
                return null;
            }

            if(i%2 == 0) {
                arrayList.get(0).add(sequence);
                Main.sendDetails("Added " + sequence + " left");
            } else {
                arrayList.get(1).add(sequence);
                Main.sendDetails("Added " + sequence + " right");
            }
        }

        Main.sendDebugMessage("Array successfully converted to two integers arrays");
        Main.sendDebugMessage("-------------------------stringToIntegerArray()----------------------------------");
        return arrayList;
     }

    /**
     * Sorts by ascendancy the elements of one of multiple given integers array list(s)
     * @param arrayLists The array list to sort
     */
    private static void sortArray(ArrayList<Integer>... arrayLists) {
        Main.sendDebugMessage("-------------------------sortArray()----------------------------------");
        Main.sendDebugMessage("Sorting arraylist");

        for(ArrayList<Integer> arrayList: arrayLists){
            int n = arrayList.size();

            for (int i = 1; i < n; i++) {
                int key = arrayList.get(i);
                int j = i - 1;

                while (j >= 0 && arrayList.get(j) > key) {
                    arrayList.set(j + 1, arrayList.get(j));
                    j--;
                }

                arrayList.set(j + 1, key);
            }

            Main.sendDebugMessage("Array list sorted");
            Main.sendDebugMessage("-------------------------sortArray()----------------------------------");
        }
    }

    /**
     * Getting the added-up distances between each element of the first arraylist having and the element with the same index in the second arraylist
     * @param arrayList The array list that contains the two arraylists
     * @return The distance
     */
    private static int getDistance(ArrayList<ArrayList<Integer>> arrayList)  {
        Main.sendDebugMessage("-------------------------getDistance()----------------------------------");
        Main.sendDebugMessage("Calculating distance...");
        int distance = 0;

        ArrayList<Integer> firstArray = arrayList.get(0);
        ArrayList<Integer> secondArray = arrayList.get(1);

        if(firstArray.toArray().length != secondArray.toArray().length) {
            System.err.println("The two sides doesn't have the same amount of sequences");
            return -1;
        }

        for(int i = 0; i<firstArray.toArray().length; i++) {
            Main.sendDetails("Calculating distance between " + firstArray.get(i) + " and " + secondArray.get(i) + " got " + Math.abs(firstArray.get(i) - secondArray.get(i)));
            distance += Math.abs(firstArray.get(i) - secondArray.get(i));
        }
        Main.sendDebugMessage("Calculated distance");
        Main.sendDebugMessage("-------------------------getDistance()----------------------------------");
        return distance;
    }

    public static void execute() {
        File file = Main.getResource(FILE_PATH);
        ArrayList<String> stringsSequences = fileToArray(file);
        ArrayList<ArrayList<Integer>> sequences = stringToIntegerArray(stringsSequences);
        sortArray(sequences.get(0), sequences.get(1));
        int distance = getDistance(sequences);
        System.out.println("[AOC DAY 1] The total distance is: " + distance);
    }


}
