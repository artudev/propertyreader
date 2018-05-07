package com.sample.propertyreader.model.filter;

import com.sample.propertyreader.model.property.Property;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
public enum PropertyNumericField {

	PRICE {
		public float getValue(Property property) {
			return property.price;
		}
	};

	public abstract float getValue(Property property);
}
