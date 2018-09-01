package com.watar.soft.watar.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.watar.soft.watar.Fragment.ArtistFragment;
import com.watar.soft.watar.Fragment.CommunityFragment;
import com.watar.soft.watar.Fragment.MyLibraryFragment;
import com.watar.soft.watar.Fragment.RecentFragment;
import com.watar.soft.watar.Fragment.WaterFragment;

import java.util.Locale;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    String langage;
    public SectionsPagerAdapter(FragmentManager fm, Context c) {
        super(fm);
        context = c;

        SharedPreferences settingss = PreferenceManager.getDefaultSharedPreferences(context);
        langage = settingss.getString("LANG", "");


    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.

       // Log.d(LOG_TAG, "postion " + position);
        if (position == 0) {
            return CommunityFragment.newInstance(position + 1);
        } else if (position == 1) {
            return WaterFragment.newInstance(position + 1);
        }
        else if (position == 2) {

            return MyLibraryFragment.newInstance(position + 1);

        } else if (position == 3) {

            return RecentFragment.newInstance(position + 1);
        }
        else {
            return ArtistFragment.newInstance(position + 1);
        }

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:

                if( langage.equals("ar")) {
                    return "المجتمع";
                }else {
                    return "Community";
                }

            case 1:

                if( langage.equals("ar")) {
                    return "وتر";
                }else {
                    return "Watar";
                }



            case 2:

                if( langage.equals("ar")) {
                     return "مكتبتي";
                }else {
                    return "MY Library";
                }


            case 3:


                if( langage.equals("ar")) {
                    return "أحدث الأغاني";
                }else {
                    return "Recent";
                }


            case 4:
                if( langage.equals("ar")) {
                    return "الفنان";
                }else {
                    return "Artist";

                }




        }
        return null;
    }

}