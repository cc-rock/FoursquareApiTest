package net.example.foursquareapitest.venuessearch;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;

import net.example.foursquareapitest.BaseApplication;
import net.example.foursquareapitest.R;
import net.example.foursquareapitest.model.entities.VenueList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VenuesSearchActivity extends AppCompatActivity implements VenuesSearchView {

    @BindView(R.id.venues_search_view)
    SearchView searchView;

    @BindView(R.id.venues_search_results)
    RecyclerView searchResults;

    @Inject
    VenuesSearchPresenter presenter;

    VenuesSearchAdapter adapter;

    private DialogFragment loadingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((BaseApplication)getApplication()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues_search);
        ButterKnife.bind(this);
        presenter.initialise();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                presenter.searchRequested(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchResults.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new VenuesSearchAdapter();
        searchResults.setAdapter(adapter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String currentQuery = searchView.getQuery() != null ? searchView.getQuery().toString() : "";
        // this method is called only if this activity is being restored after being destroyed by the system
        // (for example during screen rotation or low system resources)
        // so if there is a query in the text field, redo the search to show the results again
        if (!TextUtils.isEmpty(currentQuery)) {
            presenter.searchRequested(currentQuery);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.bindView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unbindView();
    }

    @Override
    public void showLoading() {
        loadingFragment = VenuesSearchDialogFragment.newInstance(
                R.string.loading, 0, false);
        loadingFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void showEmptySearchStringError() {
        DialogFragment newFragment = VenuesSearchDialogFragment.newInstance(
                R.string.error, R.string.empty_search_String, true);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void showSearchError() {
        DialogFragment newFragment = VenuesSearchDialogFragment.newInstance(
                R.string.error, R.string.search_error, true);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void showSearchResults(VenueList venueList) {
        adapter.setItems(venueList.getVenues());
    }

    @Override
    public void hideLoading() {
        if (loadingFragment != null) {
            loadingFragment.dismiss();
        }
    }
}
