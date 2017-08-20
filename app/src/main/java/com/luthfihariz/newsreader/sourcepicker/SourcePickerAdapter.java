package com.luthfihariz.newsreader.sourcepicker;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.luthfihariz.newsreader.BaseBindingAdapter;
import com.luthfihariz.newsreader.R;
import com.luthfihariz.newsreader.data.Source;
import com.luthfihariz.newsreader.databinding.ItemSourceBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luthfihariz on 5/22/17.
 */

class SourcePickerAdapter extends BaseBindingAdapter {

    private List<Source> mSources;
    private List<Source> mSelectedSources;
    private View.OnClickListener mListener;

    SourcePickerAdapter(List<Source> sources, View.OnClickListener listener) {
        mSources = sources;
        mListener = listener;
        mSelectedSources = new ArrayList<>();
    }

    @Override
    protected void updateBinding(ViewDataBinding binding, int position) {
        ItemSourceBinding itemBinding = (ItemSourceBinding) binding;
        Source source = mSources.get(position);
        itemBinding.tvSource.setText(source.getName());
        itemBinding.getRoot().setSelected(mSelectedSources.contains(source));
    }

    List<Source> getSelectedSources() {
        return mSelectedSources;
    }

    void setSelectedSources(List<Source> sources) {
        mSelectedSources = sources;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.item_source;
    }

    @Override
    protected View.OnClickListener getOnClickListener() {
        return v -> {
            v.setSelected(!v.isSelected());

            int selectedPos = ((RecyclerView.ViewHolder) v.getTag()).getAdapterPosition();
            Source selectedSource = mSources.get(selectedPos);

            if (v.isSelected()) {
                if (!mSelectedSources.contains(selectedSource)) {
                    mSelectedSources.add(selectedSource);
                }
            } else {
                if (mSelectedSources.contains(selectedSource)) {
                    mSelectedSources.remove(selectedSource);
                }
            }

            if (mListener != null) {
                mListener.onClick(v);
            }
        };
    }

    @Override
    public int getItemCount() {
        return mSources.size();
    }
}
