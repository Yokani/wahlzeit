package org.wahlzeit.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CoordinateHelper {

    private static CoordinateHelper instance = null;

    private Map<Integer, CartesianCoordinate> allCartesianCoordinates;
    private Map<Integer, SphericCoordinate> allSphericCoordinates;

    protected CoordinateHelper() {
        allCartesianCoordinates = new HashMap<>();
        allSphericCoordinates = new HashMap<>();
    }

    public static void initialize() {
        getInstance();
    }

    public static synchronized CoordinateHelper getInstance() {
        if (instance == null) {
            setInstance(new CoordinateHelper());
        }
        return instance;
    }

    protected static synchronized void setInstance(CoordinateHelper CHIstance) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize EnergyDrinkPhotoFactory twice");
        }
        instance = CHIstance;
    }

    /**
     * For shared object functionality
     * 
     * @return returns the similar object or the template used and shares it
     */
    public CartesianCoordinate requestCartesianCoordinate(double x, double y, double z) {
        CartesianCoordinate template;
        try {
            template = createCartesianCoordinateTemplate(x, y, z);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            //e.printStackTrace();
            throw new IllegalArgumentException("Could not create a cartesian coordinate object with given arguments...");
        }
        CartesianCoordinate result = allCartesianCoordinates.get(template.hashCode());
        if (result == null) {
            synchronized (allCartesianCoordinates) {
                result = allCartesianCoordinates.get(template.hashCode());
                if (result == null) {
                    result = template;
                    allCartesianCoordinates.put(template.hashCode(), template);
                }
            }
        }
        return result;
    }

    /**
     * For shared object functionality
     * 
     * @return returns the similar object or the template used and shares it
     */
    public SphericCoordinate requestSphericCoordinate(double phi, double theta, double radius) {
        SphericCoordinate template;
        try {
            template = createSphericCoordinateTemplate(phi, theta, radius);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            //e.printStackTrace();
            throw new IllegalArgumentException("Could not create a spheric coordinate object with given arguments...");
        }
        SphericCoordinate result = allSphericCoordinates.get(template.hashCode());
        if (result == null) {
            synchronized (allSphericCoordinates) {
                result = allSphericCoordinates.get(template.hashCode());
                if (result == null) {
                    result = template;
                    allSphericCoordinates.put(template.hashCode(), template);
                }
            }
        }
        return result;
    }

    /**
     * creates a new CartesianCoordinate object by bypassing the private constructor through java reflections
     */
    public CartesianCoordinate createCartesianCoordinateTemplate(double x, double y, double z)
            throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Class[] cArgs = new Class[3];
        cArgs[0] = double.class;
        cArgs[1] = double.class;
        cArgs[2] = double.class;
        Constructor<CartesianCoordinate> constructor = CartesianCoordinate.class.getDeclaredConstructor(cArgs);
        constructor.setAccessible(true);
        CartesianCoordinate template = constructor.newInstance(x, y, z);
        constructor.setAccessible(false);
        return template;
    }

    /**
     * creates a new SphericCoordinate object by bypassing the private constructor through java reflections
     */
    public SphericCoordinate createSphericCoordinateTemplate(double phi, double theta, double radius)
            throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
            Class[] cArgs = new Class[3];
            cArgs[0] = double.class;
            cArgs[1] = double.class;
            cArgs[2] = double.class;
        Constructor<SphericCoordinate> constructor = SphericCoordinate.class.getDeclaredConstructor(cArgs);
        constructor.setAccessible(true);
        SphericCoordinate template = constructor.newInstance(phi, theta, radius);
        constructor.setAccessible(false);
        return template;
    }

    /**
     * @param template remove the object similar to template, if it exists
     */
    public void removeCartesianCoordinate(CartesianCoordinate template){
        allCartesianCoordinates.remove(template.hashCode());
    }

    /**
     * @param template remove the object similar to template, if it exists
     */
    public void removeSphericCoordinate(SphericCoordinate template){
        allSphericCoordinates.remove(template.hashCode());
    }
    
}
