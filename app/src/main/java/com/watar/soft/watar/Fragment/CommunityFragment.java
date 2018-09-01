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

import com.watar.soft.watar.Adapter.CommunityAdapter;
import com.watar.soft.watar.Model.Community;
import com.watar.soft.watar.R;
import com.watar.soft.watar.Utility.Utils;
import com.watar.soft.watar.remote.ApiUtils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class CommunityFragment extends Fragment {
    protected View rootView;
    protected RecyclerView recyclerview;
    CommunityAdapter communityAdapter;
    List<Community.PlaylistsEntity>mPlaylist;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static CommunityFragment newInstance(int index) {
        CommunityFragment f = new CommunityFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_community, container, false);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        String lang = settings.getString("LANG", "");
        if( lang.equals("ar"))
        {
            rootView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        else
        {
            rootView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
        initView(rootView);
        getCommunity();
        return rootView;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }

    private void initView(View rootView) {
        recyclerview = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
    }

    private void getCommunity() {
       // Utils.showProgressDialog(getActivity());

        ApiUtils.getAPIService().getCommunity(ApiUtils.Key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Community>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                        communityAdapter = new CommunityAdapter(getActivity(),mPlaylist);
                        recyclerview.setAdapter(communityAdapter);
                       }

                    @Override
                    public void onError(Throwable e) {
                       // Utils.hideProgressDialog();
                        Utils.customMessage(getActivity(), e.getMessage());
                    }

                    @Override
                    public void onNext(Community userModel) {
                        if (userModel.getSuccess()) {
                            if(userModel.getData().size()>0)
                            {
                                mPlaylist = userModel.getData().get(0).getPlaylists();
                            }
                            Utils.custoAlert(getActivity(), userModel.getMessage());
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(getActivity(), userModel.getMessage());
                        }

                    }
                });

    }


}