package com.luthfihariz.newsreader.sourcepicker;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.luthfihariz.newsreader.Injection;
import com.luthfihariz.newsreader.R;
import com.luthfihariz.newsreader.data.Source;
import com.luthfihariz.newsreader.databinding.ActivitySourcePickerBinding;
import com.luthfihariz.newsreader.main.MainActivity;
import com.luthfihariz.newsreader.util.CollectionUtil;

import java.util.List;

/**
 * Created by luthfihariz on 5/22/17.
 */

public class SourcePickerActivity extends AppCompatActivity implements SourcePickerContract.View {

    private ActivitySourcePickerBinding mBinding;
    private SourcePickerContract.Presenter mPresenter;
    private SourcePickerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_source_picker);

        mPresenter = new SourcePickerPresenter(
                Injection.provideRepository(getApplicationContext()),
                Injection.provideScheduler());
        mPresenter.bind(this);
        mPresenter.getSources();

        setupToolbar();

        mBinding.btnSave.setOnClickListener(v -> {
            SourcePickerAdapter adapter = (SourcePickerAdapter) mBinding.rvSource.getAdapter();
            mPresenter.saveSelectedSources(adapter.getSelectedSources());
        });
    }

    private void setupToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.all_pick_channel);
        }
    }

    @Override
    public void setPresenter(SourcePickerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showSources(List<Source> sources) {
        mAdapter = new SourcePickerAdapter(sources, v -> {
            if (CollectionUtil.isEmpty(mAdapter.getSelectedSources())) {
                mBinding.btnSave.setVisibility(View.GONE);
            } else {
                mBinding.btnSave.setVisibility(View.VISIBLE);
            }
        });
        mBinding.rvSource.setLayoutManager(new GridLayoutManager(this, 2));
        mBinding.rvSource.setAdapter(mAdapter);

        mPresenter.getPreviouslySelectedSources();
    }

    @Override
    public void showPreviouslySelectedSources(List<Source> sources) {
        if (!CollectionUtil.isEmpty(sources)) {
            mAdapter.setSelectedSources(sources);
        }
    }

    @Override
    public void showErrorView() {
        Snackbar.make(mBinding.getRoot(), R.string.all_general_error, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showProgressBar() {
        mBinding.pbSourcePicker.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mBinding.pbSourcePicker.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }

    @Override
    public void goToNewsReader() {
        finish();
        MainActivity.intent(this);
    }

    public static void intent(Context context) {
        Intent intent = new Intent(context, SourcePickerActivity.class);
        context.startActivity(intent);
    }
}
