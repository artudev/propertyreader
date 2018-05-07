package com.sample.propertyreader.list;

import android.os.Bundle;

import com.sample.propertyreader.parent.BaseActivity;

import timber.log.Timber;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
public class ListActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Timber.d("onCreate");
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			Timber.d("savedInstanceState is NULL");
			setContent(new ListFragment());
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Timber.d("onSaveInstanceState");
	}
}
