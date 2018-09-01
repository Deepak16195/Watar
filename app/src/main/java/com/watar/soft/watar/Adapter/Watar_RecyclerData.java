package com.watar.soft.watar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.watar.soft.watar.Model.Genres;
import com.watar.soft.watar.R;
import com.watar.soft.watar.activity.AlbumsActivity;

import java.util.List;

/**
 * Created by user on 04-08-2018.
 */

public class Watar_RecyclerData extends RecyclerView.Adapter<Watar_RecyclerData.ProductViewHolder> {
    List<Genres.GenreEntity> mGenres;

    Context context;
    String Menu;

    public Watar_RecyclerData(Context mContext, List<Genres.GenreEntity> aGenres) {
        this.mGenres = aGenres;
        this.context = mContext;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.geners_list, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Watar_RecyclerData.ProductViewHolder holder, int position) {
        Genres.GenreEntity genres = mGenres.get(position);

            holder.viewGenerName.setText(genres.getName());
           /* List list = genres.getGenre().get(i).getAlbums();*/
            GenresItemAdapter genresItemAdapter = new GenresItemAdapter(context,genres.getAlbums());
            holder.recyclerview2.setHasFixedSize(true);
            holder.recyclerview2.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            holder.recyclerview2.setAdapter(genresItemAdapter);
            holder.recyclerview2.setNestedScrollingEnabled(false);







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
        return mGenres.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView viewGenerName, viewallalbums;
        public RecyclerView recyclerview2;

        public ProductViewHolder(View itemView) {
            super(itemView);
            //itemView.setClickable(true);

            viewGenerName = itemView.findViewById(R.id.viewGenerName);
            viewallalbums = itemView.findViewById(R.id.viewallalbums);
            recyclerview2 = itemView.findViewById(R.id.recyclerview2);

            viewallalbums.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Genres.GenreEntity genres = mGenres.get(position);
                    Intent mIntent=new Intent(context, AlbumsActivity.class);
                    mIntent.putExtra("GenersName", genres.getName());
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
