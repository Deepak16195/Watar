package com.watar.soft.watar.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.watar.soft.watar.Adapter.QueueslistViewAdapter;
import com.watar.soft.watar.Fragment.Createquesebottom;
import com.watar.soft.watar.R;

import java.util.ArrayList;

public class Queuelist extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<String> Number;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    LinearLayoutManager HorizontalLayout;
    View ChildView;
    int RecyclerViewItemPosition;
    QueueslistViewAdapter RecyclerViewHorizontalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_queuelist);
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

        click();
        initRecyclerView();
    }

        public void click() {
            recyclerView = findViewById(R.id.recyclerview1);
        }



    public void initRecyclerView() {
        RecyclerViewLayoutManager = new LinearLayoutManager(Queuelist.this);
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        // Adding items to RecyclerView.
        AddItemsToRecyclerViewArrayList();
        RecyclerViewHorizontalAdapter = new QueueslistViewAdapter(Number);
        HorizontalLayout = new LinearLayoutManager(Queuelist.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(RecyclerViewHorizontalAdapter);
        // Adding on item click listener to RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(Queuelist.this, new GestureDetector.SimpleOnGestureListener() {
                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
                ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if(ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {
                    //Getting clicked value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(ChildView);
                    // Showing clicked item value on screen using toast message.
                    Toast.makeText(Queuelist.this, Number.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();


                    Createquesebottom createquesebottom =
                            Createquesebottom.newInstance();
                    createquesebottom.show(getSupportFragmentManager(),
                            "fragment_createquesebottom");


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


    public void AddItemsToRecyclerViewArrayList(){

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
}
