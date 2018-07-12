package e.vellie.queenymovie.models;

import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {

    String posterPatth;
    String OriginalTitle;
    String Overview;
    String backdrop;

    public String getPosterPatth() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPatth);
    }

    public String getOriginaklTitle() {
        return OriginalTitle;
    }

    public String getOverview() {
        return Overview;
    }

    public String getBackdrop() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdrop);
    }



    public Movie(JSONObject jsonObject) throws JSONException{
        this.posterPatth= jsonObject.getString("Poster_Path");
        this.backdrop = jsonObject.getString("backdrop_path");
        this.OriginalTitle= jsonObject.getString("original_title");
        this.Overview= jsonObject.getString("overview");

    }

    public static ArrayList<Movie> fromJsonArray(JSONArray array){
        ArrayList<Movie> results = new ArrayList<>();

        for (int x =0; x< array.length(); x++){
            try {
                results.add(new Movie(array.getJSONObject(x)) );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
            return results;
    }
}
