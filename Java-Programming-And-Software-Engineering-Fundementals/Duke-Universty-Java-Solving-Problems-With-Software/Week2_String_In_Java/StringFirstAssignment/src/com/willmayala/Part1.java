package com.willmayala;

public class Part1
{

    private String findSimpleGene(String dna) {
        String gene = "";
        int startIndex = dna.indexOf("ATG");

        if (startIndex == -1) {
            //This should be an empty String according to the Algorithm
            return "NO START CODON FOUND";
        }
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if (stopIndex == -1) {
            return " NO STOP CODON FOUND";
        }
        gene = dna.substring(startIndex, stopIndex + 3);
        if ((gene.length() % 3 == 0)) {
            return gene;
        }
        //This should be  another an empty String according to the algorithm
        return "NO GENE FOUND";
    }

    private void testSimpleGene() {
        String dna = "ATGCCCGGGAAATAAATGCCCGGGAAATAGATGCCCGGGAAA";
        String dna1 = "ATAGTTAGTTTTAA";
        String dna3 = "ATATGTTAGTTTTAA";
        String dna2 = "ATGTGTTATATGGTATAT";
        String gene = findSimpleGene(dna1);
        System.out.println("GENE: " + gene);
    }

    public static void main(String[] args) {
        Part1 findGene = new Part1();
        findGene.testSimpleGene();
    }
}
