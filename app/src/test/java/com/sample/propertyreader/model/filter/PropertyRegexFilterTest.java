package com.sample.propertyreader.model.filter;

import com.sample.propertyreader.model.property.Property;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Artur Kasprzak on 30.04.2018.
 */
public class PropertyRegexFilterTest {

	private Property mProperty;

	@Before
	public void setUp() throws Exception {
		mProperty = new Property(0, 1, 0, 0, "", "", "", "X12 EW2", "A");
	}

	@Test
	public void matchFittingRegex() {
		PropertyRegexFilter filter = new PropertyRegexFilter(PropertyTextField.POSTCODE, "^X12.*");
		boolean result = filter.match(mProperty);

		Assert.assertEquals(true, result);
	}

	@Test
	public void matchNotFittingRegex() {
		PropertyRegexFilter filter = new PropertyRegexFilter(PropertyTextField.POSTCODE, "^SSS.*");
		boolean result = filter.match(mProperty);

		Assert.assertEquals(false, result);
	}
}
