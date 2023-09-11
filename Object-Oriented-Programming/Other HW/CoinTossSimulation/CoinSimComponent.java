// Name: Xinyi Cai
// USC NetID: xcai6647
// CS 455 PA1
// Spring 2023
import java.awt.*;
import javax.swing.JComponent;

/**
 * CoinSimComponent
 * Extends JComponent. Overrides paintComponent to draw the bar graph,
 * using Bar objects for each bar in the graph.
 * Fills in the content in the frame created by CoinSimViewer.
 *
 */
public class CoinSimComponent extends JComponent {

    // added instance variable for instrumentation
    final int BAR_WIDTH = 40;
    final int VERTICAL_BUFFER = 20;
    final Color TWO_HEAD_COLOR = Color.RED;
    final Color HEAD_TAIL_COLOR = Color.GREEN;
    final Color TWO_TAIL_COLOR = Color.BLUE;
    private int callCount;
    private int runCount;
    private int hh; //count for two heads
    private int ht; //count for one head and one tail
    private int tt; //count for two tails

   /**
    *  Constructor initializes callCount
    *  Runs the simulation using class CoinTossSimulator.
    *  Passes the result of heads and tails into its private invariants.
    *
    *  @param count  number of trials to simulate
    *
    */
    public CoinSimComponent(int count) {
        callCount = 0;
        CoinTossSimulator coin = new CoinTossSimulator();
        coin.run(count);
        runCount = count;
        hh = coin.getTwoHeads();
        ht = coin.getHeadTails();
        tt = coin.getTwoTails();
    }

    /**
     *  Calculate the width, height and location of each bar and label
     *  according to the frame size known by getHeight ad getWidth.
     *  Pass the parameter into Bar class to draw the bars and strings.
     *
     *  @param g  the graphics context
     *
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        callCount++;
        System.out.println("Called paintComponent(" + callCount + ")");

        double scale = (getHeight()-2*VERTICAL_BUFFER)/100.00;
        int space = getWidth()/4;   //space between each bar's center

        int hhPercent = (int) Math.round(hh*100/(double) runCount);
        int htPercent = (int) Math.round(ht*100/(double) runCount);
        int ttPercent = (int) Math.round(tt*100/(double) runCount);

        String s1 = "Two Heads: " + hh + "(" + hhPercent + "%)";
        Bar recTwoHead = new Bar(getHeight()-VERTICAL_BUFFER, space-BAR_WIDTH, BAR_WIDTH, hhPercent, scale, TWO_HEAD_COLOR, s1);
        recTwoHead.draw(g2);

        String s2 = "A Head and a Tail: " + ht + "(" + htPercent + "%)";
        Bar recHeadTail = new Bar(getHeight()-VERTICAL_BUFFER, 2*space-BAR_WIDTH, BAR_WIDTH, htPercent, scale, HEAD_TAIL_COLOR, s2);
        recHeadTail.draw(g2);

        String s3 = "Two Tails: " + tt + "(" + ttPercent + "%)";
        Bar recTwoTail = new Bar(getHeight()-VERTICAL_BUFFER, 3*space-BAR_WIDTH, BAR_WIDTH, ttPercent, scale, TWO_TAIL_COLOR, s3);
        recTwoTail.draw(g2);
    }
}
