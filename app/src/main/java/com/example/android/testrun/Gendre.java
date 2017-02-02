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
private String likes;
    private String dislikes;



//we can have two constractors

    public Gendre(String d)
    {
        songName = d;


    }

    //_____________________first
    public Gendre(String d, String m)
    {
        songName = d;
        downloadu = m;

    }
    //_____________________secound
    public Gendre(String email, String songnm, String downloaduri,String like,String dslike)
    {

        Email = email;
        songName = songnm;
        downloadu = downloaduri;
        likes = like;
        dislikes = dslike;
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

    public String getLikes() {
        return likes;
    }

    public String getDislikes() {
        return dislikes;
    }
}

