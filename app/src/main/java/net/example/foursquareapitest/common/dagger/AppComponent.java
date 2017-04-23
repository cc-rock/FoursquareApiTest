package net.example.foursquareapitest.common.dagger;

import net.example.foursquareapitest.venuessearch.VenuesSearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Carlo on 23/04/2017.
 */
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {

    void inject(VenuesSearchActivity venuesSearchActivity);

}
