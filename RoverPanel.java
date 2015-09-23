/** 
 * RoverPanel.java: Swing panel for Mars rover motion assignment.
 *     This class is responsible for 
 *     1. Creating and storing the transmit locations (already finished).
 *     2. Creating and storing the research sites; the sites are 
 *        created in the JPanel, in response to a MousePressed event
 *        at the site location.
 *     3. Creating the Rover that must do the traversal
 *     4. Creating the line object that shows where the vehicle
 *        is going. It might be responsible for updating this line;
 *        or that responsibility could be delegated to another class.
 *      
 * @author rdb
 * 02/02/11
 */

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RoverPanel extends JPanel implements Animated, MouseListener
{ 
   //-------------------- class variables -------------------------
   private static RoverPanel      thePanel;    // private access to the Panel
                                             //  used by public static methods
   //-------------------- instance variables ----------------------
   private JFrame     _frame;


   
   //--------------- constants -------------------------------------
   
   private Color[]   hospColors = { Color.WHITE, Color.RED, Color.PINK };
   private int[]     transmitX = { 50, 500, 500, 250, 300 };
   private int[]     transmitY = { 400, 100, 350, 400, 50 };
   private int       transmitW = 40;
   private int       transmitH = 70;
   private ArrayList<TransmitLocation> _transmits;
   
   //------------- Constructor ---------------------------------
   /**
    * Create and manage an Rover using Swing.
    * Create the transmit location objects
    */
   public RoverPanel( JFrame frame ) 
   {
      super();
      _frame = frame;
      
      this.setLayout( null );
      this.addMouseListener( this);
      this.setBackground( Color.LIGHT_GRAY );
      
      Color roverColor = new Color( 0, 255, 0, 128 ); // semitransparent green
      
      RoverApp.nLocations = Math.min( RoverApp.nLocations, 
                                        transmitX.length );

       //////////////////////////////////////////////////////////////
      // Create a line object that will show where the vehicle is going
      //   if it is going to a research site or a transmit location.
      // Add it to the panel.
      // This object could be created in another class, but it does
      //    need to be added to this panel.
      //////////////////////////////////////////////////////////////

      
      /////////////////////////////////////////////////////////////
      // Create a Rover and add it to the panel.
      // This also could be created in another class and added to
      //    this panel.
      /////////////////////////////////////////////////////////////

      
      
      /////////////////////////////////////////////////////////////
      // Create a FrameTimer with an initial delay of 2000 millisecs
      //   and a delay of 100 millisecs.
      // Start the timer.
      /////////////////////////////////////////////////////////////

      
      
      
      //////////////////////////////////////////////////////////////
      // Create a collection object to hold the research sites.
      //////////////////////////////////////////////////////////////
      
      //----------- make the transmit locations 
      makeTransmitLocations();
      
     ///////////////////////////////////////////////////////////////
      // Create a Dispatcher, who is responsible for controlling the
      //    travels of the vehicle. 
      // The Dispatcher has to know about or have access to (at least)
      //    -- the collection of research sites, 
      //    -- the transmits array list
      //    -- the timer
      /////////////////////////////////////////////////////////////// 

   } 
   //---------------------- makeTransmitLocations() -------------------------
   /**
    * create the desired number of transmit locations
    * This method is complete.
    */
   private void makeTransmitLocations()
   {
      _transmits = new ArrayList<TransmitLocation>();
      for ( int s = 0; s < RoverApp.nLocations; s++ )
      {
         int hx = transmitX[ s ];
         int hy = transmitY[ s ];
         Color hColor = hospColors[ s % hospColors.length ];
         TransmitLocation h = new TransmitLocation( hColor, hx, hy, transmitW, transmitH );
        
         _transmits.add( h);
      }
   }

   //+++++++++++++++++++ Animated methods +++++++++++++++++++++++
   private boolean _animated = true;
   /**
    * return true always. Program makes no sense with animation off
    */
   public boolean isAnimated()
   {
      return _animated; // can't turn off animation
   }
   /**
    * cannot turn off animation, so this method is a no-op.
    */
   public void setAnimated( boolean onOff )
   {
   }
   //---------------------- newFrame ------------------------------
   /**
    * move rover for each new frame; this is called by the FrameTimer 
    * listener; it  gets invoked when the time interval elapses and awt 
    * creates an event.
    */
   public void newFrame()
   {
      //////////////////////////////////////////////////////////////////
      // Your Dispatcher objects newFrame needs to be called; 
      //   depending on your design decisions, other things might
      //   also be done here.
      //////////////////////////////////////////////////////////////////

      repaint();
   }

   //++++++++++++++++++ MouseListener methods +++++++++++++++++++++++++++
   // You need to implement mousePressed; the others must be there but
   //   will remain "empty".
   //
   //------------------- mousePressed( MouseEvent ) ----------------------
   /**
    * On mousePressed, replace current site with a site at the mouse position.
    */
   public void mousePressed( MouseEvent me )
   {      
      //System.out.println("New site: " + me.getPoint());
      ////////////////////////////////////////////////////////////////
      // Need to add a new research site here
      ////////////////////////////////////////////////////////////////
      
      repaint();      
   }
   //------------- unused interface methods -----------------
   public void mouseDragged( MouseEvent me ){ }
   public void mouseClicked( MouseEvent me ) {}
   public void mouseEntered( MouseEvent me ) {}
   public void mouseExited( MouseEvent me ) {}
   public void mouseMoved( MouseEvent me ) {}
   public void mouseReleased( MouseEvent me ){}
   //++++++++++++++++++++++ end Mouse Listener methods ++++++++++++++++++
   

   //----------------- paintComponent( Graphics) ----------------------------
   /**
    * delegate calls to the AWT objects
    */
   public void paintComponent( Graphics g )
   {
      super.paintComponent( g );
      for ( TransmitLocation h: _transmits )
         h.display( (Graphics2D ) g );
   }
      
   //++++++++++++++++++++++ convenience system test ++++++++++++++++++++++++++
   //------------------------ main -----------------------------------
   public static void main( String[] args )
   {
      RoverApp.main( args );
   }
}