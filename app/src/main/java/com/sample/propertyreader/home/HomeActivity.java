package com.sample.propertyreader.home;

import android.os.Bundle;

import com.sample.propertyreader.parent.BaseActivity;

import timber.log.Timber;

/**
 * Created by Artur Kasprzak on 26.04.2018.
 */
public class HomeActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Timber.d("onCreate");
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			Timber.d("savedInstanceState is NULL");
			setContent(new HomeFragment());
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Timber.d("onSaveInstanceState");
	}
}
