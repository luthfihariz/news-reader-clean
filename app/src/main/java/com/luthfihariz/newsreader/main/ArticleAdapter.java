package com.luthfihariz.newsreader.main;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.luthfihariz.newsreader.BaseBindingAdapter;
import com.luthfihariz.newsreader.GlideApp;
import com.luthfihariz.newsreader.R;
import com.luthfihariz.newsreader.data.Article;
import com.luthfihariz.newsreader.databinding.ItemNewsBinding;
import com.luthfihariz.newsreader.util.CalendarUtil;

import java.util.List;

/**
 * Created by luthfihariz on 5/21/17.
 */

public class ArticleAdapter extends BaseBindingAdapter {

    private List<Article> mArticles;
    private Context mContext;

    public ArticleAdapter(Context context, List<Article> articles) {
        mArticles = articles;
        mContext = context;
    }

    @Override
    protected void updateBinding(ViewDataBinding binding, int position) {
        ItemNewsBinding itemBinding = (ItemNewsBinding) binding;
        Article article = mArticles.get(position);
        itemBinding.tvTitle.setText(article.getTitle());
        itemBinding.tvDate.setText(CalendarUtil.adjustTimePattern(article.getPublishedAt(),
                "yyyy-MM-dd'T'HH:mm:ss'Z'", "MM-dd"));
        itemBinding.tvSource.setText(article.getAuthor());
        GlideApp.with(mContext)
                .load(article.getUrlToImage())
                .placeholder(R.drawable.placeholder)
                .into(itemBinding.ivNews);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.item_news;
    }

    @Override
    protected View.OnClickListener getOnClickListener() {
        return null;
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}
