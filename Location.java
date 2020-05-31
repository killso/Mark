public class Location {
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    public boolean equals(Object ob) {
        if (ob instanceof Location) {
            Location loc=(Location) ob;
            if (xCoord==loc.xCoord && yCoord==loc.yCoord) return true;
        }
        return false;
    }
    public int hashcode() {
        return 37*(37*17+xCoord)+yCoord;
    }
}
