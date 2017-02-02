package com.example.android.testrun;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.core.deps.guava.io.Files;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Comment extends AppCompatActivity {

    public String liks;
    public String link;
    DatabaseReference reference;
    ImageButton downloadbutton;
    ImageButton likebutton;
    ImageButton dislikebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        reference = FirebaseDatabase.getInstance().getReference("Artist").child("Nkosinathi Mokotong");

        downloadbutton = (ImageButton)findViewById(R.id.downloadbtn);
        likebutton = (ImageButton)findViewById(R.id.btnlike);

        Intent intent = this.getIntent();
        link = intent.getExtras().getString("Path"); //for downloading
        liks = intent.getExtras().getString("Likes");

        //download song
        downloadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                //open browswer options to download
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(link));
                startActivity(i);


            }
        });

        //like button
        likebutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                    int i = 1 + Integer.parseInt(liks);
                    String total = Integer.toString(i);
                reference.setValue(total);
            }
        });


    }


}


