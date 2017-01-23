package com.example.android.testrun.FirebaseRecycle;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.testrun.R;

/**
 * Created by Manto on 18-Jan-17.
 */

public class MyViewHolder extends RecyclerView.ViewHolder
{
    TextView songTxt;


    public MyViewHolder (View itemView)
    {
        super(itemView);

        songTxt = (TextView)itemView.findViewById(R.id.songnameID);

    }
}
