
WorkForce Application 


This is an Employee data application, designed for merchants who use the square POS. 

Features and specifications

- The app displays a list of employees. The data is fetched through a REST API call to the given URL. 
- The call is made using square’s open source Retrofit, which returns a JSON. 
-  A JSON is serialized into the DataModel POJO Employees, which has a list of the object type Employee. NOTE - An empty list is created in the dummy constructor by default, so that Employees.getEmployees is never null. 
- The JSON is serialized with the help of a custom GSONConvertor class defined in the Retrofit library. 
- There is a Repository class EmployeesRepository, which is a singleton class. This is the repository for the application. It is a singleton because, multiple instances of the repository can lead to dirty reads and dirty writes. 
- The view model’s constructor instantiates the single instance of the repository and makes the service call to fetch the data and the repository returns the value as a mutable object. The view model sets this mutable object as the source to its mediator mutable object. 
- The view model also sorts the list by comparing the names of the employees. 
- The view model has another mediator live data that tracks the four possible states of the data - LOADING, LOADED, ERROR and EMPTY. The repository sets this value to LOADING, before starting the API call. 
- The mutable data is then set to LOADED, EMPTY or ERROR in the callback, depending on the response. 
- The UI screens are updated accordingly. If the data is LOADING, then a progress bar is displayed. 
- When LOADED successfully, the adapter is loaded and the recyclerView with the employee card views is displayed. 
- When the data is EMPTY, a layout with an image indicating that there are no employees is displayed
- When the fetch failed, an UI screen with an image view indicating that there was an error is displayed. 
- To load the images to the imageView, Picasso, another squares open source library is used. 
- Picasso caches the images and in the callback, if the offline fetch failed, the app tries again using internet, if that fails too, the issue is logged and a placeholder image is displayed. 
- The placeholder image is also displayed when the images are loading. 
- The API is called once every time the app is launched. 
- The app also uses Google’s data binding, to bind views to the data, This way, when changes to the data are posted the views will directly pick it up without the code having to specify it every time a new value is posted. 

Future Enhancements

This application at it’s current state has a wide range of possibilities for enhancements - 

1. Feature Enhancements - 

- Implement a detail screen
- Implement a search or a way to filter the employees
- Update or Edit employee details. 
- Enlarge employee image

2. Architecture and design -

- Unless the growth rate in size of the business is exponential, the employees data is not going to change on a regular basis. 
Unlike transactions, employee details need not be updated very often and the number times the API is called can be reduced by storing the json blobs in a local db and table on the device. 
The data can be synced with the server, by making sure that the server forces a sync  to the respective merchants’ devices if and when the data is updated. 

3. Testing - 

- Testing is one of the most vital parts of any software. The application can have more test coverage, with more effort and time. 

Gradle options and Dependencies

Following are the Gradle options and dependencies used - 
- used Gradle wrapper gradle version 6.1.1
- Compile sdk version - 29
- Target sdk - 29
- Java version 8
- Min sdk - 26
- Code shrinker - uses the built-in shrinker.
- ProGuard - disabled. 
- Lobok for getter and setter in compile time
- Retrofit for REST API calls
- Picasso to fetch, load and cache images
- Constraint layout and recycler view to populate UI
- Robelectric for testing purpose