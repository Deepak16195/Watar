package com.watar.soft.watar.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.watar.soft.watar.DBHelper.TableDefinition;
import com.watar.soft.watar.R;

import java.io.IOException;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    protected View rootView;
    protected EditText etFullnameProfile;
    protected EditText etMobileProfile;
    protected EditText etEmailProfile;
    protected EditText etUsernameProfile;
    protected EditText etPasswordProfile;
    protected EditText etCnfpasswordProfile;
    protected AppCompatButton btnUpdateProfile;
    protected EditText etLastName;
    private Context context;
    private int PICK_IMAGE_REQUEST = 1;

    CircleImageView circleViewProfile;
    int userID = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public static ProfileFragment newInstance(int index) {
        ProfileFragment f = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        this.context = getActivity();
        ButterKnife.bind((Object) this, rootView);
        circleViewProfile = rootView.findViewById(R.id.circleViewProfile);
        circleViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        initView(rootView);
        readProfile();
        return rootView;
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST)
                onSelectFromGalleryResult(data);
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        circleViewProfile.setImageBitmap(bm);


    }

    public void onBackPressed() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }


    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }

    private void readProfile() {
        String selectQuery = "SELECT  * FROM " + TableDefinition.User_Table;
        SQLiteDatabase db = getActivity().openOrCreateDatabase(TableDefinition.DATABASE_NAME, Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            userID = cursor.getInt(cursor.getColumnIndex(TableDefinition.User_Id));
            etFullnameProfile.setText(cursor.getString(cursor.getColumnIndex(TableDefinition.User_FirstName)));
            etLastName.setText(cursor.getString(cursor.getColumnIndex(TableDefinition.User_lastname)));
            etMobileProfile.setText(cursor.getString(cursor.getColumnIndex(TableDefinition.User_Mobile)));
            etEmailProfile.setText(cursor.getString(cursor.getColumnIndex(TableDefinition.User_Email)));
            etUsernameProfile.setText(cursor.getString(cursor.getColumnIndex(TableDefinition.User_UserName)));
            Glide.with(getActivity()).load(cursor.getString(cursor.getColumnIndex(TableDefinition.User_ProfileImage))).error(R.drawable.face).placeholder(R.drawable.face).fitCenter().into(circleViewProfile);


        }
        cursor.close();
        db.close();
    }

    private void initView(View rootView) {
        circleViewProfile = (CircleImageView) rootView.findViewById(R.id.circleViewProfile);
        etFullnameProfile = (EditText) rootView.findViewById(R.id.et_fullnameProfile);
        etMobileProfile = (EditText) rootView.findViewById(R.id.et_mobileProfile);
        etEmailProfile = (EditText) rootView.findViewById(R.id.et_emailProfile);
        etUsernameProfile = (EditText) rootView.findViewById(R.id.et_usernameProfile);
        etPasswordProfile = (EditText) rootView.findViewById(R.id.et_passwordProfile);
        etCnfpasswordProfile = (EditText) rootView.findViewById(R.id.et_cnfpasswordProfile);
        btnUpdateProfile = (AppCompatButton) rootView.findViewById(R.id.btn_updateProfile);
        etLastName = (EditText) rootView.findViewById(R.id.et_lastName);
    }
}
