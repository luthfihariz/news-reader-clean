package com.luthfihariz.newsreader.data;

import java.util.List;

/**
 * Created by luthfihariz on 5/21/17.
 */

public class ArticleResponse {
    private String status;
    private String source;
    private String sortBy;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
