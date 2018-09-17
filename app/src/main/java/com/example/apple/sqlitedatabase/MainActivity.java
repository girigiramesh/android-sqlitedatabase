package com.example.apple.sqlitedatabase;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText student_name, student_surname, student_marks, updating_id, updating_phone;
    Button btn_add;
    Button btn_view;
    Button btn_update, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);

        student_name = (EditText) findViewById(R.id.student_name);
        student_surname = (EditText) findViewById(R.id.student_surname);
        student_marks = (EditText) findViewById(R.id.student_marks);
        updating_id = (EditText) findViewById(R.id.updating_id);
        updating_phone = (EditText) findViewById(R.id.updating_phone);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_view = (Button) findViewById(R.id.btn_view);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        AddData();
        ViewAll();
        updateData();
        deleteData();
    }

    public void deleteData() {
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteRows = mydb.deleteData(updating_id.getText().toString());
                if (deleteRows > 0)
                    Toast.makeText(MainActivity.this, "Data is Deleted Sucessfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Nothing to delete", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void updateData() {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = mydb.updateData(updating_id.getText().toString(), student_name.getText().toString(), student_surname.getText().toString(), student_marks.getText().toString(), Long.parseLong(updating_phone.getText().toString()));
                if (isUpdate == true)
                    Toast.makeText(MainActivity.this, "Data is updated Sucessfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "No Data is Found", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void AddData() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = mydb.insertData(student_name.getText().toString(), student_surname.getText().toString(), student_marks.getText().toString(), Long.parseLong(updating_phone.getText().toString()));
                if (isInserted == true)
                    Toast.makeText(MainActivity.this, "Data is Add Sucessfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "No Data is Found", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ViewAll() {
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mydb.getAllData();
                if (res.getCount() == 0) {
                    ShowMessage("Error", "Nothings");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("NAME :" + res.getString(1) + "\n");
                    buffer.append("SURNAME :" + res.getString(2) + "\n");
                    buffer.append("MARKS :" + res.getString(3) + "\n\n");
                     buffer.append("PHONE :" + res.getLong(4) + "\n\n");
                }
                ShowMessage("Data", buffer.toString());
            }
        });
    }

    public void ShowMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
