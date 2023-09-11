// Name: Xinyi Cai
// USC NetID: xcai6647
// CS 455 PA4
// Spring 2023

import java.util.*;

/**
   A Rack of Scrabble tiles
 */

public class Rack {

   /**
    * Representation invariant:
    * rackWord should be string with only letters
    */
   private String rackWord;

   /**
    * Create a Rack class.
    * @param word: a word in type of String
    */
   public Rack(String word){
      rackWord = word;
   }

   /**
    * Find all subsets for the word in the class Rack using the given helper method.
    * @return all subsets in an ArrayList
    */
   public ArrayList<String> allSubsetsFinder(){
      int[] mult;
      char[] uniqueArray;
      String unique;
      HashMap<Character, Integer> letterMap = new HashMap<>();

      for (int i=0; i<rackWord.length(); i++){
         if (letterMap.containsKey(rackWord.charAt(i))){
            letterMap.put(rackWord.charAt(i), letterMap.get(rackWord.charAt(i)) + 1);
         }
         else {
            letterMap.put(rackWord.charAt(i),1);
         }
      }

      mult = new int[letterMap.size()];
      uniqueArray = new char[letterMap.size()];
      int i = 0;

      for (Map.Entry<Character, Integer> entry : letterMap.entrySet()) {
         uniqueArray[i] = entry.getKey();
         mult[i] = entry.getValue();
         i++;
      }

      unique = new String(uniqueArray);
      return allSubsets(unique, mult, 0);
   }

   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   
}
