package com.lfbl.gymglishsites;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Luiz F. Lazzarin on 10/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class WebsiteDetailActivity extends Activity {

    public static final String EXTRA_WEBSITE_URL =
            WebsiteDetailActivity.class.getSimpleName() + "extraWebsiteUrl";

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_detail);

        final ProgressDialog progressDialog = ProgressDialog.show(this, "", "Loading...",true);

        mWebView = (WebView) findViewById(R.id.web);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDomStorageEnabled(true);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissDialog();
                    }
                }, 5000);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                dismissDialog();
            }

            private void dismissDialog(){
                if(progressDialog != null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
        });

        mWebView.loadUrl("http://" + getIntent().getStringExtra(EXTRA_WEBSITE_URL));
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }

        super.onBackPressed();
    }
}
