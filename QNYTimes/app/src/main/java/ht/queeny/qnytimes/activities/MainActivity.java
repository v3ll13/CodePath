package ht.queeny.qnytimes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import ht.queeny.qnytimes.Article;
import ht.queeny.qnytimes.ArticleArrayAdapter;
import ht.queeny.qnytimes.R;

public class MainActivity extends AppCompatActivity {
        EditText qnyEdit_txt;
        GridView qnyGrid_rslt;
        Button qnyBtn_srch;
        ArrayList<Article> articles;
        ArticleArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
        public void  setViews(){
                qnyEdit_txt = (EditText) findViewById(R.id.plain_txt_srch);
                qnyGrid_rslt= (GridView) findViewById(R.id.grid_item);
                qnyBtn_srch = (Button) findViewById(R.id.btn_srch);
                articles= new ArrayList<>();
                adapter = new ArticleArrayAdapter(this, articles);
                qnyGrid_rslt.setAdapter(adapter);

                //listener
                qnyGrid_rslt.setOnItemClickListener( new AdapterView.OnItemClickListener(){

                    public void onItemClick(AdapterView<?> parent, View view,  int position,  long id){

                        //intent

                        Intent i =  new Intent(getApplicationContext(), ArticleActivity.class);

                        //get Article to display

                        Article article = articles.get(position);

                        //pass article into intent
                        i.putExtra("articles", article);

                        //start activity

                        startActivity(i);
                    }
                });
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSearch(View view) {
        String query = qnyEdit_txt.getText().toString();
        //Toast.makeText(this, "Searching for.. " + query , Toast.LENGTH_LONG).show();
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";

        RequestParams params = new RequestParams();
        params.put("api-key", "1cf62f1a6b0a4324b63cc333523c8a2e");
        params.put("page", "0");
        params.put("q", query);

        asyncHttpClient.get(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", response.toString());
                JSONArray articleresutls = null;

                try {

                    articleresutls = response.getJSONObject("response").getJSONArray("docs");
                    adapter.addAll(Article.fromJsonArray((articleresutls)));
                    Log.d("DEBUG", articleresutls.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
