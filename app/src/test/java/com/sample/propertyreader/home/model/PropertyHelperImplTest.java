package com.sample.propertyreader.home.model;

import com.sample.propertyreader.model.filter.Filter;
import com.sample.propertyreader.model.filter.PropertyRegexFilter;
import com.sample.propertyreader.model.filter.PropertyTextField;
import com.sample.propertyreader.model.property.Property;
import com.sample.propertyreader.model.property.PropertyHelperImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artur Kasprzak on 27.04.2018.
 */
public class PropertyHelperImplTest {

	private PropertyHelperImpl mPropertyHelper;
	private List<Property> mProperties;
	private String mPropertyTypeA;
	private String mPropertyTypeB;

	@Before
	public void setUp() throws Exception {
		mPropertyHelper = new PropertyHelperImpl();
		mProperties = new ArrayList<>();

		mPropertyTypeA = "a";
		mPropertyTypeB = "b";
		mProperties.add(new Property(0, 12, 0, 0, "", "", "", "XX1 SEF",
				mPropertyTypeA));
		mProperties.add(new Property(1, 24, 0, 0, "", "", "", "ZZ2 XX1",
				mPropertyTypeA));
		mProperties.add(new Property(2, 36, 0, 0, "", "", "", "XX1 QAZ",
				mPropertyTypeB));
		mProperties.add(new Property(3, 48, 0, 0, "", "", "", "XX1 SFA",
				mPropertyTypeB));
	}

	@Test
	public void averagePriceByPostcode() {
		TestObserver testObserver = mPropertyHelper.averagePriceByPostcode(mProperties, "^XX1.*")
				.subscribeOn(Schedulers.trampoline()).test();

		testObserver.assertComplete();
		testObserver.assertValue(32F);
	}

	@Test
	public void averagePriceByPropertyType() {

		TestObserver testObserver = mPropertyHelper.averagePriceByPropertyType(mProperties, "a")
				.subscribeOn(Schedulers.trampoline()).test();

		testObserver.assertComplete();
		testObserver.assertValue(18F);
	}

	@Test
	public void averagePriceByPropertyTypeEmptyList() {

		TestObserver testObserver =
				mPropertyHelper.averagePriceByPropertyType(new ArrayList<>(), "a")
						.subscribeOn(Schedulers.trampoline()).test();

		testObserver.assertComplete();
		testObserver.assertValue(PropertyHelperImpl.DEFAULT_AVERAGE);
	}

	@Test
	public void averagePrice() {

		PropertyRegexFilter filter = new PropertyRegexFilter(PropertyTextField.POSTCODE, "^XX1.*");

		TestObserver testObserver = mPropertyHelper.averagePrice(mProperties, filter)
				.subscribeOn(Schedulers.trampoline()).test();

		testObserver.assertComplete();
		testObserver.assertValue(32F);
	}

	@Test
	public void averagePriceEmptyList() {

		PropertyRegexFilter filter = new PropertyRegexFilter(PropertyTextField.POSTCODE, "^XX1.*");

		TestObserver testObserver = mPropertyHelper.averagePrice(new ArrayList<>(), filter)
				.subscribeOn(Schedulers.trampoline()).test();

		testObserver.assertComplete();
		testObserver.assertValue(PropertyHelperImpl.DEFAULT_AVERAGE);
	}

	@Test
	public void diffAveragePrice() {

		Filter filterTask2TypeA = new PropertyRegexFilter(PropertyTextField.PROPERTY_TYPE, "a");
		Filter filterTask2typeB = new PropertyRegexFilter(PropertyTextField.PROPERTY_TYPE, "b");

		TestObserver testObserver =
				mPropertyHelper.diffAveragePrice(mProperties, filterTask2TypeA, filterTask2typeB)
						.subscribeOn(Schedulers.trampoline()).test();

		testObserver.assertComplete();
		testObserver.assertValue(24F);
	}

	@Test
	public void diffAveragePriceEmptyList() {

		Filter filterTask2TypeA = new PropertyRegexFilter(PropertyTextField.PROPERTY_TYPE, "a");
		Filter filterTask2typeB = new PropertyRegexFilter(PropertyTextField.PROPERTY_TYPE, "b");

		TestObserver testObserver =
				mPropertyHelper.diffAveragePrice(new ArrayList<>(), filterTask2TypeA, filterTask2typeB)
						.subscribeOn(Schedulers.trampoline()).test();

		testObserver.assertComplete();
		testObserver.assertValue(0F);
	}
}
