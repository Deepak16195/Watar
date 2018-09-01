package com.watar.soft.watar.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.watar.soft.watar.DBHelper.DatabaseHandler;
import com.watar.soft.watar.R;

public class First_page extends AppCompatActivity {
    Intent mIntent;
    ConstraintLayout Const;
    Handler handler;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
       }



        setContentView(R.layout.activity_first_page);
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

        db = new DatabaseHandler(First_page.this);
        initView();
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                    if (db.getUserCount() == 0) {
                        db.close();
                        mIntent = new Intent(First_page.this, Login_page.class);
                        mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(mIntent);


                    } else {
                        db.close();
                        mIntent = new Intent(First_page.this, MainActivity.class);
                        mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(mIntent);
                    }
                finish();
                //

               /* mIntent = new Intent(First_page.this, Login_page.class);
                startActivity(mIntent);
              ;*/
            }
        },3000);



    }
   private void initView(){

       Const = findViewById(R.id.Const);


    }


}
