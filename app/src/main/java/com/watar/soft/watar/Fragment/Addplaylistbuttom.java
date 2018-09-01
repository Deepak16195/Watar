package com.watar.soft.watar.Fragment;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class Addplaylistbuttom  extends BottomSheetDialogFragment {

LinearLayout createnewplaylist;


    public static Addplaylistbuttom newInstance() {
        return new Addplaylistbuttom();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_addplaylistbuttom, container,
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
        createnewplaylist= view.findViewById(R.id.createnewplaylist);
        createnewplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreatealbumbuttomFragment createalbumbuttomFragment =
                        CreatealbumbuttomFragment.newInstance();
                createalbumbuttomFragment.show(getFragmentManager(),
                        "createnewalbum");

            }
        });

        return view;
    }

}
