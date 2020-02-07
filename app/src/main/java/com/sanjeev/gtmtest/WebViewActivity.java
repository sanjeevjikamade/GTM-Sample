package com.sanjeev.gtmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

public class WebViewActivity extends AppCompatActivity {

    WebView wv;

    String url="https://thebinary.tech/?page_id=159";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view);

        wv=(WebView)findViewById(R.id.wvContent);


        WebSettings webSettings = wv.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);


        wv.setWebViewClient(new MyWebClient());
        wv.setWebContentsDebuggingEnabled(true);
        wv.getSettings().setDomStorageEnabled(true); // Add this
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        // Only add the JavaScriptInterface on API version JELLY_BEAN_MR1 and above, due to
// security concerns, see link below for more information:
// https://developer.android.com/reference/android/webkit/WebView.html#addJavascriptInterface(java.lang.Object,%20java.lang.String)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wv.addJavascriptInterface(
                    new AnalyticsWebInterface(this), AnalyticsWebInterface.TAG);
        } else {
            Log.w("WebViewActivity", "Not adding JavaScriptInterface, API Version: " + Build.VERSION.SDK_INT);
        }

        wv.loadUrl(url);

    }

    class MyWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }

        @Override
        public void onLoadResource(WebView view, String url) {
            Log.d("", "onLoadResource::"+url);

            if(url.toString().contains("www.googletagmanager.com")){
                String url1 = url.replace("https://www.googletagmanager.com/ns.html?id=","");
                Toast.makeText(WebViewActivity.this, "ID::"+url1, Toast.LENGTH_LONG).show();
            }
            super.onLoadResource(view, url);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError er) {
            handler.proceed();
            // Ignore SSL certificate errors
        }

    }



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



    }
}


