package com.watar.soft.watar.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.watar.soft.watar.DBHelper.DatabaseHandler;
import com.watar.soft.watar.Model.Login;
import com.watar.soft.watar.R;
import com.watar.soft.watar.Utility.Utils;
import com.watar.soft.watar.remote.APIService;
import com.watar.soft.watar.remote.ApiUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Login_page extends AppCompatActivity {
    Intent mIntent;
    Button btn_singin;
    TextView sinup,forgotPassword;
    EditText et_loginusername, et_loginpassword;
    ImageView btn_close;
    APIService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /* if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.NightTheme);
        } else {
            setTheme(R.style.NightTheme);
        }*/


        super.onCreate(savedInstanceState);

    if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_login_page);
        initView();

        mApiService = ApiUtils.getAPIService();
        btn_singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  mIntent = new Intent(Login_page.this, MainActivity.class);
                startActivity(mIntent);*/

                if (et_loginusername.getText().length() == 0) {
                    Utils.customMessage(Login_page.this, "Username Can't Be Blank");
                    et_loginusername.requestFocus();
                    return;
                }

                if (et_loginpassword.getText().length() == 0) {
                    Utils.customMessage(Login_page.this, "Password Can't Be Blank");
                    et_loginpassword.requestFocus();
                    return;
                }

                ChkLogin(et_loginusername.getText().toString(), et_loginpassword.getText().toString());
            }
        });

        sinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mIntent = new Intent(Login_page.this, Sing_up.class);
                startActivity(mIntent);
                finish();
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Login_page.this)
                        .setTitle("Exit")
                        .setMessage("Are you sure you want to exist?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).create().show();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(Login_page.this, ForgotPassword.class);
                startActivity(mIntent);
                finish();
            }
        });

    }

    private void initView() {

        btn_singin = findViewById(R.id.btn_singin);
        sinup = findViewById(R.id.tv_signup);
        et_loginusername = findViewById(R.id.et_loginusername);
        et_loginpassword = findViewById(R.id.et_loginpassword);
        btn_close = findViewById(R.id.btn_close);
        forgotPassword= findViewById(R.id.forgotPassword);

    }


    //Check User Login
    private void ChkLogin(String mUserName, String mPassword) {
        Utils.showProgressDialog(Login_page.this);

        ApiUtils.getAPIService().chkLogin(ApiUtils.Key, mUserName, mPassword).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Login>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(Login_page.this, e.getMessage());
                    }

                    @Override
                    public void onNext(Login userModel) {
                        if (userModel.getSuccess()) {
                            DatabaseHandler db = new DatabaseHandler(Login_page.this);
                            if (db != null) {
                                db.addUserData(userModel);
                            }
                            //Utils.custoAlert(Login_page.this, userModel.getMessage());
                            Intent mIntent = new Intent(Login_page.this, MainActivity.class);
                            mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(mIntent);
                            finish();
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                            Utils.custoAlert(Login_page.this, userModel.getMessage());
                        }

                    }
                });

    }
}
