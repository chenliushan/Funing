# Funing

This is an online shopping Android demo.

### Functions
- User register, login
- Product display
- Shopping-cart
- Order and order history
- Coupons

### Related tech
- Using gson, retrofit2, okhttp3 to do the HTTP request with robust and Adapt to the RESTful server response. These techniques also help to build a more rational and elegant code structure. By the way, cache is applied here for every HTTP request.
- Picasso here is used to load pictures in these project which use cache automatically to save the traffic.
- The drop-down refresh is implemented with the help of “supperlistview”.
- The main page is set to be a singleTask activity and using the Android Fragment implementation to improve the performance of the app and save resources in the phone.
- AsyncTask is applied here to divide some HTTP request from the main thread to the background which is done asynchronously to save the layout loading time and improve the user experiments. Moreover, these AsyncTasks have been treat carefully to prevent the memory leak.
- SQLLite the android local database is used to store some not privacy sensitive data which is unlikely to be changed frequently. And data synchronization strategy is involved. And the Singleton pattern is applied here to avoid too many database connections at the same time witch will cause memory leak.
- The username and password are kept in the local preference, so the user does not have to input that information many times.
