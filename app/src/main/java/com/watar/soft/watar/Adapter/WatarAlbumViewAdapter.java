package com.watar.soft.watar.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.watar.soft.watar.R;

import java.util.List;

public class WatarAlbumViewAdapter  extends RecyclerView.Adapter<WatarAlbumViewAdapter.MyViewalbum>{



    private List<String> list;

    public class MyViewalbum extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyViewalbum(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.textview1);

        }
    }


    public WatarAlbumViewAdapter(List<String> horizontalList) {
        this.list = horizontalList;
    }

    @Override
    public MyViewalbum onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_album_secand, parent, false);

        return new MyViewalbum(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewalbum holder, final int position) {

        holder.textView.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
