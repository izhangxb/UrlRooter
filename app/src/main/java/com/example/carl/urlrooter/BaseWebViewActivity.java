package com.example.carl.urlrooter;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by Carl on 2017/1/19.
 */
public class BaseWebViewActivity extends Activity {
    public static final String WEBVIEW_URL = "WEBVIEW_URL";
    public static final String WEBVIEW_TITLE = "WEBVIEW_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_webview);
        String url = getIntent().getStringExtra(WEBVIEW_URL);

        WebView webView = (WebView) findViewById(R.id.webview);

        webView.loadUrl(url);

    }
}
