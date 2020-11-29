package org.wahlzeit.model;

public class Ingredient {

    // the name of the ingredient, e.g. Sugar
    private String name;

    // the amount of the ingredient in grams (g)
    private double amount;

    // some amounts are unknown, this flag tells if the are unknown
    private boolean amountIsKnown;

    /**
	 * @methodtype constructor
     * @methodproperties convenience
	 */
    public Ingredient(){
        this.name = "";
        this.amount = 0.0;
        this.amountIsKnown = false;
    }

    /**
	 * @methodtype constructor
     * @methodproperties convenience
	 */
    public Ingredient(String name){
        this.name = name;
        this.amount = 0.0;
        this.amountIsKnown = false;
    }

    /**
	 * @methodtype constructor
	 */
    public Ingredient(String name, double amount){
        this.name = name;
        this.amount = amount;
        this.amountIsKnown = true;
    }

    /**
	 * @methodtype set
	 */
    public void setAmount(double amount){
        this.amount = amount;
    }

    /**
	 * @methodtype set
	 */
    public void setName(String name){
        this.name = name;
    }

    /**
	 * @methodtype get
	 */
    public String getName(){
        return this.name;
    }

    /**
	 * @methodtype get
	 */
    public double getAmount(){
        return this.amount;
    }

    /**
	 * @methodtype get
	 */
    public boolean hasKnownAmount(){
        return this.amountIsKnown;
    }

    /**
	 * @methodtype query
     * @methodproperties conversion
	 */
    public String toString(){
        return this.name  + (amountIsKnown ? ": " + String.valueOf(amount) : "");
    }

    /**
     * @methodtype query
     * @description checks weither two ingredients share the same name
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
			return false;
		}
		if (this == other) {
			return true;
        }
        if (!(other instanceof Ingredient)){
            return false;
        }
        return this.name.equals(((Ingredient) other).getName());
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 42;
    }
}
