package com.sanjeev.gtmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    String userId = "UID123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mFirebaseAnalytics.setUserProperty("custID",userId);

        TextView tvUID = findViewById(R.id.tv_uid);
        tvUID.setText("value: "+userId);


        Button btnLaunchWV = findViewById(R.id.btnLaunchWV);

        btnLaunchWV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logFBEvent();

                Intent i = new Intent(MainActivity.this, WebViewActivity.class);
                i.putExtra("uid", userId);
                startActivity(i);

                finish();

            }
        });
    }


    void logFBEvent(){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "buttontapopenwv");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}
