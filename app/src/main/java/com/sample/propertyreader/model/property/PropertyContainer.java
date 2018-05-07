package com.sample.propertyreader.model.property;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sample.propertyreader.model.AssetHelper;
import com.sample.propertyreader.model.JsonHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import timber.log.Timber;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
public class PropertyContainer {

	private static final String PROPERTY_FILE = "property-data.json";

	private final AssetHelper mAssetHelper;
	private final JsonHelper mJsonHelper;
	private List<Property> mProperties;

	public PropertyContainer(AssetHelper assetHelper, JsonHelper jsonHelper) {
		mAssetHelper = assetHelper;
		mJsonHelper = jsonHelper;
		mProperties = new ArrayList<>();
	}

	public Single<List<Property>> getProperties(Context context) {

		if (!shouldReload()) {
			Timber.d("getProperties no reload, return previously retrieved list");
			return Single.create(emitter -> emitter.onSuccess(mProperties));
		}

		Type listType = new TypeToken<List<Property>>() {
		}.getType();

		Timber.d("getProperties reload, retrieve list");
		return Single.create((SingleOnSubscribe<String>) emitter -> emitter
				.onSuccess(mAssetHelper.retrieveTextAsset(context, PROPERTY_FILE)))
				.map(json -> (List<Property>) mJsonHelper.parseJsonToObjectList(json, listType))
				.doOnSuccess(this::addAllProperties);
	}

	private void addAllProperties(List<Property> properties) {
		mProperties.clear();
		mProperties.addAll(properties);
	}

	/**
	 * In case of downloading data from server we could add some custom logic.
	 * For example in our case we have static local storage so only one load is needed.
	 */
	private boolean shouldReload() {
		return mProperties.isEmpty();
	}
}
