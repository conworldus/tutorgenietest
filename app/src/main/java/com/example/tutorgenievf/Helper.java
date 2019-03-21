package com.example.tutorgenievf;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.job.JobInfo;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class Helper
{
    public static String dbusername=null;
    public static String dbpassword=null;
    public static String userID=null;
    public static String user_Name=null;
    public static String user_date_of_birth=null;
    public static String user_gender=null;
    public static String user_email=null;
    public static String register_URL="";

    public static final short NULL=0;
    public static final short GENDER_MALE = 1;
    public static final short GENDER_FEMALE = 2;
    public static final short GENDER_NEUTRAL = 3;
    public static final short DATATYPE_HASHMAP = 100;
    public static final short DATAYTPE_IMAGE = 101;

    public static void HideKeyboard(Context context, View view)
    {
        InputMethodManager manager = (InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String SendDataForResultCode(Object obj, short Type)
    {
        if(Type==DATATYPE_HASHMAP)
        {
            JSONObject JObj = new JSONObject();
            HashMap<String, String> map = (HashMap<String, String>)obj;
            try
            {
                Iterator iterator = map.entrySet().iterator();
                Log.d("Entry Count", String.valueOf(map.size()));
                for(Map.Entry<String, String> entry: map.entrySet())
                {
                    String key = entry.getKey();
                    String val = entry.getValue();
                    JObj.put(key, val);
                    Log.d(key, val);
                }
            }
            catch (JSONException e)
            {
                Log.e("JSON", e.getLocalizedMessage());

            }

            //now we send date to server

            //===============================================

                //send datea to server
                //Not Asynchronous
                HttpsURLConnection connection = null;
                String result = "";
                try
                {
                    URL networkObj = new URL(register_URL);
                    connection = (HttpsURLConnection)networkObj.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(5000);

                    DataOutputStream o_stream = new DataOutputStream(connection.getOutputStream());
                    o_stream.writeBytes(JObj.toString());
                    o_stream.flush();
                    o_stream.close();

                    InputStream in = connection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);

                    int read = reader.read();

                    while (read!=-1)
                    {
                        result+=(char)read;
                        read=reader.read();
                    }
                }catch (SocketTimeoutException e)
                {
                    Log.e("Network Err", e.getMessage());
                }
                catch (Exception e)
                {
                    Log.e("Err", e.getLocalizedMessage());
                }


            if(result=="OK")
            {
                //proceed to welcome screen
                Log.d("INFO", "Registration Success");
            }
            else
            {
                //prompt error
                Log.e("Err", "An Error has happened in the registration process");
            }

            return result;
            //===============================================
        }

        else if (Type==DATAYTPE_IMAGE)
        {

        }
        return null;
    }
}






















