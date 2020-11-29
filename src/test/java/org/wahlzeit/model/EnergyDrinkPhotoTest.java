package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Test cases for the EnergyDrinkPhoto classes.
 */
public class EnergyDrinkPhotoTest {

	/**
	 *
	 */
	@Test
	public void testCreateInstances() {
		EnergyDrinkPhotoFactory pf = EnergyDrinkPhotoFactory.getInstance();
		PhotoId id1 = new PhotoId(1);
		PhotoId id2 = new PhotoId(2);

		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Ingredient i1 = new Ingredient("sugar", 10.4);
		Ingredient i2 = new Ingredient("salt", 0.0003);
		Ingredient i3 = new Ingredient("niacin", 0.0008);
		ingredients.add(i1);
		ingredients.add(i2);
		ingredients.add(i3);

		EnergyDrinkPhoto p1 = new EnergyDrinkPhoto(id1, ingredients, "Booster Juicy", "VERITAS");
		EnergyDrinkPhoto p2 = pf.createPhoto(id2, "Booster Exotic");

		assertEquals(p1.getId(), id1);
		assertEquals(p2.getId(), id2);
		assertEquals(p1.listIngredients(), "sugar: 10.4, salt: 3.0E-4, niacin: 8.0E-4");
		assertEquals(p2.getBrand(), "Booster Exotic");
	}

    /**
	 *
	 */
	@Test
	public void testIngredientEquals() {
		Ingredient i1 = new Ingredient("sugar", 10.4);
		Ingredient i2 = new Ingredient("sugar");
		Ingredient i3 = new Ingredient("sugar", 4.4);
		Ingredient i4 = new Ingredient("niacin", 0.0008);

		assertEquals(i1, i2);
		assertEquals(i1, i3);
		assertEquals(i2, i3);
		assertNotEquals(i1, i4);
	}
}
