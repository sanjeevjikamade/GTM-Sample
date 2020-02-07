package com.sanjeev.gtmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mFirebaseAnalytics.setUserProperty("custID","GTMsanjeev");


        Button btnLaunchWV = findViewById(R.id.btnLaunchWV);

        btnLaunchWV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logFBEvent();

                Intent i = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(i);
               // finish();

            }
        });
    }


    void logFBEvent(){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "buttontapopenwv");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}


git add assets/containers/gtm-pqqfwj3_v2.json
        git add java/com/sanjeev/gtmtest/MainActivity.java
        git add java/com/sanjeev/gtmtest/WebViewActivity.java