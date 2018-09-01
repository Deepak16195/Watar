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
import com.watar.soft.watar.Model.Artist;
import com.watar.soft.watar.R;
import com.watar.soft.watar.activity.ArtistDeatils;

import java.util.ArrayList;
import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ProductViewHolder> {
    List<Artist.ArtistsEntity> mArtist = new ArrayList<>();
    int mPosition;

    Context context;
    String Menu;

    //, int aPosition
    public ArtistAdapter(Context mContext, List<Artist.ArtistsEntity> aArtist) {
        this.mArtist = aArtist;
        this.context = mContext;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vartical_artist, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        holder.textName.setText(mArtist.get(position).getName() + "");
        holder.txtDetails.setText(mArtist.get(position).getAlbumCount() + " Albums, " + mArtist.get(position).getTrackCount() + " Songs");
        Glide.with(context).load(mArtist.get(position).getImage_small().toString()).into(holder.imageArtist);
    }

    @Override
    public int getItemCount() {
        return (null != mArtist ? mArtist.size() : 0);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView textName, txtDetails;
        ImageView imageArtist;


        public ProductViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            textName = itemView.findViewById(R.id.textName);
            txtDetails = itemView.findViewById(R.id.txtDetails);
            imageArtist = itemView.findViewById(R.id.imageArtist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Artist.ArtistsEntity artistsEntity=mArtist.get(getAdapterPosition());
                    Intent mIntent=new Intent(context, ArtistDeatils.class);
                    mIntent.putExtra("ArtistID",artistsEntity.getId());
                    mIntent.putExtra("ArtistName",artistsEntity.getName());
                    context.startActivity(mIntent);
                }
            });
        }

    }

}