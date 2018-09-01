package com.watar.soft.watar.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.watar.soft.watar.Adapter.AlbumsAdapter;
import com.watar.soft.watar.Model.GenresByName;
import com.watar.soft.watar.R;
import com.watar.soft.watar.Utility.Utils;
import com.watar.soft.watar.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AlbumsActivity extends AppCompatActivity {

    /*    ArrayList<String> allSampleData;
        ArrayList<String> arrayLists;
        int i;*/
    public static String GenersName = "";
    protected TextView toolbarTitle;
    protected RecyclerView recyclerview;
    List<GenresByName.AlbumsEntity> mAlbum = new ArrayList<>();
    AlbumsAdapter albumsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        super.setContentView(R.layout.activity_albums);
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


        if (getIntent().getStringExtra("GenersName") != null) {
            GenersName = getIntent().getStringExtra("GenersName");
        }
        initView();
        getGenerByName();
//        ((RecyclerView) findViewById(R.id.trendings_recycler_view)).setHasFixedSize(true);
//        RecyclerView albums_recycler_view = (RecyclerView) findViewById(R.id.albums_recycler_view);
//        albums_recycler_view.setHasFixedSize(true);
//        this.allSampleData = new ArrayList();
//        this.arrayLists = new ArrayList();
//        for (i = 0; i < 10; i++) {
//            ArrayList arrayList = this.allSampleData;
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("sdasd");
//            stringBuilder.append(i);
//            arrayList.add(stringBuilder.toString());
//        }
//        for (i = 0; i < 10; i++) {
//            this.arrayLists.add("i");
//        }
//        RecyclerViewDataAlbumsAdapter adapter1 = new RecyclerViewDataAlbumsAdapter(this, this.allSampleData, this.arrayLists);
//        albums_recycler_view.setLayoutManager(new LinearLayoutManager(this, 1, false));
//        albums_recycler_view.setAdapter(adapter1);

    }


    //Check User Login
    private void getGenerByName() {
        Utils.showProgressDialog(AlbumsActivity.this);

        ApiUtils.getAPIService().getGenerByName(ApiUtils.Key, "genres/"+ GenersName +"/artists").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GenresByName>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                       albumsAdapter = new AlbumsAdapter(AlbumsActivity.this,mAlbum, GenersName);
                        recyclerview.setAdapter(albumsAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(AlbumsActivity.this, e.getMessage());

                    }

                    @Override
                    public void onNext(GenresByName userModel) {
                        if (userModel.getSuccess()) {
                            if (userModel.getData().size() > 0) {
                                if (userModel.getData().get(0).getGenre().size() > 0) {
                                    mAlbum = userModel.getData().get(0).getGenre().get(0).getAlbums();
                                }

                            }
                            Utils.custoAlert(AlbumsActivity.this, userModel.getMessage());
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(AlbumsActivity.this, userModel.getMessage());
                        }

                    }
                });

    }

    private void initView() {
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText(GenersName);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(AlbumsActivity.this, 3);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
    }
}
