package lesson_03;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;

public class TextAnalyzer {

    private File file;

    public TextAnalyzer(String fileName) throws URISyntaxException {
        file = new File(getClass().getClassLoader().getResource(fileName).toURI());
    }

    // Opgave 2A
    // Parameteren sorted afgør om der skal benyttes et sorteret Set
    //
    public Set<String> findUniqueWords(boolean sorted) {
        Set<String> set = sorted ? new TreeSet<>() : new HashSet<>();

        // gennemlæs filen et ord ad gangen
        // kald clean() metoden på hvert ord
        // og tilføj ordet til settet.
        try {
            Scanner scn = new Scanner(this.file);

            while (scn.hasNext()) {
                set.add(clean(scn.next()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return set;
    }

    // Opgave 2B:   Nearly as Listing 21.9 from Liang
    //
    public Map<String, Integer> countWords(boolean sorted) {
        Map<String, Integer> map = sorted ? new TreeMap<>() : new HashMap<>();

        int i = 1;
        String word = null;

        try {
            Scanner scn = new Scanner(this.file);
            while (scn.hasNext()) {
                i = 1;
                word = clean(scn.next());

                if ( map.containsKey(word) ) {
                    i += map.get(word);
                }

                map.put(word, i);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return map;
    }

    // Opgave 2C:     Udvidelse af P15.1
    //
    public Map<Integer, Set<String>> lengthOfWords(boolean sorted) {
        Map<Integer, Set<String>> mapOfSets = sorted ? new TreeMap<>() : new HashMap<>();

        String word;
        int length;

        try {
            Scanner scn = new Scanner(this.file);
            while (scn.hasNext()) {
                word = clean(scn.next());
                length = word.length();

                if(mapOfSets.containsKey(length)) {
                    mapOfSets.get(length).add(word);
                    continue;
                }

                Set<String> set = sorted ? new TreeSet<>() : new HashSet<>();
                set.add(word);
                mapOfSets.put(length, set);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapOfSets;

    }

    // Denne metode forsøger at fjerne alt 'snavs' fra en String,
    // så kun bogstaver bevares og store gøres til små
    private String clean(String s) {
        String r = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                r = r + c;
            }
        }
        return r.toLowerCase();
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws URISyntaxException {

        TextAnalyzer ta = new TextAnalyzer("lesson_03/alice30.txt");
        ta.countWords(false);
        // Opgave 2A. Find alle unikke ord i filen
		Set<String> set = ta.findUniqueWords(true);
		System.out.println(set);
		System.out.println("Number of unique words: " + set.size());

        System.out.println("\n------------------------------------------------------------------\n");

        // Opgave 2B. Tæl forekomster af ord
		Map<String, Integer> map = ta.countWords(true);
		System.out.println(map);

        System.out.println("\n------------------------------------------------------------------\n");

        // Opgave 2C. Benyt en mappe til at gruppere ord efter længde
		Map<Integer, Set<String>> map2 = ta.lengthOfWords(true);
		System.out.println(map2);

    }

}

