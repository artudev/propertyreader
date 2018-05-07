package com.sample.propertyreader.list;

import com.sample.propertyreader.MainApplication;
import com.sample.propertyreader.model.property.PropertyContainer;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
public class ListPresenter implements ListContract.Presenter {

	private final ListContract.View mView;

	private CompositeDisposable mCompositeDisposable;

	@Inject
	PropertyContainer mPropertyContainer;

	public ListPresenter(ListContract.View view) {
		Timber.d("ListPresenter create");
		mView = view;

		((MainApplication) view.getActivity().getApplication()).getPropertyComponent().inject(this);
		mCompositeDisposable = new CompositeDisposable();
	}

	@Override
	public void onStart() {
		Timber.d("onStart");
		retrieveList();
	}

	private void retrieveList() {
		Disposable disposable =
				mPropertyContainer.getProperties(mView.getContext()).subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe(properties -> mView.displayList(properties));
		mCompositeDisposable.add(disposable);
	}

	@Override
	public void onStop() {
		Timber.d("onStop");
		mCompositeDisposable.dispose();
	}
}
