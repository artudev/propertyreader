package com.sample.propertyreader.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.propertyreader.R;
import com.sample.propertyreader.list.adapter.PropertyAdapter;
import com.sample.propertyreader.model.property.Property;
import com.sample.propertyreader.parent.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by Artur Kasprzak on 29.04.2018.
 */
public class ListFragment extends BaseFragment implements ListContract.View {

	@BindView(R.id.rvList)
	RecyclerView mRvList;

	private Unbinder mUnbinder;
	private ListContract.Presenter mPresenter;
	private PropertyAdapter mPropertyAdapter;

	public ListFragment() {
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

		View view = inflater.inflate(R.layout.fragment_list, container, false);
		mUnbinder = ButterKnife.bind(this, view);

		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mPresenter = new ListPresenter(this);

		mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
		mPropertyAdapter = new PropertyAdapter();
		mRvList.setAdapter(mPropertyAdapter);
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
	public void displayList(List<Property> list) {
		mPropertyAdapter.setList(list);
	}
}
