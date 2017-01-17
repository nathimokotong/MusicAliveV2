package com.example.android.testrun;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.testrun.Data.Artist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Artistmenu extends AppCompatActivity {

    Button btnNje;
    TextView downloadlink,email,songname;
    ListView lv;
    ArrayList<String> info = new ArrayList<>();
    DatabaseReference db;
    String username;
    private FirebaseAuth.AuthStateListener mAuthListener;
    SharedPreferences preferences;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artistmenu);
//btnNje = (Button)findViewById(R.id.buttonshow);
        lv = (ListView)findViewById(R.id.listArtistsongz);
//        db = FirebaseDatabase.getInstance().getReference();
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new RecyclerAdapter());


        preferences = getSharedPreferences("User",0);

        username = preferences.getString("username","not answered");

        this.retrievedata();


//        btnNje.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                retrievedata();
//            }
//        });

    }

//Retrieve data
    private void retrievedata()
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

//        DatabaseReference usersRef = database.getReference();

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Artist").child(username);

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Artist a = new Artist();
                for (DataSnapshot child : dataSnapshot.getChildren())
                {


                    //a.setDownloadlink(dataSnapshot.getValue(Artist.class).getDownloadlink());
                    //a.setEmail(dataSnapshot.getValue(Artist.class).getEmail());
                    //a.setSongname(dataSnapshot.getValue(Artist.class).getSongname());

                    info.add(dataSnapshot.child("songName").getValue(String.class));
               //     info.add(a.getDownloadlink());
                 //   info.add(a.getEmail());
                  //  info.add(a.getSongname());
                }

                if(info.size()>0)
                {
                    Toast.makeText(Artistmenu.this,"there is something",Toast.LENGTH_LONG).show();

                    ArrayAdapter arrayAdapter = new ArrayAdapter(Artistmenu.this,android.R.layout.simple_list_item_1,info);
                    lv.setAdapter(arrayAdapter);
                }
                else
                {
                    Toast.makeText(Artistmenu.this,"Nothing to show",Toast.LENGTH_LONG).show();
                    lv.setVisibility(View.GONE);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });



    }


    private void getUpdates(DataSnapshot ds)
    {
        Artist a = new Artist();

        for(DataSnapshot data: ds.getChildren())
        {
            a.setDownloadlink(data.getValue(Artist.class).getDownloadlink());
            a.setEmail(data.getValue(Artist.class).getEmail());
            a.setSongname(data.getValue(Artist.class).getSongname());

            info.add(a.getDownloadlink());
            info.add(a.getEmail());
            info.add(a.getSongname());
        }

        if(info.size()>0)
        {
            Toast.makeText(Artistmenu.this,"there is something",Toast.LENGTH_LONG).show();

            ArrayAdapter arrayAdapter = new ArrayAdapter(Artistmenu.this,android.R.layout.simple_list_item_1,info);
            lv.setAdapter(arrayAdapter);
        }
        else
        {
            Toast.makeText(Artistmenu.this,"Nothing to show",Toast.LENGTH_LONG).show();
            lv.setVisibility(View.GONE);
        }

    }

}
