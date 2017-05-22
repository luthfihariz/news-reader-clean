package com.luthfihariz.newsreader;

import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by luthfihariz on 2/20/17.
 */

public class BaseBindingViewHolder extends RecyclerView.ViewHolder {

    ViewDataBinding mBinding;

    public BaseBindingViewHolder(ViewDataBinding layoutBinding, @Nullable View.OnClickListener listener) {
        super(layoutBinding.getRoot());
        mBinding = layoutBinding;
        mBinding.getRoot().setOnClickListener(listener);
        mBinding.getRoot().setTag(this);
    }


    public ViewDataBinding getBinding() {
        return mBinding;
    }
}
