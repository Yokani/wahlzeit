package org.wahlzeit.model;

@DesignPattern(
    name = "Template_Method",
    participants = {"ConcreteClass"}
)
@DesignPattern(
    name = "Bridge",
    participants = {"ConcreteBridge"}
)
public final class CartesianCoordinate extends AbstractCoordinate{

    private final double x;
    private final double y;
    private final double z;

    private CoordinateHelper coordinateHelper = CoordinateHelper.getInstance();

    /**
	 * @methodtype constructor
	 */
    private CartesianCoordinate(double x, double y, double z){
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
        double phi;
        try{
            phi = Math.atan(this.y / this.x);
        }catch(ArithmeticException aE){
            // if x is 0.0 - set it to 0.0001, because that equals to 0 according to our contract
            phi = Math.atan(this.y / 0.0001);
        }
        double radius = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
        double theta = Math.acos(this.z / radius);
        // postconditions are checked after constructor via. class invariants
        return coordinateHelper.requestSphericCoordinate(phi, theta, radius);
    }

    @Override
    public String toString() {
        return "x: " + String.valueOf(x) + ", y: " +  String.valueOf(y) + ", z: " + String.valueOf(z);
    }

    /**
	 * @methodtype query
	 */
    @Override
    public int hashCode() {
        assertClassInvariants();
        return this.toString().hashCode();
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
