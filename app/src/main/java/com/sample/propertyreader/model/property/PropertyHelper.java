package com.sample.propertyreader.model.property;

import com.sample.propertyreader.model.filter.Filter;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Artur Kasprzak on 27.04.2018.
 */
public interface PropertyHelper {

	/**
	 * generic options, some crazy science and require 'filter' package
	 * with all fancy field models and filter models.
	 * Of course this is just a bone structure , in end project there would be
	 * a lot more of Filters and Fields
	 */
	Single<Float> averagePrice(List<Property> properties, Filter filter);

	Single<Float> diffAveragePrice(List<Property> properties, Filter filterA, Filter filterB);

	/**
	 * manual options where each possible/needed query is individually created
	 */
	Single<Float> averagePriceByPostcode(List<Property> properties, String regex);

	Single<Float> averagePriceByPropertyType(List<Property> properties, String regex);
}
