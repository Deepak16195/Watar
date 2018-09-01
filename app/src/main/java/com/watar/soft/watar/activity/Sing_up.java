package com.watar.soft.watar.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.irvingryan.VerifyCodeView;
import com.watar.soft.watar.DBHelper.DatabaseHandler;
import com.watar.soft.watar.Model.OTPConfirm;
import com.watar.soft.watar.Model.SignUp;
import com.watar.soft.watar.R;
import com.watar.soft.watar.Utility.RealPathUtil;
import com.watar.soft.watar.Utility.Utils;
import com.watar.soft.watar.remote.ApiUtils;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Sing_up extends AppCompatActivity {

    Intent mIntent;
    TextView textsignin;
    Button btn_signup;
    private int PICK_IMAGE_REQUEST = 1;
    private static final int PERMISSIONS_ACCESS_GALLERY = 2000;
    CircleImageView profile_image;
    EditText et_fullname, et_lastname, et_mobile, et_email, et_username, et_password, et_cnfpassword;
    File profileFile;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_sing_up);

        initView();
        textsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(Sing_up.this, Login_page.class);
                startActivity(mIntent);
                finish();
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (profileFile == null) {
                    Utils.customMessage(Sing_up.this, "Profile image can't be blank");
                    profile_image.requestFocus();
                    return;
                }

                if (et_fullname.getText().length() == 0) {
                    Utils.customMessage(Sing_up.this, "Full name can't be blank");
                    et_fullname.requestFocus();
                    return;
                }

                if (et_mobile.getText().length() == 0) {
                    Utils.customMessage(Sing_up.this, "Mobile No can't be blank");
                    et_mobile.requestFocus();
                    return;
                }

                if (et_email.getText().length() == 0) {
                    Utils.customMessage(Sing_up.this, "Email can't be blank");
                    et_email.requestFocus();
                    return;
                }

                if (et_password.getText().length() == 0) {
                    Utils.customMessage(Sing_up.this, "Password can't be blank");
                    et_password.requestFocus();
                    return;
                }

                if (et_cnfpassword.getText().length() == 0) {
                    Utils.customMessage(Sing_up.this, "Confirm Password can't be blank");
                    et_cnfpassword.requestFocus();
                    return;
                }

                if (!et_password.getText().toString().equalsIgnoreCase(et_cnfpassword.getText().toString())) {
                    Utils.customMessage(Sing_up.this, "Password and confirm password not matched");
                    et_password.requestFocus();
                    return;
                }

                Register(et_fullname.getText().toString(), et_lastname.getText().toString(), et_mobile.getText().toString(),
                        et_email.getText().toString(), et_username.getText().toString(), et_password.getText().toString(),
                        et_cnfpassword.getText().toString(), profileFile);

                //OtpDialog();


            }
        });

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

    }


    private void showFileChooser() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);


        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_ACCESS_GALLERY);
        }

        Toast.makeText(getApplicationContext(), "Profile Pic", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST)
                onSelectFromGalleryResult(data);
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                String imagePath = RealPathUtil.getRealPathFromURI_API19(Sing_up.this, data.getData());
                profileFile = new File(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        profile_image.setImageBitmap(bm);


    }


    private void initView() {
        textsignin = findViewById(R.id.tv_signin);
        btn_signup = findViewById(R.id.btn_signup);
        profile_image = findViewById(R.id.profile_image);
        et_fullname = findViewById(R.id.et_fullname);
        et_lastname = findViewById(R.id.et_lastname);
        et_mobile = findViewById(R.id.et_mobile);
        et_email = findViewById(R.id.et_email);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_cnfpassword = findViewById(R.id.et_cnfpassword);
    }

    public void OtpDialog(final String mMobile) {
        dialog = new Dialog(Sing_up.this);
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
                OTPResend(mMobile);
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
    private void Register(String mFirstName, String mLastName, final String mMobile, String mEmail, String mUserName, String mPassword, String mConfirmPassword, File mProfileImage) {
        Utils.showProgressDialog(Sing_up.this);

        MultipartBody.Part avatar = null;

        if (mProfileImage != null) {

            //gst_image = MultipartBody.Part.createFormData("imagefile", mFile.getName(), amFile);
            RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), mProfileImage);
            avatar = MultipartBody.Part.createFormData("avatar", mProfileImage.getName(), mFile);

        }
        RequestBody firstName = RequestBody.create(MediaType.parse("text/plain"), mFirstName);
        RequestBody lastName = RequestBody.create(MediaType.parse("text/plain"), mLastName);
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), mMobile);
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), mEmail);
        RequestBody userName = RequestBody.create(MediaType.parse("text/plain"), mUserName);
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), mPassword);
        RequestBody confirmPassword = RequestBody.create(MediaType.parse("text/plain"), mConfirmPassword);


        ApiUtils.getAPIService().Register(ApiUtils.Key, firstName, lastName,
                mobile, email, userName, password, confirmPassword, avatar).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignUp>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(Sing_up.this, e.getMessage());

                    }

                    @Override
                    public void onNext(SignUp userModel) {
                        if (userModel.getSuccess()) {
                            Utils.hideProgressDialog();
                            OtpDialog(mMobile);

                               /* if (userModel.getMessage().equalsIgnoreCase("Success")) {
                                    for (int i = 0; i < userModel.getData().size(); i++) {
                                        db.addUserData(userModel.getData().get(i));
                                        Result = "Success";
                                    }
                                } else {
                                    //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();
                                    Utils.custoAlert(Login.this, userModel.getMessage());

                                }*/
                            Utils.customMessage(Sing_up.this, userModel.getMessage());
                        } else {
                            //Toast.makeText(LoginActivity.this, "User Does not Exist :(", Toast.LENGTH_SHORT).show();

                            if (userModel.getMessages() != null) {
                                if (userModel.getMessages().getAvatar() != null) {
                                    Utils.customMessage(Sing_up.this, userModel.getMessages().getAvatar().get(0));
                                    profile_image.requestFocus();
                                    return;

                                } else if (userModel.getMessages().getFirst_name() != null) {
                                    Utils.customMessage(Sing_up.this, userModel.getMessages().getFirst_name().get(0));
                                    et_fullname.requestFocus();
                                    return;
                                } else if (userModel.getMessages().getLast_name() != null) {
                                    Utils.customMessage(Sing_up.this, userModel.getMessages().getLast_name().get(0));
                                    et_lastname.requestFocus();
                                    return;
                                } else if (userModel.getMessages().getMobile() != null) {
                                    Utils.customMessage(Sing_up.this, userModel.getMessages().getMobile().get(0));
                                    et_mobile.requestFocus();
                                    return;
                                } else if (userModel.getMessages().getEmail() != null) {
                                    Utils.customMessage(Sing_up.this, userModel.getMessages().getEmail().get(0));
                                    et_email.requestFocus();
                                    return;
                                } else if (userModel.getMessages().getPassword() != null) {
                                    Utils.customMessage(Sing_up.this, userModel.getMessages().getPassword().get(0));
                                    et_password.requestFocus();
                                    return;
                                } else if (userModel.getMessages().getUsername() != null) {
                                    Utils.customMessage(Sing_up.this, userModel.getMessages().getUsername().get(0));
                                    et_username.requestFocus();
                                    return;
                                } else {
                                    Utils.custoAlert(Sing_up.this, userModel.getMessage());
                                }
                            } else {
                                Utils.custoAlert(Sing_up.this, userModel.getMessage());
                            }
                        }

                    }
                });
    }


    //Check User Login
    private void OTPConfirmation(String mOTP, String mMobile) {
        Utils.showProgressDialog(Sing_up.this);

        ApiUtils.getAPIService().OTPConfirm(ApiUtils.Key, mOTP, mMobile).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OTPConfirm>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.customMessage(Sing_up.this, e.getMessage());
                    }

                    @Override
                    public void onNext(OTPConfirm userModel) {
                        if (userModel.getSuccess()) {
                            Utils.hideProgressDialog();
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            DatabaseHandler db = new DatabaseHandler(Sing_up.this);
                            if (db != null) {
                                db.addUserDataFromOTP(userModel);
                            }
                            mIntent = new Intent(Sing_up.this, MainActivity.class);
                            startActivity(mIntent);
                            finish();
                        } else {
                            Utils.custoAlert(Sing_up.this, userModel.getMessage());
                        }
                    }
                });
    }

    private void OTPResend(String mMobile) {
        Utils.showProgressDialog(Sing_up.this);

        ApiUtils.getAPIService().OTPResend(ApiUtils.Key, mMobile).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignUp>() {
                    @Override
                    public void onCompleted() {
                        Utils.hideProgressDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.hideProgressDialog();
                        Utils.custoAlert(Sing_up.this, e.getMessage());

                    }

                    @Override
                    public void onNext(SignUp userModel) {
                        if (userModel.getSuccess()) {
                            Utils.hideProgressDialog();
                            Utils.customMessage(Sing_up.this, userModel.getMessage());
                        } else {
                            Utils.custoAlert(Sing_up.this, userModel.getMessage());
                        }

                    }
                });

    }


}
