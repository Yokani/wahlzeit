package org.wahlzeit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnergyDrinkPhotoManager extends PhotoManager {

    protected static final EnergyDrinkPhotoManager instance = new EnergyDrinkPhotoManager();

    private Map<Integer, EnergyDrinkType> allEnergyDrinkTypes;

    /**
	 * @methodtype constructor
	 */
    public EnergyDrinkPhotoManager(){
        super();
        this.allEnergyDrinkTypes = new HashMap<>();
    }

    /**
	 * @methodtype get
	 */
    public static EnergyDrinkPhotoManager getInstance() {
        return instance;
    }

    /**
	 * @methodtype get
	 */
    @Override
    public EnergyDrinkPhoto getPhotoFromId(PhotoId id) {
        return (EnergyDrinkPhoto) super.getPhotoFromId(id);
    }

    public EnergyDrinkType requestEnergyDrinkType(String brand, String tasteDirection, String manufacturer, ArrayList<Ingredient> ingredients){
        EnergyDrinkType referenceType = new EnergyDrinkType(brand, tasteDirection, manufacturer, ingredients);
        EnergyDrinkType result = allEnergyDrinkTypes.get(referenceType.hashCode());
        if(result == null){
            synchronized (allEnergyDrinkTypes) {
                result = allEnergyDrinkTypes.get(referenceType.hashCode());
                if (result == null) {
                    result = referenceType;
                    allEnergyDrinkTypes.put(referenceType.hashCode(), referenceType);
                }
            }
        }
        if(ingredients != null && !ingredients.isEmpty() && result.getIngredients().isEmpty()){
            result.setIngredients(ingredients);
        }
        return result;
    }

    public void removeEnergyDrinkType(EnergyDrinkType template){
        allEnergyDrinkTypes.remove(template.hashCode());
    }
}
