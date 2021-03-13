package com.willmayala;

public class Part1
{
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

    private void testFindStopCodon() {
        System.out.println();
        String dna = "xxxyyyzzzTAAxxxzzzyyyTAAxx";
        String findGene = "ATGxxxyyyzzzsssTAGxxxATGzzzyyytmkTAAxxxTAG";
        String testGene = "ATGxxxTAA";
        System.out.println("findStopCodon Method");
        System.out.println("===================");
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("error 9");
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) System.out.println("error 21");
        dex = findStopCodon(dna, 1, "TGA");
        if (dex != -1) System.out.println("error 26");
        dex = findStopCodon(dna, 0, "TAG");
        if (dex != -1) System.out.println("error 26");
        System.out.println("Tests finished");
        System.out.println();

        System.out.println("findGene method");
        System.out.println("===============");
        String gene = findGene(testGene, 0);
        System.out.println("Gene: " + gene);
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

    private void testFindGene() {
        String dna = "ATGCCCGGGAAAwTAGCCC";
        String gene = findGene(dna, 0);
        if (!gene.equals("ATGCCCGGGAAATAG")) {
            System.out.println("test failed");
        }
        System.out.println("tests finished");
        System.out.println("GENE: " + gene);
    }

    public void printAllGene(String dna) {
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
            System.out.println("Gene: " + currentGene);
            //Set the startIndex to just past the and of the gene
            startIndex = dna.indexOf(currentGene, startIndex) +
                    currentGene.length();
        }
    }

    private void testOn(String dna) {
        System.out.println("Testing printAllGenes on Dna: " + dna);
        printAllGene(dna);
    }

    public void test() {
        System.out.println();
        System.out.println("Finding Multiple Genes");
        System.out.println("======================");
        //      ATGv    TAAv    ATG v v TGA
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        System.out.println();
        testOn("");
        System.out.println();
        //      ATGv v v v TAAv v v ATGTAA
        testOn("ATGATCTAAGAAGATAATAGAGGGCCATGTAA");
    }

    public static void main(String[] args) {
//        System.out.println();
//        Part1 findCodon = new Part1();
//        System.out.println("Find stop codon");
//        System.out.println("================");
//        findCodon.testFindStopCodon();
//        System.out.println();
//        Part1 findGene = new Part1();
//        System.out.println("Find Gene");
//        System.out.println("==========");
//        findGene.testFindGene();
//        System.out.println();
        Part1 printGene = new Part1();
        System.out.println("Print Gene");
        System.out.println("==========");
        printGene.test();
    }
}
