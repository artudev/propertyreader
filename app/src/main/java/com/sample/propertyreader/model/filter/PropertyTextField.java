package com.sample.propertyreader.model.filter;

import com.sample.propertyreader.model.property.Property;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
public enum PropertyTextField {
	PROPERTY_TYPE {
		public String getValue(Property property) {
			return property.propertyType;
		}
	}, POSTCODE {
		public String getValue(Property property) {
			return property.postcode;
		}
	};

	public abstract String getValue(Property property);
}
