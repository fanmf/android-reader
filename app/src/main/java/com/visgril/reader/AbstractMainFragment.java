package com.visgril.reader;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AbstractMainFragment extends Fragment {

	public View mView;
	public boolean isViewInitiated;
	public boolean isVisibleToUser;
	public boolean isDataRequested;
	public Context context;


	public AbstractMainFragment() {
	}

	@Nullable
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		this.context = super.getContext();
		this.mView = inflater.inflate(getLayoutId(), null);
		this.isViewInitiated = true;
		initView();
		prepareGetData();
		return this.mView;
	}

	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initComp();
	}

	public abstract void initComp();

	public abstract void initView();

	public abstract int getLayoutId();

	public abstract void getDataFromServer();

	private boolean prepareGetData() {
		return prepareGetData(false);
	}

	public boolean prepareGetData(boolean isforceUpdate) {
		if ((this.isVisibleToUser) && (this.isViewInitiated) && ((!this.isDataRequested) || (isforceUpdate))) {
			getDataFromServer();
			this.isDataRequested = true;
			return true;
		}
		return false;
	}
}
