package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate{

    @Override
    public double getCartesianDistance(Coordinate other) {
        assertClassInvariants();
        assertNotNullArgument(other);
        CartesianCoordinate selfInCartesian = this.asCartesianCoordinate();
        CartesianCoordinate otherInCartesian = other.asCartesianCoordinate();
        return selfInCartesian.getDistance(otherInCartesian);
    }

    @Override
    public double getCentralAngle(Coordinate other) {
        assertClassInvariants();
        assertNotNullArgument(other);
        SphericCoordinate selfInSpheric = this.asSphericCoordinate();
        SphericCoordinate otherInSpheric = other.asSphericCoordinate();
        return selfInSpheric.getDistance(otherInSpheric);
    }

    @Override
    public boolean isEqual(Coordinate other) {
        assertClassInvariants();
        return this.equals(other);
    }

    protected void assertNotNullArgument(Object o) throws IllegalArgumentException{
        if(o == null){
            String message = "Argument can't be null";
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertNotNullState(Object o) throws IllegalStateException{
        if(o == null){
            String message = "Internal variable can't be null";
            throw new IllegalStateException(message);
        }
    }

    protected abstract void assertClassInvariants();

    /**
	 * @methodtype query
     * @methodproperties primitive
	 */
    protected boolean checkEqual(double x, double y, double delta){
        return Math.abs(x - y) < delta;
    }
    
}
