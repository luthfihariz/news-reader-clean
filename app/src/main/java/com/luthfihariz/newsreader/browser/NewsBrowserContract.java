package com.luthfihariz.newsreader.browser;

import com.luthfihariz.newsreader.BasePresenter;
import com.luthfihariz.newsreader.BaseView;

/**
 * Created by luthfihariz on 6/13/17.
 */

interface NewsBrowserContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
