package com.watar.soft.watar.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.watar.soft.watar.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {

    RelativeLayout  mFaq,contactus;
    TextView viewfaqs,viewcontactus;
    LinearLayout ll_contactus,ll_faqs;
    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4;
    Button expandableButton1,expandableButton2,expandableButton3,expandableButton4;

    private String first="1",secand="1",thired="1",fours="1";


    private FragmentActivity context;

    public static ContactUsFragment newInstance(int index) {
        ContactUsFragment f = new ContactUsFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }



    public ContactUsFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
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
        this.context = getActivity();
        expandableLayout1 = (ExpandableRelativeLayout)view.findViewById(R.id.expandableLayout1);
        expandableLayout2 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout2);
        expandableLayout3 = (ExpandableRelativeLayout)view.findViewById(R.id.expandableLayout3);
        expandableLayout4 = (ExpandableRelativeLayout)view.findViewById(R.id.expandableLayout4);
        expandableButton1 = view.findViewById(R.id.expandableButton1);
        expandableButton2 = view.findViewById(R.id.expandableButton2);
        expandableButton3 = view.findViewById(R.id.expandableButton3);
        expandableButton4 = view.findViewById(R.id.expandableButton4);
         mFaq = view.findViewById(R.id.faqs);
         contactus = view.findViewById(R.id.contactus);
        viewfaqs  = view.findViewById(R.id.viewfaqs);
        viewcontactus  = view.findViewById(R.id.viewcontactus);
        ll_contactus = view.findViewById(R.id.ll_contactus);
        ll_faqs  = view.findViewById(R.id.ll_faqs);


        expandableButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    expandableLayout1.toggle(); // toggle expand and collapse
                    expandableButton1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
                    first = "0";
            }
        });

        expandableButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout2.toggle(); // toggle expand and collapse
                expandableButton2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
            }
        });
        expandableButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout3.toggle(); // toggle expand and collapse
                expandableButton3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
            }
        });

        expandableButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout4.toggle(); // toggle expand and collapse
                expandableButton4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
            }
        });



        mFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewfaqs.setTextColor(Color.parseColor("#1c1c24"));
                contactus.setBackgroundColor(Color.WHITE);
                viewfaqs.setBackgroundColor(Color.parseColor("#1c1c24"));
                viewcontactus.setBackgroundColor(Color.parseColor("#8e8d92"));
                ll_contactus.setVisibility(View.GONE);
                ll_faqs.setVisibility(View.VISIBLE);
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //this.mContactus.setTextColor(Color.parseColor("#1c1c24"));
                viewfaqs.setTextColor(Color.parseColor("#8e8d92"));
                viewcontactus.setBackgroundColor(Color.parseColor("#1c1c24"));
                viewfaqs.setBackgroundColor(Color.WHITE);
                ll_contactus.setVisibility(View.VISIBLE);
                ll_faqs.setVisibility(View.GONE);

            }
        });

        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }




}
