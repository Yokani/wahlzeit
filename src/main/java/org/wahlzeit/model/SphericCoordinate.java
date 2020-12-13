package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate{

    // also called azimuthal, in radian
    private double phi;
    // also called polar, in radian
    private double theta;
    
    private double radius;

    /**
	 * @methodtype constructor
     * @methodproperties convenience
	 */
    public SphericCoordinate(){
        this.phi = 0.0;
        this.theta = 0.0;
        this.radius = 0.0;
    }
    /**
	 * @methodtype constructor
	 */
    public SphericCoordinate(double phi, double theta, double radius){
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
        assertClassInvariants();
    }

    public double getPhi(){
        assertClassInvariants();
        return this.phi;
    }
    public double getTheta(){
        assertClassInvariants();
        return this.theta;
    }
    public double getRadius(){
        assertClassInvariants();
        return this.radius;
    }
    public void setPhi(double phi){
        assertClassInvariants();
        this.phi = phi;
        assertClassInvariants();
    }
    public void setTheta(double theta){
        assertClassInvariants();
        this.theta = theta;
        assertClassInvariants();
    }
    public void setRadius(double radius){
        assertClassInvariants();
        this.radius = radius;
        assertClassInvariants();
    }
    /**
	 * @methodtype comparison
     * @methodproperties primitive
     * @description Computes the spheric distance between two spheric coordinates or rather their central angle
	 */
    public double getDistance(SphericCoordinate other){
        assertClassInvariants();
        double selfLat = this.theta - (90 * Math.PI)/180;
        double selfLon = this.phi;
        double otherLat = other.getTheta() - (90 * Math.PI)/180;
        double otherLon = other.getPhi();
        double diffLon = Math.abs(selfLon - otherLon);
        return Math.acos(Math.sin(selfLat) * Math.sin(otherLat) + Math.cos(selfLat) * Math.cos(otherLat) * Math.cos(diffLon));
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
        if (!(other instanceof SphericCoordinate)){
            return false;
        }
        double tolerance = 0.0001;
        boolean eqPhi = this.checkEqual(((SphericCoordinate) other).getPhi(), this.phi, tolerance);
        boolean eqTheta = this.checkEqual(((SphericCoordinate) other).getTheta(), this.theta, tolerance);
        boolean eqRadius = this.checkEqual(((SphericCoordinate) other).getRadius(), this.radius, tolerance);
		return eqPhi && eqTheta && eqRadius;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();
        double x = this.radius * Math.sin(this.theta) * Math.cos(this.phi);
        double y = this.radius * Math.sin(this.theta) * Math.sin(this.phi);
        double z = this.radius * Math.cos(this.theta);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariants();
        return this;
    }

    /**
	 * @methodtype query
	 */
    @Override
    public int hashCode() {
        assertClassInvariants();
        int a = (int) this.phi / 3;
        int b = (int) this.theta / 3;
        int c = (int) this.radius / 3;
        return a + b + c;
    }

    @Override
    protected void assertClassInvariants() {
        if(Double.isNaN(this.phi)){
            throw new IllegalStateException("phi is not a number");
        }
        if(Double.isNaN(this.theta)){
            throw new IllegalStateException("theta is not a number");
        }
        if(Double.isNaN(this.radius)){
            throw new IllegalStateException("radius is not a number");
        }
    }
}
