package com.example.firebasestudent2020priyanka;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddButtonClick extends Activity
{
    String email;
    String password;
    EditText etUserRegId,etUserRegPass,etUserRegName,etUserRegAddr,etUserRegPhone,etUserRegPer;
    Button btnUserRegSub,btnUserRegCan;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_button_click);

        dbref=FirebaseDatabase.getInstance().getReference("students"); //here dbref will contain the reference of the database in the firebase
        //and .getInstance().getRefernce() would take the reference and would create the child node named students
        //if the students child is not created then it would create it and if it is created then it would take its reference

        etUserRegId=findViewById(R.id.et_email);
        etUserRegPass=findViewById(R.id.et_password);
        etUserRegName=findViewById(R.id.et_name);
        etUserRegAddr=findViewById(R.id.et_address);
        etUserRegPhone=findViewById(R.id.et_phone);
        etUserRegPer=findViewById(R.id.et_per);

        btnUserRegSub=findViewById(R.id.btn_submit);
        btnUserRegCan=findViewById(R.id.btn_cancel);


        Intent ik=getIntent();
        email=ik.getStringExtra("email");
        password=ik.getStringExtra("password");

        etUserRegId.setText(email);
        etUserRegPass.setText(password);

        btnUserRegSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email=etUserRegId.getText().toString();
                String pass=etUserRegPass.getText().toString();
                String name=etUserRegName.getText().toString();
                String addr=etUserRegAddr.getText().toString();
                String phone=etUserRegPhone.getText().toString();
                Float per=Float.parseFloat(etUserRegPer.getText().toString());

                String unId=dbref.push().getKey(); //  to generate unique id in firebase realtime database

                Student s=new Student(unId,email,pass,name,addr,phone,per);

                dbref.child(unId).setValue(s);

                Toast.makeText(getApplicationContext(),"STUDENT SUCCESSFULLY REGISTERED.....",Toast.LENGTH_LONG).show();

                etUserRegId.setText("");
                etUserRegPass.setText("");
                etUserRegName.setText("");
                etUserRegAddr.setText("");
                etUserRegPhone.setText("");
                etUserRegPer.setText("");

            }
        });

        btnUserRegCan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                etUserRegId.setText("");
                etUserRegPass.setText("");
                etUserRegName.setText("");
                etUserRegAddr.setText("");
                etUserRegPhone.setText("");
                etUserRegPer.setText("");

            }
        });


    }
}