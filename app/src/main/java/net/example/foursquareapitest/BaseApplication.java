package net.example.foursquareapitest;

import android.app.Application;

import net.example.foursquareapitest.common.dagger.AppComponent;
import net.example.foursquareapitest.common.dagger.AppModule;
import net.example.foursquareapitest.common.dagger.DaggerAppComponent;

/**
 * Created by Carlo on 23/04/2017.
 */

public class BaseApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    void setAppComponentForTests(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

}
