package com.willmayala;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class MaxTemperatureAssignment {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;

        for (CSVRecord currentRow : parser) {
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            } else {
                double currentTemperature = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemperature = Double.parseDouble(coldestSoFar.get("TemperatureF"));

                if (currentTemperature != -9999 && currentTemperature < coldestTemperature) {
                    coldestSoFar = currentRow;
                }
            }
        }
        return coldestSoFar;
    }

    public void testColdestHourInFile() {
        FileResource myFile = new FileResource();
        CSVRecord coldestSoFar = coldestHourInFile(myFile.getCSVParser());
        System.out.println("The coldest hour is: " + coldestSoFar.get("DateUTC") + ", " + coldestSoFar.get("TemperatureF"));
    }

    public String fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        String coldestFileName = null;
        DirectoryResource dr = new DirectoryResource();

        for (File file : dr.selectedFiles()) {
            FileResource myFile = new FileResource(file);
            CSVRecord currentRow = coldestHourInFile(myFile.getCSVParser());

            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
                coldestFileName = file.getName();
            } else {
                double currentTemperature = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemperature = Double.parseDouble(coldestSoFar.get("TemperatureF"));

                if (currentTemperature != -9999 && currentTemperature < coldestTemperature) {
                    coldestSoFar = currentRow;
                    coldestFileName = file.getName();
                }
            }
        }
        return coldestFileName;
    }

    public void testFileWithColdestTemperature() {
        String coldestName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file: " + coldestName);

        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());

        System.out.print("The coldest temperature on that day was ");
        System.out.println(coldest.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were");
        for (CSVRecord record : fr.getCSVParser()) {
            System.out.print(record.get("DateUTC"));
            System.out.print(" ");
            System.out.println(record.get("TemperatureF"));
        }
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        double lowest = 0;
        double current = 0;

        for (CSVRecord record : parser) {
            if (lowestSoFar == null) lowestSoFar = record;
            if (record.get("Humidity").equals("N/A")) {
                current = -999;
            } else {
                current = Double.parseDouble(record.get("Humidity"));
            }

            if (lowestSoFar.get("Humidity").equals("N/A")) {
                lowest = -999;
            } else {
                lowest = Double.parseDouble(lowestSoFar.get("Humidity"));
            }

            if (current < lowest && current != -999) lowestSoFar = record;
        }
        return lowestSoFar;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVRecord result = lowestHumidityInFile(fr.getCSVParser());
        System.out.print("Lowest Humidity was ");
        System.out.print(result.get("Humidity"));
        System.out.print(" at ");
        System.out.println(result.get("DateUTC"));
    }

    public String lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowest = null;
        String fileName = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            if (lowest == null) lowest = current;
            double currentHum = Double.parseDouble(current.get("Humidity"));
            double lowestHum = Double.parseDouble(lowest.get("Humidity"));
            if (currentHum < lowestHum) {
                lowest = current;
                fileName = f.getName();
            }
        }
        return fileName;
    }

    public void testLowerHumidityInManyFiles() {
        String filename = lowestHumidityInManyFiles();
        System.out.print("Day with lowest humidity was in file ");
        System.out.println(filename);
        FileResource fr = new FileResource();
        CSVRecord lowest = lowestHumidityInFile(fr.getCSVParser());
        System.out.print("Lowest Humidity was ");
        System.out.print(lowest.get("Humidity"));
        System.out.print(" at ");
        System.out.println(lowest.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0;
        int number = 0;
        for (CSVRecord record : parser) {
            double current = Double.parseDouble(record.get("TemperatureF"));
            sum = sum + current;
            number += 1;
        }
        sum = sum / number;
        return sum;
    }

    public void testaverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.print("Average temperature in file is ");
        System.out.println(average);
        System.out.println("Nothing to show");

    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        double number = 0;
        double humidity = 0;

        for (CSVRecord record : parser) {
            if (record.get("Humidity").equals("N/A")) humidity = -999;
            else humidity = Double.parseDouble(record.get("Humidity"));
            if (humidity >= value) {
                number += 1;
                sum = sum + Double.parseDouble(record.get("TemperatureF"));
            }
        }
        return sum / number;
    }

    public void testAverageTemperatureWithHumidityInFile() {

        System.out.println();
        FileResource fr = new FileResource();
        double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (average == 0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.print("Average temperature with high Humidity is ");
            System.out.println(average);
        }
    }

    public static void main(String[] args) {
        System.out.println();
        MaxTemperatureAssignment tempTest = new MaxTemperatureAssignment();
        System.out.println("Testing the average temperature With Humidity in a file");
        System.out.println("========================================================");
        tempTest.testAverageTemperatureWithHumidityInFile();
        System.out.println();
        System.out.println("Coldest temperature In a hour");
        System.out.println("==============================");
        tempTest.testColdestHourInFile();
        System.out.println();
        System.out.println("Average temperature in a file");
        System.out.println("=============================");
        tempTest.testaverageTemperatureInFile();
        System.out.println();
        System.out.println("Lower humidity In a file");
        System.out.println("=========================");
        tempTest.testLowestHumidityInFile();
        System.out.println();
        System.out.println("Lower humiditty in many files");
        System.out.println("==============================");
        tempTest.lowestHumidityInManyFiles();
        System.out.println();
    }

}
