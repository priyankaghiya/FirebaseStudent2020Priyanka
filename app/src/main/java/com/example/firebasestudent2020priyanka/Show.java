package com.example.firebasestudent2020priyanka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Show extends Activity
{
    ListView lv;
    List<Student> list=new ArrayList<Student>();
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        lv=findViewById(R.id.lv);

        dbref= FirebaseDatabase.getInstance().getReference("students"); //confirm that only the student records will come

        dbref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)//will be called when all the data are converted into objects
            {
                list.clear(); //for clearing the old records
                for(DataSnapshot snap : snapshot.getChildren())//as we donot know the no of records these for loop will work for that many times as the number of recoords
                {
                    Student st=snap.getValue(Student.class);
                    list.add(st);
                }

                //Collection here (list) is the advanced java and it cannot be directly fitted into the component of the Android
                //for that we have to use the ArrayAdapter in that first the Collection is given to the android development and then the
                //andoid component listView is givent the adapter

                //when add to list and add that list onto the array adapter
                //first parameter - for which application, second parameter - what will be the view,what data
                ArrayAdapter<Student> ad=new ArrayAdapter<Student>(getApplicationContext(),android.R.layout.simple_list_item_1,list);
                lv.setAdapter(ad);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }
}