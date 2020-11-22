package org.wahlzeit.model;

import java.util.List;

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

    public EnergyDrinkPhoto createPhoto(List<Ingredient> ingredients, String brand, String manufacturer) {
        return new EnergyDrinkPhoto(ingredients, brand, manufacturer);
    }

    public EnergyDrinkPhoto createPhoto(PhotoId id, List<Ingredient> ingredients, String brand, String manufacturer) {
        return new EnergyDrinkPhoto(id, ingredients, brand, manufacturer);
    }

    public EnergyDrinkPhoto createPhoto(String brand) {
        return new EnergyDrinkPhoto(brand);
    }

    public EnergyDrinkPhoto createPhoto(PhotoId id, String brand) {
        return new EnergyDrinkPhoto(id, brand);
    }
}
