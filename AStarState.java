import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
public class AStarState {
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;
    private Map<Location, Waypoint> openPoints = new HashMap<Location, Waypoint> ();
    private Map<Location, Waypoint> closePoints = new HashMap<Location, Waypoint> ();
    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }
    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        if (openPoints.size()==0) return null;
        Set openPointKeys=openPoints.keySet();
        Iterator i=openPointKeys.iterator();
        Waypoint top=null;
        float top1 = Float.MAX_VALUE;
        while (i.hasNext()) {
            Location loc=(Location)i.next();
            Waypoint waypoint = openPoints.get(loc);
            float pointTotalCost = waypoint.getTotalCost();
            if (pointTotalCost<top1) {
                top=openPoints.get(loc);
                top1= pointTotalCost;
            }
        }
        return top;
    }
    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Location loc = newWP.getLocation();
        if (openPoints.containsKey(loc)) {
            if (!(newWP.getPreviousCost() < openPoints.get(loc).getPreviousCost())) return false;
        }
        openPoints.put(loc, newWP);
        return true;
    }
    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        return openPoints.size();
    }
    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        closePoints.put(loc, openPoints.remove(loc));
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closePoints.containsKey(loc);
    }
}
