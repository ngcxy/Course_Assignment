// Name: Xinyi Cai
// USC NetID: xcai6647
// CS 455 PA1
// Spring 2023

// we included the import statements for you
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */


public class Bar {
   private Rectangle rec;
   private Color color;
   private String label;
   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter scale).
  
      @param bottom  location of the bottom of the bar
      @param left  location of the left side of the bar
      @param width  width of the bar (in pixels)
      @param applicationHeight  height of the bar in application units
      @param scale  how many pixels per application unit
      @param color  the color of the bar
      @param label  the label under the bar
   */

   public Bar(int bottom, int left, int width, int applicationHeight, 
              double scale, Color color, String label) {
      int height =(int) Math.round(applicationHeight*scale);
      int top = bottom-height;
      rec = new Rectangle(left,top,width,height);
      this.color = color;
      this.label = label;
   }
   
   /**
      Draw the labeled bar.
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {
      if (rec.height>0) {
         g2.setColor(color);
         g2.draw(rec);
         g2.fill(rec);
      }
      g2.setColor(Color.BLACK);
      FontMetrics metrics = g2.getFontMetrics();
      int leftString = rec.x+(rec.width - metrics.stringWidth(label))/2;
      g2.drawString(label, leftString, rec.y+rec.height+15);
   }


}
