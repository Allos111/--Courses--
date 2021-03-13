package com.willmayala;

public class Part3
{

    private boolean twoOccurrences(String stringa, String stringb) {
        int indexOfStringaInStringb = stringb.indexOf(stringa);
        System.out.println(indexOfStringaInStringb);
        int indexOfStringaInStringb2 = stringb.indexOf(stringa, indexOfStringaInStringb + 1);
        System.out.println(indexOfStringaInStringb2);

        if (indexOfStringaInStringb == -1) {
            return false;
        }
        return indexOfStringaInStringb2 != -1;
    }

    private void testing() {
        System.out.println("twoOccurrences");
        System.out.println("---------------");
        boolean twoStrings = twoOccurrences("by", "A story by Abby Long and Abby Short");
        System.out.println(twoStrings);
        System.out.println("\n");
        System.out.println("lastPart");
        System.out.println("-------");
        String lastPart = lastPart("zoo", "banana");
        System.out.println(lastPart);
    }

    private String lastPart(String stringa, String stringb) {
        //finds the first occurrence of stringa in stringb
        if (stringb.contains(stringa)) {
            // .substring(startIndex, endIndex)
            int startPos = stringb.indexOf(stringa);
            int stopPos = stringb.lastIndexOf(stringa);
            //returns the part of stringb that follows stringa.
            return stringb.substring(startPos + (stopPos - 1));
        }
        return stringb;
    }

    public static void main(String[] args) {
        Part3 stringOccurrences = new Part3();
        stringOccurrences.testing();
    }

}
