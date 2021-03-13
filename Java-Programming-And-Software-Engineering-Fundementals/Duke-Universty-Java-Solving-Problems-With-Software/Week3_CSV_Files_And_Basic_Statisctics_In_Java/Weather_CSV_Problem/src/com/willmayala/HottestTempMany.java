package com.willmayala;

/**
 * Find hottest temperature in many days by parsing more csv files
 *
 * @uthor Toussaint Will Mayala
 */

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class HottestTempMany {

    private FileResource myFile;
    private CSVParser theParser;

    public HottestTempMany() {
        myFile = new FileResource("data/2015/weather-2015-01-02.csv");
        theParser = myFile.getCSVParser();
    }

    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            //If largestSoFar is nothing
            if (largestSoFar == null) {
                largestSoFar = currentRow;
            }
            //Otherwise
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < largestTemp) {
                    //If so update largestSoFar to currentRow
                    largestSoFar = currentRow;
                }
            }
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }

    public void testHottestInDay() {
        CSVRecord theLargest = hottestHourInFile(myFile.getCSVParser());
        System.out.println("The hottest temperature was " + theLargest.get("TemperatureF") +
                " at " + theLargest.get("TimeEST") + " at the UCT day/time " + theLargest.get("DateUTC"));
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource myDirectory = new DirectoryResource();

        for (File file : myDirectory.selectedFiles()) {
            FileResource myFile = new FileResource(file);
            CSVRecord currentRow = hottestHourInFile(myFile.getCSVParser());

            if (largestSoFar == null) {
                largestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));

                if (currentTemp > largestTemp) {
                    largestSoFar = currentRow;
                }
            }
        }
        return largestSoFar;
    }

    public void testHottestInManyDays() {
        CSVRecord theLargest = hottestInManyDays();
        System.out.println("The hottest hour in the files was " + theLargest.get("TemperatureF") +
                " at " + theLargest.get("TimeEST"));
    }

    public static void main(String[] args) {
        System.out.println();
        HottestTempMany hottestTemp = new HottestTempMany();
        System.out.println("Hottest Hour in a file");
        System.out.println("======================");
        //hottestTemp.testHottestInDay();
        System.out.println();
        System.out.println("Hottest day in many files");
        System.out.println("==========================");
        hottestTemp.testHottestInManyDays();
    }
}

