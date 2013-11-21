//  Author: Chuck Griffin
//  Purpose: an example for my friends
//  Date:  4/19/13

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MouseDrawDemo extends JFrame
{

   private static final long serialVersionUID = 1L;

   public static void main(String[] args) 
   {
	  // This is that class that creates the frame, sets it's size
	  // and title and the calls my MyCanvas class and adds it
	  // to the frame.
	   
	  // make the frame
      JFrame mf = new JFrame();
      mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mf.setSize(800, 600);
      mf.setTitle("My Little Painter");
      mf.setVisible(true);
      
      // create the canvas and add it to the frame.
      MyCanvas mc = new MyCanvas();
      mf.add(mc);
      
   }  // end of main
} // end of class that creates the frame

class MyCanvas extends JPanel implements MouseListener, MouseMotionListener
{
   // This class creates a single JPanel for drawing and then implements 
   // mouse listeners.  
        
   private ArrayList<Point> brushStroke = new ArrayList<Point>();
   private String mouseStatus = "Mouse Status:";
   private int x;
   private int y;
   private int clickCount = 0;
   
   public MyCanvas()
   {
      // Constructor
      addMouseListener(this);             // add the mouse listeners 
      addMouseMotionListener(this);       // to this panel
      this.setBackground(Color.darkGray); // set the color
   }  // end constructor
   
   
   public void paintComponent(Graphics g)
   {
      // This class gets called when the program starts
      // AND every time the repaint() method is called.
      // this is where everything gets drawn to the panel
       
      super.paintComponent(g);      // Always do this first! 
      g.setColor(Color.green);      // set the drawing color
      
      // print some text to the screen
      g.drawString("Mouse position: "+ x + " , " + y, 10, 10);
      g.drawString(mouseStatus,                       10, 20);
      g.drawString("Click Count = " + clickCount,     10, 30);
      g.drawString("Right click to reset",            10, 40);
      
      // fill a little oval at each point in the brushstroke ArrayList
      for(Point p : brushStroke)
      {
         // the Point class stores an X coordinate and a Y coordinate
         // as a single object. The coordinates are stored as type double 
         // so I need to cast them back to an integer before I can use them.
         // The oval is 10 pixels by 10 pixels, and it will be drawn from
         // where my mouse is pointing, so to make it look like my mouse
         // is drawing the circle, I needed to use a -5 to offset the drawing
         g.fillOval((int)p.getX()-5, (int)p.getY()-5, 10, 10);
      
      } // end of drawing the array of points
          
   } // end of paintComponent


// ******** Mouse Listener Methods ********************
// any time you use the MouseListener Interface, you must 
// implements these methods.
   
   public void mouseEntered(MouseEvent event) 
   {
      // when your mouse comes into the frame, set the 
      // mouseStatus variable and call the repaint() method.
       
      mouseStatus = "Mouse Status: IN THE FRAME";
      repaint();
   }
   
   public void mouseExited(MouseEvent event)  
   {
      // when your mouse leaves the frame, set the mouseStatus 
      // variable, and set the values of x and y to a -1.
      // This is only for your display so it doesn't show an
      // X and Y coordinate when you're our of the panel.
      // Then call the repaint() method to update the screen.
        
        mouseStatus = "Mouse Status: OUT OF FRAME";
        x = y = -1; 
        repaint();
    }   
    public void mouseClicked(MouseEvent event) 
    {
        if(event.getButton() == event.BUTTON3) 
        {
            brushStroke.clear();
            clickCount = 0;
        }
        else
        {
            x=event.getX(); 
            y=event.getY(); 
            
            Point p = new Point(x,y);
            brushStroke.add(p);
        }
        
        repaint();
    }
    public void mousePressed(MouseEvent event) 
    {
        clickCount += event.getClickCount(); 
        repaint();
    }
    public void mouseReleased(MouseEvent event){}

    // mouse motion listener
    public void mouseDragged(MouseEvent event)
    {
        x=event.getX(); 
        y=event.getY(); 
        
        Point p = new Point(x,y);
        brushStroke.add(p);
        
        repaint();
    }
    public void mouseMoved(MouseEvent event)
    {
        x=event.getX(); 
        y=event.getY(); 
        repaint();
    }
    
}   

	
	
	
	

