package com.luthfihariz.newsreader.data;

import java.util.List;

/**
 * Created by luthfihariz on 5/21/17.
 */

public class SourceResponse {
    private String status;
    private List<Source> sources;

    public String getStatus() {
        return status;
    }

    public List<Source> getSources() {
        return sources;
    }
}
