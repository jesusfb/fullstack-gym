# Android Gym Companion App

## **This app requires an API to run. Please refer to the [API](../../Backend/README.md) for more information.**

This is android app enables registered users to register for the gym classes provided by their subscription.

**Android 6.0 (Marshmallow) or above is required to run this app.**

# Built With

- [Android Studio](https://developer.android.com/studio)
- [Kotlin](https://kotlinlang.org/)
- [Gradle](https://gradle.org/)

## Technologies Used

- Retrofit - To make network calls
- Gson - To parse JSON returned by the API
- Navigation Component - To navigate between fragments
- Material Design - To make the app look good
- Glide - To load images
- Coroutines - To make network calls asynchronous

## Features

- Parallel processing of network calls
  - Make network calls in parallel using coroutines as the user logs in
- User login & authentication
  - Send user credentials to the server and get a token
- Register/Unregister from gym classes
- Simple and intuitive UI
  - Built using Material Design

# How to run the app

- Clone the repository
- Open the project in Android Studio (Dolphin or above)
- Navigate to the build.gradle file in the app folder
- Change the value of the BASE_URL variable (inside the app->defaultConfig) to the IP address of your machine
- Build and run the app on an emulator or a physical device

# Screenshots

<table>
  <tr>
    <td><img src="../../Images/android/login_page.jpg" height=100% width=100%></td>
    <td><img src="../../Images/android/home.jpg" height=100% width=100%></td>
    <td><img src="../../Images/android/my_profile.jpg" height=100% width=100%></td>
  </tr>
    <td><img src="../../Images/android/available_courses.jpg" height=100% width=100%></td>
    <td><img src="../../Images/android/unavailable_courses.jpg" height=100% width=100%></td>
  <tr></tr>
</table>
