package com.watar.soft.watar.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.watar.soft.watar.R;

import java.util.List;

public class ArtistViewAdapter extends RecyclerView.Adapter<ArtistViewAdapter.ArtistMyView> {


    private List<String> list;

    public class ArtistMyView extends RecyclerView.ViewHolder {

        public TextView textView;

        public ArtistMyView(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.textview1);

        }
    }


    public ArtistViewAdapter(List<String> horizontalList) {
        this.list = horizontalList;
    }

    @Override
    public ArtistMyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vartical_artist, parent, false);

        return new ArtistMyView(itemView);
    }

    @Override
    public void onBindViewHolder(final ArtistMyView holder, final int position) {

        holder.textView.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
