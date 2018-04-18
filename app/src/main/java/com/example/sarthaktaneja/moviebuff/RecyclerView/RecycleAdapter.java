package com.example.sarthaktaneja.moviebuff.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarthaktaneja.moviebuff.Pojo;
import com.example.sarthaktaneja.moviebuff.Pojo1;
import com.example.sarthaktaneja.moviebuff.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sarthaktaneja on 18/4/18.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.DetailViewHolder> {

    List<Pojo1> results;
    public RecycleAdapter(List<Pojo1> results) {
        this.results=results;
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView rating;
        ImageView movieimg;

        public DetailViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.recyclename);
            rating = (TextView) itemView.findViewById(R.id.recyclerating);
            movieimg = (ImageView) itemView.findViewById(R.id.image);
        }

    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main2, parent, false);

        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        holder.title.setText(results.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
