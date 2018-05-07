package com.sample.propertyreader.home;

import com.sample.propertyreader.MainApplication;
import com.sample.propertyreader.R;
import com.sample.propertyreader.model.filter.Filter;
import com.sample.propertyreader.model.filter.PropertyRegexFilter;
import com.sample.propertyreader.model.filter.PropertyTextField;
import com.sample.propertyreader.model.property.Property;
import com.sample.propertyreader.model.property.PropertyContainer;
import com.sample.propertyreader.model.property.PropertyHelper;
import com.sample.propertyreader.model.property.PropertyType;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Artur Kasprzak on 27.04.2018.
 */
public class HomePresenter implements HomeContract.Presenter {

	private final HomeContract.View mView;

	private static final String TASK_1_POSTCODE_REGEX = "^W1F.*";
	private static final PropertyType TASK_2_TYPE_A = PropertyType.DETACHED;
	private static final PropertyType TASK_2_TYPE_B = PropertyType.FLAT;

	private CompositeDisposable mCompositeDisposable;

	@Inject
	PropertyHelper mPropertyHelper;
	@Inject
	PropertyContainer mPropertyContainer;

	public HomePresenter(HomeContract.View view) {
		Timber.d("HomePresenter create");
		mView = view;

		((MainApplication) view.getActivity().getApplication()).getPropertyComponent().inject(this);
		mCompositeDisposable = new CompositeDisposable();
	}

	@Override
	public void onStart() {
		Timber.d("onStart");
		computeTasks();
	}

	private void computeTasks() {
		Disposable disposable =
				mPropertyContainer.getProperties(mView.getContext()).subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread()).subscribe(properties -> {
					task1(properties);
					task2(properties, TASK_2_TYPE_A.getTagName(), TASK_2_TYPE_B.getTagName());
				});
		mCompositeDisposable.add(disposable);
	}

	private void task1(List<Property> properties) {
		Filter filterTask1 =
				new PropertyRegexFilter(PropertyTextField.POSTCODE, TASK_1_POSTCODE_REGEX);

		Disposable disposable = mPropertyHelper.averagePrice(properties, filterTask1)
				.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
				.subscribe(aFloat -> {
					String formatted = String.format("%.2f", aFloat);
					String text = mView.getContext()
							.getString(R.string.task_1_text, TASK_1_POSTCODE_REGEX, formatted);
					mView.displayAveragePrice(text);
				});
		mCompositeDisposable.add(disposable);
	}

	private void task2(List<Property> properties, String typeA, String typeB) {
		Filter filterTask2TypeA = new PropertyRegexFilter(PropertyTextField.PROPERTY_TYPE, typeA);
		Filter filterTask2typeB = new PropertyRegexFilter(PropertyTextField.PROPERTY_TYPE, typeB);

		Disposable disposable =
				mPropertyHelper.diffAveragePrice(properties, filterTask2TypeA, filterTask2typeB)
						.subscribeOn(Schedulers.computation())
						.observeOn(AndroidSchedulers.mainThread()).subscribe(aFloat -> {
					String formatted = String.format("%.2f", aFloat);
					String text = mView.getContext()
							.getString(R.string.task_2_text, typeA, typeB, formatted);
					mView.displayDiffInAveragePrice(text);
				});
		mCompositeDisposable.add(disposable);
	}

	@Override
	public void onStop() {
		mCompositeDisposable.dispose();
	}
}
