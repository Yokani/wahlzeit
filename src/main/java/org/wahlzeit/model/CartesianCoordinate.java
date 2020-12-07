package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate{

    private double x;
    private double y;
    private double z;

    /**
	 * @methodtype constructor
     * @methodproperties convenience
	 */
    public CartesianCoordinate(){
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }
    /**
	 * @methodtype constructor
	 */
    public CartesianCoordinate(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getZ(){
        return this.z;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void setZ(double z){
        this.z = z;
    }

    /**
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
	 * @methodtype set
     * @methodproperties composed, convenience
     * @description implies that coordinates are given as the specific ordering [x,y,z]
	 */
    public void setFromArray(double[] coords){
        assert coords.length == 3;
        this.setX(coords[0]);
        this.setY(coords[1]);
        this.setZ(coords[2]);
    }
    /**
	 * @methodtype conversion
     * @methodproperties convenience
	 */
    public String toString(){
        String xS = String.valueOf(this.x);
        String yS = String.valueOf(this.y);
        String zS = String.valueOf(this.z);
        return xS + "," + yS + "," + zS;
    }
    
    /**
	 * @methodtype comparison
     * @methodproperties primitive
     * @description Computes the cartesian distance between two cartesian coordinates
	 */
    public double getDistance(CartesianCoordinate other){
        double xDiff = Math.pow(other.getX() - this.x, 2);
        double yDiff = Math.pow(other.getY() - this.y, 2);
        double zDiff = Math.pow(other.getZ() - this.z, 2);
        return Math.sqrt(xDiff + yDiff + zDiff);
    }

    /**
	 * @methodtype query
     * @methodproperties composed
	 */
    @Override
    public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (this == other) {
			return true;
        }
        if (!(other instanceof CartesianCoordinate)){
            return false;
        }
        double tolerance = 0.0001;
        boolean eqX = this.checkEqual(((CartesianCoordinate) other).getX(), this.x, tolerance);
        boolean eqY = this.checkEqual(((CartesianCoordinate) other).getY(), this.y, tolerance);
        boolean eqZ = this.checkEqual(((CartesianCoordinate) other).getZ(), this.z, tolerance);
		return eqX && eqY && eqZ;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        // to prevent arithmetric exceptions
        double tmpX;
        if(this.x == 0.0){
            // by my specification this value is equal to zero
            tmpX = 0.000001;
        }else{
            tmpX = this.x;
        }
        double phi = Math.atan(this.y / tmpX);
        double radius = Math.sqrt(Math.pow(tmpX, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
        double theta = Math.acos(this.z / radius);
        return new SphericCoordinate(phi, theta, radius);
    }

    /**
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
