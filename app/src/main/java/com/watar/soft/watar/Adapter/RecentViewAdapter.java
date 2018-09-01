package com.watar.soft.watar.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.watar.soft.watar.R;

import java.util.List;

public class RecentViewAdapter extends RecyclerView.Adapter<RecentViewAdapter.RecentMyView> {

    private List<String> list;

    public class RecentMyView extends RecyclerView.ViewHolder {

        public TextView textView;

        public RecentMyView(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.textview1);

        }
    }


    public RecentViewAdapter(List<String> horizontalList) {
        this.list = horizontalList;
    }

    @Override
    public RecentMyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vartical_recent, parent, false);

        return new RecentMyView(itemView);
    }

    @Override
    public void onBindViewHolder(final RecentMyView holder, final int position) {

        holder.textView.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}