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
     * implies that coordinates are given as the specific ordering [x,y,z]
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
    private boolean checkEqual(double x, double y, double delta){
        return Math.abs(x - y) < delta;
    }

    /**
	 * 
	 * @methodtype query
     * @methodproperties composed
	 */
    @Override
    public boolean equals(Object o) {
        // null check
		if (o == null) {
			return false;
		}
		// this instance check
		if (this == o) {
			return true;
		}
        // instanceof Check and actual value check
        boolean tmp1 = (o instanceof Coordinate) && this.checkEqual(((Coordinate) o).getX(), this.x, 0.0001);
        boolean tmp2 = (o instanceof Coordinate) && this.checkEqual(((Coordinate) o).getY(), this.y, 0.0001);
        boolean tmp3 = (o instanceof Coordinate) && this.checkEqual(((Coordinate) o).getZ(), this.z, 0.0001);
		if (tmp1 && tmp2 && tmp3) {
			return true;
		} else {
			return false;
		}
    }

    /**
	 * 
	 * @methodtype query
	 */
    @Override
    public int hashCode() {
        int a = (int) this.x / 3;
        int b = (int) this.y / 3;
        int c = (int) this.z / 3;
        return a + b + c;
    }
}
