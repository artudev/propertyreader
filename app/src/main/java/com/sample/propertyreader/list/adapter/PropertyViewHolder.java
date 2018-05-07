package com.sample.propertyreader.list.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sample.propertyreader.R;
import com.sample.propertyreader.model.property.Property;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
public class PropertyViewHolder extends RecyclerView.ViewHolder {

	private final View mView;
	private final Context mContext;

	@BindView(R.id.tvHeader)
	TextView mTvHeader;
	@BindView(R.id.tvContent)
	TextView mTvContent;

	public PropertyViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		mView = itemView;

		mContext = mView.getContext();
	}

	protected void bindData(final Property property) {
		mTvHeader.setText(property.getListItemHeader(mContext));
		mTvContent.setText(property.getListItemContent(mContext));
	}
}
