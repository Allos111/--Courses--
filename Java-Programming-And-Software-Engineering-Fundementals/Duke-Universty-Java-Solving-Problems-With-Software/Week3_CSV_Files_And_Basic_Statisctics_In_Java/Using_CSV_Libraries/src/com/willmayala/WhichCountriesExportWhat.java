package com.willmayala;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class WhichCountriesExportWhat {
    FileResource file;
    CSVParser theParser;

    public WhichCountriesExportWhat() {
        file = new FileResource("data/exports_small_Ms.csv");
        theParser = file.getCSVParser();
    }

    public void tester() // All informations are printed from this method
    {
        System.out.println();
        System.out.println("Country's Informations");
        System.out.println("======================");
        String countryInfo = countryInfo(theParser, "Germany");
        System.out.println(countryInfo);
        System.out.println();

        System.out.println("ListExporterTwoProducts");
        System.out.println("========================");
        listExportersTwoProducts(theParser, "cotton", "flowers");
        System.out.println();

        System.out.println("Mumber of Exporters");
        System.out.println("===================");
        int numberOfExporters = numberOfExporters(theParser, "cocoa");
        System.out.println(numberOfExporters);
        System.out.println();

        System.out.println("Big Exporters");
        System.out.println("=============");
        bigExporters(theParser, "$999,999,999,999");

    }

    public String countryInfo(CSVParser parser, String country) {
        parser = file.getCSVParser();

        for (CSVRecord record : parser) {
            String currentCountry = record.get("Country");
            String countryExport = record.get("Exports");
            String exportValue = record.get("Value (dollars)");

            if (currentCountry.contains(country)) {
                return "\n Country Info: " + currentCountry + ": " + countryExport + ": " + exportValue;
            }
        }
        return "NOT FOUND";
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1,
                                         String exportItem2) {
        parser = file.getCSVParser();

        for (CSVRecord record : parser) {
            String country = record.get("Country");
            String export = record.get("Exports");

            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(country);
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        parser = file.getCSVParser();
        int countNumberOfExpporters = 0;

        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                countNumberOfExpporters++;
            }
        }
        System.out.println("Number Of Countries that export " + exportItem + " : ");
        return countNumberOfExpporters;
    }

    public void bigExporters(CSVParser parser, String amount) {
        parser = file.getCSVParser();

        for (CSVRecord record : parser) {
            String currentValue = record.get("Value (dollars)");

            if (currentValue.length() > amount.length()) {
                System.out.println("Count: " + record.get("Country") + ", " + currentValue);
            }
        }
    }
}
