# Android Persistance API Integration
Android Local Storage and integration with a web API.

**Part 1: Local storage using ORMLite**

1) Download the project code, import it into Android Studio and make sure it compiles correctly.

2) Check the data structure of the following json resource and add the proper attributes and annotations to the class Team in order to be able to persist the teams data (make sure that the _Team_ class has an empty constructor that OrmLite requires to instantiate the model).


Teams url:   https://raw.githubusercontent.com/sancarbar/starting-android-lists/master/teams.json
 
ORMLite documentation examples: http://ormlite.com/android/examples/

   
   3) Instantiate the _OrmModel_ class in the _MainActivity_ and create some team objects in the database by using the methods provided by the dao. 
   
   4) Close your app and re-run it and verify that the created objects were save on your data base by using the method _getAll()_ from the _TeamDao_ class.
   
   
   
   **Part 2: retrieving data from the API using Retrofit**
   
   1) Add the following dependency on your build.gradle(app) file:
   ```groovy
   compile 'com.squareup.retrofit2:retrofit:2.2.0'
   ```
   
   2) Create an interface called _TeamsService_ that has a get method to retrieve the teams
   
  ```java
      interface TeamsService
      {
      
          @POST( "teams.json" )
          Call<List<Team>> getTeamsList( );
      
      }
  ```
      
3) Create a class called RetrofitNetwork inside a package called network that contains the following code:
 
    
  ```java
     public class RetrofitNetwork
      {
      
       private static final String BASE_URL = "https://raw.githubusercontent.com/sancarbar/starting-android-lists/master/";
       
       private TeamsService teamsService;
      
           public RetrofitNetwork()
              {
                  Retrofit retrofit =
                      new Retrofit.Builder().baseUrl( BASE_URL ).addConverterFactory( GsonConverterFactory.create() ).build();
                  teamsService = retrofit.create( TeamsService.class );
              }
      
      }
  ```
      
4) Create a _Callback_ interface inside the network package to handle the response from the network request:
       
  ```java
     public interface RequestCallback<T>
     {
     
         void onSuccess( T response );
     
         void onFailed( NetworkException e );
     
     }
  ```
         
  
5) Add a method called _getTeams_ to the _RetrofitNetwork_ class  to retrieve the Teams list.
               
  ```java
       public void getTeams( RequestCallback<List<Teams>> requestCallback )
         {             
             try
             {
                Call<List<Teams>> call = teamsService.getTeamsList( );
                 Response<List<Teams>> execute = call.execute();
                 requestCallback.onSuccess( execute.body() );
             }
             catch ( IOException e )
             {
                 requestCallback.onFailed( new NetworkException( 0, null, e ) );
             }
         }

  ```
  
  6) Verify that the method works as expected and that the teams are retrieved correctly (you can try using the debugger)
  
  7) Persist the teams objects into the database, then restart your application and make sure that teams are store correctly.