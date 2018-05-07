package com.sample.propertyreader.model.property;

import android.content.Context;

import com.sample.propertyreader.R;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
public enum PropertyType {
	MANSION {
		@Override
		public String getTagName() {
			return "Mansion";
		}

		@Override
		public String getFullName(Context context) {
			return context.getString(R.string.type_mansion);
		}
	}, TERRACED {
		@Override
		public String getTagName() {
			return "Terraced";
		}

		@Override
		public String getFullName(Context context) {
			return context.getString(R.string.type_terraced);
		}
	}, DETACHED {
		@Override
		public String getTagName() {
			return "Detached";
		}

		@Override
		public String getFullName(Context context) {
			return context.getString(R.string.type_detached);
		}
	}, FLAT {
		@Override
		public String getTagName() {
			return "Flat";
		}

		@Override
		public String getFullName(Context context) {
			return context.getString(R.string.type_flat);
		}
	}, NONE {
		@Override
		public String getTagName() {
			return "";
		}

		@Override
		public String getFullName(Context context) {
			return "";
		}
	};

	public abstract String getTagName();

	public abstract String getFullName(Context context);

	public static PropertyType instance(String name) {
		if (MANSION.getTagName().equals(name)) {
			return MANSION;
		} else if (TERRACED.getTagName().equals(name)) {
			return TERRACED;
		} else if (DETACHED.getTagName().equals(name)) {
			return DETACHED;
		} else if (FLAT.getTagName().equals(name)) {
			return FLAT;
		} else {
			return NONE;
		}
	}
}
