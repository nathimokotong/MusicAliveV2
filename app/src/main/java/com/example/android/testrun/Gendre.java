package com.example.android.testrun;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by Codetribe on 2016/12/02.
 */

public class Gendre implements Serializable {

    private String songName;
    private String downloadu;
private String Email;



    //we can have two constractors

    //_____________________first
    public Gendre(String d , String m)
    {
        songName = d;
        downloadu = m;

    }
    //_____________________secound
    public Gendre( String email,String songnm, String downloaduri)
    {

        Email = email;
        songName = songnm;
        downloadu = downloaduri;

    }

    public String getSongName()
    {
        return songName;
    }

    public String getDownloadu()
    {
        return downloadu;
    }


    public String getEmail()
    {
        return Email;
    }

}

