package com.example.android.testrun;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
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
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;


public class Artistmenu extends AppCompatActivity
{

    private RecyclerView recyclerView;
    RecyclerView rv;
    DatabaseReference reference;
    ItemClickListener itemClickListener;
    String link = "http://pitchfork-cdn.s3.amazonaws.com/longform/381/streaming-2.png";
    FirebaseRecyclerAdapter<Artist,FireViewHolder> fireAdapter;
    String streamlink;
    ImageButton streambtn;
    String username;
    SharedPreferences preferences;
    String[] links;
    CardView cardView;
    boolean isplaying = false;
    private MediaPlayer mediaPlayer;
    private TelephonyManager telephonyManager;
    private PhoneStateListener phoneStateListener;
    private boolean ongoingCall = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artistmenu);
        mediaPlayer = new MediaPlayer();
       callStateListener();

        //get users name from shared preference
        preferences = getSharedPreferences("User",0);

        username = preferences.getString("username","not answered");


        cardView = (CardView)findViewById(R.id.cardview);
    //Recycler view...............
        recyclerView = (RecyclerView)findViewById(R.id.recycview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send a Query to the database
        reference = FirebaseDatabase.getInstance().getReference("Artist").child(username);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null)
                {
                    Toast.makeText(Artistmenu.this,"No Data to display, add song(s)",Toast.LENGTH_LONG).show();
                }
                else
                {
                    fireAdapter =  new FirebaseRecyclerAdapter<Artist, FireViewHolder>(Artist.class,R.layout.design_row_cardview,FireViewHolder.class,reference)
                    {

                        @Override
                        protected void populateViewHolder(final FireViewHolder viewHolder, final Artist model, final int position) {

                            links = new String[]{Integer.toString(position)};
                            viewHolder.setTextSongName(model.getSongName());
                            viewHolder.setImage(link);
                            viewHolder.txtViewlike.setText(model.getLikes());
                            viewHolder.txtViewDislike.setText(model.getLikes());

                            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //   Toast.makeText(Artistmenu.this,model.getDownloadu(),Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Artistmenu.this,Comment.class);

                                    intent.putExtra("Path",model.getDownloadu());
                                    intent.putExtra("Likes",model.getLikes());
                                    startActivity(intent);
                                }
                            });


                        }
                    };

                    recyclerView.setAdapter(fireAdapter);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

                Toast.makeText(Artistmenu.this,"Please load data",Toast.LENGTH_LONG).show();

            }
        });


    }


//Castom view holder to make interactions for each card
    public static class FireViewHolder extends  RecyclerView.ViewHolder {

        private View mView;
        CardView cardView;
        TextView txtViewlike;
        TextView txtViewDislike;
        Uri streamline;
        ImageButton imageButton;

        public FireViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            cardView = (CardView)itemView.findViewById(R.id.cardview);
            streamline  = Uri.parse("");

            txtViewDislike = (TextView)itemView.findViewById(R.id.txtDisikes);
            txtViewlike = (TextView)itemView.findViewById(R.id.txtLikes);
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



    }

    //when a call is received
//when someone calls
    private void callStateListener() {
        // Get the telephony manager
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //Starting listening for PhoneState changes
        phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    //if at least one call exists or the phone is ringing
                    //pause the MediaPlayer
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                    case TelephonyManager.CALL_STATE_RINGING:
                        if (mediaPlayer != null) {
                            mediaPlayer.pause();
                            ongoingCall = true;
                        }
                        break;
                    case TelephonyManager.CALL_STATE_IDLE:
                        // Phone idle. Start playing.
                        if (mediaPlayer != null) {
                            if (ongoingCall) {
                                ongoingCall = false;
                                mediaPlayer.start();
                            }
                        }
                        break;
                }
            }
        };
        // Register the listener with the telephony manager
        // Listen for changes to the device call state.
        telephonyManager.listen(phoneStateListener,
                PhoneStateListener.LISTEN_CALL_STATE);
    }



}
//_____________________________________________________________________________

