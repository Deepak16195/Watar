package com.watar.soft.watar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.watar.soft.watar.Model.SignUp;
import com.watar.soft.watar.R;
import com.watar.soft.watar.Utility.Utils;
import com.watar.soft.watar.remote.ApiUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user on 15/08/2018.
 */

public class ResetPassword extends AppCompatActivity {

    protected EditText etMobile;
    protected EditText etPassword;
    protected EditText etCnfpassword;
    protected Button btnReset;
    Intent mIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }*/
        setContentView(R.layout.resetpassword);

        initView();
        if(getIntent().getStringExtra("Mobile")!=null)
        {
            etMobile.setText(getIntent().getStringExtra("Mobile").toString());
            etMobile.setEnabled(false);
        }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMobile.getText().length() == 0) {
                    Utils.customMessage(ResetPassword.this, "Mobile No Can't Be Blank");
                    etMobile.requestFocus();
                    return;
                }

                if (etPassword.getText().length() == 0) {
                    Utils.customMessage(ResetPassword.this, "Password can't be blank");
                    etPassword.requestFocus();
                    return;
                }

                if (etCnfpassword.getText().length() == 0) {
                    Utils.customMessage(ResetPassword.this, "Confirm Password can't be blank");
                    etCnfpassword.requestFocus();
                    return;
                }

                if (!etPassword.getText().toString().equalsIgnoreCase(etCnfpassword.getText().toString())) {
                    Utils.customMessage(ResetPassword.this, "Password and confirm password not matched");
                    etPassword.requestFocus();
                    return;
                }
                ResetPassword(etMobile.getText().toString(), etPassword.getText().toString(), etCnfpassword.getText().toString());
            }
        });

    }


    private void initView() {
        etMobile = (EditText) findViewById(R.id.et_Mobile);
        etPassword = (EditText) findViewById(R.id.et_password);
        etCnfpassword = (EditText) findViewById(R.id.et_cnfpassword);
        btnReset = (Button) findViewById(R.id.btn_Reset);


    }


    private void ResetPassword(String mMobile, String mPassword, String mConfirmPassword) {
        Utils.showProgressDialog(ResetPassword.this);

        ApiUtils.getAPIService().ResetPassword(ApiUtils.Key, mPassword, mConfirmPassword, mMobile).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignUp>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.custoAlert(ResetPassword.this, e.getMessage());

                    }

                    @Override
                    public void onNext(SignUp userModel) {
                        if (userModel.getSuccess()) {
                            Utils.hideProgressDialog();
                            Utils.customMessage(ResetPassword.this, userModel.getMessage());
                            mIntent = new Intent(ResetPassword.this, Login_page.class);
                            startActivity(mIntent);
                            finish();
                        } else {
                            Utils.custoAlert(ResetPassword.this, userModel.getMessage());
                        }

                    }
                });

    }


}
