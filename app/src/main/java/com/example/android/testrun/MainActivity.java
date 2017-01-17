package com.example.android.testrun;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

import static android.support.test.InstrumentationRegistry.getContext;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]
    String username;
    private GoogleApiClient mGoogleApiClient;
    CountDownTimer timer = null;

    Button Musicbtn;
    Button MusicUplbtn;
    Button MusicRatBtn;

    //FloatingActionMenu materialDesignFAM;
    FloatingActionButton fABmain;
    FloatingActionButton fabsign;
    //floating button bool
    Boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        Musicbtn = (Button) findViewById(R.id.BtnMusicGal);
        MusicUplbtn = (Button) findViewById(R.id.Btnupload);
        MusicRatBtn = (Button) findViewById(R.id.Btnrating);

        Musicbtn.startAnimation(myAnim);
        MusicUplbtn.startAnimation(myAnim);
        MusicRatBtn.startAnimation(myAnim);

        Musicbtn.setClickable(true);
        MusicUplbtn.setClickable(true);
        MusicRatBtn.setClickable(true);

        final Animation clic = didTapButton();


        //_____________AUTHENTICATION___________________________________________

        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(AppIndex.API).build();

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                username = user.getDisplayName();
                } else {

                    Intent intent = new Intent(MainActivity.this, SignIN.class);
                    startActivity(intent);
                }
                // [START_EXCLUDE]
                //updateUI(user);
                // [END_EXCLUDE]
            }
        };
        // [END auth_state_listener]


        //____________________On Click listener__________________________________________________________

        Musicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Musicbtn.startAnimation(clic);
                Musicbtn.setClickable(false);
                MusicUplbtn.setClickable(false);
                MusicRatBtn.setClickable(false);
                timer = new CountDownTimer(3000,1000) {
                    @Override
                    public void onTick(long l) {

                    }
                    @Override
                    public void onFinish() {
                        Intent intent = new Intent(MainActivity.this,MusicGallery.class);
                       startActivity(intent);
                    }
                }.start();

            }
        });

        MusicRatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MusicRatBtn.startAnimation(clic);
                Musicbtn.setClickable(false);
                MusicUplbtn.setClickable(false);
                MusicRatBtn.setClickable(false);
                timer = new CountDownTimer(3000,1000) {
                    @Override
                    public void onTick(long l) {

                    }
                    @Override
                    public void onFinish() {

                    }
                }.start();
            }
        });



        MusicUplbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicUplbtn.startAnimation(clic);
                Musicbtn.setClickable(false);
                MusicUplbtn.setClickable(false);
                MusicRatBtn.setClickable(false);
                timer = new CountDownTimer(3000,1000) {
                    @Override
                    public void onTick(long l) {

                    }
                    @Override
                    public void onFinish() {

                        SharedPreferences preferences = getSharedPreferences("User",0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("username",username);
                        editor.commit();

                        Intent intent = new Intent(MainActivity.this,Uploadmusic.class);
                        startActivity(intent);
                    }
                }.start();

            }
        });

        //_______________________floating buttons_________________________________

        fABmain = (FloatingActionButton)findViewById(R.id.fabmain);
        fabsign = (FloatingActionButton)findViewById(R.id.fab);

        fABmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(isOpen)
                {
                    rotateFabBackward(fABmain);
                    fabsign.setClickable(false);
                    fabsign.setVisibility(View.INVISIBLE);
                    isOpen = false;
                }
                else {
                    rotateFabForward(fABmain);
                    fabsign.startAnimation(myAnim);
                    fabsign.setClickable(true);
                    fabsign.setVisibility(View.VISIBLE);

                    isOpen = true;
                }
            }
        });


        fabsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useroption();
            }
        });


    }
//_______________________________Google SIgn in________________________________

    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient.connect();
        mAuth.addAuthStateListener(mAuthListener);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(mGoogleApiClient, getIndexApiAction());
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mGoogleApiClient, getIndexApiAction());
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient.disconnect();
    }
    // [END on_stop_remove_listener]

    // [Set up display according to user info]
    private void updateUI(FirebaseUser user) {

        if (user != null) {
         //   username = (TextView)findViewById(R.id.usernameTxt);
           // username.setText(user.getDisplayName());
        } else {

           // findViewById(R.id.usernameTxt).setVisibility(View.VISIBLE);

        }
    }



    //________________________________________________________________________


    //__________________________sign out-___________________________________
    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateUI(null);
                    }
                });
    }

    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateUI(null);
                    }
                });
    }


    //_____________________animation______________________
    public Animation didTapButton() {


        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
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

//__________________________________________________________


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(MainActivity.this,"No Connection",Toast.LENGTH_SHORT).show();

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    //___________________________user sign out
    public void useroption()
    {

        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Would you like to sign out ?");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                signOut();
                Intent intent = new Intent(MainActivity.this, SignIN.class);
                startActivity(intent);
            }
        });

        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                builder1.setCancelable(true);
            }
        });

        builder1.show();
    }

    //___________________________________________________
@Override
public void onBackPressed()
{ super.onBackPressed(); // optional //you may put your intent here, putExtra, startActivity

    finish();
    startActivity(getIntent());
 }

}
