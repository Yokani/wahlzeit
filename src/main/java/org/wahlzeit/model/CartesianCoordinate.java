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
        assertClassInvariants();
    }

    public double getX(){
        assertClassInvariants();
        return this.x;
    }
    public double getY(){
        assertClassInvariants();
        return this.y;
    }
    public double getZ(){
        assertClassInvariants();
        return this.z;
    }
    public void setX(double x){
        assertClassInvariants();
        this.x = x;
        assertClassInvariants();
    }
    public void setY(double y){
        assertClassInvariants();
        this.y = y;
        assertClassInvariants();
    }
    public void setZ(double z){
        assertClassInvariants();
        this.z = z;
        assertClassInvariants();
    }

    /**
	 * @methodtype conversion
     * @methodproperties composed, convenience
	 */
    public double[] asArray(){
        assertClassInvariants();
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
     * @pre: coords.length == 3
	 */
    public void setFromArray(double[] coords){
        assertClassInvariants();
        assert coords.length == 3;
        this.setX(coords[0]);
        this.setY(coords[1]);
        this.setZ(coords[2]);
        assertClassInvariants();
    }
    /**
	 * @methodtype conversion
     * @methodproperties convenience
	 */
    public String toString(){
        assertClassInvariants();
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
        assertClassInvariants();
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
        assertClassInvariants();
        return this;
    }

    /**
     * @pre: x > 0.0
     * @post: radius > 0.0
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariants();
        assertIsValidX(this.x);
        double phi = Math.atan(this.y / this.x);
        double radius = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
        double theta = Math.acos(this.z / radius);
        assert radius > 0.0;
        return new SphericCoordinate(phi, theta, radius);
    }

    protected void assertIsValidX(double x) throws ArithmeticException{
        if(x == 0.0){
            String msg = "CartesianCoordinate's x value equals 0.0";
            throw new ArithmeticException(msg);
        }
    }

    /**
	 * @methodtype query
	 */
    @Override
    public int hashCode() {
        assertClassInvariants();
        int a = (int) this.x / 3;
        int b = (int) this.y / 3;
        int c = (int) this.z / 3;
        return a + b + c;
    }

    @Override
    protected void assertClassInvariants() {
        if(Double.isNaN(this.x)){
            throw new IllegalStateException("x is not a number");
        }
        if(Double.isNaN(this.y)){
            throw new IllegalStateException("y is not a number");
        }
        if(Double.isNaN(this.z)){
            throw new IllegalStateException("z is not a number");
        }
    }
}
