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

	@Test
	public void testCreateInstances() throws CreateEnergyDrinkPhotoException {
		EnergyDrinkPhotoFactory pf = EnergyDrinkPhotoFactory.getInstance();
		EnergyDrinkPhotoManager pm = EnergyDrinkPhotoManager.getInstance();
		PhotoId id1 = new PhotoId(1);
		PhotoId id2 = new PhotoId(2);

		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		Ingredient i1 = new Ingredient("sugar", 10.4);
		Ingredient i2 = new Ingredient("salt", 0.0003);
		Ingredient i3 = new Ingredient("niacin", 0.0008);
		ingredients.add(i1);
		ingredients.add(i2);
		ingredients.add(i3);

		String brand = "Booster";
		String tasteDirection = "Juicy";
		String manufacturer = "VERITAS";

		EnergyDrinkType t1 = pm.requestEnergyDrinkType(brand, tasteDirection, manufacturer, ingredients);
		EnergyDrinkPhoto p1 = new EnergyDrinkPhoto(id1, t1);
		EnergyDrinkPhoto p2 = pf.createPhoto(id2, brand, tasteDirection, manufacturer, ingredients);

		assertEquals(p1.getId(), id1);
		assertEquals(p2.getId(), id2);
		assertEquals(p1.listIngredients(), "sugar: 10.4, salt: 3.0E-4, niacin: 8.0E-4");
		assertEquals(p2.getBrand(), "Booster");
		assertEquals(p2.getTasteDirection(), "Juicy");
		assertEquals(p2.getManufacturer(), "VERITAS");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateEnergyDrinkPhotoExceptionNullArgument() throws CreateEnergyDrinkPhotoException {
		EnergyDrinkPhotoFactory pf = EnergyDrinkPhotoFactory.getInstance();
		EnergyDrinkPhoto p2 = pf.createPhoto(null, "a", "b", "c", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateEnergyDrinkPhotoException() throws CreateEnergyDrinkPhotoException {
		EnergyDrinkPhotoFactory pf = EnergyDrinkPhotoFactory.getInstance();
		PhotoId id1 = new PhotoId(1);
		EnergyDrinkPhoto p2 = pf.createPhoto(id1, null, null, null, null);
	}

	@Test(expected = IllegalStateException.class)
	public void testEnergyDrinkPhotoCorruption() throws CreateEnergyDrinkPhotoException {
		EnergyDrinkPhotoManager pm = EnergyDrinkPhotoManager.getInstance();
		pm.requestEnergyDrinkType(null, null, null, null);
	}

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

	@Test
	public void testUniqueEnergyDrinkType(){
		EnergyDrinkPhotoManager pm = EnergyDrinkPhotoManager.getInstance();

		String brand = "Booster";
		String otherBrand = "Monster";
		String tasteDirection = "Juicy";
		String manufacturer = "VERITAS";

		EnergyDrinkType t1 = pm.requestEnergyDrinkType(brand, tasteDirection, manufacturer, null);
		EnergyDrinkType t2 = pm.requestEnergyDrinkType(otherBrand, tasteDirection, manufacturer, null);
		assertNotEquals(t1, t2);
		assertEquals(t1, pm.requestEnergyDrinkType(brand, tasteDirection, manufacturer, null));
		assertEquals(t2, pm.requestEnergyDrinkType(otherBrand, tasteDirection, manufacturer, null));
		assertNotEquals(t1, pm.requestEnergyDrinkType(otherBrand, tasteDirection, manufacturer, null));
	}

	@Test
	public void testEnergyDrinkTypeIngredientIndependence(){
		EnergyDrinkPhotoManager pm = EnergyDrinkPhotoManager.getInstance();

		String brand = "Booster";
		String tasteDirection = "Juicy";
		String manufacturer = "VERITAS";

		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		Ingredient i1 = new Ingredient("sugar", 10.4);
		Ingredient i2 = new Ingredient("salt", 0.0003);
		Ingredient i3 = new Ingredient("niacin", 0.0008);
		ingredients.add(i1);
		ingredients.add(i2);
		ingredients.add(i3);

		EnergyDrinkType t1 = pm.requestEnergyDrinkType(brand, tasteDirection, manufacturer, null);
		EnergyDrinkType t2 = pm.requestEnergyDrinkType(brand, tasteDirection, manufacturer, ingredients);
		assertEquals(t1, t2);
		assertEquals(ingredients, t1.getIngredients());
		assertEquals(t2, pm.requestEnergyDrinkType(brand, tasteDirection, manufacturer, null));
	}
}
