package com.watar.soft.watar.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.watar.soft.watar.Model.Login;
import com.watar.soft.watar.Model.OTPConfirm;

/**
 * Created by user on 07-09-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;


    /*---- Creation of Tables Start -----*/
    //Create Table Query
    public String CREATE_User_Table = "Create Table " + TableDefinition.User_Table + "("
            + TableDefinition.User_Id + " INTEGER,"
            + TableDefinition.User_APIToken + " Text,"
            + TableDefinition.User_UserName + " Text,"
            + TableDefinition.User_FirstName + " Text,"
            + TableDefinition.User_lastname + " Text,"
            + TableDefinition.User_ProfileImage + " Text,"
            + TableDefinition.User_Mobile + " Text,"
            + TableDefinition.User_Email + " Text,"
            + TableDefinition.User_DisplayName + " Text)";


    public DatabaseHandler(Context context) {
        super(context, TableDefinition.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_User_Table);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new row
    public void addUserData(Login data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TableDefinition.User_Table + "");
        ContentValues values = new ContentValues();
        if (data.getData().size() > 0) {
            values.put(TableDefinition.User_APIToken, data.getData().get(0).getApi_token());
            if (data.getData().get(0).getUser() != null) {
                Login.UserEntity mUser = data.getData().get(0).getUser();
                values.put(TableDefinition.User_Id, mUser.getId());
                values.put(TableDefinition.User_UserName, mUser.getUsername());
                values.put(TableDefinition.User_FirstName, mUser.getFirst_name());
                values.put(TableDefinition.User_lastname, mUser.getLast_name());
                values.put(TableDefinition.User_ProfileImage, mUser.getAvatar());
                values.put(TableDefinition.User_Mobile, mUser.getMobile());
                values.put(TableDefinition.User_Email, mUser.getEmail());
                values.put(TableDefinition.User_DisplayName, mUser.getDisplay_name());
            }
            db.insertOrThrow(TableDefinition.User_Table, null, values);
            db.close(); // Closing database connection
        }
    }


    public void addUserDataFromOTP(OTPConfirm data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TableDefinition.User_Table + "");
        ContentValues values = new ContentValues();


        if (data.getData().size() > 0) {
            values.put(TableDefinition.User_APIToken, data.getData().get(0).getApi_token());
            if (data.getData().get(0).getUser() != null) {
                OTPConfirm.UserEntity mUser = data.getData().get(0).getUser();
                values.put(TableDefinition.User_Id, mUser.getId());
                values.put(TableDefinition.User_UserName, mUser.getUsername());
                values.put(TableDefinition.User_FirstName, mUser.getFirst_name());
                values.put(TableDefinition.User_lastname, mUser.getLast_name());
                values.put(TableDefinition.User_ProfileImage, mUser.getAvatar());
                values.put(TableDefinition.User_Mobile, mUser.getMobile());
                values.put(TableDefinition.User_Email, mUser.getEmail());
                values.put(TableDefinition.User_DisplayName, mUser.getDisplay_name());

            }


        }

        //values.put(TableDefinition.User_Type, data.getUserType());
        // Inserting Row
        db.insertOrThrow(TableDefinition.User_Table, null, values);
        db.close(); // Closing database connection
    }

    // Getting UserM Count
    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + TableDefinition.User_Table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        // return count
        return cursor.getCount();
    }


    public static boolean deleteUser(Context mContext) {
        try {
            String deleteQuery = "DELETE  FROM " + TableDefinition.User_Table;
            SQLiteDatabase db = mContext.openOrCreateDatabase(TableDefinition.DATABASE_NAME, Context.MODE_PRIVATE, null);
            db.execSQL(deleteQuery);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
