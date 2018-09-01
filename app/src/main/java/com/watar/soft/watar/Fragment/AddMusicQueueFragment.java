package com.watar.soft.watar.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.watar.soft.watar.R;
import com.watar.soft.watar.activity.Queuelist;


public class AddMusicQueueFragment extends BottomSheetDialogFragment {
    LinearLayout addqueues;

    public static AddMusicQueueFragment newInstance() {
        return new AddMusicQueueFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.addqueuebuttom, container,
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

        addqueues = view.findViewById(R.id.addqueues);

        addqueues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Queuelist.class));
            }
        });

        // get the views and attach the listener

        return view;

    }
}