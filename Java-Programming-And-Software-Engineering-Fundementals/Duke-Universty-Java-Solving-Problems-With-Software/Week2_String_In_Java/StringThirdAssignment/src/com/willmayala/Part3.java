package com.willmayala;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Part3 {
    private int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopCodonIndex = dna.indexOf(stopCodon, startIndex + 3);

        while (stopCodonIndex != -1) {
            int diff = stopCodonIndex - startIndex;
            if (diff % 3 == 0) {
                return stopCodonIndex;
            }
            stopCodonIndex = dna.indexOf(stopCodon, stopCodonIndex + 1);
        }
        return -1;
    }

    private String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);

        if (startIndex == -1) {
            return "";
        } else {
            int taaIndex = findStopCodon(dna, startIndex, "TAA");
            int tagIndex = findStopCodon(dna, startIndex, "TAG");
            int tgaIndex = findStopCodon(dna, startIndex, "TGA");

            int minIndex = 0;

            if (taaIndex == -1 || tagIndex != -1 && tagIndex < taaIndex) {
                minIndex = tagIndex;
            } else {
                minIndex = taaIndex;
            }
            if (minIndex == -1 || tgaIndex != -1 && tgaIndex < minIndex) {
                minIndex = tgaIndex;
            }
            if (minIndex == -1) {
                return "";
            }
            return dna.substring(startIndex, minIndex + 3);
        }
    }

    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        //Start index
        int startIndex = 0;
        //Repeat the following steps
        while (true) {
            //Find the next gene after startIndex
            String currentGene = findGene(dna, startIndex);
            //If no gene was found leave that loop
            if (currentGene.isEmpty()) {
                break;
            }
            geneList.add(currentGene);
            //Set the startIndex to just past the and of the gene
            startIndex = dna.indexOf(currentGene, startIndex) +
                    currentGene.length();
        }
        return geneList;
    }

    public float cgRatio(String dna) {

        int CGcounter = 0;
        String CG = "CG";

        for (int k = 0; k < dna.length(); k++) {
            if (dna.charAt(k) == CG.charAt(0) || dna.charAt(k) == CG.charAt(1)) {
                CGcounter++;
            }
        }
        return (float) (CGcounter) / dna.length();
    }

    public int countCTG(String dna) {
        int counter = 0, start = 0;

        while (true) {
            int startIndex = dna.indexOf("CTG", start);
            if (startIndex == -1) {
                break;
            }
            counter++;
            start = startIndex + 2;
        }
        return counter;
    }

    public void testCTG() {
        System.out.println("Number of CTGs: " + countCTG("CTGxxxCTGxxxCTGyyyCTGxCTG"));      //5
    }

    public void processGenes(StorageResource sr) {
        int nineplusGENE = 0;
        int cgRatioCounter = 0;
        int longestGene = 0;
        int totalGenes = 0;

        for (String s : sr.data()) {
            String upperString = s.toUpperCase();

            if (!findGene(upperString, 0).isEmpty()) {
                totalGenes++;

                if (findGene(s, 0).length() > 60) {
                    System.out.println("Gene with more than  60 characters: " + upperString);
                    nineplusGENE++;
                }

                if (cgRatio(s) > 0.35) {
                    cgRatioCounter++;
                    System.out.println("C and G's Ratio bigger than 0.35: " + cgRatioCounter);
                }

                if (s.length() > longestGene) {
                    longestGene = upperString.length();
                }
            }
        }

        for (String s : sr.data()) {
            System.out.println("CG Ratio in the gene " + s + " is: " + cgRatioCounter);
            System.out.println("Genes that are 60+ chars: " + nineplusGENE);
            System.out.println("The length of the longest gene: " + longestGene);
            System.out.println("Total Genes: " + totalGenes);
            System.out.println();
        }
    }

    public void testProcessGenes() {

        String nineLong = "ATGxxxTAAyyyATGxxxTAG";             //no genes longer than 9
        StorageResource geneList = getAllGenes(nineLong);
        processGenes(geneList);

        String dna1 = "ATGxxxyyyTAAyyyATGxxxyyyxxxyyyxxxLOONGESTGENETAGATGCyyTAA";  //2 genes longer than 9
        geneList = getAllGenes(dna1);
        processGenes(geneList);

        String dna2 = "ATGCGCCyyTAAyyyATGxxxyyyCGGGGCxxxTAG";  //genes with 0.35+ CG ratio
        geneList = getAllGenes(dna2);
        processGenes(geneList);

        String dna3 = "ATGxxxyyyxxxyyyTAG";                    //genes with 0.35- CG ratio
        geneList = getAllGenes(dna3);
        processGenes(geneList);

    }

    public void testProcesswithRealDNA() {
        FileResource fr = new FileResource("data/brca1line.fa");
        String dna = fr.asString();

        System.out.println("String DNA: " + dna.toUpperCase());

        StorageResource geneList = getAllGenes(dna.toUpperCase());
        processGenes(geneList);
    }

    public static void main(String[] args) {
        System.out.println();
        Part3 geneProcess = new Part3();
        System.out.println("Processing Some Genes");
        System.out.println("=====================");
        geneProcess.testProcessGenes();
        System.out.println();
        System.out.println("Processing Real Genes");
        System.out.println("======================");
        geneProcess.testProcesswithRealDNA();
    }
}
