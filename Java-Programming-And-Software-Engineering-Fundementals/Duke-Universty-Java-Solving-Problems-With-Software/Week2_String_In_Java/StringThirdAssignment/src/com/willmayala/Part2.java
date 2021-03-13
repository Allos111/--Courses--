package com.willmayala;

/**
 *
 */

public class Part2 {
    /**
     * @param dna
     * @return
     */
    public float cgRatio(String dna) {
        String CG = "CG";
        int CGcounter = 0;

        for (int k = 0; k < dna.length(); k++) {
            if (dna.charAt(k) == CG.charAt(0) || dna.charAt(k) == CG.charAt(1)) {
                CGcounter++;
            }
        }
        System.out.println("Total of Cs and Gs: " + CGcounter);
        return (float) CGcounter / dna.length();
    }

    /**
     *
     */
    public void testCgRatio() {
        float cgRatioCounter = cgRatio("ATGCGCCyyTAAyyyATGxxxyyyCGGGGCxxxTAG");
        System.out.println("Ratio of C's and G's: " + cgRatioCounter);
    }

    /**
     * @param dna
     * @return
     */
    public int countCTG(String dna) {
        int counter = 0;
        int start = 0;

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
        System.out.println("Number of CTGs: " + countCTG("CTGxxxCTGxxxCTGyyyCTGxCTGmmmCTG"));      //5
    }

    public static void main(String[] args) {
        Part2 cg = new Part2();
        System.out.println();
        System.out.println("countCTG");
        System.out.println("=========");
        cg.testCTG();
        System.out.println();
        System.out.println("cgRation");
        System.out.println("=========");
        cg.testCgRatio();
    }
}
