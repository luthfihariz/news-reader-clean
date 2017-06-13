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

import com.luthfihariz.newsreader.Injection;
import com.luthfihariz.newsreader.R;
import com.luthfihariz.newsreader.data.Source;
import com.luthfihariz.newsreader.databinding.ActivitySourcePickerBinding;
import com.luthfihariz.newsreader.main.MainActivity;

import java.util.List;

/**
 * Created by luthfihariz on 5/22/17.
 */

public class SourcePickerActivity extends AppCompatActivity implements SourcePickerContract.View {

    private ActivitySourcePickerBinding mBinding;
    private SourcePickerContract.Presenter mPresenter;

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
            getSupportActionBar().setTitle("Pick Your News Channel");
        }
    }

    @Override
    public void setPresenter(SourcePickerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showSources(List<Source> sources) {
        SourcePickerAdapter adapter = new SourcePickerAdapter(sources, null);
        mBinding.rvSource.setLayoutManager(new GridLayoutManager(this, 3));
        mBinding.rvSource.setAdapter(adapter);
    }

    @Override
    public void showErrorView() {

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
