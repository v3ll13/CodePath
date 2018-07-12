package e.vellie.queenymovie;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import e.vellie.queenymovie.adapters.MovieArrayAdapter;
import e.vellie.queenymovie.models.Movie;

public class FlickstarMov extends AppCompatActivity {
    ArrayList<Movie> movies;
    MovieArrayAdapter movieArrayAdapter;
    ListView lvitems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickstar_mov);

        lvitems=(ListView) findViewById(R.id.list_item);
        movies = new ArrayList<>();
        movieArrayAdapter = new MovieArrayAdapter( this , movies);
        lvitems.setAdapter(movieArrayAdapter);

        String url="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.get(url, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
               JSONArray movieJsonfilm = null;
                try {
                    movieJsonfilm = response.getJSONArray("results");
                    movies.addAll( Movie.fromJsonArray(movieJsonfilm));
                    movieArrayAdapter.notifyDataSetChanged();
                    Log.d("DEBUG", movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(FlickstarMov.this, "no internet access", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

}
