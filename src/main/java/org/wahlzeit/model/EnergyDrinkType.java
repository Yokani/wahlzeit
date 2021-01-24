package org.wahlzeit.model;

import java.util.ArrayList;

public class EnergyDrinkType {

    private final String brand;
    private final String tasteDirection;
    private final String manufacturer;
    private ArrayList<Ingredient> ingredients;

    /**
     * methodtype constructor
     * @param brand the brand of the drink, e.g. Monster
     * @param tasteDirection the taste of the drink, e.g. exotic
     * @param manufacturer the manufacturer of the drink, e.g. S.Spitz GmbH
     * @param ingredients the list of ingredients for the drink, e.g. sugar 11g
     */
    public EnergyDrinkType(String brand, String tasteDirection, String manufacturer, ArrayList<Ingredient> ingredients){
        this.brand = brand;
        this.tasteDirection = tasteDirection;
        this.manufacturer = manufacturer;
        if(ingredients == null){
            this.ingredients = new ArrayList<Ingredient>();
        }else{
            this.ingredients = ingredients;
        }
        assertClassInvariants();
    }
    
    public String getBrand(){
        assertClassInvariants();
        return this.brand;
    }
    public String getTasteDirection(){
        assertClassInvariants();
        return this.tasteDirection;
    }
    public String getManufacturer(){
        assertClassInvariants();
        return this.manufacturer;
    }
    public void setIngredients(ArrayList<Ingredient> ingredients){
        assertClassInvariants();
        this.ingredients = ingredients;
        assertClassInvariants();
    }
    public ArrayList<Ingredient> getIngredients(){
        assertClassInvariants();
        return this.ingredients;
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
     * the hashcode / identity of an energydrinktype is independend of its ingredients!
     */
    @Override
    public boolean equals(Object other) {
        assertClassInvariants();
        if (other == null) {
			return false;
		}
		if (this == other) {
			return true;
        }
        if (!(other instanceof EnergyDrinkType)){
            return false;
        }
        boolean eqBrand = this.brand == ((EnergyDrinkType) other).brand;
        boolean eqTaste = this.tasteDirection == ((EnergyDrinkType) other).tasteDirection;
        boolean eqManufacturer = this.manufacturer == ((EnergyDrinkType) other).manufacturer;
        return eqBrand && eqTaste && eqManufacturer;
    }

    /**
     * the hashcode / identity of an energydrinktype is independend of its ingredients!
     */
    @Override
    public int hashCode() {
        assertClassInvariants();
        return (int) ((brand.hashCode() / 3) + (tasteDirection.hashCode() / 3) + (manufacturer.hashCode() / 3));
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
        if(this.tasteDirection == null){
			throw new IllegalStateException("tasteDirection may never be null, at most an empty String");
		}
		if(this.manufacturer == null){
			throw new IllegalStateException("manufacturer may never be null, at most an empty String");
		}
    }

}
