/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

/**
 * Test cases for a variety of value object classes.
 */
public class ValueTest {

	private CoordinateHelper coordinateHelper = CoordinateHelper.getInstance();

	/**
	 *
	 */
	@Test
	public void testUserStatus() {
		assertTrue(UserStatus.CREATED == UserStatus.getFromInt(0));
		assertTrue(UserStatus.CONFIRMED == UserStatus.getFromInt(1));
		assertTrue(UserStatus.DISABLED == UserStatus.getFromInt(2));

		UserStatus us = UserStatus.CREATED;
		assertTrue(us.asInt() == 0);
		assertTrue(!us.isConfirmed());

		UserStatus us2 = us.asConfirmed();
		assertTrue(us != us2);
		assertTrue(us2.isConfirmed());

		UserStatus us3 = us2.asDisabled();
		assertTrue(us2 != us3);
		assertTrue(us3.isCreated());
		assertTrue(us3.isConfirmed());
		assertTrue(us3.isDisabled());

		UserStatus us4 = us.asDisabled();
		assertTrue(us4.isDisabled());
		assertTrue(us3 != us4);

		us3 = us3.asEnabled();
		assertTrue(us3.isConfirmed());
		assertTrue(!us3.isDisabled());

		us4 = us4.asEnabled();
		assertTrue(!us4.isConfirmed());
		assertTrue(!us4.isDisabled());

		us4 = us4.asConfirmed();
		assertTrue(us3 == us4);
	}

	/**
	 *
	 */
	@Test
	public void testPhotoStatus() {
		assert (PhotoStatus.VISIBLE == PhotoStatus.getFromInt(0));
		assert (PhotoStatus.INVISIBLE == PhotoStatus.getFromInt(1));
		assert (PhotoStatus.FLAGGED2 == PhotoStatus.getFromInt(3));
		assert (PhotoStatus.MODERATED == PhotoStatus.getFromInt(4));
		assert (PhotoStatus.MODERATED3 == PhotoStatus.getFromInt(6));

		PhotoStatus ps = PhotoStatus.VISIBLE;
		assert (ps.asInt() == 0);

		PhotoStatus ps2 = ps.asInvisible(true);
		assert (ps != ps2);
		assert (ps2.isInvisible());
		assert (!ps2.isFlagged());
		assert (!ps2.isModerated());
		assert (!ps2.isDisplayable());

		PhotoStatus ps3 = ps2.asFlagged(true);
		assert (ps2 != ps3);
		assert (ps3.isInvisible());
		assert (ps3.isFlagged());
		assert (!ps3.isModerated());
		assert (!ps3.isDisplayable());

		PhotoStatus ps3b = PhotoStatus.FLAGGED;
		assert (ps3 != ps3b);
		assert (!ps3b.isInvisible());
		assert (ps3.isFlagged());
		assert (!ps3.isModerated());
		assert (!ps3.isDisplayable());

		PhotoStatus ps2b = ps3b.asInvisible(true);
		assert (ps2b != ps3b);

		PhotoStatus ps4 = ps3.asModerated(true);
		assert (ps4.isInvisible());
		assert (ps4.isFlagged());
		assert (ps4.isModerated());
		assert (!ps4.isDisplayable());
	}

	/**
	 *
	 */
	@Test
	public void testObjectId() {
		PhotoId test = PhotoId.getNextId();

		int testInt = test.asInt();
		assert (test == PhotoId.getIdFromInt(testInt));

		String testString = test.asString();
		assert (test == PhotoId.getIdFromString(testString));
	}


	/**
	 * creates several locations and tests their equals function
	 */
	@Test
	public void testLocationEquals() {
		CartesianCoordinate ca = coordinateHelper.requestCartesianCoordinate(42.0, 24.0, 1337.0);
		CartesianCoordinate cb = coordinateHelper.requestCartesianCoordinate(42.0, 24.0, 1337.1);
		CartesianCoordinate cc = coordinateHelper.requestCartesianCoordinate(42.0, 24.0, 1337.000000001);
		Location la = new Location(ca);
		Location lb = new Location(cb);
		Location lc = new Location(cc);
		assert la.equals(lc);
		assert !la.equals(lb);
		assert !lb.equals(lc);
	}

	/**
	 * creates several locations and tests their distance calculation
	 */
	@Test
	public void testLocationDistance() {
		double tolerance = 0.001;
		CartesianCoordinate ca = coordinateHelper.requestCartesianCoordinate(42.0, 24.0, 1337.0);
		CartesianCoordinate cb = coordinateHelper.requestCartesianCoordinate(42.0, 24.0, 1337.0);
		CartesianCoordinate cc = coordinateHelper.requestCartesianCoordinate(24.0, 42.0, 1337.0);
		Location la = new Location(ca);
		Location lb = new Location(cb);
		Location lc = new Location(cc);

		double distAB = la.distanceTo(lb);
		double distAC = la.distanceTo(lc);

		assert (distAB - 0.0) <= tolerance;
		assert (distAC - 25.455844122715711) <= tolerance;
	}

	@Test
	public void testCoordinateConversions() {
		CartesianCoordinate cartA = coordinateHelper.requestCartesianCoordinate(42.0, 24.0, 1337.0);
		SphericCoordinate sphericA = cartA.asSphericCoordinate();
		SphericCoordinate sphericB = coordinateHelper.requestSphericCoordinate(0.5191461142465229, 0.0361648881158502, 1337.8748072970056);
		CartesianCoordinate cartB = sphericB.asCartesianCoordinate();

		assertEquals(cartA, sphericA.asCartesianCoordinate());
		assertEquals(sphericB, cartB.asSphericCoordinate());
	}

	@Test
	public void testCoordinateEquals() {
		CartesianCoordinate cartA = coordinateHelper.requestCartesianCoordinate(42.0, 24.0, 1337.0);
		SphericCoordinate sphericA = cartA.asSphericCoordinate();
		SphericCoordinate sphericB = coordinateHelper.requestSphericCoordinate(0.5191461142465229, 0.0361648881158502, 1337.8748072970056);
		CartesianCoordinate cartB = sphericB.asCartesianCoordinate();

		assertEquals(cartA.isEqual(cartB), true);
		assertEquals(sphericA.isEqual(sphericB), true);
		assertEquals(cartA, cartB);
		assertEquals(sphericA, sphericB);
		assertNotEquals(cartA, sphericA);
		assertNotEquals(cartB, sphericB);
		assertNotEquals(cartA, sphericB);
		assertNotEquals(cartB, sphericA);
	}

	@Test
	public void testCoordinateCartesianDistance() {
		CartesianCoordinate cartA = coordinateHelper.requestCartesianCoordinate(42.0, 24.0, 1337.0);
		CartesianCoordinate cartB = coordinateHelper.requestCartesianCoordinate(24.0, 42.0, 1337.0);
		SphericCoordinate sphericB = cartB.asSphericCoordinate();

		double tolerance = 0.0001;
		double expectedCartesianDistance = 25.455844122715711;
		double distABcartcart = cartA.getCartesianDistance(cartB);
		double distABcartspheric = cartA.getCartesianDistance(sphericB);

		assert (distABcartcart - expectedCartesianDistance) <= tolerance;
		assert (distABcartspheric - expectedCartesianDistance) <= tolerance;
		assert (distABcartcart - distABcartspheric) <= tolerance;
	}

	@Test
	public void testCoordinateCentralAngle() {
		CartesianCoordinate cartA = coordinateHelper.requestCartesianCoordinate(42.0, 24.0, 1337.0);
		CartesianCoordinate cartB = coordinateHelper.requestCartesianCoordinate(24.0, 42.0, 1337.0);
		SphericCoordinate sphericB = cartB.asSphericCoordinate();

		double tolerance = 0.0001;
		double expectedCentralAngle = 0.019027361895043796;
		double distABcartcart = cartA.getCentralAngle(cartB);
		double distABcartspheric = cartA.getCentralAngle(sphericB);

		assert (distABcartcart - expectedCentralAngle) <= tolerance;
		assert (distABcartspheric - expectedCentralAngle) <= tolerance;
		assert (distABcartcart - distABcartspheric) <= tolerance;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCartesianCoordinateNaN(){
		double nan = Double.NaN;
		coordinateHelper.requestCartesianCoordinate(nan, 24.0, 1337.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCartesianDistanceNullArgument(){
		CartesianCoordinate cartA = coordinateHelper.requestCartesianCoordinate(42.0, 24.0, 1337.0);
		cartA.getCartesianDistance(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCentralAngleNullArgument(){
		CartesianCoordinate cartA = coordinateHelper.requestCartesianCoordinate(42.0, 24.0, 1337.0);
		cartA.getCentralAngle(null);
	}
}
