package org.wahlzeit.model;

/**
 * interface for coordinate representations
 */
public interface Coordinate {

    /**
	 * @methodtype conversion
     * @methodproperties hook
     * @return the instance itself if already cartesian, a new cartesian coordinate instance otherwise by conversion
	 */
    public CartesianCoordinate asCartesianCoordinate();

    /**
	 * @methodtype conversion
     * @methodproperties hook
     * @return the instance itself if already spheric, a new spheric coordinate instance otherwise by conversion
	 */
    public SphericCoordinate asSphericCoordinate();


    /**
	 * @methodtype comparison
     * @methodproperties hook
     * @return the cartesian distance between two coordinate instances independent of their type
	 */
    public double getCartesianDistance(Coordinate other);

    /**
	 * @methodtype comparison
     * @methodproperties hook
     * @return the spherical distance or rather the central angle between two coordinate instances independent of their type
	 */
    public double getCentralAngle(Coordinate other);

    /**
	 * @methodtype query
     * @methodproperties hook
     * @description Checks weither two coordinate instances are equal dependent on their type and fields
     * @return true if they are equal, false if not
	 */
    public boolean isEqual(Coordinate other);
}
