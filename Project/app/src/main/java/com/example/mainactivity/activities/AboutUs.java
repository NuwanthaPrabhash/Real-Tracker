package com.example.mainactivity.activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mainactivity.R;

public class AboutUs extends AppCompatActivity {

    WebView webPageView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        webPageView = findViewById(R.id.webpage);
        webPageView.setWebViewClient(new MyBrowser()); // For Open all the web Pages inside the Application
        webPageView.getSettings().setJavaScriptEnabled(true);
        webPageView.loadUrl("http://nsbm.lk/");

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
