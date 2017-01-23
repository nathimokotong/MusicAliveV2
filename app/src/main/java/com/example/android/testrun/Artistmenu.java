package com.example.android.testrun;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.testrun.Data.Artist;
import com.example.android.testrun.Data.ItemClickListener;
import com.example.android.testrun.FirebaseRecycle.FirebaseHelper;
import com.example.android.testrun.FirebaseRecycle.MyAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


public class Artistmenu extends AppCompatActivity
{

    private RecyclerView recyclerView;
    DatabaseReference reference;
    ItemClickListener itemClickListener;
    String link = "http://pitchfork-cdn.s3.amazonaws.com/longform/381/streaming-2.png";
    FirebaseRecyclerAdapter<Artist,FireViewHolder> fireAdapter;
    String streamlink;
    ImageButton streambtn;
    CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artistmenu);

        Toast.makeText(Artistmenu.this,"Loading your songs may take a while, please be patient!",Toast.LENGTH_LONG).show();

        streambtn = (ImageButton)findViewById(R.id.btnArtStream);
        cardView = (CardView)findViewById(R.id.cardview);
    //Recycler view...............
        recyclerView = (RecyclerView)findViewById(R.id.recycview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send a Query to the database
        reference = FirebaseDatabase.getInstance().getReference("Artist").child("Nkosinathi Mokotong"); //fix this in time

        fireAdapter =  new FirebaseRecyclerAdapter<Artist, FireViewHolder>(Artist.class,R.layout.design_row_cardview,FireViewHolder.class,reference)
        {

            @Override
            protected void populateViewHolder(FireViewHolder viewHolder, Artist model, int position) {

                streamlink = Integer.toString(position);
              viewHolder.setTextSongName(model.getSongName());
                viewHolder.setImage(link );
               // viewHolder.itemClickListener.onItemClick(position);

            }
        };

         recyclerView.setAdapter(fireAdapter);


    }



    public static class FireViewHolder extends  RecyclerView.ViewHolder {

        private View mView;
        ItemClickListener itemClickListener;

        public FireViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }


        public void setTextSongName(String name) {


            TextView tvSongName = (TextView) mView.findViewById(R.id.songnameID);
            tvSongName.setText(name);
        }

        public void  setImage(String uri)
        {
            ImageView albumpic = (ImageView)itemView.findViewById(R.id.alubcover);
            Picasso.with(mView.getContext()).load(uri).into(albumpic);


        }
        public void showthis()
        {


        }



    }


private void retrievedata()
    //Retrieve data
    {}
}
//_____________________________________________________________________________

