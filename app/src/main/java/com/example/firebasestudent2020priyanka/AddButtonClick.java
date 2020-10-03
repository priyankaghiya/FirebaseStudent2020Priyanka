package com.example.firebasestudent2020priyanka;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class AddButtonClick extends Activity
{
    String email;
    String password;
    EditText et_email;
    EditText et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_button_click);

        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);

        Intent ik=getIntent();
        email=ik.getStringExtra("email");
        password=ik.getStringExtra("password");

        et_email.setText(email);
        et_password.setText(password);

    }
}