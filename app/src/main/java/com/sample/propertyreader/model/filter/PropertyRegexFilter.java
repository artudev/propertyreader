package com.sample.propertyreader.model.filter;

import com.sample.propertyreader.model.property.Property;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
public class PropertyRegexFilter implements Filter<Property> {

	private final PropertyTextField mField;
	private final String mRegex;

	public PropertyRegexFilter(PropertyTextField field, String regex) {
		mField = field;
		mRegex = regex;
	}

	@Override
	public boolean match(Property property) {
		return mField.getValue(property).matches(mRegex);
	}
}
