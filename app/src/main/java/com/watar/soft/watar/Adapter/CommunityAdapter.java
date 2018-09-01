package com.watar.soft.watar.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.watar.soft.watar.Model.Community;
import com.watar.soft.watar.R;

import java.util.List;

/**
 * Created by user on 08-08-2018.
 */

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ProductViewHolder> {
    List<Community.PlaylistsEntity> mPlaylist;

    Context context;
    String Menu;

    public CommunityAdapter(Context mContext, List<Community.PlaylistsEntity> aPlaylist) {
        this.mPlaylist = aPlaylist;
        this.context = mContext;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.community_row, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        Community.PlaylistsEntity playlists = mPlaylist.get(position);
        holder.txtName.setText(playlists.getName());
        holder.txtDetails.setText(playlists.getDescription());
    }

    @Override
    public int getItemCount() {
        return mPlaylist.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName, txtDetails;

        public ProductViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtDetails = itemView.findViewById(R.id.txtDetails);

        }

    }

}

