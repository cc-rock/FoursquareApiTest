package net.example.foursquareapitest.venuessearch;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import net.example.foursquareapitest.BaseApplication;
import net.example.foursquareapitest.common.dagger.AppComponent;
import net.example.foursquareapitest.common.dagger.AppModule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Carlo on 23/04/2017.
 */
@RunWith(AndroidJUnit4.class)
public class VenuesSearchActivityTest {

    @Rule
    public DaggerMockRule<AppComponent> daggerRule = new DaggerMockRule<>(AppComponent.class, new AppModule())
            .set(new DaggerMockRule.ComponentSetter<AppComponent>() {
                @Override public void setComponent(AppComponent component) {
                    BaseApplication app = (BaseApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
                    app.setAppComponentForTests(component);
                }
            });

    @Rule
    public ActivityTestRule<VenuesSearchActivity> activityRule = new ActivityTestRule<>(VenuesSearchActivity.class, false, false);

    @Mock
    VenuesSearchPresenter mockPresenter;

    @Test
    public void presenterIsInitialised() {
        activityRule.launchActivity(null);
        Assert.assertSame(mockPresenter, activityRule.getActivity().presenter);
        verify(mockPresenter, times(1)).initialise(null);
    }
}
