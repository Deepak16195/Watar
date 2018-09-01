package com.watar.soft.watar.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.watar.soft.watar.Model.Genres;
import com.watar.soft.watar.R;
import com.watar.soft.watar.Utility.Utils;
import com.watar.soft.watar.activity.MusicPlay;
import com.watar.soft.watar.remote.ApiUtils;

import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaterclickFragment extends Fragment {
    private Context context;
    TextView playall;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static WaterclickFragment newInstance(int index) {
        WaterclickFragment f = new WaterclickFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_waterclick, container, false);
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
        click(view);

        return view;
    }

    public void click(View v) {

        playall = v.findViewById(R.id.playall);

        playall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MusicPlay.class);
                startActivity(intent);


            }
        });

    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }

    //Check User Login
    private void getGenerByName() {
        Utils.showProgressDialog(getActivity());

        ApiUtils.getAPIService().getGeners(ApiUtils.Key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Genres>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                   /*     mWatar_recyclerData = new Watar_RecyclerData(context, mGenres);
                        recyclerViewData.setAdapter(mWatar_recyclerData);*/

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(getActivity(), e.getMessage());

                    }

                    @Override
                    public void onNext(Genres userModel) {
                        if (userModel.getSuccess()) {
                            /*if(userModel.getData().size()>0)
                            {
                                mGenres = userModel.getData().get(0).getGenre();
                            }*/



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
}
