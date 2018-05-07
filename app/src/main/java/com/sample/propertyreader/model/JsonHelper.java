package com.sample.propertyreader.model;

import java.lang.reflect.Type;

/**
 * Created by Artur Kasprzak on 27.04.2018.
 */
public interface JsonHelper {

	<C> C parseJsonToObject(String json, Class C);

	<T> T parseJsonToObjectList(String json, Type T);

}
