package e.vellie.queenymovie.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import e.vellie.queenymovie.R;
import e.vellie.queenymovie.models.Movie;

public class MovieArrayAdapter extends ArrayAdapter <Adapter> {
    private static class ViewHolder {
        ImageView ivimage;
        TextView tvTitle;
        TextView tvOverview;


    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1);

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = (Movie) getItem(position);
        ViewHolder viewHolder = new ViewHolder();

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder.ivimage = (ImageView) convertView.findViewById(R.id.image);


            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.mvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.mvOverview);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivimage.setImageResource(0);

        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPatth()).fit().centerCrop()
                    .placeholder(R.drawable.ic_launcher_background).into(viewHolder.ivimage);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackdrop()).fit().centerCrop()
                    .placeholder(R.drawable.ico1).into(viewHolder.ivimage);

        }
        viewHolder.tvTitle.setText(movie.getOriginaklTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        return convertView;
    }
}