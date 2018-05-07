package com.sample.propertyreader.model.filter;

import com.sample.propertyreader.model.property.Property;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
public class PropertyRangeFilter implements Filter<Property> {

	private final PropertyNumericField mField;
	private final float mMin;
	private final float mMax;

	public PropertyRangeFilter(PropertyNumericField field, float min, float max) {
		mField = field;
		mMin = min;
		mMax = max;
	}

	@Override
	public boolean match(Property property) {
		return mMin <= mField.getValue(property) && mField.getValue(property) <= mMax;
	}
}
