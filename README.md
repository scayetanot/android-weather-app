GOAT - Android Interview


What to build:


The goal of this assignment is to build a small app to give the user information about the weather in their current location. This is an opportunity for you to show off your skills!

The app should use the Dark Sky API (log in with the account user name jarrod.holliday@goat.com with password 8VqH^*H8RaFadLD at https://darksky.net/dev and follow their doc for using their api).


Overview: the Dark Sky app should display the current weather conditions at Los Angeles or the user’s current location.

This app will consist of two pages; the landing page to show the current weather and a details page to show more fine grained information about the weather.

- Upon loading the app and hitting the landing page it should request permission to use the user’s location
- On "Allow": Refresh weather list to show current user location's weather.
- On "Don't Allow”: Do nothing
- When the user allows location permission the landing page should show at least:
- The current date and time
- The current high and low temperature for the current day in the “daily” section
- The icon for the current day in the “daily” section
- On tap for the current weather the user should be sent to the details screen for more information

Don't worry too much about design here just get something working with something like a simple RecyclerView with an empty state.

- The details page should show a list of the “hourly” data
- Each item should include the hour for the time, icon, and temperature
- Bonus points to grey out or filter out the items before the current time so it’s more obvious what hourly data is more relevant for the rest of the day

What to use:

- Use any libraries (Retrofit, for instance, for making api calls), but if you know them please include the use of Dagger and RxJava(or any of the other reactive libraries).
- Use a clear architecture pattern that you feel most comfortable with (MVC is still valid).
- Be prepared to clearly explain how/why you architect your code.


