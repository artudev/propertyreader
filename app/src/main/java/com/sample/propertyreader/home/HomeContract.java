package com.sample.propertyreader.home;

import com.sample.propertyreader.parent.BaseFragmentView;
import com.sample.propertyreader.parent.BasePresenter;

/**
 * Created by Artur Kasprzak on 26.04.2018.
 */
public interface HomeContract {

	interface View extends BaseFragmentView {

		void displayAveragePrice(String text);

		void displayDiffInAveragePrice(String text);
	}

	interface Presenter extends BasePresenter {

		void onStart();

		void onStop();
	}
}
