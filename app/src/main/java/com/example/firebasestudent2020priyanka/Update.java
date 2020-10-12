package com.example.firebasestudent2020priyanka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Update extends Activity
{
    Spinner spUserUpdMail;
    EditText etUserUpdPass,etUserUpdName,etUserUpdAddr,etUserUpdPhone,etUserUpdPer;
    Button btUserUpdSub,btUserUpdCan;
    String pass,name,addr,phone;
    float per;
    String mail;
    String unId;
    DatabaseReference dbref,dbupd;
    List<Student> stlist=new ArrayList<Student>();//db mathi jetla pan records avse te akho record store karva records object form ma avse
    List<String>maillist=new ArrayList<String>(); //spinner par mail batava mathe ane mail string ma avse

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        spUserUpdMail=findViewById(R.id.spUserUpdMail);
        etUserUpdPass=findViewById(R.id.etUserUpdAddr);
        etUserUpdName=findViewById(R.id.etUserUpdName);
        etUserUpdAddr=findViewById(R.id.etUserUpdAddr);
        etUserUpdPhone=findViewById(R.id.etUserUpdPhone);
        etUserUpdPer=findViewById(R.id.etUserUpdPer);

        btUserUpdSub=findViewById(R.id.btUserUpdSub);
        btUserUpdCan=findViewById(R.id.btUserUpdCancel);

        dbref= FirebaseDatabase.getInstance().getReference("students");

        dbref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                stlist.clear(); // har time record avse toh pehla akhi screen clear thase and then new records add thase
                maillist.clear();

                for(DataSnapshot snap:snapshot.getChildren())
                {
                    Student st=snap.getValue(Student.class);
                    stlist.add(st);
                    maillist.add(st.getEmail());
                }
                ArrayAdapter<String>ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,maillist);
                spUserUpdMail.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

        spUserUpdMail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id)//integer i  will contain the index of the item(record)
            {
                Student stud=stlist.get(i);

                unId=stud.getUnId();
                mail=stud.getEmail();

                etUserUpdName.setText(stud.getSname());
                etUserUpdPass.setText(stud.getPass());
                etUserUpdAddr.setText(stud.getAddr());
                etUserUpdPhone.setText(stud.getPhone());
                etUserUpdPer.setText(""+stud.getPer());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        btUserUpdSub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pass=etUserUpdPass.getText().toString();
                name=etUserUpdName.getText().toString();
                addr=etUserUpdAddr.getText().toString();
                phone=etUserUpdPhone.getText().toString();
                per=Float.parseFloat(etUserUpdPer.getText().toString());

                dbupd=FirebaseDatabase.getInstance().getReference("students");

                dbupd.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        for(DataSnapshot snap:snapshot.getChildren())
                        {
                            Student st=snap.getValue(Student.class);
                            if(st.getUnId().equals(unId))
                            {
                                dbupd=FirebaseDatabase.getInstance().getReference("students").child(unId);

                                Student stt=new Student(unId,mail,pass,name,addr,phone,per);

                                dbupd.setValue(stt);
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {

                    }
                });

            }
        });

    }
}