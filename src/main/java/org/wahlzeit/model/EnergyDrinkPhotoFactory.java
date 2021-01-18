package org.wahlzeit.model;

import java.util.List;

@DesignPattern(
	name = "Abstract_Factory",
	participants = {"ConcreteFactory"}
)
public class EnergyDrinkPhotoFactory extends PhotoFactory {
    
    private static EnergyDrinkPhotoFactory instance = null;

	protected EnergyDrinkPhotoFactory() {
		super();
	}

	public static void initialize() {
		getInstance(); 
    }

    public static synchronized EnergyDrinkPhotoFactory getInstance() {
        if(instance == null){
            setInstance(new EnergyDrinkPhotoFactory());
        }
        return instance;
    }
    
    protected static synchronized void setInstance(EnergyDrinkPhotoFactory EDPFIstance) {
        if (instance != null) {
			throw new IllegalStateException("attempt to initialize EnergyDrinkPhotoFactory twice");
		}
        instance = EDPFIstance;
    }

    /**
     * @pre: id may not be null
     * @post: ingredients, brand, manufacturer of new instance may not be null
     * @param id PhotoId
     * @param ingredients list of this energy drinks ingredients
     * @param brand brand of this energy drink
     * @param manufacturer manufacturer of this energy drink
     * @return a new EnergyDrinkPhoto instance
     * @throws CreateEnergyDrinkPhotoException
     */
    public EnergyDrinkPhoto createPhoto(PhotoId id, List<Ingredient> ingredients, String brand, String manufacturer) throws CreateEnergyDrinkPhotoException {
        assertNotNullArgument(id);
        EnergyDrinkPhoto newPhoto;
        try{
            newPhoto = new EnergyDrinkPhoto(id, ingredients, brand, manufacturer);
        }catch(IllegalStateException e){
            throw new CreateEnergyDrinkPhotoException(e);
        }
        return newPhoto;
    }

    /**
     * @pre: id may not be null
     * @post: brand of new instance may not be null 
     * @param id PhotoId
     * @param brand brand of this energy drink
     * @return a new EnergyDrinkPhoto instance
     * @throws CreateEnergyDrinkPhotoException
     */
    public EnergyDrinkPhoto createPhoto(PhotoId id, String brand) throws CreateEnergyDrinkPhotoException {
        assertNotNullArgument(id);
        EnergyDrinkPhoto newPhoto;
        try{
            newPhoto = new EnergyDrinkPhoto(id, brand);
        }catch(IllegalStateException e){
            throw new CreateEnergyDrinkPhotoException(e);
        }
        return newPhoto;
    }

    protected void assertNotNullArgument(Object o) throws IllegalArgumentException {
		if(o == null){
            String message = "Argument can't be null";
            throw new IllegalArgumentException(message);
        }
	}
}
