package com.example.sqlliteapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText namebox,surnamebox,marksbox,idbox;
    Button adddata,viewdata,updatedata,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        namebox=(EditText)findViewById(R.id.namebox);
        surnamebox=(EditText)findViewById(R.id.surnamebox);
        marksbox=(EditText)findViewById(R.id.marksbox);
        adddata=(Button)findViewById(R.id.adddata);
        viewdata=(Button)findViewById(R.id.viewdata);
        updatedata=(Button)findViewById(R.id.updatedata);
        delete=(Button)findViewById(R.id.delete);
        idbox=(EditText)findViewById(R.id.idbox);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }
    public void DeleteData()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedrows = myDB.deleteData(idbox.getText().toString());
                if(deletedrows>0)
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data Delete Failed ",Toast.LENGTH_LONG).show();
            }
        }
    );
    }
    public void UpdateData(){
        updatedata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdated = myDB.upDateData(idbox.getText().toString(),namebox.getText().toString(),surnamebox.getText().toString(),marksbox.getText().toString());
                        if(isUpdated==true)
                            Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data Update Failed",Toast.LENGTH_LONG).show();
                        }
                }
        );
    }
    public void AddData(){
        adddata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean isInserted = myDB.insertData(namebox.getText().toString(),surnamebox.getText().toString(),marksbox.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data Insert Failed",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void viewAll(){
        viewdata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res=myDB.getAllData();
                        if(res.getCount()==0){
                            //show msg
                            showMessage("Error","no data found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("ID:"+res.getString(0)+"\n");
                            buffer.append("NAME:"+res.getString(1)+"\n");
                            buffer.append("SURNAME:"+res.getString(2)+"\n");
                            buffer.append("MARKS:"+res.getString(3)+"\n\n");
                        }
                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}