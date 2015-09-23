/**
 * TransmitLocation.java - represent a transmit location using A-classes
 * 
 *        This class is completed.
 * 
 *        Note: the location returned by getLocation is the bottom middle,
 *              not the upper left corner of the bounding box.
 * @author rdb
 * 02/02/2011
 */

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class TransmitLocation
{
   //---------------- instance variables ------------------------
   // bottom middle location -- returned by getLocation
   private Point             _middle;
   private ArrayList<AShape> _shapes;
   
   //--------- magic constants
   private int winH   = 10;
   private int winW   = 6;
   
   //--------------------  constructors ---------------------------
   /**
    * Constructor for TransmitLocation: specify color, location, and size
    */
   public TransmitLocation( Color aColor, int x, int y, int w, int h )
   {     
      _shapes = new ArrayList<AShape>();
      
      int baseH = (int)(.25 * h);
      ARectangle base = new ARectangle( x, y );
      base.setFillColor( aColor );
      base.setFrameColor( Color.BLACK );
      base.setSize( w,  baseH);
      _shapes.add( base );
      
      int stemW = (int)(.1 * w);
      int stemX = x + (int)((.5 * w) + (.5 * stemW));
      int stemH = (int)(.5 * h);
      int stemY = y - (int)(stemH);
      ARectangle stem = new ARectangle( stemX, stemY );
      stem.setFillColor( aColor );
      stem.setFrameColor( Color.BLACK );
      stem.setSize( stemW, stemH );
      _shapes.add( stem );

      int dishH = (int)(.75 * h);
      int dishW = (int)(.25 * w);
      AEllipse dish = new AEllipse( x + (int)(.5 * w) - (int)(.5 * dishW), 
        y - stemH - (int)(.5 * dishH) );
      dish.setFillColor( aColor );
      dish.setFrameColor( Color.BLACK );
      dish.setSize( dishW, dishH );
      _shapes.add( dish );
      
      _middle = new Point( x + (int)(.5 * w), y );
   }
   //--------------------- getLocation() --------------------------------
   /**
    * return location at the bottom middle
    */
   public Point getLocation()
   {
      return _middle;
   }
   //----------------- display( Graphics2D ) ----------------------------
   /**
    * delegate calls to the AWT objects
    */
   public void display( Graphics2D g2 )
   {
      for ( AShape shape: _shapes )
         shape.display( g2 );
   }
}    