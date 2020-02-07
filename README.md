# GTM-Sample
## Google Tag manager integration:

Sample code demonstrate GTM integration with firebase.
On Launch Webview button firebase event is being triggered

Also if There is any event inside webview URL. If it occured then can be captured by JavaScriptInterface.

### Code Snippet:

```
// This code communicate with Webview analytics elements.
    public class AnalyticsWebInterface {

        public static final String TAG = "AnalyticsWebInterface";
        private FirebaseAnalytics mAnalytics;

        public AnalyticsWebInterface(Context context) {
            mAnalytics = FirebaseAnalytics.getInstance(context);
        }

        @JavascriptInterface
        public void logEvent(String name, String jsonParams) {
            LOGD("logEvent:" + name);
           // mAnalytics.logEvent(name, bundleFromJson(jsonParams));
        }

        @JavascriptInterface
        public void setUserProperty(String name, String value) {
            LOGD("setUserProperty:" + name);
            mAnalytics.setUserProperty(name, value);
        }

        private void LOGD(String message) {
            // Only log on debug builds, for privacy
            if (BuildConfig.DEBUG) {
                Log.d(TAG, message);
            }
        }
```

For more details Refer: https://firebase.google.com/docs/analytics/webview?platform=android


For this integration we need to add code to web-page as below:

````
// Following code Call Android interface
    window.AnalyticsWebInterface.setUserProperty(name, value);
    
````



