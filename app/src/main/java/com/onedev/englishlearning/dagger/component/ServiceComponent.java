
package com.onedev.englishlearning.dagger.component;

import com.onedev.englishlearning.dagger.PerService;
import com.onedev.englishlearning.dagger.module.ServiceModule;
import com.onedev.englishlearning.service.SyncService;

import dagger.Component;

/**
 * Created by Nhat on 12/13/17.
 */


@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);

}
