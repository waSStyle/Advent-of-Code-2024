package dz.wasstyle.aoc;

import dz.wasstyle.aoc.dayfour.CeresSearch;
import dz.wasstyle.aoc.dayone.HistorianHysteria;
import dz.wasstyle.aoc.daythree.MullItOver;
import dz.wasstyle.aoc.daytwo.RedNosedReports;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class Main {

    private static boolean isDebug = false;
    private static boolean isDetailled = false;
    private static ArrayList<Integer> daysToExecute = new ArrayList<>();
    public static void main(String[] args) {

        // Args Management
        for(String arg : args) {
            if(!arg.startsWith("-")) continue;
            if(arg.startsWith("-day")) {
                String dayString = arg.substring(4);
                int day = -1;
                try {
                    day = Integer.parseInt(dayString);
                } catch(NumberFormatException e) {
                    System.err.println("Invalid flag " + arg);
                }
                if(day >= 0 && day<=25) daysToExecute.add(day); else System.err.println("Invalid flag " + arg);
            }
            switch(arg) {
                case "debug":
                    isDebug = true;
                    break;
                case "-debug":
                    isDebug = true;
                    isDetailled = true;
                    break;
            }
        }

        Map<Integer, Day> executions = Map.of(
                1, new HistorianHysteria(),
                2, new RedNosedReports(),
                3, new MullItOver(),
                4, new CeresSearch()
        );

        if (daysToExecute.isEmpty()) {
            executions.values().forEach(Day::execute);
        } else {
            daysToExecute.forEach(day -> {
                executions.get(day).execute();
            });
        }

    }

    /**
     * Getting a file from resources folder
     * @param filePath The file's path
     * @return The file in resources that has the correct file path
     */
    public static File getResource(String filePath) {
        return new File(HistorianHysteria.class.getClassLoader().getResource(filePath).getFile());
    }

    public static void sendDebugMessage(String s) {
        if(isDebug) System.out.println("[DEBUG] " + s);
    }

    public static void sendDetails(String s) {
        if(isDetailled) System.out.println("[DETAILS] " + s);
    }
}