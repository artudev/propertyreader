package com.sample.propertyreader.list;

import com.sample.propertyreader.model.property.Property;
import com.sample.propertyreader.parent.BaseFragmentView;
import com.sample.propertyreader.parent.BasePresenter;

import java.util.List;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
public interface ListContract {

	interface View extends BaseFragmentView {

		void displayList(List<Property> list);
	}

	interface Presenter extends BasePresenter {

		void onStart();

		void onStop();
	}
}
