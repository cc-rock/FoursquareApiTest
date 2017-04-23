package net.example.foursquareapitest.venuessearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import net.example.foursquareapitest.BaseApplication;
import net.example.foursquareapitest.R;
import net.example.foursquareapitest.model.entities.VenueList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VenuesSearchActivity extends AppCompatActivity implements VenuesSearchView {

    @BindView(R.id.venues_search_view)
    SearchView searchView;

    @Inject
    VenuesSearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((BaseApplication)getApplication()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues_search);
        ButterKnife.bind(this);
        presenter.initialise(null);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.searchRequested(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmptySearchStringError() {

    }

    @Override
    public void showSearchError() {

    }

    @Override
    public void showSearchResults(VenueList venueList) {

    }
}
