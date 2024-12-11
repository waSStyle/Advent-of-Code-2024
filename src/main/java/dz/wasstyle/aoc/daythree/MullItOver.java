package dz.wasstyle.aoc.daythree;

import dz.wasstyle.aoc.Day;
import dz.wasstyle.aoc.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver implements Day {

    private static String fileToString(File file) {
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> findMulSyntax(String text) {
        String mulPattern = "mul\\(\\d{1,3},\\d{1,3}\\)";
        String controlPattern = "(do\\(\\)|don't\\(\\))";
        Pattern combinedPattern = Pattern.compile(mulPattern + "|" + controlPattern);
        Matcher matcher = combinedPattern.matcher(text);

        ArrayList<String> matches = new ArrayList<>();
        boolean adding = true;

        while (matcher.find()) {
            String match = matcher.group();

            if (match.equals("don't()")) {
                adding = false;
            } else if (match.equals("do()")) {
                adding = true;
            } else if (adding && match.startsWith("mul(")) {
                matches.add(match);
            }
        }

        return matches;
    }

    public static int addedUpMultiplications(ArrayList<String> inputs) {

        int stacker = 0;

        for(String input: inputs) {
            String[] parts = input.substring(4, input.length() - 1).split(",");
            int int1 = Integer.parseInt(parts[0]);
            int int2 = Integer.parseInt(parts[1]);
            stacker += int1*int2;
        }

        return stacker;
    }

    @Override
    public void execute() {
        File file = Main.getResource("day3_input.txt");
        String text = fileToString(file);
        ArrayList<String> operations = findMulSyntax(text);
        int total = addedUpMultiplications(operations);
        System.out.println("[AOC DAY 3] The added up multiplications is: " + total);
    }
}
