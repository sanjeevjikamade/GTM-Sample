package com.sanjeev.gtmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SuccessPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_page);

        String tagID = getIntent().getStringExtra("tag_id");
        String uid = getIntent().getStringExtra("uid");

        TextView tvUID = findViewById(R.id.tv_uid);
        TextView tvWebViewData = findViewById(R.id.tv_wv_ga_data);

        tvUID.setText("Value: "+uid);
        tvWebViewData.setText("Value: "+tagID);

    }
}
