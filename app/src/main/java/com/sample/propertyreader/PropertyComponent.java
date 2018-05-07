package com.sample.propertyreader;

import com.sample.propertyreader.home.HomePresenter;
import com.sample.propertyreader.list.ListPresenter;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
@Singleton
@Component(modules = {PropertyModule.class})
public interface PropertyComponent extends AndroidInjector<MainApplication> {

	void inject(HomePresenter presenter);

	void inject(ListPresenter presenter);
}
