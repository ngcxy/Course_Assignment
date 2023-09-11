// Name: Xinyi Cai
// USC NetID: xcai6647
// CS 455 PA1
// Spring 2023
import javax.swing.JFrame;
import java.util.Scanner;
/**
 * CoinSimViewer class
 * Contains the main method.
 * See comment for main method.
 *
 */
public class CoinSimViewer {
    /**
     * Prompts for the number of trials with an error checking,
     * and creates the JFrame containing the CoinSimComponent.
     * When running the main method, input the trail number as a positive integer.
     *
     */
    public static void main(String[] args) {
        System.out.print("Enter number of trials: ");
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        while (count<1){
            System.out.println("ERROR: Number of entered must be greater than 0.");
            System.out.print("Enter number of trials: ");
            in = new Scanner(System.in);
            count = in.nextInt();
        }

        CoinSimComponent component = new CoinSimComponent(count);

        JFrame frame = new JFrame();
        frame.setSize(800, 500);
        frame.setTitle("CoinSim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(component);
        frame.setVisible(true);
    }
}
