package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

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
	public void createInstances() {

		final EnergyDrinkPhotoManager pm = EnergyDrinkPhotoManager.getInstance();
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

		/*
		I would like to test database add, save features
		but i get Nullpointer exceptions if i try (getDataBaseConnection())
		pm.addPhoto(p1);
		pm.addPhoto(p2);
		assertEquals(2, pm.photoCache.size());
		assertEquals(p1, pm.getPhoto(id1));
		*/
	}
}
