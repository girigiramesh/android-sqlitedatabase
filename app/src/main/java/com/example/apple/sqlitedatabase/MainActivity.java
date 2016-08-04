package com.example.apple.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText student_name,student_surname,student_marks;
    Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);

        student_name = (EditText) findViewById(R.id.student_name);
        student_surname = (EditText) findViewById(R.id.student_surname);
        student_marks = (EditText) findViewById(R.id.student_marks);
        btn_add = (Button) findViewById(R.id.btn_add);
        AddData();
    }
    public void AddData(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = mydb.insertData(student_name.getText().toString(),student_surname.getText().toString(),student_marks.getText().toString());
                if(isInserted == true)
                    Toast.makeText(MainActivity.this, "Data is Add Sucessfully", Toast.LENGTH_LONG).show();
                else

                    Toast.makeText(MainActivity.this, "No Data is Found", Toast.LENGTH_LONG).show();

            }
        });
    }
}
