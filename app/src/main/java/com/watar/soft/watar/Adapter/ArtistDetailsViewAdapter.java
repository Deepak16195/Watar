package com.watar.soft.watar.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.watar.soft.watar.R;

import java.util.List;

public class ArtistDetailsViewAdapter extends RecyclerView.Adapter<ArtistDetailsViewAdapter.ArtistDetailsMyView> {


    private List<String> list;

    public class ArtistDetailsMyView extends RecyclerView.ViewHolder {

        public TextView textView;

        public ArtistDetailsMyView(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.textview1);

        }
    }


    public ArtistDetailsViewAdapter(List<String> horizontalList) {
        this.list = horizontalList;
    }

    @Override
    public ArtistDetailsMyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_watar_top, parent, false);

        return new ArtistDetailsMyView(itemView);
    }

    @Override
    public void onBindViewHolder(final ArtistDetailsMyView holder, final int position) {

        holder.textView.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
