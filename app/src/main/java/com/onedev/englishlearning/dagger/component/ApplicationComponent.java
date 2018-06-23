
package com.onedev.englishlearning.dagger.component;

import android.app.Application;
import android.content.Context;

import com.onedev.englishlearning.App;
import com.onedev.englishlearning.dagger.ApplicationContext;
import com.onedev.englishlearning.dagger.module.ApplicationModule;
import com.onedev.englishlearning.data.DataManager;
import com.onedev.englishlearning.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nhat on 12/13/17.
 */


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}