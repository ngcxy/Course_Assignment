// Name: Xinyi Cai
// USC NetID: xcai6647
// CS 455 PA4
// Spring 2023

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Has a main that's responsible for processing the command-line argument,
 * and handling any error processing.
 * Also contains helper methods to realize the loop of creating rack.
 */

public class WordFinder {

    /**
     * The main part of the program.
     * Deals with errors.
     * @param args user input
     */
    public static void main(String[] args) {
        String fileName;
        if (args.length != 0) {
            fileName = args[0];
        }
        else {
            fileName ="./sowpods.txt";
        }

        try {
            AnagramDictionary anaDic = new AnagramDictionary(fileName);
            System.out.println("Type . to quit.");
            goRack(anaDic);
        }
        catch (FileNotFoundException exception) {
            System.out.println("ERROR: Dictionary file \"" + fileName + "\" does not exist.");
            System.out.println("Exiting program.");
        }
        catch (IllegalDictionaryException exception) {
            System.out.println("ERROR: Illegal dictionary: dictionary file has a duplicate word: "
                    + exception.getMessage());
            System.out.println("Exiting program.");
        }
    }

    /**
     * The loop of creating and dealing with Racks according to user input.
     * Exits when input is "."
     * @param anaDic selected anagram dictionary
     */
    private static void goRack(AnagramDictionary anaDic){
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.print("Rack? ");
            if (!scan.hasNext()){
                continue;
            }
            String word = scan.next();
            if (word.equals(".")){
                break;
            }
            oneRack(anaDic, word);
        }
    }

    /**
     * A single step of dealing with one Rack.
     * Output all the answers by the order that is required.
     * @param anaDic selected anagram dictionary
     * @param word given word according to the Rack
     */
    private static void oneRack(AnagramDictionary anaDic, String word){
        ArrayList<String> subsets = new Rack(word).allSubsetsFinder();
        TreeMap<String, Integer> wordWithScore = new TreeMap<>();
        ArrayList<Map.Entry<String, Integer>> entryArrayList;

        for (String subset : subsets){
            ArrayList<String> anagrams = anaDic.getAnagramsOf(subset);
            for (String anagram : anagrams){
                wordWithScore.put(anagram, new ScoreTable().wordScore(anagram));
            }
        }

        entryArrayList = new ArrayList<>(wordWithScore.entrySet());
        entryArrayList.sort(new wordsComparator());

        System.out.println("We can make " + entryArrayList.size() + " words from \""+ word + "\"");
        if (entryArrayList.size() != 0){
            System.out.println("All of the words with their scores (sorted by score):");
            for (Map.Entry<String, Integer> entry: entryArrayList){
                System.out.println(entry.getValue() + ": " + entry.getKey());
            }        
        }

    }

}

/**
 * Words comparator class to compare words by score in decreasing order
 * and by alphabetic order if have same score.
 * Implements the Comparator interface.
 */
class wordsComparator implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2){

        return (e1.getValue() == e2.getValue()? e1.getKey().compareTo(e2.getKey()) : e2.getValue() - e1.getValue());
    }
}
