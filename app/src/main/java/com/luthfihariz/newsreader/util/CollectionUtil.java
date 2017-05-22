package com.luthfihariz.newsreader.util;

import java.util.List;

/**
 * Created by luthfihariz on 3/17/17.
 */

public final class CollectionUtil {

    public static boolean isEmpty(List<? extends Object> objects) {
        return objects == null || objects.size() == 0;
    }
}
