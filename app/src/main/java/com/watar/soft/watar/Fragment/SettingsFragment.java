package com.watar.soft.watar.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.watar.soft.watar.R;

import butterknife.ButterKnife;

public class SettingsFragment extends Fragment {
    private Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
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
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }
}
