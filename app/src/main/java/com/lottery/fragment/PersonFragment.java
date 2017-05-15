package com.lottery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lottery.R;

/**
 * Created by Administrator on 2017/5/15.
 */

public class PersonFragment extends BaseFragment {
    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        setStatusBar(ContextCompat.getColor(activity,R.color.colorAccent));
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }
}
