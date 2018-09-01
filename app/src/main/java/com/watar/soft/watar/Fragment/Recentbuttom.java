package com.watar.soft.watar.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.watar.soft.watar.R;
import com.watar.soft.watar.activity.Queuelist;

/**
 * A simple {@link Fragment} subclass.
 */
public class Recentbuttom extends BottomSheetDialogFragment {

LinearLayout addinqueues;
    public static Recentbuttom newInstance() {
        return new Recentbuttom();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recentbuttom, container,
                false);

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
        addinqueues = view.findViewById(R.id.addinqueues);

        addinqueues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               startActivity(new Intent(getContext(), Queuelist.class));
            }
        });

        return view;


    }

}
