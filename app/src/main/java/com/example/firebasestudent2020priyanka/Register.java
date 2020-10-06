package com.example.firebasestudent2020priyanka;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity
{
    String val_email;
    String val_pass;
    Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);

        Intent ii=getIntent();
        val_email=ii.getStringExtra("vmail");
        val_pass=ii.getStringExtra("vpass");
        Toast.makeText(getApplicationContext(), val_email+" , "+val_pass, Toast.LENGTH_LONG).show();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent ij2=new Intent(getApplicationContext(),AddButtonClick.class);
                ij2.putExtra("email",val_email);
                ij2.putExtra("password",val_pass);
                startActivity(ij2);
//                finish();



            }
        });

    }
}