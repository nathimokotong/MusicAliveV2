package com.example.android.testrun.Data;

/**
 * Created by Manto on 16-Jan-17.
 */

public class Artist {

    private String downloadlink;
    private String email;
    private  String songname;

    public Artist()
    {

    }

    public String getDownloadlink() {
        return downloadlink;
    }

    public void setDownloadlink(String downloadlink) {
        this.downloadlink = downloadlink;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }
}
