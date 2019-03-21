package com.example.tutorgenievf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class ActivityLoading extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(!CheckExisting())
        {
            boolean t = Registrate();
        }
    }


    public boolean CheckExisting() {
        return false;
    }

    public boolean Registrate()
    {
        Intent i = new Intent(this, ActivityRegistration.class);
        startActivity(i);
        this.finish();
        //To register, we need to go through a three step process
        //Use a List View for This?
        return true; //if successful. otherwise, return false
    }

    public void LoadInfo()
    {
        //if not registered, Load user information.
    }


}
