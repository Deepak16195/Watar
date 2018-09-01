package com.watar.soft.watar.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.watar.soft.watar.Adapter.WatarAlbumViewAdapter;
import com.watar.soft.watar.Adapter.WatarMusicViewAdapter;
import com.watar.soft.watar.Adapter.WatarPoetryViewAdapter;
import com.watar.soft.watar.Adapter.WatarRecyclerViewAdapter;
import com.watar.soft.watar.Adapter.Watar_RecyclerData;
import com.watar.soft.watar.Model.Genres;
import com.watar.soft.watar.Model.TrendingTracks;
import com.watar.soft.watar.R;
import com.watar.soft.watar.Utility.Utils;
import com.watar.soft.watar.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaterFragment extends Fragment {

    RecyclerView recyclerView, recyclerViewData, recyclerview2, recyclerview3, recyclerview4;
    ArrayList<String> Number;
    ArrayList<String> Number2;
    RecyclerView.LayoutManager RecyclerViewLayoutManager, RecyclerViewLayoutManager2, RecyclerViewLayoutManager3, RecyclerViewLayoutManager4;
    LinearLayoutManager HorizontalLayout, HorizontalLayout2, HorizontalLayout3, HorizontalLayout4;
    View ChildView, ChildView2, ChildView3, ChildView4;
    int RecyclerViewItemPosition, RecyclerViewItemPosition2, RecyclerViewItemPosition3, RecyclerViewItemPosition4;
    WatarRecyclerViewAdapter RecyclerViewHorizontalAdapter;
    WatarAlbumViewAdapter RecyclerViewHorizontalAdapter2;
    WatarMusicViewAdapter RecyclerViewHorizontalAdapter3;
    WatarPoetryViewAdapter RecyclerViewHorizontalAdapter4;
    List<Genres.GenreEntity> mGenres = new ArrayList<>();
    List<TrendingTracks.TracksEntity> mTrends = new ArrayList<>();

    View view;
    private TextView viewallalbums;
    Watar_RecyclerData mWatar_recyclerData;


    private Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static WaterFragment newInstance(int index) {
        WaterFragment f = new WaterFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        String lang = settings.getString("LANG", "");
        if( lang.equals("ar"))
        {
            setLangRecreate("ar");
        }
        else
        {
            setLangRecreate("en");
        }

        view = inflater.inflate(R.layout.fragment_water, container, false);

        if( lang.equals("ar"))
        {
            view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        }
        else
        {
            view.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


        }
        this.context = getActivity();

        ButterKnife.bind((Object) this, view);
        click(view);
        recyclerViewData = view.findViewById(R.id.recyclerview);
        initView();
        initRecyclerView();
        getTrendingTracks();
        //getGener();

       /* initRecyclerView2();
        initRecyclerView3();
        initRecyclerView4();*/
        return view;
    }


    public void initRecyclerView() {
        RecyclerViewLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        // Adding items to RecyclerView.
        //AddItemsToRecyclerViewArrayList();
        RecyclerViewHorizontalAdapter = new WatarRecyclerViewAdapter(getActivity(),mTrends);
        HorizontalLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(RecyclerViewHorizontalAdapter);




        // Adding on item click listener to RecyclerView.
     /*   recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
                ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {
                    //Getting clicked value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(ChildView);
                    // Showing clicked item value on screen using toast message.
                    Toast.makeText(getContext(), Number.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();

                    AddMusciDialogFragment addPhotoBottomDialogFragment =
                            AddMusciDialogFragment.newInstance();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    addPhotoBottomDialogFragment.show(transaction, "bottom_music_play");


                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });*/
    }


  /*  public void initRecyclerView2() {
        RecyclerViewLayoutManager2 = new LinearLayoutManager(getContext());
        recyclerview2.setLayoutManager(RecyclerViewLayoutManager2);
        // Adding items to RecyclerView.
        AddItemsToRecyclerViewArrayList2();
        RecyclerViewHorizontalAdapter2 = new WatarAlbumViewAdapter(Number2);
        HorizontalLayout2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerview2.setLayoutManager(HorizontalLayout2);
        recyclerview2.setAdapter(RecyclerViewHorizontalAdapter2);
        // Adding on item click listener to RecyclerView.
        recyclerview2.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
                ChildView2 = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if(ChildView2 != null && gestureDetector.onTouchEvent(motionEvent)) {
                    //Getting clicked value.
                    RecyclerViewItemPosition2 = Recyclerview.getChildAdapterPosition(ChildView2);
                    // Showing clicked item value on screen using toast message.
                    Toast.makeText(getContext(), Number2.get(RecyclerViewItemPosition2), Toast.LENGTH_LONG).show();

                    Fragment fragment2= new WaterFragment();
                    Fragment fragment= new WaterclickFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainfragments, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
                    transaction.addToBackStack(null);  // this will manage backstack
                    transaction.remove(fragment2);
                    transaction.commit();
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
    }

    public void initRecyclerView3() {
        RecyclerViewLayoutManager3 = new LinearLayoutManager(getContext());
        recyclerview3.setLayoutManager(RecyclerViewLayoutManager3);
        // Adding items to RecyclerView.
        AddItemsToRecyclerViewArrayList2();
        RecyclerViewHorizontalAdapter3 = new WatarMusicViewAdapter(Number2);
        HorizontalLayout3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerview3.setLayoutManager(HorizontalLayout3);
        recyclerview3.setAdapter(RecyclerViewHorizontalAdapter3);
        // Adding on item click listener to RecyclerView.
        recyclerview3.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetectors = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
                ChildView3 = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if(ChildView3 != null && gestureDetectors.onTouchEvent(motionEvent)) {
                    //Getting clicked value.
                    RecyclerViewItemPosition3 = Recyclerview.getChildAdapterPosition(ChildView3);
                    // Showing clicked item value on screen using toast message.
                    Toast.makeText(getContext(), Number2.get(RecyclerViewItemPosition3), Toast.LENGTH_LONG).show();
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

    }


    public void initRecyclerView4() {
        RecyclerViewLayoutManager4 = new LinearLayoutManager(getContext());
        recyclerview4.setLayoutManager(RecyclerViewLayoutManager4);
        // Adding items to RecyclerView.
        AddItemsToRecyclerViewArrayList2();
        RecyclerViewHorizontalAdapter4 = new WatarPoetryViewAdapter(Number2);
        HorizontalLayout4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerview4.setLayoutManager(HorizontalLayout4);
        recyclerview4.setAdapter(RecyclerViewHorizontalAdapter4);
        // Adding on item click listener to RecyclerView.
        recyclerview4.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetectors = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
                ChildView4 = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if(ChildView4 != null && gestureDetectors.onTouchEvent(motionEvent)) {
                    //Getting clicked value.
                    RecyclerViewItemPosition4 = Recyclerview.getChildAdapterPosition(ChildView4);
                    // Showing clicked item value on screen using toast message.
                    Toast.makeText(getContext(), Number2.get(RecyclerViewItemPosition4), Toast.LENGTH_LONG).show();



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


    public void click(View v) {

        recyclerView = v.findViewById(R.id.recyclerview1);
        /*recyclerview2 = v.findViewById(R.id.recyclerview2);
        recyclerview3 = v.findViewById(R.id.recyclerview3);
        recyclerview4 = v.findViewById(R.id.recyclerview4);
        viewallalbums = v.findViewById(R.id.viewallalbums);*/

        /*viewallalbums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), Albums.class);
                startActivity(intent);


            }
        });
*/
    }

    private void initView() {

        recyclerViewData.setHasFixedSize(true);
        recyclerViewData.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerViewData.setItemAnimator(new DefaultItemAnimator());
        recyclerViewData.setAdapter(mWatar_recyclerData);

        //initRecyclerView2();
    }


    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }


    //Check User Login
    private void getTrendingTracks() {
        Utils.showProgressDialog(getActivity());

        ApiUtils.getAPIService().getTrending(ApiUtils.Key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TrendingTracks>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        RecyclerViewHorizontalAdapter = new WatarRecyclerViewAdapter(getActivity(),mTrends);
                        recyclerView.setAdapter(RecyclerViewHorizontalAdapter);
                        getGener();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(getActivity(), e.getMessage());
                        }

                    @Override
                    public void onNext(TrendingTracks userModel) {
                        if (userModel.getSuccess()) {
                            if(userModel.getData().size()>0)
                            {
                                mTrends = userModel.getData().get(0).getTracks();
                            }



                               /* if (userModel.getMessage().equalsIgnoreCase("Success")) {
                                    for (int i = 0; i < userModel.getData().size(); i++) {
                                        db.addUserData(userModel.getData().get(i));
                                        Result = "Success";
                                    }
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(Login.this, userModel.getMessage());

                                }*/
                            Utils.custoAlert(getActivity(), userModel.getMessage());
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(getActivity(), userModel.getMessage());
                        }

                    }
                });

    }



    //Check User Login
    private void getGener() {
        Utils.showProgressDialog(getActivity());

        ApiUtils.getAPIService().getGeners(ApiUtils.Key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Genres>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        mWatar_recyclerData = new Watar_RecyclerData(context, mGenres);
                        recyclerViewData.setAdapter(mWatar_recyclerData);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(getActivity(), e.getMessage());

                    }

                    @Override
                    public void onNext(Genres userModel) {
                        if (userModel.getSuccess()) {
                            if(userModel.getData().size()>0)
                            {
                                mGenres = userModel.getData().get(0).getGenre();
                            }



                               /* if (userModel.getMessage().equalsIgnoreCase("Success")) {
                                    for (int i = 0; i < userModel.getData().size(); i++) {
                                        db.addUserData(userModel.getData().get(i));
                                        Result = "Success";
                                    }
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(Login.this, userModel.getMessage());

                                }*/
                            Utils.custoAlert(getActivity(), userModel.getMessage());
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(getActivity(), userModel.getMessage());
                        }

                    }
                });

    }


    public void AddItemsToRecyclerViewArrayList() {

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

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setLangRecreate(String langval) {
        Configuration config = getContext().getResources().getConfiguration();
        Locale locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        TextUtilsCompat.getLayoutDirectionFromLocale(locale);
        getContext().getResources().updateConfiguration(config, getContext().getResources().getDisplayMetrics());
        getContext();

        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            //RTL

        }

//        getActivity().finish();
//        Intent i = getContext().getPackageManager()
//                .getLaunchIntentForPackage( getContext().getPackageName() );
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(i);
    }


    /*public void AddItemsToRecyclerViewArrayList2(){

        Number2 = new ArrayList<>();
        Number2.add("Album name.. ");
        Number2.add("Album name..");
        Number2.add("Album name..");
        Number2.add("Album name..");
        Number2.add("FIVE");
        Number2.add("SIX");
        Number2.add("SEVEN");
        Number2.add("EIGHT");
        Number2.add("NINE");
        Number2.add("TEN");

    }*/

}