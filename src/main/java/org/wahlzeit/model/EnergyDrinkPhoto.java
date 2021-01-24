package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@DesignPattern(
	name = "Abstract_Factory",
	participants = {"ConcreteProduct"}
)
public class EnergyDrinkPhoto extends Photo {

	protected EnergyDrinkType edType;

	/**
	 * @methodtype constructor
	 */
    public EnergyDrinkPhoto(PhotoId myId, EnergyDrinkType typ){
		super(myId);
		this.edType = typ;
		assertClassInvariants();
	}

	/**
	 * @methodtype query
	 */
	public String listIngredients(){
		assertClassInvariants();
		return this.edType.listIngredients();
	}
	/**
	 * @methodtype get
	 */
	public List<Ingredient> getIngredients(){
		assertClassInvariants();
		return this.edType.getIngredients();
	}
	/**
	 * @methodtype get
	 */
	public String getBrand(){
		assertClassInvariants();
		return this.edType.getBrand();
	}
	/**
	 * @methodtype get
	 */
	public String getTasteDirection(){
		assertClassInvariants();
		return this.edType.getTasteDirection();
	}
	/**
	 * @methodtype get
	 */
	public String getManufacturer(){
		assertClassInvariants();
		return this.edType.getManufacturer();
	}

	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);
		// TODO: insert method to save ingredients here

		rset.updateString("brand", this.edType.getBrand());
		rset.updateString("tasteDirection", this.edType.getTasteDirection());
		rset.updateString("manufacturer", this.edType.getManufacturer());	
	}

	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		super.readFrom(rset);
		// TODO: insert method to load ingredients here

		String brand = rset.getString("brand");
		String tasteDirection = rset.getString("tasteDirection");
		String manufacturer = rset.getString("manufacturer");
		this.edType = EnergyDrinkPhotoManager.getInstance().requestEnergyDrinkType(brand, tasteDirection, manufacturer, null);
	}

	protected void assertNotNullArgument(Object o) throws IllegalArgumentException {
		if(o == null){
            String message = "Argument can't be null";
            throw new IllegalArgumentException(message);
        }
	}

	protected void assertClassInvariants() {
        if(this.edType == null){
			throw new IllegalStateException("An energy drink photo must always have a type!");
		}
    }
}
