package com.sample.propertyreader;

import timber.log.Timber;

/**
 * Created by Artur Kasprzak on 26.04.2018.
 */
public class DebugApplication extends MainApplication {

	@Override
	public void onCreate() {
		super.onCreate();

		Timber.plant(new Timber.DebugTree());
		Timber.d("DebugApplication onCreate");
	}
}
