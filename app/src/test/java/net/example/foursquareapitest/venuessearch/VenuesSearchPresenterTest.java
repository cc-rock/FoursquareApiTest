package net.example.foursquareapitest.venuessearch;

import net.example.foursquareapitest.model.FoursquareApi;
import net.example.foursquareapitest.model.entities.Venue;
import net.example.foursquareapitest.model.entities.VenueList;
import net.example.foursquareapitest.model.entities.VenuesResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.schedulers.TestScheduler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Carlo on 23/04/2017.
 */

public class VenuesSearchPresenterTest {

    @Mock
    FoursquareApi mockFoursquareApi;

    @Mock
    VenuesSearchView mockView;

    private TestScheduler ioTestScheduler;
    private TestScheduler mainThreadTestScheduler;
    private VenuesSearchPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ioTestScheduler = new TestScheduler();
        mainThreadTestScheduler = new TestScheduler();
        presenter = new VenuesSearchPresenter(mockFoursquareApi, ioTestScheduler, mainThreadTestScheduler);
        presenter.bindView(mockView);
    }

    @Test
    public void searchRequestedWithEmptyQuery() {
        presenter.searchRequested("");
        verify(mockView, times(1)).showEmptySearchStringError();
        verify(mockFoursquareApi, times(0)).searchVenues("");
    }

    @Test
    public void searchPerformedWithErrorResult() {
        when(mockFoursquareApi.searchVenues("test query")).thenReturn(Observable.<VenuesResponse>error(new Exception("Test exception")));
        presenter.searchRequested("test query");
        verify(mockView, times(1)).showLoading();
        ioTestScheduler.triggerActions();
        mainThreadTestScheduler.triggerActions();
        verify(mockView, times(1)).hideLoading();
        verify(mockView, times(1)).showSearchError();
    }

    @Test
    public void searchPerformedWithSuccessResult() {
        VenuesResponse mockResponse = mock(VenuesResponse.class);
        VenueList mockVenueList = mock(VenueList.class);
        when(mockResponse.getVenues()).thenReturn(mockVenueList);
        when(mockFoursquareApi.searchVenues("test query")).thenReturn(Observable.just(mockResponse));
        presenter.searchRequested("test query");
        verify(mockView, times(1)).showLoading();
        ioTestScheduler.triggerActions();
        mainThreadTestScheduler.triggerActions();
        verify(mockView, times(1)).hideLoading();
        verify(mockView, times(1)).showSearchResults(mockVenueList);
    }

    @Test
    public void searchPerformedAndViewUnboundWhenResultsArrive() {
        VenuesResponse mockResponse = mock(VenuesResponse.class);
        VenueList mockVenueList = mock(VenueList.class);
        when(mockResponse.getVenues()).thenReturn(mockVenueList);
        when(mockFoursquareApi.searchVenues("test query")).thenReturn(Observable.just(mockResponse));
        presenter.searchRequested("test query");
        verify(mockView, times(1)).showLoading();
        presenter.unbindView();
        ioTestScheduler.triggerActions();
        mainThreadTestScheduler.triggerActions();
        verify(mockView, times(0)).hideLoading();
        verify(mockView, times(0)).showSearchResults(mockVenueList);
    }

}
