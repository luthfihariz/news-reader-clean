package com.luthfihariz.newsreader.sourcepicker;

import com.luthfihariz.newsreader.BasePresenter;
import com.luthfihariz.newsreader.BaseView;
import com.luthfihariz.newsreader.data.Source;

import java.util.List;

/**
 * Created by luthfihariz on 5/22/17.
 */

public class SourcePickerContract {

    interface View extends BaseView<Presenter> {
        void showSources(List<Source> sources);

        void showErrorView();

        void showProgressBar();

        void hideProgressBar();

        void goToNewsReader();
    }

    interface Presenter extends BasePresenter<View> {
        void getSources();

        void saveSelectedSources(List<Source> selectedSources);
    }
}
