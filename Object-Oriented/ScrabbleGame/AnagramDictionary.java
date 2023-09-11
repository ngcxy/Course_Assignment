// Name: Xinyi Cai
// USC NetID: xcai6647
// CS 455 PA4
// Spring 2023

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {

   /**
    * Representation invariant:
    * size of anagramWords should be no more than size of
    * the original dictionary
    */
   private TreeMap<String, ArrayList<String>> anagramWords;

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {
      File dictionary = new File(fileName);
      Scanner dicScan = new Scanner(dictionary);
      HashSet<String> scannedWords = new HashSet<>();
      anagramWords = new TreeMap<>();

      while (dicScan.hasNext()){
         String word = dicScan.next();
         if (scannedWords.contains(word)){
            throw new IllegalDictionaryException(word);
         }
         else {
            scannedWords.add(word);
            String sortedWord = sort(word);
            if (anagramWords.containsKey(sortedWord)){
               addToExist(sortedWord, word);
            }
            else{
               addToNew(sortedWord, word);
            }
         }
      }

   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      String sortedString = sort(string);
      if (anagramWords.containsKey(sortedString)){
         return anagramWords.get(sortedString);
      }
      else return new ArrayList<>();
   }


   /**
    Sort a given string.
    @param word: non-sorted String
    @return sorted String in alphabetic order
    */
   private String sort(String word){
      char[] tempArray = word.toCharArray();
      Arrays.sort(tempArray);
      return new String(tempArray);
   }


   /**
    Add current word to an existing sorted anagram.
    @param sortedWord: existing sorted anagram
    @param word: current word
    */
   private void addToExist(String sortedWord, String word){
      ArrayList<String> tempArray;
      tempArray = anagramWords.get(sortedWord);
      tempArray.add(word);
      anagramWords.put(sortedWord, tempArray);
   }


   /**
    Create a new sorted anagram and add current word to it.
    @param sortedWord: new sorted anagram
    @param word: current word
    */
   private void addToNew(String sortedWord, String word){
      ArrayList<String> tempArray = new ArrayList<>();
      tempArray.add(word);
      anagramWords.put(sortedWord, tempArray);
   }
}
