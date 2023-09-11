// Name: Xinyi Cai
// USC NetID: xcai6647
// CSCI455 PA2
// Spring 2023


import java.util.ArrayList;

/**
 * Class BookshelfKeeper
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in
 * non-decreasing order by height, with the restriction that single books can only be added
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 * Works for extra credit cases with duplicate heights.
 */
public class BookshelfKeeper {

    /**
     Representation invariant:

     Heights of the books should always be positive
     Books should be kept as a non-decreasing order in height

     */

    // <add instance variables here>
    private Bookshelf itsBookshelf;
    private Bookshelf tempBooks;
    private int lastOperations;
    private int totalOperations;

    /**
     * Creates a BookShelfKeeper object with an empty bookshelf
     */
    public BookshelfKeeper() {
        itsBookshelf = new Bookshelf();
        tempBooks = new Bookshelf();
        lastOperations = 0;
        totalOperations = 0;
        assert isValidBookshelfKeeper();
    }

    /**
     * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
     * Note: method does not make a defensive copy of the bookshelf.
     *
     * PRE: sortedBookshelf.isSorted() is true.
     */
    public BookshelfKeeper(Bookshelf sortedBookshelf) {
        itsBookshelf = sortedBookshelf;
        tempBooks = new Bookshelf();
        lastOperations = 0;
        totalOperations = 0;
        assert isValidBookshelfKeeper();
    }

    /**
     * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted
     * after picking up the book.
     *
     * Returns the number of calls to mutators on the contained bookshelf used to complete this
     * operation. This must be the minimum number to complete the operation.
     *
     * PRE: 0 <= position < getNumBooks()
     */
    public int pickPos(int position) {
        int numCalls = 0;
        if (position<getNumBooks()/2){
            numCalls += removeFrontPile(position);
            itsBookshelf.removeFront();
            numCalls ++;
            numCalls += putFrontPile();
        }
        else{
            numCalls += removeLastPile(position+1);
            itsBookshelf.removeLast();
            numCalls ++;
            numCalls += putLastPile();
        }
        lastOperations = numCalls;
        totalOperations += lastOperations;
        assert isValidBookshelfKeeper();
        return numCalls;
    }

    /**
     * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted
     * after the insertion.
     *
     * Returns the number of calls to mutators on the contained bookshelf used to complete this
     * operation. This must be the minimum number to complete the operation.
     *
     * PRE: height > 0
     */
    public int putHeight(int height) {
        int numCalls = 0;
        int position = getPutPosition(height);
        if (position<=getNumBooks()/2){
            numCalls += removeFrontPile(position);
            itsBookshelf.addFront(height);
            numCalls ++;
            numCalls += putFrontPile();
        }
        else{
            numCalls += removeLastPile(position);
            itsBookshelf.addLast(height);
            numCalls ++;
            numCalls += putLastPile();
        }
        lastOperations = numCalls;
        totalOperations += lastOperations;
        assert isValidBookshelfKeeper();
        return numCalls;

    }

    /**
     * Returns the total number of calls made to mutators on the contained bookshelf
     * so far, i.e., all the ones done to perform all of the pick and put operations
     * that have been requested up to now.
     */
    public int getTotalOperations() {
        assert isValidBookshelfKeeper();
        return totalOperations;
    }

    /**
     * Returns the number of books on the contained bookshelf.
     */
    public int getNumBooks() {
        assert isValidBookshelfKeeper();
        return itsBookshelf.size();
    }

    /**
     * Returns string representation of this BookshelfKeeper. Returns a String containing height
     * of all books present in the bookshelf in the order they are on the bookshelf, followed
     * by the number of bookshelf mutator calls made to perform the last pick or put operation,
     * followed by the total number of such calls made since we created this BookshelfKeeper.
     *
     * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
     *
     */
    public String toString() {
        assert isValidBookshelfKeeper();
        return (itsBookshelf.toString() + " " + lastOperations + " " + getTotalOperations());

    }

    /**
     * Returns true iff the BookshelfKeeper data is in a valid state.
     * (See representation invariant comment for details.)
     */
    private boolean isValidBookshelfKeeper() {
        if (!itsBookshelf.isSorted()) { return false; }    // isSorted() method in Bookshelf checks if heights are positive automatically
        return true;
    }

    // add any other private methods here
    /**
     * Remove a pile of books from index 0 to index position,
     * and meanwhile put every removed book to the temporary bookshelf object.
     * (All in the order of left to right)
     *
     * Returns the number of calls to mutators on the contained bookshelf used to remove
     * all the books in this pile.
     */
    private int removeFrontPile(int position){
        int numCalls = 0;
        for (int i = position-1; i>-1; i--){
            tempBooks.addLast(itsBookshelf.removeFront());
            numCalls++;
        }
        return numCalls;
    }

    /**
     * Remove every book in the temporary bookshelf object,
     * and meanwhile put them back at the front of the original 'books' bookshelf.
     * (All in the order of right to left)
     *
     * Returns the number of calls to mutators on the contained bookshelf used to put
     * all the books in this pile.
     */
    private int putFrontPile(){
        int numCalls = 0;
        while (tempBooks.size() != 0) {
            itsBookshelf.addFront(tempBooks.removeLast());
            numCalls++;
        }
        return numCalls;
    }

    /**
     * Remove a pile of books from index books.size()-1 to index position,
     * and meanwhile put every removed book to the temporary bookshelf object.
     * (All in the order of right to left)
     *
     * Returns the number of calls to mutators on the contained bookshelf used to remove
     * all the books in this pile.
     */
    private int removeLastPile(int position){
        int numCalls = 0;
        int num = getNumBooks();
        for (int i = position; i<num; i++){
            tempBooks.addFront(itsBookshelf.removeLast());
            numCalls++;
        }
        return numCalls;
    }

    /**
     * Remove every book in the temporary bookshelf object,
     * and meanwhile put them back at the end of the original 'books' bookshelf.
     * (All in the order of left to right)
     *
     * Returns the number of calls to mutators on the contained bookshelf used to put
     * all the books in this pile.
     */
    private int putLastPile(){
        int numCalls = 0;
        while (tempBooks.size() != 0) {
            itsBookshelf.addLast(tempBooks.removeFront());
            numCalls++;
        }
        return numCalls;
    }

    /**
     * Find position of the book with a given height that it should be put.
     * Take extra consideration in the extra-credit cases
     *
     * Returns the value of the position, which is its index after being put in the bookshelf.
     */
    private int getPutPosition(int height){
        int positionFromLeft=0, positionFromRight=getNumBooks()-1;
        if (getNumBooks() == 0){ 
           return positionFromLeft; 
        }
        while (positionFromLeft < getNumBooks() && height > itsBookshelf.getHeight(positionFromLeft)){
            positionFromLeft++;
        }
        while (positionFromRight > -1 && height < itsBookshelf.getHeight(positionFromRight)){
            positionFromRight--;
        }
        if ((getNumBooks()-positionFromRight-1) < positionFromLeft){
            return positionFromRight+1;
        }
        else { 
           return positionFromLeft; 
        }
    }

}

