package com.sample.propertyreader.model.property;

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.annotations.SerializedName;
import com.sample.propertyreader.R;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Artur Kasprzak on 27.04.2018.
 */
public class Property {

	@SerializedName("propertyReference")
	public final int propertyReference;
	@SerializedName("price")
	public final float price;
	@SerializedName("bedrooms")
	public final int bedrooms;
	@SerializedName("bathrooms")
	public final int bathrooms;
	@SerializedName("houseNumber")
	public final String houseNumber;
	@SerializedName("address")
	public final String address;
	@SerializedName("region")
	public final String region;
	@SerializedName("postcode")
	public final String postcode;
	@SerializedName("propertyType")
	public final String propertyType;

	private PropertyType mPropertyType;
	private String mListItemHeader;
	private String mListItemContent;

	public Property(int propertyReference, float price, int bedrooms, int bathrooms,
			String houseNumber, String address, String region, String postcode,
			String propertyType) {
		this.propertyReference = propertyReference;
		this.price = price;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.houseNumber = houseNumber;
		this.address = address;
		this.region = region;
		this.postcode = postcode;
		this.propertyType = propertyType;
	}

	public PropertyType getPropertyType() {
		if (mPropertyType == null) {
			mPropertyType = PropertyType.instance(propertyType);
		}
		return mPropertyType;
	}

	public String getListItemHeader(Context context) {
		if (mListItemHeader == null || mListItemHeader.isEmpty()) {
			String priceFormatted =
					NumberFormat.getNumberInstance(Locale.getDefault()).format(price);
			mListItemHeader = context.getString(R.string.item_price, priceFormatted);
		}

		return mListItemHeader;
	}

	public String getListItemContent(Context context) {
		if (mListItemContent == null || mListItemContent.isEmpty()) {

			Resources resources = context.getResources();
			StringBuilder stringBuilder = new StringBuilder();

			//bedrooms
			String bed = resources.getQuantityString(R.plurals.item_bed, bedrooms, bedrooms);
			stringBuilder.append(bed).append(" ");
			//bathrooms
			String bathroom =
					resources.getQuantityString(R.plurals.item_bathroom, bathrooms, bathrooms);
			stringBuilder.append(bathroom).append(" ");
			//property type
			String propType = getPropertyType().getFullName(context);
			stringBuilder.append(propType).append("\n");
			//address
			if (!houseNumber.isEmpty()) {
				stringBuilder.append(houseNumber).append(" ");
			}
			if (!address.isEmpty()) {
				stringBuilder.append(address).append(", ");
			}
			if (!region.isEmpty()) {
				stringBuilder.append(region).append(", ");
			}
			if (!postcode.isEmpty()) {
				stringBuilder.append(postcode);
			}

			mListItemContent = stringBuilder.toString();
		}
		return mListItemContent;
	}
}
