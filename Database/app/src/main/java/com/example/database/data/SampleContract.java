package com.example.database.data;

import android.provider.BaseColumns;

public class SampleContract {
    private SampleContract(){}
    public static final class SampleEntry implements BaseColumns{
        public final static String TABLE_NAME="sampletable";
        public final static String _ID=BaseColumns._ID;
        public final static String FIRST_NAME="FirstName";
        public final static String LAST_NAME="LastName";
        public final static String Gender="Gender";
        public final static String ROLL_NO="Rollno";



    }

}
