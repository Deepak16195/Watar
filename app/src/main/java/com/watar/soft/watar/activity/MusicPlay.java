package com.watar.soft.watar.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.watar.soft.watar.Fragment.AddMusicQueueFragment;
import com.watar.soft.watar.Fragment.Addplaylistbuttom;
import com.watar.soft.watar.R;

public class MusicPlay extends AppCompatActivity {
    private ImageView addQueue,createplaylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);

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

        addQueue= (ImageView)findViewById(R.id.addQueue);
        createplaylist= (ImageView)findViewById(R.id.createplaylist);
        addQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMusicQueueFragment addMusicQueueFragment =
                        AddMusicQueueFragment.newInstance();
                addMusicQueueFragment.show(getSupportFragmentManager(),
                        "addqueuebuttom");
            }
        });


        createplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Addplaylistbuttom addplaylistbuttom = Addplaylistbuttom.newInstance();
                addplaylistbuttom.show(getSupportFragmentManager(),
                        "fragment_addplaylistbuttom");

            }
        });


    }

    public void back(View view){
        super.onBackPressed();
    }
}
