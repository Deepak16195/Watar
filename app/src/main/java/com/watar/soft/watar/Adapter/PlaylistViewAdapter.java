package com.watar.soft.watar.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.watar.soft.watar.R;

import java.util.List;
public class PlaylistViewAdapter extends RecyclerView.Adapter<PlaylistViewAdapter.PlaylistMyView> {

    private List<String> list;

    public class PlaylistMyView extends RecyclerView.ViewHolder {

        public TextView textView;

        public PlaylistMyView(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.textview1);

        }
    }


    public PlaylistViewAdapter(List<String> horizontalList) {
        this.list = horizontalList;
    }

    @Override
    public PlaylistMyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_album_secand, parent, false);

        return new PlaylistMyView(itemView);
    }

    @Override
    public void onBindViewHolder(final PlaylistMyView holder, final int position) {

        holder.textView.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

