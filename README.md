# Android Persistance API Integration
Android Local Storage and integration with a web API.

**Part 1: Local storage using ORMLite**

1) Download the project code, import it into Android Studio and make sure it compiles correctly.

2) Check the data structure of the following json resource and add the proper attributes and annotations to the class Team in order to be able to persist the teams data.


Teams url:   https://raw.githubusercontent.com/sancarbar/starting-android-lists/master/teams.json
 
ORMLite documentation examples: http://ormlite.com/android/examples/

   
   3) Instantiate the _OrmModel_ class and create some team objects in the database by using the methods provided by the dao. 
   
   4) Close your app and re-run it and verify that the created objects were save on your data base by using the method _getAll()_ from the _TeamDao_ class.
   
   
   
   **Part 2: retrieving data from the API using Retrofit**
   
   