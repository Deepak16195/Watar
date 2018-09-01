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
import com.watar.soft.watar.Model.Genres;
import com.watar.soft.watar.R;
import com.watar.soft.watar.activity.Albums;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by user on 04-08-2018.
 */

public class GenresItemAdapter extends RecyclerView.Adapter<GenresItemAdapter.ProductViewHolder> {
    List<Genres.AlbumsEntity> mAlbums=new ArrayList<>();
    int mPosition;

    Context context;
    String Menu;
//, int aPosition
    public GenresItemAdapter(Context mContext, List<Genres.AlbumsEntity> aAlbum) {
        this.mAlbums = aAlbum;
        this.context = mContext;
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


            holder.txtSongName.setText(mAlbums.get(mPosition).getName()+"");
            holder.txtTrackCount.setText(mAlbums.get(position).getTracks().size()+" Tracks");
            Glide.with(context).load(mAlbums.get(position).getImage().toString()).into(holder.imageAlbum);
            if (mAlbums.get(position).getArtist().getName()!=null) {
                holder.txtArtistName.setText(mAlbums.get(position).getArtist().getName()+"");
            }







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
        return (null != mAlbums ? mAlbums.size() : 0);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView txtSongName, txtTrackCount, txtArtistName;
        ImageView imageAlbum;


        public ProductViewHolder(View itemView) {
            super(itemView);
            //itemView.setClickable(true);

            txtSongName = itemView.findViewById(R.id.txtSongName);
            txtTrackCount = itemView.findViewById(R.id.txtTrackCount);
            txtArtistName = itemView.findViewById(R.id.txtArtistName);
            imageAlbum = itemView.findViewById(R.id.imageAlbum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Genres.AlbumsEntity albumsEntity = mAlbums.get(getAdapterPosition());
                    Intent mIntent = new Intent(context, Albums.class);
                    mIntent.putExtra("AlbumName", albumsEntity.getName());
                    mIntent.putExtra("GenerName", Menu);
                    mIntent.putExtra("AlbumID", albumsEntity.getId());
                    mIntent.putExtra("Tracks", albumsEntity.getTracks().size());
                    mIntent.putExtra("ImageURL", albumsEntity.getImage());
                    context.startActivity(mIntent);
                }
            });

       /* txtDesignNo = itemView.findViewById(R.id.txtDesignNo);
        productImage = itemView.findViewById(R.id.productImage);
        txtSizeChart = itemView.findViewById(R.id.txtSizeChart);
        viewSizeChart= itemView.findViewById(R.id.viewSizeChart);
        btnDownload=itemView.findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Product productPojo = mProductList.get(position);

                new DownloadImage(context,productImage,productPojo.getOrgURL(),productPojo.getURL()).execute();
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = getAdapterPosition();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("data", mProductList);
                intent.putExtra("pos", position);
                context.startActivity(intent);
            }
        });*/
        }

    }

}

