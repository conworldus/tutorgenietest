package com.example.tutorgenievf;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class ActivityLoading extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


    }


    public boolean CheckExisting() {
        return true;
    }

    public boolean Registrate()
    {
        //To register, we need to go through a three step process
        //Use a List View for This?
        return true; //if successful. otherwise, return false
    }

    public void LoadInfo()
    {
        //if not registered, Load user information.
    }


}
