package com.example.news.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.news.R;
import com.example.news.Utils.Utils;

public class WebViewActivity extends AppCompatActivity {
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView mywebview = (WebView) findViewById(R.id.webView);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString(Utils.URL);
//        final Activity activity = this;
        progressDialog = new ProgressDialog(this);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progressDialog.setCancelable(false);
//        mywebview.setWebViewClient(new WebViewClient() {
//            public void onProgressChanged(WebView view, int progress) {
//                progressDialog.show();
//                progressDialog.setProgress(0);
//                activity.setProgress(progress * 1000);
//                progressDialog.incrementProgressBy(progress);
//                if (progress == 100 && progressDialog.isShowing())
//                    progressDialog.dismiss();
//            }
//        });

        progressDialog.setMessage("Please wait Loading...");
        progressDialog.show();
        mywebview.setWebViewClient(new MyWebViewClient());
        mywebview.loadUrl(url);

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            System.out.println("on finish");
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }
    }
}
