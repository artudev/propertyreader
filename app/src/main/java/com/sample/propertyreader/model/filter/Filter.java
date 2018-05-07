package com.sample.propertyreader.model.filter;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
public interface Filter<T> {
	boolean match(T t);
}
