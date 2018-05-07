package com.sample.propertyreader.model.filter;

import com.sample.propertyreader.model.property.Property;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Artur Kasprzak on 30.04.2018.
 */
public class PropertyRangeFilterTest {

	private Property mProperty;

	@Before
	public void setUp() throws Exception {
		mProperty = new Property(0, 1, 0, 0, "", "", "", "X", "A");
	}

	@Test
	public void matchInRange() {
		PropertyRangeFilter filter = new PropertyRangeFilter(PropertyNumericField.PRICE, 0, 1);
		boolean result = filter.match(mProperty);

		Assert.assertEquals(true, result);
	}

	@Test
	public void matchOutOfRange() {
		PropertyRangeFilter filter = new PropertyRangeFilter(PropertyNumericField.PRICE, 10, 11);
		boolean result = filter.match(mProperty);

		Assert.assertEquals(false, result);
	}
}
