package ht.queeny.qnytimes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by root on 7/15/18.
 */

public class Article implements Serializable {
    public String getWebUrl() {
        return webUrl;
    }

    public String getHeadLine() {
        return headLine;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    String webUrl;
    String headLine;
    String thumbnail;

    public Article(JSONObject jsonObject){

        try {
            this.webUrl= jsonObject.getString("web_url");
            this.headLine= jsonObject.getJSONObject("Headline").getString("main");

            JSONArray multimedia = jsonObject.getJSONArray("multimedia");


            if (multimedia.length()> 0){
                JSONObject multimediaJson =  multimedia.getJSONObject(0);
                this.thumbnail= "http://www.nytimes.com/" + multimediaJson.getString("url");

            }else{
                this.thumbnail = "";
            }
        } catch (JSONException e) {

        }
    }
     public static ArrayList<Article> fromJsonArray( JSONArray array){
            ArrayList<Article> results = new ArrayList<>();

            for (int x= 0; x< array.length(); x++){

                try {
                    results.add(new Article(array.getJSONObject(x)));
                }catch ( JSONException e){
                    e.printStackTrace();
                }
            }
            return results;
     }
}
