package com.luthfihariz.newsreader.main;

import android.support.v7.util.DiffUtil;

import com.luthfihariz.newsreader.data.Article;

import java.util.List;

/**
 * Created by luthfihariz on 6/12/17.
 */

public class ArticleDiffCallback extends DiffUtil.Callback {

    List<Article> mOldArticles;
    List<Article> mNewArticles;

    public ArticleDiffCallback(List<Article> old, List<Article> newer) {
        mOldArticles = old;
        mNewArticles = newer;
    }

    @Override
    public int getOldListSize() {
        return mOldArticles.size();
    }

    @Override
    public int getNewListSize() {
        return mNewArticles.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldArticles.get(oldItemPosition).equals(mNewArticles.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Article oldItem = mOldArticles.get(oldItemPosition);
        Article newItem = mNewArticles.get(newItemPosition);
        return oldItem.getTitle().equals(newItem.getTitle());
    }
}
