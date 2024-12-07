package dz.wasstyle.aoc.dayone;

import dz.wasstyle.aoc.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HistorianHysteriaFail {

    private static String FILE_PATH = "day1_input.txt";

    private static File getFileFromClasspath(String filePath) {
        return new File(HistorianHysteriaFail.class.getClassLoader().getResource(filePath).getFile());
    }

    private static ArrayList<String> fileToArray() {
        ArrayList<String> inputs = new ArrayList<>();
        Main.sendDebugMessage("fileToArray(): Getting file...");
        File inputFile;
        try {
            inputFile = getFileFromClasspath(FILE_PATH);
            Main.sendDebugMessage("fileToArray(): Got file: " + inputFile.getName());
        } catch (NullPointerException e) {
            System.err.println("fileToArray(): Invalid file");
            return null;
        }
        try (Scanner scanner = new Scanner(inputFile)) {
            Main.sendDebugMessage("fileToArray(): Reading file...");
            while (scanner.hasNextLine()) {
                inputs.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("fileToArray(): Invalid file");
            return null;
        }
        Main.sendDebugMessage("fileToArray(): Read file");
        return inputs;
    }


    private static void sortSequence(ArrayList<Integer> sequence) {
        Collections.sort(sequence);
    }

    public static ArrayList<Integer> getSequenceFromInput(String s) {
        if(s.length() != 5) {
            System.err.println("Invalid input");
            return null;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(char c: s.toCharArray()) {
            try {
                arrayList.add(Integer.parseInt(String.valueOf(c)));
            } catch (NumberFormatException e) {
                System.err.println("Invalid input");
                return null;
            }
        }
        if(arrayList.toArray().length == 5) return arrayList;

        return null;
    }

    public static int getSequenceDistance(ArrayList<Integer> firstSequence, ArrayList<Integer> secondSequence) {
        int distance = 0;
        for(int i = 0; i < firstSequence.toArray().length; i++) {
            distance += Math.abs(firstSequence.get(i)-secondSequence.get(i));
        }
        return distance;
    }

    public static int getAllDistances(ArrayList<String> strings) {
        int totalDistance = 0;

        Main.sendDebugMessage("getAllDistances(): Starting calculating distance");
        for(String input: strings) {
            String[] sequencesInString = input.split("   ");
            ArrayList<Integer> firstSequence = getSequenceFromInput(sequencesInString[0]);
            ArrayList<Integer> secondSequence = getSequenceFromInput(sequencesInString[1]);
            sortSequence(firstSequence);
            sortSequence(secondSequence);

            totalDistance += getSequenceDistance(firstSequence,secondSequence);
        }

        return totalDistance;
    }


    public static void execute() {
        ArrayList<String> inputs = fileToArray();
        System.out.println("[DAY 1] The distance is: " + getAllDistances(inputs));
    }

}
