package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.data.SampleContract;
import com.example.database.data.SampleDbHelper;

public class MainActivity extends AppCompatActivity {
    private EditText firstname,id;
    private EditText lastname;
    private EditText gender;
    private EditText rollno;
    private Button adddata;
    private Button showdata;
    private Button updatedata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        gender = findViewById(R.id.gender);
        rollno = findViewById(R.id.rollno);
        adddata = findViewById(R.id.AddData);
        showdata = findViewById(R.id.ShowData);
        updatedata=findViewById(R.id.Update);
        id=findViewById(R.id.id);


        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        showdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowData();
            }
        });
        updatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData(id.getText().toString(),firstname.getText().toString(),lastname.getText().toString(),gender.getText().toString(),Integer.parseInt(rollno.getText().toString()));
            }
        });

    }


    private void insertData() {
        String mfirstname = firstname.getText().toString().trim();
        String mlastname = lastname.getText().toString().trim();
        String mgender = gender.getText().toString().trim();
        int mrollno = Integer.parseInt(rollno.getText().toString().trim());
        SampleDbHelper mDbHelper = new SampleDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SampleContract.SampleEntry.FIRST_NAME, mfirstname);
        values.put(SampleContract.SampleEntry.LAST_NAME, mlastname);
        values.put(SampleContract.SampleEntry.Gender, mgender);
        values.put(SampleContract.SampleEntry.ROLL_NO, mrollno);
        long newRowId = db.insert(SampleContract.SampleEntry.TABLE_NAME, null, values);
        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    private void ShowData() {
        SampleDbHelper mDbHelper = new SampleDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                SampleContract.SampleEntry._ID,
                SampleContract.SampleEntry.FIRST_NAME,
                SampleContract.SampleEntry.LAST_NAME,
                SampleContract.SampleEntry.Gender,
                SampleContract.SampleEntry.ROLL_NO

        };
        Cursor cursor = db.query(
                SampleContract.SampleEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null

        );
        if (cursor.getCount() == 0) {
            showMessage("Error", "Nothing Found");
            return;
        }
        int idcolumnindex = cursor.getColumnIndex(SampleContract.SampleEntry._ID);
        int fcolumnindex = cursor.getColumnIndex(SampleContract.SampleEntry.FIRST_NAME);
        int lcolumnindex = cursor.getColumnIndex(SampleContract.SampleEntry.LAST_NAME);
        int gdcolumnindex = cursor.getColumnIndex(SampleContract.SampleEntry.Gender);
        int rdcolumnindex = cursor.getColumnIndex(SampleContract.SampleEntry.ROLL_NO);


        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append("ID" + cursor.getString(idcolumnindex));
            buffer.append("First Name" + cursor.getString(fcolumnindex));
            buffer.append("Last Name" + cursor.getString(lcolumnindex));
            buffer.append("Gender" + cursor.getString(gdcolumnindex));
            buffer.append("Roll No" + cursor.getString(rdcolumnindex));

        }
        showMessage("Data", buffer.toString());

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public boolean UpdateData(String id, String firstname, String lastname, String gender, int rollno) {
        SampleDbHelper mDbHelper = new SampleDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SampleContract.SampleEntry._ID, id);
        values.put(SampleContract.SampleEntry.FIRST_NAME, firstname);
        values.put(SampleContract.SampleEntry.LAST_NAME, lastname);
        values.put(SampleContract.SampleEntry.Gender, gender);
        values.put(SampleContract.SampleEntry.ROLL_NO, rollno);
        db.update(SampleContract.SampleEntry.TABLE_NAME,values,"_ID=?",new String[] {id});
        return true;




    }
}