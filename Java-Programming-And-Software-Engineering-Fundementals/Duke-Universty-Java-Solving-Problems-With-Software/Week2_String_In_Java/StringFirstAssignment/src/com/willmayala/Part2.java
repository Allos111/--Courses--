package com.willmayala;

public class Part2
{
    private String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String gene = " ";
        int startIndex = dna.indexOf(startCodon);

        if (startIndex == -1) {
            return "NO START CODON FOUND ";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1) {
            return "NO STOP CODON FOUND ";
        }
        gene = dna.substring(startIndex, stopIndex + 3);
        if ((gene.length() % 3 == 0)) {
            return gene;
        }
        return "NO GENE FOUND";
    }

    private void testSimpleGene() {
        String dna = "AGTATGATTATGGTATGTAGTTGATTAATGG";
        String dna1 = "ATATGTTAGTTTTAA";
        String dn2 = "ATGTGTTATATGGTATAT";
        String gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("GENE: " + gene);
    }

    public static void main(String[] args) {
        Part2 findGene = new Part2();
        findGene.testSimpleGene();
    }

}
