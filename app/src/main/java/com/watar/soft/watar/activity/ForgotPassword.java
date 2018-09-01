package com.watar.soft.watar.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.irvingryan.VerifyCodeView;
import com.watar.soft.watar.DBHelper.DatabaseHandler;
import com.watar.soft.watar.Model.OTPConfirm;
import com.watar.soft.watar.Model.SignUp;
import com.watar.soft.watar.R;
import com.watar.soft.watar.Utility.Utils;
import com.watar.soft.watar.remote.ApiUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ForgotPassword extends AppCompatActivity {

    protected EditText etMobile;
    protected Button btnOTP;
    Intent mIntent;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.forgotpassword);

        initView();

        btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OTPResend(etMobile.getText().toString(), true);

                //OtpDialog();
            }
        });

    }


    private void initView() {
        etMobile = (EditText) findViewById(R.id.et_mobile);
        btnOTP = (Button) findViewById(R.id.btn_OTP);

    }

    public void OtpDialog(final String mMobile) {
        dialog = new Dialog(ForgotPassword.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.otp_layout, null);
        final VerifyCodeView et_OTP = dialogView.findViewById(R.id.et_otpNumber);
        Button btnDone = dialogView.findViewById(R.id.btnDone);
        TextView txt_Resend = dialogView.findViewById(R.id.txt_Resend);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OTPConfirmation(et_OTP.getText().toString(), mMobile);
            }
        });

        txt_Resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OTPResend(mMobile, false);
            }
        });
        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.show();
        dialog.setCancelable(false);

    }


    //Check User Login
    private void OTPConfirmation(String mOTP, String mMobile) {
        Utils.showProgressDialog(ForgotPassword.this);

        ApiUtils.getAPIService().OTPConfirm(ApiUtils.Key, mOTP, mMobile).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OTPConfirm>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(ForgotPassword.this, e.getMessage());
                    }

                    @Override
                    public void onNext(OTPConfirm userModel) {
                        if (userModel.getSuccess()) {
                            Utils.hideProgressDialog();
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            DatabaseHandler db = new DatabaseHandler(ForgotPassword.this);
                            if (db != null) {
                                db.addUserDataFromOTP(userModel);
                            }
                            mIntent = new Intent(ForgotPassword.this, ResetPassword.class);
                            mIntent.putExtra("Mobile",etMobile.getText().toString());
                            startActivity(mIntent);
                            finish();
                        } else {
                            Utils.custoAlert(ForgotPassword.this, userModel.getMessage());
                        }
                    }
                });
    }

    private void OTPResend(String mMobile, final boolean ShowDialog) {
        Utils.showProgressDialog(ForgotPassword.this);

        ApiUtils.getAPIService().OTPResend(ApiUtils.Key, mMobile).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignUp>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.custoAlert(ForgotPassword.this, e.getMessage());

                    }

                    @Override
                    public void onNext(SignUp userModel) {
                        if (userModel.getSuccess()) {
                            Utils.hideProgressDialog();
                            Utils.customMessage(ForgotPassword.this, userModel.getMessage());
                            if (ShowDialog) {
                                OtpDialog(etMobile.getText().toString());
                            }

                        } else {
                            Utils.custoAlert(ForgotPassword.this, userModel.getMessage());
                        }

                    }
                });

    }


}
