package com.example.carl.urlrooter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Carl on 2017/1/19.
 */
public class TestActivity extends Activity {
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        text = (TextView) findViewById(R.id.text);
    }
}
