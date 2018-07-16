package ht.queeny.qnytimes.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ht.queeny.qnytimes.Article;
import ht.queeny.qnytimes.R;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Article Article =  (Article) getIntent().getSerializableExtra("article");

        WebView webView = (WebView) findViewById(R.id.web_view);

        webView.setWebViewClient( new WebViewClient(){

            public boolean  showOverideurl( WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });



    }

}
