package com.example.firebasestudent2020priyanka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity
{
    EditText etEmail,etPass;
    Button btlog,btreg;
    ProgressBar prog;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth=FirebaseAuth.getInstance();//these will have the reference of the connection of our
        //project with the firebase
        //here  FirebaseAuth means Authentication in the firebase on browser

        etEmail=findViewById(R.id.email);
        etPass=findViewById(R.id.password);

        btlog=findViewById(R.id.login);
        btreg=findViewById(R.id.register);

        prog=findViewById(R.id.progressBar);


        btreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String vmail=etEmail.getText().toString();
                String vpass=etPass.getText().toString();

                etEmail.setText("");
                etPass.setText("");


                //onComplete method meaning that your program has been connected
                fAuth.createUserWithEmailAndPassword(vmail,vpass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"USER HAS BEEN SUCCESSFULLY REGISTERED..........",Toast.LENGTH_LONG).show();
                            }
                            else
                                {
                                    Toast.makeText(getApplicationContext(),"REGISTERATION FAILED........"+task.getException(),Toast.LENGTH_LONG).show();
                            }

                        }
                    });
            }
        });

        btlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vmail=etEmail.getText().toString();
                String vpass=etPass.getText().toString();

                etEmail.setText("");
                etPass.setText("");

                fAuth.signInWithEmailAndPassword(vmail,vpass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(),"WEL-COME AUTHORISED USER",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"YOU ARE UNAUTHORISED USER",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
