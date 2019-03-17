package com.example.tutorgenievf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class ActivityRegistration extends Activity
{
    private Button btn_Submit;
    private Button btn_Clear;
    private EditText edt_Name;
    private ImageView img_Male;
    private ImageView img_Female;
    private ImageView img_Other;
    private EditText edt_Date_of_Birth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);
    }
}
