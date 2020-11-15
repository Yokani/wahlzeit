package org.wahlzeit.model;

/**
 * Represents the location where a photo was taken at
 */
public class Location {

    private Coordinate coords;

    /**
	 * 
	 * @methodtype constructor
     * @methodproperties convenience
	 */
    public Location(){
        this.coords = new Coordinate();
    }

    /**
	 * 
	 * @methodtype constructor
     * @methodproperties convenience
	 */
    public Location(double[] coords){
        this.coords = new Coordinate(coords);
    }

    /**
	 * 
	 * @methodtype constructor
     * @methodproperties convenience
	 */
    public Location(String coords){
        this.coords = new Coordinate(coords);
    }

    /**
	 * 
	 * @methodtype constructor
	 */
    public Location(Coordinate coords){
        this.coords = coords;
    }

    /**
	 * 
	 * @methodtype set
     * @methodproperties primitive
	 */
    public void setCoordinates(Coordinate coords){
        this.coords = coords;
    }

    /**
	 * 
	 * @methodtype get
     * @methodproperties primitive
	 */
    public Coordinate getCoordinates(){
        return this.coords;
    }

    /**
	 * 
	 * @methodtype conversion
     * @methodproperties primitive
	 */
    public double[] asArray(){
        return this.coords.asArray();
    }

    /**
	 * 
	 * @methodtype conversion
     * @methodproperties primitive
	 */
    public String toString(){
        return this.coords.asString();
    }

    /**
	 * 
	 * @methodtype query
     * @methodproperties primitive
	 */
    public boolean equals(Location other){
        return this.coords.isEqual(other.getCoordinates());
    }

    /**
	 * 
	 * @methodtype comparison
     * @methodproperties primitive
	 */
    public double distanceTo(Location other){
        return this.coords.getDistance(other.getCoordinates());
    }
    
}
