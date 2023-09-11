// Name: Xinyi Cai
// USC NetID: xcai6647
// CSCI455 PA2
// Spring 2023

import java.util.*;

/**
 * Class BookshelfKeeperProg
 *
 * Contains the main method and is responsible for reading user input and printing results.
 * Allows the user to perform a series of pickPos and putHeight operations on a bookshelf
 * in an interactive mode with user commands called pick and put.
 * Contains error checking to ensure the input of the user is valid for the method.
 */
public class BookshelfKeeperProg {

    /**
     * The main method for the program.
     * Read the input from user to initialize the BookshelfKeeper object by method 'constructor'
     * Read the input of instruction and perform put and pick operation, with error checking function.
     * Terminate the program after error or input of end.
     */
    public static void main(String[] args){
        Bookshelf books = new Bookshelf();
        BookshelfKeeper keptBooks;
        boolean done = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter initial arrangement of books followed by newline:");

        if (constructSuccess(scan.nextLine(),books)){
            keptBooks = new BookshelfKeeper(books);
            System.out.println(keptBooks.toString());
            System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");

            while (!done){
                String operation = scan.next();
                if (operation.equals("end")){ 
                   done = true; 
                }
                else if (operation.equals("put")) { done = putOperation(scan.nextInt(), keptBooks); }
                else if (operation.equals("pick")){ done = pickOperation(scan.nextInt(), keptBooks); }
                else {
                    System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                    done = true;
                }
            }
        }
        System.out.println("Exiting Program.");
    }



    /*------auxiliary private methods------ */

    /**
     * Create the initial Bookshelf object according to the user input.
     * Validates the constructed Bookshelf object based on two criteria: positive and sorted.
     * Returns true if the Bookshelf satisfies the rule, false if not.
     */
    private static boolean constructSuccess(String line, Bookshelf book){
        if (line != null) {
            Scanner bookInput = new Scanner(line);
            while (bookInput.hasNextInt()) {
                int in = bookInput.nextInt();
                if (in < 1){
                    System.out.println("ERROR: Height of a book must be positive.");
                    return false;
                }
                book.addLast(in);
            }
            if (!book.isSorted()){
                System.out.println("ERROR: Heights must be specified in non-decreasing order.");
                return false;
            }
        }
        return true;
    }

    /**
     * Call the putHeight operation with the given height in the input.
     * First check is the input is valid, and return false if not.
     * If valid, print out the BookshelfKeeper object after the operation, and then return true.
     */
    private static boolean putOperation(int in, BookshelfKeeper books){
        if (in < 1){
            System.out.println("ERROR: Height of a book must be positive.");
            return true;
        }
        books.putHeight(in);
        System.out.println(books.toString());
        return false;
    }

    /**
     * Call the pickPos operation with the given position in the input.
     * First check is the input is valid, and return true if not.
     * If valid, print out the BookshelfKeeper object after the operation, and then return false.
     */
    private static boolean pickOperation(int in, BookshelfKeeper books){
        if (in < 0 || in > books.getNumBooks()-1){
            System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
            return true;
        }
        books.pickPos(in);
        System.out.println(books.toString());
        return false;
    }
}
