// Name: Xinyi Cai
// USC NetID: xcai6647
// CS 455 PA4
// Spring 2023

/**
 * Information about Scrabble scores for scrabble letters and words.
 * In scrabble letters that occur more often in the English language are worth less,
 * and letters that occur less often are worth more
 * Values are hard-coded in its data.
 */

public class ScoreTable {

    /**
     * Calculate the total score of a word.
     * @param w the given word
     * @return score of the word
     */
    public int wordScore(String w) {
        int sum = 0;
        for (int i = 0; i < w.length(); i++) {
            sum += letterScore(w.charAt(i));
        }
        return sum;
    }

    /**
     * Get the score of a letter.
     * @param l the given letter
     * @return score of the letter
     */
    private int letterScore(char l) {
        if (l < 97) {
            l = (char) (l + 32);
        }
        if (l == 'a' || l == 'e' || l == 'i' || l == 'o' || l == 'u' || l == 'l'
                || l == 'n' || l == 's' || l == 't' || l == 'r') {
            return 1;
        }
        else if (l == 'd' || l == 'g') {
            return 2;
        }
        else if (l == 'b' || l == 'c'|| l == 'm' || l == 'p') {
            return 3;
        }
        else if (l == 'f' || l == 'h' || l == 'v' || l == 'w' || l == 'y') {
            return 4;
        }
        else if (l == 'k') {
            return 5;
        }
        else if (l == 'j' || l == 'x') {
            return 8;
        }
        else if (l == 'q' || l == 'z'){
            return 10;
        }
        return 0;
    }

}
