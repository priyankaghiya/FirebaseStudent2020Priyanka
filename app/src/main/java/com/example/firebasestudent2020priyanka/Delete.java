package com.example.firebasestudent2020priyanka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Delete extends Activity {

    Spinner spUserDelMail;
    Button btUserDelDel,btUserDelCan;
    List<String> maillist=new ArrayList<String>();
    DatabaseReference dblist,dbdel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        spUserDelMail=findViewById(R.id.spUserDelMail);
        btUserDelDel=findViewById(R.id.btUserDelDel);
        btUserDelCan=findViewById(R.id.btUserDelCan);

        dblist= FirebaseDatabase.getInstance().getReference("students");

        dblist.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                maillist.clear();

                for(DataSnapshot snap: snapshot.getChildren())
                {
                    Student st=snap.getValue(Student.class);
                    maillist.add(st.getEmail());
                }
                //listvview and spinner ma direct data put thai nai so we use the ArrayAdapter

                ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,maillist);
                spUserDelMail.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {


            }
        });

        dbdel=FirebaseDatabase.getInstance().getReference();


        btUserDelDel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String mail=spUserDelMail.getSelectedItem().toString();
                Query delQ=dbdel.child("students").orderByChild("email").equalTo(mail);
                //now these query may contain more than record ie some mail id may be same
                delQ.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        for(DataSnapshot snap: snapshot.getChildren())
                        {
                            snap.getRef().removeValue();
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