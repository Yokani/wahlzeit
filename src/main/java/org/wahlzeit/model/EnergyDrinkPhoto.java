package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DesignPattern(
	name = "Abstract_Factory",
	participants = {"ConcreteProduct"}
)
public class EnergyDrinkPhoto extends Photo {

	protected List<Ingredient> ingredients = new ArrayList<Ingredient>();
	protected String brand;
	protected String manufacturer = "";

	/**
	 * @methodtype constructor
	 * @methodproperties convenience
	 */
    public EnergyDrinkPhoto(PhotoId myId, String brand){
		super(myId);
		this.brand = brand;
		assertClassInvariants();
	}

	/**
	 * @methodtype constructor
	 */
    public EnergyDrinkPhoto(PhotoId myId, List<Ingredient> ingredients, String brand, String manufacturer){
		super(myId);
		this.ingredients = ingredients;
		this.brand = brand;
		this.manufacturer = manufacturer;
		assertClassInvariants();
	}

	/**
	 * @methodtype query
	 */
	public String listIngredients(){
		assertClassInvariants();
		String tmp = "";
		for(Ingredient ingr : this.ingredients){
			tmp += ingr.toString() + ", ";
		}
		return tmp == "" ? "No ingredients saved." : tmp.substring(0, tmp.length() - 2);
	}

	/**
	 * @pre: ingredientName may not be null
	 * @methodtype query
	 * @methodproperties convenience
	 */
	public boolean hasIngredient(String ingredientName){
		assertClassInvariants();
		assertNotNullArgument(ingredientName);
		for(Ingredient ingredient : this.ingredients){
			if(ingredient.getName() == ingredientName){
				return true;
			}
		}
		return false;
	}

	/**
	 * @pre: ingredient may not be null
	 * @methodtype query
	 */
	public boolean hasIngredient(Ingredient ingredient){
		assertClassInvariants();
		assertNotNullArgument(ingredient);
		return this.ingredients.contains(ingredient);
	}

	/**
	 * @pre: ingredient may not be null
	 * @post: size of ingredients list increased by one
	 * @methodtype command
	 */
	public void addIngredient(Ingredient ingredient){
		assertClassInvariants();
		assertNotNullArgument(ingredient);
		int numIngredientsBefore = this.ingredients.size();
		this.ingredients.add(ingredient);
		if(this.ingredients.size() != numIngredientsBefore + 1){
			String message = "ingredient was not added correctly to list of ingredients";
			throw new RuntimeException(message);
		}
		assertClassInvariants();
	}

	/**
	 * @pre: ingredient may not be null
	 * @post: size of ingredients reduced by one
	 * @methodtype command
	 */
	public void removeIngredient(Ingredient ingredient){
		assertClassInvariants();
		assertNotNullArgument(ingredient);
		if(this.hasIngredient(ingredient)){
			int numIngredientsBefore = this.ingredients.size();
			this.ingredients.remove(ingredient);
			if(this.ingredients.size() != numIngredientsBefore - 1){
				String message = "ingredient was not removed correctly from list of ingredients";
				throw new RuntimeException(message);
			}
		}else{
			// no error handling here, only a message, because no error state was produced
			System.out.println("Ingredient " + ingredient.toString() + " was not part of " + this.brand + " from " + this.manufacturer);
		}
		assertClassInvariants();
	}

	/**
	 * @methodtype set
	 */
	public void setIngredients(List<Ingredient> ingredients){
		assertNotNullArgument(ingredients);
		assertClassInvariants();
		this.ingredients = ingredients;
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 */
	public List<Ingredient> getIngredients(){
		assertClassInvariants();
		return this.ingredients;
	}

	/**
	 * @methodtype set
	 */
	public void setBrand(String brand){
		assertClassInvariants();
		this.brand = brand;
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 */
	public String getBrand(){
		assertClassInvariants();
		return this.brand;
	}

	/**
	 * @methodtype set
	 */
	public void setManufacturer(String manufacturer){
		assertClassInvariants();
		this.manufacturer = manufacturer;
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 */
	public String getManufacturer(){
		assertClassInvariants();
		return this.manufacturer;
	}

	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);
		// TODO: insert method to save ingredients here

		rset.updateString("brand", this.brand);
		rset.updateString("manufacturer", this.manufacturer);	
	}

	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		super.readFrom(rset);
		// TODO: insert method to load ingredients here

		this.brand = rset.getString("brand");
		this.manufacturer = rset.getString("manufacturer");
	}

	protected void assertNotNullArgument(Object o) throws IllegalArgumentException {
		if(o == null){
            String message = "Argument can't be null";
            throw new IllegalArgumentException(message);
        }
	}

	protected void assertClassInvariants() {
        if(this.ingredients == null){
			throw new IllegalStateException("ingredient list may never be null");
		}
		if(this.brand == null){
			throw new IllegalStateException("brand may never be null, at most an empty String");
		}
		if(this.manufacturer == null){
			throw new IllegalStateException("manufacturer may never be null, at most an empty String");
		}
    }
}
