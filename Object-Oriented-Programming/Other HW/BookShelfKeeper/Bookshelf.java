// Name: Xinyi Cai
// USC NetID: xcai6647
// CSCI455 PA2
// Spring 2023


import java.util.ArrayList;

/**
 * Class Bookshelf
 *
 * Contains the main method that is responsible for reading user input and printing results.
 * Allows the user to perform a series of pickPos and putHeight operations
 * on a bookshelf in an interactive mode with user commands called pick and put.
 */

public class Bookshelf {

    /**
     Representation invariant:

     Heights of the books should always be positive

     */

    // <add instance variables here>
    private ArrayList<Integer> books;


    /**
     * Creates an empty Bookshelf object i.e. with no books
     */
    public Bookshelf() {
        books = new ArrayList<>();
        assert isValidBookshelf();  // sample assert statement (you will be adding more of these calls)
    }

    /**
     * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
     * values: [20, 1, 9].
     *
     * PRE: pileOfBooks contains an array list of 0 or more positive numbers
     * representing the height of each book.
     */
    public Bookshelf(ArrayList<Integer> pileOfBooks) {
        this.books = new ArrayList<>(pileOfBooks);
        assert isValidBookshelf();
    }

    /**
     * Inserts book with specified height at the start of the Bookshelf, i.e., it
     * will end up at position 0.
     *
     * PRE: height > 0 (height of book is always positive)
     */
    public void addFront(int height) {
        books.add(0,height);
        assert isValidBookshelf();
    }

    /**
     * Inserts book with specified height at the end of the Bookshelf.
     *
     * PRE: height > 0 (height of book is always positive)
     */
    public void addLast(int height) {
        books.add(height);
        assert isValidBookshelf();
    }

    /**
     * Removes book at the start of the Bookshelf and returns the height of the
     * removed book.
     *
     * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
     */
    public int removeFront() {
        int removedHeight = books.remove(0);
        assert isValidBookshelf();
        return removedHeight;
    }

    /**
     * Removes book at the end of the Bookshelf and returns the height of the
     * removed book.
     *
     * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
     */
    public int removeLast() {
        int removedHeight = books.remove(this.size()-1);
        assert isValidBookshelf();
        return removedHeight;
    }

    /*
     * Gets the height of the book at the given position.
     *
     * PRE: 0 <= position < this.size()
     */
    public int getHeight(int position) {
        int height = books.get(position);
        assert isValidBookshelf();
        return height;
    }

    /**
     * Returns number of books on this Bookshelf.
     */
    public int size() {
        assert isValidBookshelf();
        return books.size();
    }

    /**
     * Returns string representation of this Bookshelf. Returns a string with the height of all
     * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
     * by example here:  “[7, 33, 5, 4, 3]”
     */
    public String toString() {
        assert isValidBookshelf();
        return (String.valueOf(books));
    }

    /**
     * Returns true iff the books on this Bookshelf are in non-decreasing order.
     * (Note: this is an accessor; it does not change the bookshelf.)
     */
    public boolean isSorted() {
        for (int i=1; i<this.size();i++){
            if (getHeight(i-1)>getHeight(i)) { 
               return false; 
            }
        }
        assert isValidBookshelf();
        return true;
    }

    /**
     * Returns true iff the Bookshelf data is in a valid state.
     * (See representation invariant comment for more details.)
     */
    private boolean isValidBookshelf() {
        for (int i : books) {
            if (i <= 0) { 
               return false; 
            }
        }
        return true;
    }

}

