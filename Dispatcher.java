/**
 * Dispatcher.java -- Controls the activities of the Rover
 *        Resonsibilities:
 *            1. Know the current state of the vehicle: is it at home,
 *               heading to a research site, heading to transmit, heading home.
 *            2. Tell the vehicle what to do next. Normally, this will be
 *               after it gets to its next destination. However, if the
 *               vehicle is heading home and a research site gets added,
 *               it should get re-directed to the site.
 *            3. It must know about or be able to get information from
 *               a. the research sites collection
 *               b. the transmit location collection
 *               c. the timer (because it needs to know when that 
 *                  vehicle needs to wait for 2 seconds. The easiest
 *                  way to do this is to simply "restart()" the timer.
 *                  This works because there is nothing else moving in
 *                  this application except one vehicle. If that weren't
 *                  the case, the timer would have to keep running and
 *                  the code would have to count events in order to figure
 *                  out when 2 seconds had elapsed.)
 * 
 *        Framework:
 *            This class should implement the Animated interface, and get
 *            called (by RoverPanel) on every frame event. For example, 
 *            if the vehicle is home or going home and there is an entry 
 *            in the sites array, the dispatcher needs to tell the vehicle 
 *            to go to the next site in the array. There are of course 
 *            several other cases.
 *
 * @author huy le
 * 09/28/15
 */
import java.util.*;
import java.awt.*;

public class Dispatcher implements Animated
{
    //-------------------- instance variables ---------------------
    private ArrayList<TransmitLocation>      _transmits;
    private Rover rover;
    private ArrayList<ResearchSite> rs;
    private Boolean visiting = false;
    private int homeX = 10;
    private int homeY = 10;
    
    //------------------ constructor -----------------------------
    public Dispatcher( ArrayList<TransmitLocation>      trans
                          , Rover _r, ArrayList<ResearchSite> _rs
                     )
    {
//       Rover rover1 = new Rover( 30, 30, Color.GREEN );
//       ArrayList researchsite = new ArrayList<ResearchSite>();
        _transmits   = trans;
        // most/all of what you get, will be saved in instance variables
        rover = new Rover( 5, 5, Color.GREEN );
        rover = _r;
        rs = _rs;
    }
    //---------------------- setNextSite() -------------------------
    /**
     * identify the next goal site, if there is one, and start vehicle
     * towards it.
     */
    private ResearchSite getNextSite()
    {
        ResearchSite next = null;
        ///////////////////////////////////////////////////////////////
        // get next site from list of sites or from someone who has the
        //    list of sites.
        ///////////////////////////////////////////////////////////////
        rs.get(0);
        
        
        return next;
    }
    //--------------- getClosestTransmitLocation( Point ) -----------------
    /**
     * find the closest hospital site to the parameter
     *    This method is complete.
     * Note that we don't bother to compute the correct distance,
     *    we make the decision based on the square of the distance
     *    which saves the computation of the square root, a reasonably
     *    costly operation that serves no purpose for this test.
     */
    private TransmitLocation getClosestTransmitLocation( Point loc )
    {
        double distanceSq = Float.MAX_VALUE;
        TransmitLocation   closest    = null;
        for ( TransmitLocation h: _transmits )
        {
            double d2 = loc.distanceSq( h.getLocation() );
            if ( d2 < distanceSq )
            {
                distanceSq = d2;
                closest = h;
            }
        }
        return closest;
    }
    
    //++++++++++++++++++++++ Animated interface +++++++++++++++++++++++++
    private boolean _animated = true;
    //---------------------- isAnimated() ----------------------------------
    public boolean isAnimated()
    {
        return _animated;
    }
    //---------------------- setAnimated( boolean ) --------------------
    public void setAnimated( boolean onOff )
    {
        _animated = onOff;
    }
    //---------------------- newFrame ------------------------------
    /**
     * invoked for each frame of animation; 
     * update the position of the vehicle; check if it has reached the
     * goal position. If so, figure out what the next goal should be.
     * 
     * If previous goal was a research site, new goal is transmit,
     *    find nearest one.
     * If previous goal was transmit, new goal is the next
     *    research site, if there is one, or home if no more 
     *    sites to be researched.
     */
    public void newFrame()
    {
        //////////////////////////////////////////////////////////////////
        // If the vehicle is moving, update its position (by calling its 
        //    newFrame method. 
        // Somehow, need to know if it has reached its goal position. 
        //    If so, figure out what the next goal should be.
        // 
        //    If previous goal was a research site, new goal is transmit,
        //    If previous goal was a transmit location (or if it was at
        //       home, or if it was going home), new goal is the next
        //       research site, if there is one, or home if no more 
        //       research sites.
        /////////////////////////////////////////////////////////////////
        
        if( rover.isAnimated() == true ) //1
        {
            rover.newFrame();
        }
        if( rover.isAnimated() == false ) //2
        {
            double x = rover.getBounds().getX();
            double y = rover.getBounds().getY();
            
            Point o = new Point();
            o.x = (int)x;
            o.y = (int)y;
            
            if( visiting == true ) //at site
            {
                Utilities.sleep( 2000 );
                TransmitLocation nexttransmit = getClosestTransmitLocation( o );
                rover.goTo( nexttransmit.getLocation().x, nexttransmit.getLocation().y, 10 );
                visiting = false;
                return;
            }
            if( visiting == false )
            {
                if( rs.size() == 0 ) //if no more site to go
                {
                    
                    
                    //rover.goTo( homeX, homeY, 10 );
                    
                    if( rs.size() > 0 )
                    {
                        double dx1 = rs.get( 0 ).getBounds().getX();
                        double dy1 = rs.get( 0 ).getBounds().getY();
                        rover.goTo( dx1, dy1, 20 );
                        rs.get( 0 ).setVisited( true );
                        rs.remove( 0 );
                        visiting = true;
                    }
                    else
                        rover.goTo( homeX, homeY, 5 );
                    
                
                }
                else if( rs.size() > 0 ) //still have sites to go
                {
                    double dx = rs.get( 0 ).getBounds().getX();
                    double dy = rs.get( 0 ).getBounds().getY();
                    rover.goTo( dx, dy, 20 );
                    
                    rs.get( 0 ).setVisited( true );
                    rs.remove( 0 );
                    visiting = true;
                }
            }
            
        }
        //+++++++++++++++ end Animated interface ++++++++++++++++++++++++++++++++++
    }
}
    
