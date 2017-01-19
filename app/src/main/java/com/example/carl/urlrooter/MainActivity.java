package com.example.carl.urlrooter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.carl.urlrooter.rooter.Url;
import com.example.carl.urlrooter.rooter.UrlRouter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UrlRouter.init(this);
    }

    public void jump(View view) {
        UrlRouter.toUrl(this, Url.getUrl("test"));
    }

}
