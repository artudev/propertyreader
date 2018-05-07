package com.sample.propertyreader.model.property;

import android.support.annotation.NonNull;

import com.sample.propertyreader.model.filter.Filter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Artur Kasprzak on 27.04.2018.
 */
public class PropertyHelperImpl implements PropertyHelper {

	public static final float DEFAULT_AVERAGE = 0f;

	@Override
	public Single<Float> averagePrice(List<Property> properties, Filter filter) {
		return Observable.fromIterable(properties)

				.filter(filter::match)

				.map(property -> new AverageItem(property.price, 1))

				.reduce((averageItem, averageItem2) -> averageItem.increment(averageItem2))

				.map(averageItem -> averageItem.mSum / averageItem.getCount())

				.toSingle(DEFAULT_AVERAGE);
	}

	@Override
	public Single<Float> diffAveragePrice(List<Property> properties, Filter filterA,
			Filter filterB) {

		return Single.zip(averagePrice(properties, filterA), averagePrice(properties, filterB),
				(aFloat, aFloat2) -> Math.abs(aFloat - aFloat2));
	}

	@Override
	public Single<Float> averagePriceByPostcode(List<Property> properties, String regex) {
		return Observable.fromIterable(properties)

				.filter(property -> property.postcode.matches(regex))

				.map(property -> new AverageItem(property.price, 1))

				.reduce((averageItem, averageItem2) -> averageItem.increment(averageItem2))

				.map(averageItem -> averageItem.mSum / averageItem.getCount())

				.toSingle(DEFAULT_AVERAGE);
	}

	@Override
	public Single<Float> averagePriceByPropertyType(@NonNull List<Property> properties,
			final String regex) {
		return Observable.fromIterable(properties)

				.filter(property -> property.propertyType.matches(regex))

				.map(property -> new AverageItem(property.price, 1))

				.reduce((averageItem, averageItem2) -> averageItem.increment(averageItem2))

				.map(averageItem -> averageItem.mSum / averageItem.getCount())

				.toSingle(DEFAULT_AVERAGE);
	}

	class AverageItem {
		private float mSum;
		private int mCount;

		public AverageItem(float sum, int count) {
			mSum = sum;
			mCount = count;
		}

		public float getSum() {
			return mSum;
		}

		public void setSum(float sum) {
			mSum = sum;
		}

		public int getCount() {
			return mCount;
		}

		public void setCount(int count) {
			mCount = count;
		}

		public AverageItem increment(AverageItem other) {
			mCount += other.getCount();
			mSum += other.getSum();
			return this;
		}
	}
}
