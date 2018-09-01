package com.watar.soft.watar.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.watar.soft.watar.Model.GenresAlbum;
import com.watar.soft.watar.R;

import java.util.ArrayList;
import java.util.List;

public class GeneresAlbumsAdapter extends RecyclerView.Adapter<GeneresAlbumsAdapter.ProductViewHolder> {
    List<GenresAlbum.TracksEntity> mAlbums = new ArrayList<>();
    int mPosition;

    Context context;
    String ImageURL;

    //, int aPosition
    public GeneresAlbumsAdapter(Context mContext, List<GenresAlbum.TracksEntity> aAlbum,String aImageURL) {
        this.mAlbums = aAlbum;
        this.context = mContext;
        this.ImageURL=aImageURL;

        //this.mPosition = aPosition;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.watar_album_row, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        holder.txtName.setText(mAlbums.get(position).getName() + "");
        if (mAlbums.get(position).getArtists().size() > 0) {
            holder.txtArtistName.setText(mAlbums.get(position).getArtists().toString());
        }
        holder.txtDuration.setText(mAlbums.get(position).getDuration() + "");

        if(ImageURL!=null)
        {
            Glide.with(context).load(ImageURL).error(R.drawable.albumbg1).fitCenter().placeholder(R.drawable.albumbg1).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return (null != mAlbums ? mAlbums.size() : 0);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName, txtArtistName, txtDuration;
        ImageView imageView, btnPlay;


        public ProductViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            txtName = itemView.findViewById(R.id.txtName);
            txtArtistName = itemView.findViewById(R.id.txtArtistName);
            txtDuration = itemView.findViewById(R.id.txtDuration);
            imageView = itemView.findViewById(R.id.imageView);
            btnPlay = itemView.findViewById(R.id.btnPlay);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GenresByName.AlbumsEntity albumsEntity = mAlbums.get(getAdapterPosition());
                    Intent mIntent = new Intent(context, Albums.class);
                    mIntent.putExtra("AlbumName", albumsEntity.getName());
                    mIntent.putExtra("GenerName", Menu);
                    mIntent.putExtra("AlbumID", albumsEntity.getId());
                    mIntent.putExtra("Tracks", albumsEntity.getTracks().size());
                    context.startActivity(mIntent);
                }
            });*/

        }

    }

}