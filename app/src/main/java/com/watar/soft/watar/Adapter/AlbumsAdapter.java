package com.watar.soft.watar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.watar.soft.watar.Model.GenresByName;
import com.watar.soft.watar.R;
import com.watar.soft.watar.activity.Albums;

import java.util.ArrayList;
import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ProductViewHolder> {
    List<GenresByName.AlbumsEntity> mAlbums = new ArrayList<>();
    int mPosition;

    Context context;
    String Menu;

    //, int aPosition
    public AlbumsAdapter(Context mContext, List<GenresByName.AlbumsEntity> aAlbum, String aMenu) {
        this.mAlbums = aAlbum;
        this.context = mContext;
        this.Menu = aMenu;
        //this.mPosition = aPosition;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_album_secand, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        holder.txtSongName.setText(mAlbums.get(mPosition).getName() + "");
        holder.txtTrackCount.setText(mAlbums.get(position).getTracks().size() + " Tracks");
        Glide.with(context).load(mAlbums.get(position).getImage().toString()).into(holder.imageAlbum);
        if (mAlbums.get(position).getArtist().getName() != null) {
            holder.txtArtistName.setText(mAlbums.get(position).getArtist().getName() + "");
        }

    }

    @Override
    public int getItemCount() {
        return (null != mAlbums ? mAlbums.size() : 0);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView txtSongName, txtTrackCount, txtArtistName;
        ImageView imageAlbum;


        public ProductViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            txtSongName = itemView.findViewById(R.id.txtSongName);
            txtTrackCount = itemView.findViewById(R.id.txtTrackCount);
            txtArtistName = itemView.findViewById(R.id.txtArtistName);
            imageAlbum = itemView.findViewById(R.id.imageAlbum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GenresByName.AlbumsEntity albumsEntity = mAlbums.get(getAdapterPosition());
                    Intent mIntent = new Intent(context, Albums.class);
                    mIntent.putExtra("AlbumName", albumsEntity.getName());
                    mIntent.putExtra("GenerName", Menu);
                    mIntent.putExtra("AlbumID", albumsEntity.getId());
                    mIntent.putExtra("Tracks", albumsEntity.getTracks().size());
                    mIntent.putExtra("ImageURL", albumsEntity.getImage());
                    context.startActivity(mIntent);
                }
            });

        }

    }

}