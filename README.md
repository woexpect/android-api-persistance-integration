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
  
  
  **Part 3: Display the teams using a RecyclerView.Adapter**
  
  
  1) Create a class that extends the _RecyclerView.Adapter_. Inside this class create a _ViewHolder_ inner class with the corresponding element views that will represent a team (name, short name and image).
  
  2) Include the cardLayout and RecyclerView dependencies on your gradle file.
       ```groovy
           dependencies {
                        compile 'com.android.support:cardview-v7:21.0.+'
                        compile 'com.android.support:recyclerview-v7:21.0.+'
                    }
        ```
  
  3) Create an xml file that will represent the row of a team and make sure that this values are mapped on the ViewHolder class.
  

       <?xml version="1.0" encoding="utf-8"?>
       <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                     xmlns:card_view="http://schemas.android.com/apk/res-auto"
                     xmlns:tools="http://schemas.android.com/tools"
                     android:orientation="horizontal"
                     android:layout_width="match_parent"
                     android:layout_height="wrap_content">
       
         <android.support.v7.widget.CardView
             android:id="@+id/card_view"
             android:layout_gravity="center"
             android:layout_width="match_parent"
             android:layout_height="wrap_content"
             android:layout_marginBottom="4dp"
             android:layout_marginLeft="8dp"
             android:layout_marginStart="8dp"
             android:layout_marginRight="8dp"
             android:layout_marginEnd="8dp"
             android:layout_marginTop="4dp"
             card_view:cardCornerRadius="4dp">
       
           <LinearLayout
               android:orientation="horizontal"
               android:layout_width="match_parent"
               android:layout_height="wrap_content"
               android:padding="10dp">
       
             <ImageView
                 android:id="@+id/logo"
                 android:layout_width="50dp"
                 android:layout_height="50dp"
                 tools:src="@drawable/ic_launcher" />
       
             <LinearLayout
                 android:layout_width="match_parent"
                 android:layout_height="wrap_content"
                 android:orientation="vertical"
                 android:layout_marginLeft="10dp"
                 android:layout_marginStart="10dp">
       
               <TextView
                   android:id="@+id/name"
                   android:layout_width="wrap_content"
                   android:layout_height="wrap_content"
                   tools:text="Borrusia Dortmund"
                   android:textColor="@android:color/black"
                   android:textSize="16sp"
                   android:textStyle="bold" />
       
               <TextView
                   android:id="@+id/shortName"
                   android:layout_width="wrap_content"
                   android:layout_height="wrap_content"
                   tools:text="Borrusia Dortmund"
                   android:textColor="@android:color/black"
                   android:textSize="14sp" />
       
             </LinearLayout>
           </LinearLayout>
         </android.support.v7.widget.CardView>
       </LinearLayout>
