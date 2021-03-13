package com.willmayala;

public class AllCodons
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
        return dnaString.length();
    }

    public void testFindStop() {
        System.out.println();
        String dna = "xxxyyyzzzTAAxxxzzzyyyTAAxx";
        String findGene = "ATGxxxyyyzzzTAAxxxATGzzzyyyTAAxx";
        String testGene = "AATGCTAACTAGCTGACTAAT";
        System.out.println("findStopCodon Method");
        System.out.println("===================");
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("error 9");
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) System.out.println("error 21");
        dex = findStopCodon(dna, 1, "TGA");
        if (dex != 26) System.out.println("error 26");
        dex = findStopCodon(dna, 0, "TAG");
        if (dex != 26) System.out.println("error 26 TAG");
        System.out.println("Tests finished");

        System.out.println();

        System.out.println("findGene method");
        System.out.println("===============");
        String gene = findGene(testGene);
        System.out.println("Gene: " + gene);
    }

    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");

        if (startIndex == -1) {
            return " NO START CODON FOUND";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);

        if (minIndex == dna.length()) {
            return "NO GENE FOUND";
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public static void main(String[] args) {
        AllCodons findCodon = new AllCodons();
        findCodon.testFindStop();
    }
}
