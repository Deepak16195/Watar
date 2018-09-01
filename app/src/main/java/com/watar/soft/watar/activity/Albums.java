package com.watar.soft.watar.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.watar.soft.watar.Adapter.GeneresAlbumsAdapter;
import com.watar.soft.watar.Model.GenresAlbum;
import com.watar.soft.watar.R;
import com.watar.soft.watar.Utility.Utils;
import com.watar.soft.watar.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Albums extends AppCompatActivity {

    protected TextView toolbarTitle;
    protected RecyclerView recyclerview;
    protected TextView txtName;
    protected TextView totalTrack;
    protected TextView playall;
    protected ImageView drable;
    protected Toolbar toolbarnew;
    protected ImageView imageAlbum;
    List<GenresAlbum.TracksEntity> mAlbum = new ArrayList<>();
    GeneresAlbumsAdapter albumsAdapter;
    String AlbumName = "", GenerName = "";
    int AlbumID = 0, Tracks = 0;
    String imageURL = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.fragment_waterclick);
        if (getIntent().getStringExtra("AlbumName") != null) {
            AlbumName = getIntent().getStringExtra("AlbumName").toString();
        }
        View view = new View(getApplication());
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplication());
        String lang = settings.getString("LANG", "");
        if( lang.equals("ar"))
        {
            view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        else
        {
            view.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }


        if (getIntent().getStringExtra("GenerName") != null) {
            GenerName = getIntent().getStringExtra("GenerName").toString();
        }
        AlbumID = getIntent().getIntExtra("AlbumID", 0);
        Tracks = getIntent().getIntExtra("Tracks", 0);

        /*if (getIntent().getStringExtra("ImageURL") != null) {
            imageURL = getIntent().getStringExtra("ImageURL").toString();
            Glide.with(Albums.this).load(imageURL).error(R.drawable.albumbg1).fitCenter().placeholder(R.drawable.albumbg1).into(imageAlbum);
        }else {
            imageURL="";
        }*/

        initView();

        if (getIntent().getStringExtra("ImageURL") != null) {
            imageURL = getIntent().getStringExtra("ImageURL").toString();
            Glide.with(Albums.this).load(imageURL).error(R.drawable.albumbg1).fitCenter().placeholder(R.drawable.albumbg1).into(imageAlbum);
        }else {
            imageURL="";
        }
        getAlbum(AlbumID);
    }

    private void initView() {
        txtName = (TextView) findViewById(R.id.txt_name);
        totalTrack = (TextView) findViewById(R.id.totalTrack);
        playall = (TextView) findViewById(R.id.playall);
        recyclerview = (RecyclerView) findViewById(R.id.albumsplay_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Albums.this);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        drable = (ImageView) findViewById(R.id.drable);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText(GenerName);
        txtName.setText(AlbumName);
        totalTrack.setText(Tracks + " Songs");
        toolbarnew = (Toolbar) findViewById(R.id.toolbarnew);
        imageAlbum = (ImageView) findViewById(R.id.imageAlbum);
    }

    //Check User Login
    private void getAlbum(int albumID) {
        Utils.showProgressDialog(Albums.this);

        ApiUtils.getAPIService().getAlbumByID(ApiUtils.Key, "albums/" + String.valueOf(albumID)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GenresAlbum>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        albumsAdapter = new GeneresAlbumsAdapter(Albums.this, mAlbum,imageURL);
                        recyclerview.setAdapter(albumsAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(Albums.this, e.getMessage());

                    }

                    @Override
                    public void onNext(GenresAlbum userModel) {
                        if (userModel.getSuccess()) {
                            if (userModel.getData().size() > 0) {
                                if (userModel.getData().size() > 0) {
                                    imageURL = userModel.getData().get(0).getAlbum().getImage();

                                    mAlbum = userModel.getData().get(0).getAlbum().getTracks();
                                }

                            }
                            Utils.custoAlert(Albums.this, userModel.getMessage());
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(Albums.this, userModel.getMessage());
                        }

                    }
                });

    }


}
