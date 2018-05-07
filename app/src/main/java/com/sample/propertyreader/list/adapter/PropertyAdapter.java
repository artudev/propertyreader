package com.sample.propertyreader.list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.propertyreader.R;
import com.sample.propertyreader.model.property.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
public class PropertyAdapter extends RecyclerView.Adapter<PropertyViewHolder> {

	private List<Property> mProperties;

	public PropertyAdapter() {
		mProperties = new ArrayList<>();
	}

	public void setList(List<Property> list) {
		mProperties.clear();
		mProperties.addAll(list);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_property, parent, false);
		return new PropertyViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
		holder.bindData(mProperties.get(position));
	}

	@Override
	public int getItemCount() {
		return mProperties.size();
	}
}
