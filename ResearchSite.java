/**
 * ResearchSite -- represents a research site. 
 *         Dragging (mousePressed followed by mouseDragged) repositions it
 *            Not allowed to drag if its the current ResearchSite, easiest
 *            way to do that is make that site not draggable.
 *         MouseClicked -- if clicked, this site is no longer an emergency 
 *            site; remove it from the set of sites or somehow make it known
 *            that it is not real emergency site any more, even
 *            if it is the current ResearchSite!
 * 
 * @author rdb
 * 02/02/11
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ResearchSite extends JRectangle
                     implements MouseListener, MouseMotionListener, Draggable
{
   //--------------------- class variables --------------------------------
   public static int    size = 10;
   
   //--------------------- instance variables -----------------------------
   private boolean     _visited = false;
   
   //--------------------- constants --------------------------------------
   private Color  fillColor = Color.RED;
   private Color  lineColor = Color.BLACK;
   
   //---------------------- constructors ----------------------------------
   /**
    * Generate a site at the specified location.
    */
   public ResearchSite( Point p )
   {
      super( p.x, p.y );
      setFillColor( fillColor );
      setFrameColor( lineColor );
      setSize( size, size );
      
      addMouseListener( this );
      addMouseMotionListener( this );
   }
   //++++++++++++++++++++++ Draggable interface methods +++++++++++++++++++
   private boolean   _draggable = false; // true if this object can be dragged
   public void setDraggable( boolean onOff )
   {
      _draggable = onOff;
   }
   public boolean isDraggable()
   {
      return _draggable;
   }
   public boolean contains( java.awt.geom.Point2D point )
   {
      return getBounds().contains( point );
   }
   
   //++++++++++++++++++++ mouse methods / instance variables ++++++++++++++++
   private Point _saveMouse;   // instance variable for last mouse position
                               //   used for dragging      
   //+++++++++++++++++++++++++ mouseListener methods ++++++++++++++++++++++++
   //-------------- mousePressed -----------------------------------
   public void mousePressed( MouseEvent me )
   {
      ////////////////////////////////////////////////////////////////////
      // me.getPoint(), which we've used before is the location of the 
      // mouse "inside" the JComponent; this won't work.
      //
      // We need the position of the mouse in the container that holds the
      // JComponent: getParent().getMousePosition()
      //
      // Assign it to the instance variable, _saveMouse
      ////////////////////////////////////////////////////////////////////


      
   }
   //-------------- mouseClicked -----------------------------------
   public void mouseClicked( MouseEvent me )
   {
      ////////////////////////////////////////////////////////////////////
      // me.getPoint(), which we've used before is the location of the 
      // mouse "inside" the JComponent; this won't work.
      //
      // We need the position of the mouse in the container that holds the
      // JComponent: getParent().getMousePosition()
      //
      // Assign it to the instance variable, _saveMouse
      ////////////////////////////////////////////////////////////////////


      
   }
   //--------------- unimplemented mouse listener methods ---------------------
   public void mouseReleased( MouseEvent me ){}
   public void mouseEntered( MouseEvent me ){}
   public void mouseExited( MouseEvent me ){}
   
   //+++++++++++++++++++ mouseMotionListener methods ++++++++++++++++++++++++
   //---------------- mouseDragged ----------------------------------------
   public void mouseDragged( MouseEvent me )
   {
      //////////////////////////////////////////////////////////////////////
      //  IF this object is draggable
      //     Get new position of mouse:
      //         getParent().getMousePosition()
      //     For each of x and y coordinates, compute
      //       dX = newX - oldX (stored in _saveMouse.x)
      //       dY = newY - oldY (stored in _saveMouse.y)
      //     invoke moveBy( dX, dY ) 
      //     Save new position in _saveMouse
      //     getParent().repaint()
      //////////////////////////////////////////////////////////////////////
      


      
   }
   //----------------- mouseMoved not implemented --------------------------
   public void mouseMoved( MouseEvent me ){}
   //+++++++++++++++++ end MouseMotionListeners ++++++++++++++++++++++++++++
   
   //--------------------- main -----------------------------------
   /**
    * unit test
    */
   public static void main( String[] args )
   {     
      JFrame testFrame = new JFrame();
      testFrame.setSize( 700, 500 );  // define window size
      
      testFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      JPanel testPanel = new JPanel( (LayoutManager) null );
      testFrame.add( testPanel );
      
      ResearchSite s1 = new ResearchSite( new Point( 200, 200 ));
      testPanel.add( s1 );
      
      ResearchSite s2 = new ResearchSite( new Point( 200, 100 ));
      testPanel.add( s2 );
      //s2.setVisited( true );
 
      testFrame.setVisible( true );  // Initially, JFrame is not visible, make it so.

   }
}
      
      