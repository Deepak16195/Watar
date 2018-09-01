package com.watar.soft.watar.Utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RDave on 15/09/2017.
 */

public class Utils {

    private static ProgressDialog progressDialog;

    public static Toast customMessage(Context ctx, String message) {

        Toast toast = Toast.makeText(ctx, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return toast;

    }

    public static boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        }
        return true;
    }

    public static AlertDialog custoAlert(final Context ctx, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(message);
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                //((AppCompatActivity)ctx).finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        return dialog;
    }


    public static Double checkDouboe(String aValue) {
        double amt = 0;
        if (aValue.length() > 0) {
            amt = Double.parseDouble(aValue);
        } else {
            amt = 0;
        }
        return amt;
    }

    public static int checkInt(String aValue) {
        int amt = 0;
        if (aValue.length() > 0) {
            amt = Integer.parseInt(aValue);
        } else {
            amt = 0;
        }
        return amt;
    }



/*
    public static Double checkInteger(String aValue)
    {
        int amt=0;
        if(aValue.length()>0)
        {
            amt =Integer.parseInt(aValue);
        }
        else
        {
            amt=0;
        }
        return amt;
    }
*/

    public static boolean isBlank(String aValue) {
        boolean isBlank = false;
        if (aValue.length() > 0) {
            isBlank = true;
        } else {
            isBlank = false;
        }
        return isBlank;
    }


    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static String getMacAddress(Context mContext) {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(mContext.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress = wInfo.getMacAddress();
        return macAddress;
    }

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

    public static String checkMacAddress(Context mContext) {
        String MacAddress = "";
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(mContext.WIFI_SERVICE);
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            MacAddress = Utils.getMacAddr();
        } else {
            MacAddress = Utils.getMacAddress(mContext);
        }
        if (MacAddress == null) {
            wifiManager.setWifiEnabled(true);
            if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
                MacAddress = Utils.getMacAddr();
            } else {
                MacAddress = Utils.getMacAddress(mContext);
            }

        }
        return MacAddress;
    }


    public static void showProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void hideProgressDialog() {
        progressDialog.dismiss();

    }


}
