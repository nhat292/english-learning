
package com.onedev.englishlearning.ui.feed.blogs;

import com.onedev.englishlearning.data.network.model.BlogResponse;
import com.onedev.englishlearning.ui.base.BaseView;

import java.util.List;

/**
 * Created by Nhat on 12/13/17.
 */


public interface BlogBaseView extends BaseView {

    void updateBlog(List<BlogResponse.Blog> blogList);
}
