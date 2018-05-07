package com.sample.propertyreader.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sample.propertyreader.R;
import com.sample.propertyreader.list.ListActivity;
import com.sample.propertyreader.parent.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by Artur Kasprzak on 26.04.2018.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {

	@BindView(R.id.tvAveragePrice)
	TextView mTvAveragePrice;
	@BindView(R.id.tvDiffAveragePrice)
	TextView mTvDiffAveragePrice;
	@BindView(R.id.btnShowList)
	Button mBtnShowList;

	private Unbinder mUnbinder;
	private HomeContract.Presenter mPresenter;

	public HomeFragment() {
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		Timber.d("onCreateView");

		View view = inflater.inflate(R.layout.fragment_home, container, false);
		mUnbinder = ButterKnife.bind(this, view);

		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mPresenter = new HomePresenter(this);
	}

	@Override
	public void onStart() {
		super.onStart();
		mPresenter.onStart();
	}

	@Override
	public void onStop() {
		mPresenter.onStop();
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		Timber.d("onDestroyView");
		super.onDestroyView();
		mUnbinder.unbind();
	}

	@Override
	public void displayAveragePrice(@NonNull String text) {
		mTvAveragePrice.setText(text);
	}

	@Override
	public void displayDiffInAveragePrice(@NonNull String text) {
		mTvDiffAveragePrice.setText(text);
	}

	@OnClick(R.id.btnShowList)
	public void showListView() {
		Timber.d("showListView");
		startActivity(new Intent(getContext(), ListActivity.class));
	}
}
