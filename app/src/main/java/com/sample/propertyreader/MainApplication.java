package com.sample.propertyreader;

import android.app.Application;

/**
 * Created by Artur Kasprzak on 26.04.2018.
 */
public class MainApplication extends Application {

	private PropertyComponent mPropertyComponent;

	@Override
	public void onCreate() {
		super.onCreate();

		mPropertyComponent =
				DaggerPropertyComponent.builder().propertyModule(new PropertyModule()).build();
	}

	public PropertyComponent getPropertyComponent() {
		return mPropertyComponent;
	}
}
