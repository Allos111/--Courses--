package com.willmayala;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Find the highest temperature in a csv file of weather data
 * @uthor Will Toussaint Mayala
 */

public class CSVMax {
    private FileResource myFile;
    private CSVParser theParser;

    public CSVMax() {
        myFile = new FileResource("data/2015/weather-2015-01-01.csv");
        theParser = myFile.getCSVParser();
    }

    /**
     * This method return the hottest hour in a CSV file
     * @param parser to parse the headers of the file
     * @return a CSVRecord with the largest value
     */
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing declare and initialise
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            //If largestSoFar is nothing
            if (largestSoFar == null) {
                //largestSoFar get currentRow
                largestSoFar = currentRow;
            }
            //Otherwise
            else {
                //Get the values in numerical format(Type double)
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));

                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp > largestTemp) {
                    //If so update largestSoFar to currentRow
                    largestSoFar = currentRow;
                }
            }
        }
        //The value inside largestSoFar is the answer
        return largestSoFar;
    }

    public void testHottestInDay() {
        CSVRecord largest = hottestHourInFile(myFile.getCSVParser());

        System.out.println("The hottest temperature was " + largest.get("TemperatureF") +
                " at " + largest.get("TimeEST") + " at the UTC date/Time of " +
                largest.get("DateUTC"));
    }
}
