package com.example.android.testrun;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



import java.io.IOException;

/**
 * Created by Codetribe on 2016/11/25.
 */

public class MotherClass {


    String gnr;
  //  private int PICKFILE_RESULT_CODE;
    private int x;
    Activity activity;

    private TelephonyManager telephonyManager;
    private PhoneStateListener phoneStateListener;


    public  MotherClass()
    {




    }

//    public  MotherClass(int i)
//    {
//
//        PICKFILE_RESULT_CODE = 1001 ;
//
//
//    }

//    public void popup()
//    {
//
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("audio/mpeg");
//        activity.startActivityForResult(intent,PICKFILE_RESULT_CODE);
//    }



    public void playsong(Uri pathofsong, int t)
    {


        try {
            CountDownTimer timer = null;

            final String pathsss = pathofsong.toString();
            final MediaPlayer mediaplayer = new MediaPlayer();
            mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaplayer.reset();
            mediaplayer.setDataSource(pathsss);
            mediaplayer.prepare();
            mediaplayer.start();

            final int dur = mediaplayer.getDuration();

            if (t >=  20000)
            {   timer = new CountDownTimer(20000,1000) {
                @Override
                public void onTick(long l) {

                }
                @Override
                public void onFinish()
                {
                    mediaplayer.stop();
                }
            }.start();
            }

            if(t < 20000)
            {
                int sec = (int) ((t / 1000) % 60);
                sec = sec * 1000;
                timer = new CountDownTimer(sec,1000) {
                    @Override
                    public void onTick(long l) {

                    }
                    @Override
                    public void onFinish()
                    {
                        mediaplayer.stop();
                    }
                }.start();

            }




        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void songplaying(String pathofsong)
    {


        try {
            CountDownTimer timer = null;

            final String pathsss = pathofsong.toString();
            final MediaPlayer mediaplayer = new MediaPlayer();
            mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaplayer.reset();
            mediaplayer.setDataSource(pathsss);
            mediaplayer.prepare();
            mediaplayer.start();

            final int dur = mediaplayer.getDuration();


        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public int getDuration(Uri path)
    {


        try {
            final String pathsss = path.toString();
            final MediaPlayer mediaplayer = new MediaPlayer();
            mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaplayer.reset();
            mediaplayer.setDataSource(pathsss);
            x = mediaplayer.getDuration();

        }
        catch (Exception e)
        {

        }

        return x;
    }


public void writeCat(String gen, String songnm, Uri downloaduri)
{
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    DatabaseReference usersRef = ref.child("Genre").child(gen);
    usersRef.push().setValue(new Gendre(songnm,downloaduri.toString()));

}

    public void writeArtist(String name, String email,String songnm, Uri downloaduri)
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        DatabaseReference usersRef = ref.child("Artist").child(name);
        usersRef.push().setValue(new Gendre(email,songnm,downloaduri.toString()));

    }




    //_____________________animation______________________
    public Animation didTapButton(Context context) {


        final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        return myAnim;
    }

    public void rotateFabForward(FloatingActionButton fab) {
        ViewCompat.animate(fab)
                .rotation(135.0F)
                .withLayer()
                .setDuration(300L)
                .setInterpolator(new OvershootInterpolator(10.0F))
                .start();
    }

    public void rotateFabBackward(FloatingActionButton fab) {
        ViewCompat.animate(fab)
                .rotation(0.0F)
                .withLayer()
                .setDuration(300L)
                .setInterpolator(new OvershootInterpolator(10.0F))
                .start();
    }
}
