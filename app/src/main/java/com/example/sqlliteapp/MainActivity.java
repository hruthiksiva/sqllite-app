package com.example.sqlliteapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText namebox,surnamebox,marksbox;
    Button adddata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        namebox=(EditText)findViewById(R.id.namebox);
        surnamebox=(EditText)findViewById(R.id.surnamebox);
        marksbox=(EditText)findViewById(R.id.marksbox);
        adddata=(Button)findViewById(R.id.adddata);
        AddData();
    }
    public void AddData(){
        adddata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean isInserted = myDB.insertData(namebox.getText().toString(),surnamebox.getText().toString(),marksbox.getText().toString());
                        if(isInserted=true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data Insert Failed",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}