package net.example.foursquareapitest;

import android.support.test.InstrumentationRegistry;

import net.example.foursquareapitest.common.dagger.AppComponent;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Created by Carlo on 23/04/2017.
 */
public class DaggerMockComponentSetter implements DaggerMockRule.ComponentSetter<AppComponent> {

    // I moved this into a separate class so that it can be in the same package as BaseApplication
    // and so the setAppComponentForTests method can be restricted to package visibility instead of public

    @Override public void setComponent(AppComponent component) {
        BaseApplication app = (BaseApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        app.setAppComponentForTests(component);
    }

}
