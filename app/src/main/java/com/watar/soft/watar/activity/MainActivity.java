package com.watar.soft.watar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.watar.soft.watar.Adapter.SectionsPagerAdapter;
import com.watar.soft.watar.DBHelper.DatabaseHandler;
import com.watar.soft.watar.DBHelper.TableDefinition;
import com.watar.soft.watar.Fragment.ContactUsFragment;
import com.watar.soft.watar.Fragment.FeatureArtistFragment;
import com.watar.soft.watar.Fragment.ProfileFragment;
import com.watar.soft.watar.Fragment.PromoFragment;
import com.watar.soft.watar.Fragment.SettingsFragment;
import com.watar.soft.watar.Model.Login;
import com.watar.soft.watar.R;
import com.watar.soft.watar.Utility.Utils;
import com.watar.soft.watar.remote.ApiUtils;

import java.util.Locale;

import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView promo, settings, logoutImage, iv_closedrawer;
    DrawerLayout drawer;
    TextView txtLogOut;

    ViewPager viewPager;
    FragmentManager fragmentManager;
    private static final String BACK_STACK_ROOT_TAG = "root_fragment";
    DatabaseHandler db;
    String APIToken;
    SwitchCompat switcher,switchersec;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(getApplication());
        String langage = setting.getString("LANG", "");
        if( langage.equals("ar"))
        {
            setLangRecreatechanck("ar");

        }
        else
        {
            setLangRecreatechanck("en");
        }
        if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        View view = new View(getApplication());

        if( langage.equals("ar"))
        {
            view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        }
        else
        {
            view.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


        }

        setContentView(R.layout.activity_main);
        ButterKnife.bind((Activity) this);
        db = new DatabaseHandler(MainActivity.this);
        initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView drable = (ImageView) findViewById(R.id.drable);
        setSupportActionBar(toolbar);
        //getActionBar().setElevation(0);

        readProfile();

        if( langage.equals("ar")) {
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        else
        {
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if( langage.equals("ar"))
        {
            drable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.openDrawer(Gravity.RIGHT);
                }
            });

        }
        else
        {
            drable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.openDrawer(Gravity.LEFT);
                }
            });


        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.nav_nightmode);
        MenuItem lang = menu.findItem(R.id.lang);

        View actionViewsec = MenuItemCompat.getActionView(lang);
        switchersec = (SwitchCompat) actionViewsec.findViewById(R.id.drawer_switchsec);
        if (langage.equals("ar")) {
            switchersec.setChecked(true);
        }

        View actionView = MenuItemCompat.getActionView(menuItem);
        switcher = (SwitchCompat) actionView.findViewById(R.id.drawer_switch);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            switcher.setChecked(true);



      /*  if (InitApplication.getInstance().isNightModeEnabled()) {
            switcher.setChecked(true);
        }
        else
        {
            switcher.setChecked(false);
        }*/

        switchersec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Changelang(1);
                } else {
                    Changelang(0);
                }
            }
        });



        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    InitApplication.getInstance().setIsNightModeEnabled(true);
                    Intent intent = getApplicationContext().getPackageManager()
                            .getLaunchIntentForPackage(getApplicationContext().getPackageName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("Menu","Direct");
                    finish();
                    startActivity(intent);

                } else {
                    InitApplication.getInstance().setIsNightModeEnabled(false);
                    Intent intent = getApplicationContext().getPackageManager()
                            .getLaunchIntentForPackage(getApplicationContext().getPackageName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("Menu","Direct");
                    finish();
                    startActivity(intent);
                }
            }
        });


        //DividerItemDecoration.setDrawable(MainActivity.this.getResources().getDrawable(R.drawable.sk_line_divider));
        fragmentManager = getSupportFragmentManager();
        this.iv_closedrawer = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.iv_closedrawer);

        int decorPriorityIndex = 0;

        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        // navMenuView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL),decorPriorityIndex);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),getApplication());

        viewPager.setAdapter(mSectionsPagerAdapter);

// setting the views to be accessed through tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        tab.select();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int currentTab = tab.getPosition();
                viewPager.setCurrentItem(currentTab);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        iv_closedrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer((int) GravityCompat.START);
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.menu_selection_container, new SettingsFragment());
                ft.addToBackStack(BACK_STACK_ROOT_TAG);
                ft.commit();
                ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
            }
        });


        promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.menu_selection_container, new PromoFragment());
                ft.addToBackStack(BACK_STACK_ROOT_TAG);
                ft.commit();
                ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
            }
        });

        logoutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Exit")
                        .setMessage("Are you sure you want to logout?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getLogout("Bearer " + APIToken);
                            }
                        }).create().show();

            }

        });

        txtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutImage.performClick();
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }


    private void initView() {


        promo = findViewById(R.id.promo);
        settings = findViewById(R.id.settings);
        logoutImage = findViewById(R.id.logoutImage);
        iv_closedrawer = findViewById(R.id.iv_closedrawer);
        txtLogOut = findViewById(R.id.txtLogOut);

    }


    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen((int) GravityCompat.START)) {
            drawer.closeDrawer((int) GravityCompat.START);
        } else {


            getFragmentManager().popBackStackImmediate();
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.menu_selection_container, new ProfileFragment());
            ft.addToBackStack(BACK_STACK_ROOT_TAG);
            ft.commit();
            ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);

            // Handle the camera action
        } else if (id == R.id.nav_contactus) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.menu_selection_container, new ContactUsFragment());
            ft.addToBackStack(BACK_STACK_ROOT_TAG);
            ft.commit();
            ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);

        } else if (id == R.id.nav_artist) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.menu_selection_container, new FeatureArtistFragment());
            ft.addToBackStack(BACK_STACK_ROOT_TAG);
            ft.commit();
            ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);

        } else if (id == R.id.nav_announcements) {

            Intent i = new Intent(MainActivity.this, MusicPlay.class);
            startActivity(i);

        } else if (id == R.id.nav_library) {

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
            TabLayout.Tab tab = tabLayout.getTabAt(2);
            tab.select();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






   public void Changelang(int langpos )
   {
       switch(langpos) {
           case 0: //English
               PreferenceManager.getDefaultSharedPreferences(getApplication()).edit().putString("LANG", "en").commit();
               setLangRecreate("en");
               return;
           case 1: //French
               PreferenceManager.getDefaultSharedPreferences(getApplication()).edit().putString("LANG", "ar").commit();
               setLangRecreate("ar");
               return;
           case 2: //Spanish
               PreferenceManager.getDefaultSharedPreferences(getApplication()).edit().putString("LANG", "fr").commit();
               setLangRecreate("fr");
               return;
           case 3: //Chinese
               PreferenceManager.getDefaultSharedPreferences(getApplication()).edit().putString("LANG", "zh").commit();
               setLangRecreate("zh");
               return;
           default: //By default set to english
               PreferenceManager.getDefaultSharedPreferences(getApplication()).edit().putString("LANG", "en").commit();
               setLangRecreate("en");
               return;
       }
   }









    //change language
//    public void showChangeLangDialog() {
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getApplication());
//        LayoutInflater inflater = this.getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.activity_show, null);
//        dialogBuilder.setView(dialogView);
//
//        final Spinner spinner1 = (Spinner) dialogView.findViewById(R.id.spinner1);
//
//        dialogBuilder.setTitle(R.string.select_note);
//
//        dialogBuilder.setPositiveButton(R.string.Change, new DialogInterface.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//            public void onClick(DialogInterface dialog, int whichButton) {
//                int langpos = spinner1.getSelectedItemPosition();
//                switch(langpos) {
//                    case 0: //English
//                        PreferenceManager.getDefaultSharedPreferences(getApplication()).edit().putString("LANG", "en").commit();
//                        setLangRecreate("en");
//                        return;
//                    case 1: //French
//                        PreferenceManager.getDefaultSharedPreferences(getApplication()).edit().putString("LANG", "ar").commit();
//                        setLangRecreate("ar");
//
//                        return;
//                    case 2: //Spanish
//                        PreferenceManager.getDefaultSharedPreferences(getApplication()).edit().putString("LANG", "fr").commit();
//                        setLangRecreate("fr");
//                        return;
//                    case 3: //Chinese
//                        PreferenceManager.getDefaultSharedPreferences(getApplication()).edit().putString("LANG", "zh").commit();
//                        setLangRecreate("zh");
//                        return;
//                    default: //By default set to english
//                        PreferenceManager.getDefaultSharedPreferences(getApplication()).edit().putString("LANG", "en").commit();
//                        setLangRecreate("en");
//                        return;
//                }
//            }
//        });
//        dialogBuilder.setNegativeButton(R.string.CANCEL, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//            }
//        });
//        AlertDialog b = dialogBuilder.create();
//        b.show();
//    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setLangRecreate(String langval) {
        Configuration config = getApplication().getResources().getConfiguration();
        Locale locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        TextUtilsCompat.getLayoutDirectionFromLocale(locale);
        getApplication().getResources().updateConfiguration(config, getApplication().getResources().getDisplayMetrics());
        getApplication();

        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            //RTL

        }
        MainActivity.this.finish();
        Intent i = getApplication().getPackageManager()
                .getLaunchIntentForPackage( getApplication().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setLangRecreatechanck(String langval) {
        Configuration config = getApplication().getResources().getConfiguration();
        Locale locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        TextUtilsCompat.getLayoutDirectionFromLocale(locale);
        getApplication().getResources().updateConfiguration(config, getApplication().getResources().getDisplayMetrics());
        getApplication();

        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            //RTL

        }

//        getActivity().finish();
//        Intent i = getContext().getPackageManager()
//                .getLaunchIntentForPackage( getContext().getPackageName() );
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(i);
    }





    //Check User Login
    private void getLogout(String mAuthorisation) {
        Utils.showProgressDialog(MainActivity.this);

        ApiUtils.getAPIService().getLogOut(mAuthorisation, ApiUtils.Key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Login>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(MainActivity.this, e.getMessage());
                    }

                    @Override
                    public void onNext(Login userModel) {
                        if (userModel.getSuccess()) {
                            if (db != null) {
                                if (db.deleteUser(MainActivity.this)) {
                                    InitApplication.getInstance().setIsNightModeEnabled(false);
                                    Intent mIntent = new Intent(MainActivity.this, Login_page.class);
                                    mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                                    finish();
                                    startActivity(mIntent);
                                }
                            }
                            //Utils.custoAlert(Login_page.this, userModel.getMessage());

                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(MainActivity.this, userModel.getMessage());
                        }

                    }
                });

    }

    private void readProfile() {
        String selectQuery = "SELECT  * FROM " + TableDefinition.User_Table;
        SQLiteDatabase db = MainActivity.this.openOrCreateDatabase(TableDefinition.DATABASE_NAME, Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            APIToken = cursor.getString(cursor.getColumnIndex(TableDefinition.User_APIToken));
        }
        cursor.close();
        db.close();
    }


}
