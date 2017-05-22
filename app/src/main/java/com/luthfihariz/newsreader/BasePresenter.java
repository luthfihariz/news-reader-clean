package com.luthfihariz.newsreader;

/**
 * Created by Luthfi Haris on 21/10/2016.
 */

public interface BasePresenter<V>{

    void bind(V view);
    void unbind();
}
