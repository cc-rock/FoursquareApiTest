# FoursquareApiTest
A test of integration of foursquare search api in an Android app

* Using dependency injection with Dagger 2
* Using Model-View-Presenter architecture
* Using TDD approach for user interface (espresso + junit + mockito)
* Using TDD approach for business logic (junit + mockito)

Improvements / To-Do:

* Use the new dagger android integration and activity scoped subcomponents
* Put searchView inside action bar 
* Improve the UI (make it look better)
* Add a "Show on map" link to each search result, that opens another activity
  with a Google Maps view that shows all the results, with a balloon view on the clicked one.
* Handling screen rotation without losing the active network call 
* Currently some espresso test fails if the device is in landscape orientation

