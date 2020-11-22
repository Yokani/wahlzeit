package org.wahlzeit.model;

import java.util.ArrayList;
import java.util.List;

public class EnergyDrinkPhoto extends Photo {

	protected List<Ingredient> ingredients = new ArrayList<Ingredient>();
	protected String brand = "";
	protected String manufacturer = "";

	/**
	 * @methodtype constructor
	 * @methodproperties convenience
	 */
    public EnergyDrinkPhoto(String brand){
		super();
		this.brand = brand;
	}

	/**
	 * @methodtype constructor
	 * @methodproperties convenience
	 */
    public EnergyDrinkPhoto(PhotoId myId, String brand){
		super(myId);
		this.brand = brand;
	}

	/**
	 * @methodtype constructor
	 * @methodproperties convenience
	 */
    public EnergyDrinkPhoto(List<Ingredient> ingredients, String brand, String manufacturer){
		super();
		this.ingredients = ingredients;
		this.brand = brand;
		this.manufacturer = manufacturer;
	}

	/**
	 * @methodtype constructor
	 */
    public EnergyDrinkPhoto(PhotoId myId, List<Ingredient> ingredients, String brand, String manufacturer){
		super(myId);
		this.ingredients = ingredients;
		this.brand = brand;
		this.manufacturer = manufacturer;
	}

	/**
	 * @methodtype query
	 */
	public String listIngredients(){
		String tmp = "";
		for(Ingredient ingr : this.ingredients){
			tmp += ingr.toString() + ", ";
		}
		return tmp == "" ? "No ingredients saved." : tmp.substring(0, tmp.length() - 2);
	}

	/**
	 * @methodtype query
	 * @methodproperties convenience
	 */
	public boolean hasIngredient(String ingredientName){
		for(Ingredient ingredient : this.ingredients){
			if(ingredient.getName() == ingredientName){
				return true;
			}
		}
		return false;
	}

	/**
	 * @methodtype query
	 */
	public boolean hasIngredient(Ingredient ingredient){
		return this.ingredients.contains(ingredient);
	}

	/**
	 * @methodtype command
	 */
	public void addIngredient(Ingredient ingredient){
		this.ingredients.add(ingredient);
	}

	/**
	 * @methodtype command
	 */
	public void removeIngredient(Ingredient ingredient){
		if(this.hasIngredient(ingredient)){
			this.ingredients.remove(ingredient);
		}
	}

	/**
	 * @methodtype set
	 */
	public void setIngredients(List<Ingredient> ingredients){
		this.ingredients = ingredients;
	}

	/**
	 * @methodtype get
	 */
	public List<Ingredient> getIngredients(){
		return this.ingredients;
	}

	/**
	 * @methodtype get
	 */
	public String getBrand(){
		return this.brand;
	}

	/**
	 * @methodtype get
	 */
	public String getManufacturer(){
		return this.manufacturer;
	}
}
