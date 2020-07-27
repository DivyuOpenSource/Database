package com.example.database.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.xml.parsers.SAXParser;

public class SampleDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG =SampleDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME="sample.db";
    private static final int DATABASE_VERSION=1;

    public SampleDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL__SAMPLE_TABLE=" CREATE TABLE "+ SampleContract.SampleEntry.TABLE_NAME + "( " +
        SampleContract.SampleEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SampleContract.SampleEntry.FIRST_NAME + " TEXT NOT NULL," +
                SampleContract.SampleEntry.LAST_NAME +" TEXT NOT NULL," +
                SampleContract.SampleEntry.Gender +" TEXT," +
                SampleContract.SampleEntry.ROLL_NO +" INT" +
               " );";

        db.execSQL(SQL__SAMPLE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
