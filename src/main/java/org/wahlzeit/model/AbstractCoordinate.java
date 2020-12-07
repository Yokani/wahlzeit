package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate{

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
     * @methodproperties primitive
	 */
    protected boolean checkEqual(double x, double y, double delta){
        return Math.abs(x - y) < delta;
    }
    
}
