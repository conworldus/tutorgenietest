package com.example.tutorgenievf;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class ActivityRegistration extends Activity
{
    private Button btn_Submit;
    private Button btn_Clear;
    private EditText edt_Name;
    private ImageView img_Male;
    private ImageView img_Female;
    private ImageView img_Other;
    private EditText edt_Date_of_Birth;
    private EditText edt_Email;
    private DatePickerDialog datePickerDialog;
    private Context context = this;

    private short GenderSelect = Helper.NULL;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.registration_page);
        btn_Submit = findViewById(R.id.button_submit);
        btn_Clear = findViewById(R.id.button_clear);
        edt_Name = findViewById(R.id.name_input);
        edt_Date_of_Birth = findViewById(R.id.dob_input);
        img_Male=findViewById(R.id.male_image);
        img_Female=findViewById(R.id.female_image);
        img_Other=findViewById(R.id.other_image);
        edt_Email=findViewById(R.id.email_input);
        initControls();
    }


    public void initControls()
    {
        edt_Date_of_Birth.setInputType(InputType.TYPE_NULL);
        edt_Date_of_Birth.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                Helper.HideKeyboard(context, v);
                datePickerDialog = new DatePickerDialog(context);
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        edt_Date_of_Birth.setText(String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(dayOfMonth));
                    }
                });

                datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, getString(R.string.control_ok), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        datePickerDialog.dismiss();
                    }
                });

                datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, getString(R.string.control_cancel), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        edt_Date_of_Birth.getText().clear();
                        datePickerDialog.dismiss();
                    }
                });

                datePickerDialog.show();
                return false;
            }
        });

        img_Male.setImageResource(R.drawable.boysymbol);
        img_Female.setImageResource(R.drawable.girlsymbol);
        img_Other.setImageResource(R.drawable.genderneutral);

        img_Male.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                clearGenderSelection();
                img_Male.setBackgroundColor(Color.LTGRAY);
                GenderSelect = Helper.GENDER_MALE;
            }
        });

        img_Female.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                clearGenderSelection();
                img_Female.setBackgroundColor(Color.LTGRAY);
                GenderSelect = Helper.GENDER_FEMALE;
            }
        });

        img_Other.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                clearGenderSelection();
                img_Other.setBackgroundColor(Color.LTGRAY);
                GenderSelect = Helper.GENDER_NEUTRAL;
            }
        });

        btn_Clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                edt_Date_of_Birth.getText().clear();
                edt_Name.getText().clear();
                edt_Email.getText().clear();
                clearGenderSelection();
                GenderSelect = Helper.NULL;
            }
        });

        btn_Submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!validate())
                {
                    return;
                }
                registerUser();
            }
        });
    }

    public void clearGenderSelection()
    {
            img_Male.setBackgroundColor(Color.TRANSPARENT);
            img_Other.setBackgroundColor(Color.TRANSPARENT);
            img_Female.setBackgroundColor(Color.TRANSPARENT);
    }

    public boolean validate()
    {
        //return the code
        /*
        * 0=success
        * 1000=name empty
        * 0100=dob empty or wrong format
        * 0010=gender not selected
        * 0001=email empty or wrong format*/
        return true;
    }

    public void registerUser()
    {
        String name = edt_Name.getText().toString();
        String dob = edt_Date_of_Birth.getText().toString();
        String email = edt_Email.getText().toString();
        String gender = null;

        switch (GenderSelect)
        {
            case Helper.GENDER_FEMALE: gender=getString(R.string.option_female); break;
            case Helper.GENDER_MALE: gender=getString(R.string.option_male); break;
            case Helper.GENDER_NEUTRAL: gender=getString(R.string.option_unknown); break;
        }
        //send the user data to Server
        HashMap<String, String> datamap = new HashMap<String, String>();
        datamap.put("Name", name);
        datamap.put("DOB", dob);
        datamap.put("Email", email);
        datamap.put("Gender", gender);
        Helper.SendDataForResultCode(datamap, Helper.DATATYPE_HASHMAP);
    }
}
