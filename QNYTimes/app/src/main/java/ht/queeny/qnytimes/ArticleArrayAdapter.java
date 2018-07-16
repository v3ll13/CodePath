package ht.queeny.qnytimes;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by root on 7/15/18.
 */

public class ArticleArrayAdapter extends ArrayAdapter<Article> {

    public  ArticleArrayAdapter(Context context , List<Article> articles){
        super(context, android.R.layout.simple_list_item_1, articles);

    }

        public View getView(int position , View convertview, ViewGroup parent){
           //get Data item for position
            Article article = this.getItem(position);

            //if  existing view being reused
            if (convertview == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertview = inflater.inflate(R.layout.q_item_articles_results, parent, false);

                //find the image view

                ImageView imageView = convertview.findViewById(R.id.imageV);

                //Clear out  recycled image
                imageView.setImageResource(0);


                TextView txtTitle = (TextView) convertview.findViewById(R.id.q_txt_view);
                txtTitle.setText(article.getHeadLine());

                //populate the  thumbnail image
                //remote download the image in the bg

                String thumbnail = article.getThumbnail();

                if (!TextUtils.isEmpty(thumbnail));
                Picasso.with(getContext()).load(thumbnail).into(imageView);


            }
            return convertview;
        }

    }
