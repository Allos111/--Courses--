package com.willmayala;


import edu.duke.URLResource;

public class Part4 {
    private void findUrl() {
        String theUrl = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
        URLResource webPageFile = new URLResource(theUrl);
        int urlCount = 0;

        for (String wordsInWebPage : webPageFile.words()) {
            String wordsInWebPageLower = wordsInWebPage.toLowerCase();
            int position = wordsInWebPageLower.indexOf("youtube.com");

            if (position != -1) {
                int start = wordsInWebPageLower.lastIndexOf("\"", position);
                int end = wordsInWebPageLower.indexOf("\"", position + 1);
                System.out.println(wordsInWebPageLower.substring(start + 1, end));
                urlCount += 1;
            }
        }
        System.out.println("A total of " + urlCount + " URLS was found.");

    }

    public static void main(String[] args) {
        Part4 findURL = new Part4();
        findURL.findUrl();
    }
}


