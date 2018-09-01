package com.watar.soft.watar.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.watar.soft.watar.Adapter.ArtistAdapter;
import com.watar.soft.watar.Model.Artist;
import com.watar.soft.watar.R;
import com.watar.soft.watar.Utility.Utils;
import com.watar.soft.watar.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ArtistFragment extends Fragment {
    protected View rootView;
    protected RecyclerView recyclerview1;
    private Context context;
    List<Artist.ArtistsEntity> mArtist=new ArrayList<>();
    ArtistAdapter mArtistAdapter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static ArtistFragment newInstance(int index) {
        ArtistFragment f = new ArtistFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist, container, false);
        this.context = getActivity();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        String lang = settings.getString("LANG", "");
        if( lang.equals("ar"))
        {
            view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        else
        {
            view.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

        ButterKnife.bind((Object) this, view);
     /*   click(view);
        initRecyclerView();*/
        initView(view);
        getArtist();
        return view;
    }


/*    public void initRecyclerView() {
        RecyclerViewLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        // Adding items to RecyclerView.
       // AddItemsToRecyclerViewArrayList();
        RecyclerViewHorizontalAdapter = new ArtistViewAdapter(Number);
        HorizontalLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(RecyclerViewHorizontalAdapter);
        // Adding on item click listener to RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
                    Intent i = new Intent(getContext(), ArtistDeatils.class);
                    startActivity(i);
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


    /*public void click(View v) {

        recyclerView = v.findViewById(R.id.recyclerview1);

    }
*/

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }

    private void initView(View rootView) {
        recyclerview1 = (RecyclerView) rootView.findViewById(R.id.recyclerview1);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerview1.setLayoutManager(mLayoutManager);
        recyclerview1.setItemAnimator(new DefaultItemAnimator());
    }

    //Check User Login
    private void getArtist() {
        Utils.showProgressDialog(getActivity());

        ApiUtils.getAPIService().getArtist(ApiUtils.Key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Artist>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        mArtistAdapter = new ArtistAdapter(getActivity(), mArtist);
                        recyclerview1.setAdapter(mArtistAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(getActivity(), e.getMessage());

                    }

                    @Override
                    public void onNext(Artist userModel) {
                        if (userModel.getSuccess()) {
                            if (userModel.getData().size() > 0) {
                                if (userModel.getData().size() > 0) {
                                    mArtist = userModel.getData().get(0).getArtists();
                                }

                            }
                            Utils.custoAlert(getActivity(), userModel.getMessage());
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(getActivity(), userModel.getMessage());
                        }

                    }
                });

    }


 /*public void AddItemsToRecyclerViewArrayList() {

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