package com.willmayala;

public class AllCodonsAnd
{
    private int findStopCodon(String dnaString, int startIndex,
                              String stopCodon) {
        int currentIndex = dnaString.indexOf(stopCodon, startIndex + 3);
        while (currentIndex != -1) {
            int diff = currentIndex - startIndex;
            if (diff % 3 == 0) {
                return currentIndex;
            }
            currentIndex = dnaString.indexOf(stopCodon, currentIndex + 1);
        }
        return -1;
    }

    private void testFindStop() {
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
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = 0;

        if (taaIndex == -1 || tgaIndex != -1 && tgaIndex < taaIndex) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || tagIndex != -1 && tagIndex < minIndex) {
            minIndex = tagIndex;
        }
        if (minIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
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

    private void printAllGenes(String dna) {
        int startIndex = 0;

        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println("GENE: " + currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) +
                    currentGene.length();
        }
    }

    private void testOn(String dna) {
        System.out.println("Testing printAllGenes on: " + dna);
        printAllGenes(dna);
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
        AllCodonsAnd printGenes = new AllCodonsAnd();
//        findCodon.testFindStop();
        printGenes.test();
        //printGenes.testFindGene();
    }
}
