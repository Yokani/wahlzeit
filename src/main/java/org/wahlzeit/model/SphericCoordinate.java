package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate{

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
    }

    public double getPhi(){
        return this.phi;
    }
    public double getTheta(){
        return this.theta;
    }
    public double getRadius(){
        return this.radius;
    }
    public void setPhi(double phi){
        this.phi = phi;
    }
    public void setTheta(double theta){
        this.theta = theta;
    }
    public void setRadius(double radius){
        this.radius = radius;
    }
    /**
	 * @methodtype comparison
     * @methodproperties primitive
     * @description Computes the spheric distance between two spheric coordinates or rather their central angle
	 */
    protected double getDistance(SphericCoordinate other){
        double selfLat = this.theta - (90 * Math.PI)/180;
        double selfLon = this.phi;
        double otherLat = other.getTheta() - (90 * Math.PI)/180;
        double otherLon = other.getPhi();
        double diffLon = Math.abs(selfLon - otherLon);
        return Math.acos(Math.sin(selfLat) * Math.sin(otherLat) + Math.cos(selfLat) * Math.cos(otherLat) * Math.cos(diffLon));
    }
    /**
	 * @methodtype query
     * @methodproperties primitive
	 */
    private boolean checkEqual(double x, double y, double delta){
        return Math.abs(x - y) < delta;
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
        double x = this.radius * Math.sin(this.theta) * Math.cos(this.phi);
        double y = this.radius * Math.sin(this.theta) * Math.sin(this.phi);
        double z = this.radius * Math.cos(this.theta);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public double getCartesianDistance(Coordinate other) {
        CartesianCoordinate selfInCartesian = this.asCartesianCoordinate();
        CartesianCoordinate otherInCartesian = other.asCartesianCoordinate();
        return selfInCartesian.getDistance(otherInCartesian);
    }

    @Override
    public double getCentralAngle(Coordinate other) {
        SphericCoordinate selfInSpheric = this.asSphericCoordinate();
        SphericCoordinate otherInSpheric = other.asSphericCoordinate();
        return selfInSpheric.getDistance(otherInSpheric);
    }

    @Override
    public boolean isEqual(Coordinate other) {
        return this.equals(other);
    }
    
    /**
	 * @methodtype query
	 */
    @Override
    public int hashCode() {
        int a = (int) this.phi / 3;
        int b = (int) this.theta / 3;
        int c = (int) this.radius / 3;
        return a + b + c;
    }
}
