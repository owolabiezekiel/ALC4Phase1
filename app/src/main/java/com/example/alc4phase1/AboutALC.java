package com.example.alc4phase1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AboutALC extends AppCompatActivity {

    private WebView aboutALC;
    String url = "https://twitter.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle("About ALC");
        setContentView(R.layout.activity_about_alc);


        aboutALC = (WebView) findViewById(R.id.about_alc_webview);
        aboutALC.setWebViewClient(new MyBrowser());

        if(isNetworkAvailable()){
            Toast.makeText(this, "Loading page, please wait...", Toast.LENGTH_LONG).show();
            aboutALC.getSettings().setLoadsImagesAutomatically(true);
            aboutALC.getSettings().setJavaScriptEnabled(true);
            aboutALC.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            aboutALC.loadUrl(url);
        }
        else{
            Toast.makeText(this, R.string.no_internt_access, Toast.LENGTH_LONG).show();
        }
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}





