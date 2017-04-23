package net.example.foursquareapitest.venuessearch;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.SearchView;

import net.example.foursquareapitest.DaggerMockComponentSetter;
import net.example.foursquareapitest.R;
import net.example.foursquareapitest.common.dagger.AppComponent;
import net.example.foursquareapitest.common.dagger.AppModule;
import net.example.foursquareapitest.model.entities.Venue;
import net.example.foursquareapitest.model.entities.VenueList;
import net.example.foursquareapitest.model.entities.VenueLocation;
import net.example.foursquareapitest.model.entities.VenueStats;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Carlo on 23/04/2017.
 */
@RunWith(AndroidJUnit4.class)
public class VenuesSearchActivityTest {

    @Rule
    public DaggerMockRule<AppComponent> daggerRule = new DaggerMockRule<>(AppComponent.class, new AppModule())
            .set(new DaggerMockComponentSetter());

    @Rule
    public ActivityTestRule<VenuesSearchActivity> activityRule = new ActivityTestRule<>(VenuesSearchActivity.class, false, false);

    @Mock
    VenuesSearchPresenter mockPresenter;

    @Test
    public void presenterIsInitialised() {
        activityRule.launchActivity(null);
        Assert.assertSame(mockPresenter, activityRule.getActivity().presenter);
        verify(mockPresenter, times(1)).initialise();
    }

    @Test
    public void initialViewsAreShown() {
        activityRule.launchActivity(null);
        onView(withId(R.id.venues_search_view)).check(matches(isDisplayed()));
        onView(withId(R.id.venues_search_results)).check(matches(isDisplayed()));
    }

    @Test
    public void submittingSearchQueryCallsPresenter() {
        activityRule.launchActivity(null);
        onView(withId(R.id.venues_search_view)).perform(click());
        onView(isAssignableFrom(SearchView.SearchAutoComplete.class)).perform(typeText("test query"));
        onView(isAssignableFrom(SearchView.SearchAutoComplete.class)).perform(pressImeActionButton());
        verify(mockPresenter, times(1)).searchRequested("test query");
    }

    @Test
    public void callingShowLoadingShowsLoadingDialog() {
        activityRule.launchActivity(null);
        activityRule.getActivity().showLoading();
        onView(withText(R.string.loading)).inRoot(isDialog()).check(matches(isDisplayed()));
    }

    @Test
    public void callingShowErrorEmptyQueryShowsCorrectError() {
        activityRule.launchActivity(null);
        activityRule.getActivity().showEmptySearchStringError();
        onView(withText(R.string.error)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText(R.string.empty_search_String)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText(R.string.alert_dialog_ok)).inRoot(isDialog()).check(matches(isDisplayed()));
    }

    @Test
    public void callingShowErrorDuringSearchShowsCorrectError() {
        activityRule.launchActivity(null);
        activityRule.getActivity().showSearchError();
        onView(withText(R.string.error)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText(R.string.search_error)).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText(R.string.alert_dialog_ok)).inRoot(isDialog()).check(matches(isDisplayed()));
    }

    @Test
    public void callingShowResultsShowsTheResults() {
        activityRule.launchActivity(null);
        onView(isAssignableFrom(SearchView.SearchAutoComplete.class)).perform(closeSoftKeyboard());
        final VenueList fakeResults = getFakeSearchResults();
        activityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activityRule.getActivity().showSearchResults(fakeResults);
            }
        });
        for(Venue venue : fakeResults.getVenues()) {
            checkVenueIsDisplayed(venue);
        }
    }

    private void checkVenueIsDisplayed(Venue venue) {
        onView(withText(venue.getName())).check(matches(isDisplayed()));
    }

    private VenueList getFakeSearchResults() {
        List<Venue> venues = new ArrayList();
        venues.add(createVenue("1", "Test Venue 1", "test avenue", "London", "NW1 2HT", 10, 2 ));
        venues.add(createVenue("2", "Test Venue 2", "test street", "London", "NW1 3FR", 200, 5 ));
        venues.add(createVenue("3", "Test Venue 3", "test grove", "London", "SE3 2HT", 450, 8 ));
        return new VenueList(venues);
    }

    private Venue createVenue(String id, String name, String address, String city, String postcode, int checkins, int rating) {
        VenueLocation location = new VenueLocation(address, null, city, null, postcode, "UK", 0, 0, 0);
        VenueStats stats = new VenueStats(checkins, 0, 0);
        return new Venue(id, name, location, stats, rating);
    }
}
