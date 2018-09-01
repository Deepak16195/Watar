package com.watar.soft.watar.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.watar.soft.watar.Model.TrendingTracks;
import com.watar.soft.watar.R;

import java.util.List;

public class WatarRecyclerViewAdapter extends RecyclerView.Adapter<WatarRecyclerViewAdapter.ProductViewHolder> {
    List<TrendingTracks.TracksEntity> mTracks;

    Context context;
    String Menu;

    public WatarRecyclerViewAdapter(Context mContext, List<TrendingTracks.TracksEntity> aGenres) {
        this.mTracks = aGenres;
        this.context = mContext;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_watar_top, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        TrendingTracks.TracksEntity tracks = mTracks.get(position);

        holder.txtSongName.setText(tracks.getName()+"");
        holder.txtAlbumName.setText(tracks.getAlbum_name()+"");
        holder.txtArtistName.setText(tracks.getAlbum_name()+"");
        Glide.with(context).load(mTracks.get(position).getAlbum().getImage().toString()).into(holder.itemImage);






      /*  holder.txtDesignNo.setText(product.getDesignNo()+" "+ product.getProductName());
        holder.txtSizeChart.setText(product.getDetails());
        if(Menu.equalsIgnoreCase("Product"))
        {
        holder.viewSizeChart.setText("Color Chart");
        }
        else
        {
        holder.viewSizeChart.setText("Size Chart");
        }
        File file = new File(product.getURL()) ;
        Uri imageUri = Uri.fromFile(file);
        Glide.with(context).load(imageUri).placeholder(Util.getRandomDrawbleColor()).error(R.drawable.noimage).into(holder.productImage);*/
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView txtSongName, txtAlbumName,txtArtistName;
       ImageView itemImage;

        public ProductViewHolder(View itemView) {
            super(itemView);
            //itemView.setClickable(true);

            txtSongName = itemView.findViewById(R.id.txtSongName);
            txtAlbumName = itemView.findViewById(R.id.txtAlbumName);
            txtArtistName = itemView.findViewById(R.id.txtArtistName);
            itemImage= itemView.findViewById(R.id.itemImage);


        }

    }

}
