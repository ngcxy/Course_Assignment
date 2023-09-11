// Name: Xinyi Cai
// USC NetID: xcai6647
// CS 455 PA1
// Spring 2023

import java.util.Random;

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator {

   private int count;
   private int twoHead;
   private int twoTail;
   private int HeadTail;
   private Random generator;

   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
      count = 0;
      twoHead = 0;
      twoTail = 0;
      HeadTail = 0;
      generator = new Random();
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
      int i = numTrials;
      int res = 0;
      count += i;
      while (i>0){
         res = generator.nextInt(2)+generator.nextInt(2);
         if (res == 2)        { twoHead++; }
         else if (res == 1)   { HeadTail++; }
         else                 { twoTail++; }
         i--;
      }
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return count; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return twoHead; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return twoTail; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return HeadTail; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
      count = 0;
      twoHead = 0;
      twoTail = 0;
      HeadTail = 0;
   }

}
