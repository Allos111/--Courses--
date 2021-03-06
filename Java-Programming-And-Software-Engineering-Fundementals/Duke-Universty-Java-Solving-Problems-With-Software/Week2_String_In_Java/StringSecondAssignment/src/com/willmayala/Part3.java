package com.willmayala;

public class Part3 {
    public int countGenes(String dna) {

        int startIndex = 0, counter = 0;

        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            counter++;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return counter;
    }

    private int findStopCodon(String dna, int startIndex, String stopCodon) {

        int currIndex = dna.indexOf(stopCodon, startIndex + 1);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            currIndex = dna.indexOf(stopCodon, currIndex + stopCodon.length());
        }

        return dna.length();
    }

    public String findGene(String dna, int where) {

        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = 0;

        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }

        if (minIndex == -1) {
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);
    }

    public void printAllGenes(String dna) {

        int startIndex = 0;

        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            //update statIndex to be just past the end of the Gene
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }

    public static void main(String[] args) {
        Part3 howMany = new Part3();
        System.out.println(howMany.countGenes("ATGTAAGATGCCCTAGT"));        //2
        howMany.printAllGenes("ATGTAAGATGCCCTAGT");
    }
}
