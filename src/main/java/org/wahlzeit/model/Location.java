package org.wahlzeit.model;

/**
 * Represents the location where a photo was taken at
 */
public class Location {

    private CartesianCoordinate coords;

    /**
	 * 
	 * @methodtype constructor
	 */
    public Location(CartesianCoordinate coords){
        this.coords = coords;
    }

    /**
	 * 
	 * @methodtype set
     * @methodproperties primitive
	 */
    public void setCoordinates(CartesianCoordinate coords){
        this.coords = coords;
    }

    /**
	 * 
	 * @methodtype get
     * @methodproperties primitive
	 */
    public CartesianCoordinate getCoordinates(){
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
	 * @methodtype query
     * @methodproperties primitive
	 */
    public boolean equals(Location other){
        return this.coords.equals(other.getCoordinates());
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
