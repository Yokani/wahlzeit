package org.wahlzeit.model;

import java.util.ArrayList;

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
     * @pre: arguments except ingredients may not be null
     * @post: ingredients, brand, manufacturer of new instance may not be null
     * @param id the PhotoId for the new photo
     * @param brand the brand of the drink, e.g. Monster
     * @param tasteDirection the taste of the drink, e.g. exotic
     * @param manufacturer the manufacturer of the drink, e.g. S.Spitz GmbH
     * @param ingredients the list of ingredients for the drink, e.g. sugar 11g - can be null if not known
     * @return a new EnergyDrinkPhoto instance
     * @throws CreateEnergyDrinkPhotoException
     * for sequence of method calls, visit documentation at ...
     */
    public EnergyDrinkPhoto createPhoto(PhotoId id, String brand, String tasteDirection, String manufacturer, ArrayList<Ingredient> ingredients) throws CreateEnergyDrinkPhotoException {
        assertNotNullArgument(id);
        assertNotNullArgument(brand);
        assertNotNullArgument(tasteDirection);
        assertNotNullArgument(manufacturer);
        EnergyDrinkPhoto newPhoto;
        try{
            EnergyDrinkType typ = EnergyDrinkPhotoManager.getInstance().requestEnergyDrinkType(brand, tasteDirection, manufacturer, ingredients);
            if(ingredients != null  && !ingredients.isEmpty()){
                typ.setIngredients(ingredients);
            }
            newPhoto = new EnergyDrinkPhoto(id, typ);
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
