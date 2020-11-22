package org.wahlzeit.model;

public class EnergyDrinkPhotoManager extends PhotoManager {

    protected static final EnergyDrinkPhotoManager instance = new EnergyDrinkPhotoManager();

    /**
	 * @methodtype constructor
	 */
    public EnergyDrinkPhotoManager(){
        super();
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
}
