package com.watar.soft.watar.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.watar.soft.watar.R;

import java.util.List;

public class WatarPoetryViewAdapter  extends RecyclerView.Adapter<WatarPoetryViewAdapter.MyViewaPoetry>{



    private List<String> list;

    public class MyViewaPoetry extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyViewaPoetry(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.textview1);

        }
    }


    public WatarPoetryViewAdapter(List<String> horizontalList) {
        this.list = horizontalList;
    }

    @Override
    public MyViewaPoetry onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_music_third, parent, false);

        return new MyViewaPoetry(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewaPoetry holder, final int position) {

        holder.textView.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
