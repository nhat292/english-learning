
package com.onedev.englishlearning.data.prefs;

import com.onedev.englishlearning.data.model.User;

/**
 * Created by Nhat on 12/13/17.
 */


public interface PreferencesHelper {

    void setUser(User user);

    User getUser();

}
