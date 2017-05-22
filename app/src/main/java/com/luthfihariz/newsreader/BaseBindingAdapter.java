package com.luthfihariz.newsreader;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by luthfihariz on 2/20/17.
 */

public abstract class BaseBindingAdapter extends RecyclerView.Adapter<BaseBindingViewHolder> {

    @Override
    public BaseBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseBindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                getLayoutResource(), parent, false), getOnClickListener());
    }

    @Override
    public void onBindViewHolder(BaseBindingViewHolder holder, int position) {
        updateBinding(holder.getBinding(), position);
    }

    protected abstract void updateBinding(ViewDataBinding binding, int position);

    protected abstract int getLayoutResource();

    protected abstract View.OnClickListener getOnClickListener();
}
