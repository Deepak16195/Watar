package com.watar.soft.watar.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.watar.soft.watar.Adapter.ArtistAlbumsAdapter;
import com.watar.soft.watar.Model.ArtsitDetails;
import com.watar.soft.watar.R;
import com.watar.soft.watar.Utility.Utils;
import com.watar.soft.watar.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ArtistDeatils extends AppCompatActivity {

    protected TextView toolbarTitle;
    protected ImageView imageView2;
    protected TextView text1, txtBio;
    protected RecyclerView recyclerview1;
    ArtistAlbumsAdapter albumsAdapter;
    List<ArtsitDetails.AlbumsEntity> mAlbum = new ArrayList<>();
    int ArtistID=0;
    String ArtistName="Artist Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        super.setContentView(R.layout.activity_artist_deatils);
        //click();
        //initRecyclerView();
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


        initView();
        ArtistID=getIntent().getIntExtra("ArtistID",0);
        if(getIntent().getStringExtra("ArtistName")!=null)
        {
            ArtistName=getIntent().getStringExtra("ArtistName").toString();
            toolbarTitle.setText(ArtistName);
        }
        getArtistDetails(ArtistID);

    }

    private void initView() {
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        text1 = (TextView) findViewById(R.id.text1);
        txtBio = (TextView) findViewById(R.id.txtBio);
        recyclerview1 = (RecyclerView) findViewById(R.id.recyclerview1);
        recyclerview1.setHasFixedSize(true);
        recyclerview1.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerview1.setNestedScrollingEnabled(false);
    }

    public void back(View view){
        super.onBackPressed();
    }


    //Check User Login
    private void getArtistDetails(int artistID) {
        Utils.showProgressDialog(ArtistDeatils.this);

        ApiUtils.getAPIService().getArtistDetails(ApiUtils.Key, "artists/" + String.valueOf(artistID)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArtsitDetails>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        if (mAlbum.size() > 0 && mAlbum != null) {
                            albumsAdapter = new ArtistAlbumsAdapter(ArtistDeatils.this, mAlbum);
                            recyclerview1.setAdapter(albumsAdapter);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(ArtistDeatils.this, e.getMessage());

                    }

                    @Override
                    public void onNext(ArtsitDetails userModel) {
                        if (userModel.getSuccess()) {
                            if (userModel.getData().size() > 0) {
                                if (userModel.getData().size() > 0) {

                                    if (userModel.getData().get(0).getArtist().size() > 0) {
                                        txtBio.setText(userModel.getData().get(0).getArtist().get(0).getBio().getBio());
                                        Glide.with(ArtistDeatils.this).load(userModel.getData().get(0).getArtist().get(0).getImage_large()).error(R.drawable.albumbg1).placeholder(R.drawable.albumbg1).fitCenter().into(imageView2);
                                        mAlbum = userModel.getData().get(0).getArtist().get(0).getAlbums();
                                    }

                                }

                            }
                            Utils.custoAlert(ArtistDeatils.this, userModel.getMessage());
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(ArtistDeatils.this, userModel.getMessage());
                        }

                    }
                });

    }

/*
    public void click() {
        recyclerView = findViewById(R.id.recyclerview1);
    }
*/


  /*  public void initRecyclerView() {
        RecyclerViewLayoutManager = new LinearLayoutManager(ArtistDeatils.this);
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        // Adding items to RecyclerView.
        AddItemsToRecyclerViewArrayList();
        RecyclerViewHorizontalAdapter = new ArtistDetailsViewAdapter(Number);
        HorizontalLayout = new LinearLayoutManager(ArtistDeatils.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(RecyclerViewHorizontalAdapter);
        // Adding on item click listener to RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(ArtistDeatils.this, new GestureDetector.SimpleOnGestureListener() {
                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
                ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if(ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {
                    //Getting clicked value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(ChildView);
                    // Showing clicked item value on screen using toast message.
                    Toast.makeText(ArtistDeatils.this, Number.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
//                    Fragment fragment2= new WaterFragment();
//                    Fragment fragment= new WaterclickFragment();
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.mainfragments, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
//                    transaction.addToBackStack(null);  // this will manage backstack
//                    transaction.remove(fragment2);
//                    transaction.commit();
                    // manager.popBackStack();

                }
                return false;
            }
            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });
    }*/
  /*  public void AddItemsToRecyclerViewArrayList(){

        Number = new ArrayList<>();
        Number.add("Song name... ");
        Number.add("Song name...");
        Number.add("Song name...");
        Number.add("Song name...");
        Number.add("FIVE");
        Number.add("SIX");
        Number.add("SEVEN");
        Number.add("EIGHT");
        Number.add("NINE");
        Number.add("TEN");

    }*/
}
