package org.wahlzeit.model;

import java.lang.Math;

/**
 * object for cartesian coordinate representation
 */
public class Coordinate {

    private double x;
    private double y;
    private double z;

    /**
	 * 
	 * @methodtype constructor
     * @methodproperties convenience
	 */
    public Coordinate(){
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }
    /**
	 * 
	 * @methodtype constructor
     * @methodproperties convenience
	 */
    public Coordinate(double x){
        this.setX(x);
        this.y = 0.0;
        this.z = 0.0;
    }
    /**
	 * 
	 * @methodtype constructor
     * @methodproperties convenience
	 */
    public Coordinate(double x, double y){
        this.setX(x);
        this.setY(y);
        this.z = 0.0;
    }
    /**
	 * 
	 * @methodtype constructor
	 */
    public Coordinate(double x, double y, double z){
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }
    /**
	 * 
	 * @methodtype constructor
     * @methodproperties convenience
	 */
    public Coordinate(double[] coords){
        this.setFromArray(coords);
    }
    /**
	 * 
	 * @methodtype constructor
     * @methodproperties convenience
	 */
    public Coordinate(String coords){
        this.setFromString(coords);
    }
    /**
	 * 
	 * @methodtype conversion
     * @methodproperties composed, convenience
	 */
    public double[] asArray(){
        double[] coords = new double[3];
        coords[0] = this.getX();
        coords[1] = this.getY();
        coords[2] = this.getZ();
        return coords;
    }
    /**
	 * 
	 * @methodtype conversion
     * @methodproperties convenience
	 */
    public String asString(){
        String xS = String.valueOf(this.x);
        String yS = String.valueOf(this.y);
        String zS = String.valueOf(this.z);
        return xS + "," + yS + "," + zS;
    }
    /**
	 * 
	 * @methodtype get
     * @methodproperties primitive
	 */
    public double getX(){
        return this.x;
    }
    /**
	 * 
	 * @methodtype get
     * @methodproperties primitive
	 */
    public double getY(){
        return this.y;
    }
    /**
	 * 
	 * @methodtype get
     * @methodproperties primitive
	 */
    public double getZ(){
        return this.z;
    }
    /**
	 * 
	 * @methodtype set
     * @methodproperties primitive
	 */
    public void setX(double newX){
        this.x = newX;
    }
    /**
	 * 
	 * @methodtype set
     * @methodproperties primitive
	 */
    public void setY(double newY){
        this.y = newY;
    }
    /**
	 * 
	 * @methodtype set
     * @methodproperties primitive
	 */
    public void setZ(double newZ){
        this.z = newZ;
    }
    /**
	 * 
	 * @methodtype set
     * @methodproperties composed, convenience
	 */
    public void setFromArray(double[] coords){
        assert coords.length == 3;
        this.setX(coords[0]);
        this.setY(coords[1]);
        this.setZ(coords[2]);
    }
    /**
	 * 
	 * @methodtype set
     * @methodproperties composed, convenience
	 */
    public void setFromString(String coordString) throws java.lang.NumberFormatException{
        // remove whitespaces and non-visible characters
        String tmp = coordString.replaceAll("\\s+","");
        // remove all non-numeric characters
        tmp = tmp.replaceAll("[^\\d.,-]", "");
        String[] coords = tmp.split(",");
        assert coords.length == 3;
        this.setX(Double.parseDouble(coords[0]));
        this.setY(Double.parseDouble(coords[1]));
        this.setZ(Double.parseDouble(coords[2]));
    }
    /**
	 * 
	 * @methodtype comparison
     * @methodproperties primitive
	 */
    protected double getDistance(Coordinate other){
        double tmp1 = Math.pow(other.getX() - this.x, 2);
        double tmp2 = Math.pow(other.getY() - this.y, 2);
        double tmp3 = Math.pow(other.getZ() - this.z, 2);
        return Math.sqrt(tmp1 + tmp2 + tmp3);
    }
    /**
	 * 
	 * @methodtype query
     * @methodproperties primitive
	 */
    private boolean checkX(double x){
        return this.x == x;
    }
    /**
	 * 
	 * @methodtype query
     * @methodproperties primitive
	 */
    private boolean checkY(double y){
        return this.y == y;
    }
    /**
	 * 
	 * @methodtype query
     * @methodproperties primitive
	 */
    private boolean checkZ(double z){
        return this.z == z;
    }
    /**
	 * 
	 * @methodtype query
     * @methodproperties composed
	 */
    protected boolean isEqual(Coordinate other){
        boolean tmp1 = this.checkX(other.getX());
        boolean tmp2 = this.checkY(other.getY());
        boolean tmp3 = this.checkZ(other.getZ());
        return tmp1 && tmp2 && tmp3;
    }    
}
