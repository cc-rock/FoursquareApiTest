package net.example.foursquareapitest.venuessearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.example.foursquareapitest.BaseApplication;
import net.example.foursquareapitest.R;

import javax.inject.Inject;

public class VenuesSearchActivity extends AppCompatActivity {

    @Inject
    VenuesSearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((BaseApplication)getApplication()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues_search);

        presenter.initialise(null);
    }
}
