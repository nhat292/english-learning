
package com.onedev.englishlearning.ui.feed.opensource;

import com.onedev.englishlearning.data.network.model.OpenSourceResponse;
import com.onedev.englishlearning.ui.base.BaseView;

import java.util.List;

/**
 * Created by Nhat on 12/13/17.
 */


public interface OpenSourceBaseView extends BaseView {

    void updateRepo(List<OpenSourceResponse.Repo> repoList);
}
