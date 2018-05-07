package com.sample.propertyreader;

import com.sample.propertyreader.model.AssetHelper;
import com.sample.propertyreader.model.AssetsHelperImpl;
import com.sample.propertyreader.model.GsonHelperImpl;
import com.sample.propertyreader.model.JsonHelper;
import com.sample.propertyreader.model.property.PropertyContainer;
import com.sample.propertyreader.model.property.PropertyHelper;
import com.sample.propertyreader.model.property.PropertyHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
@Module
public class PropertyModule {

	@Provides
	@Singleton
	static PropertyContainer providePropertyContainer(AssetHelper assetHelper,
			JsonHelper jsonHelper) {
		return new PropertyContainer(assetHelper, jsonHelper);
	}

	@Provides
	public AssetHelper provideAssetHelper() {
		return new AssetsHelperImpl();
	}

	@Provides
	public JsonHelper provideJsonHelper() {
		return new GsonHelperImpl();
	}

	@Provides
	public PropertyHelper providePropertyHelper() {
		return new PropertyHelperImpl();
	}
}
