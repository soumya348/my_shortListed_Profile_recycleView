package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubActivity extends AppCompatActivity {

    TextView name,father,memberId,age,religion,cast,marital;
    String strname,strfather,strmemberId,strage,strreligion,strcast,strmarital;
    String id;
    String HttpURL = "https://mob.odialagna.com/loginAction.php";
    Response response;String responsemsg,responseerrormsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//        }
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//        }
        name =findViewById(R.id.name);
        father =findViewById(R.id.father_name);
        marital = findViewById(R.id.marStatus);
        memberId=findViewById(R.id.memberId);
        age = findViewById(R.id.user_age);
        religion = findViewById(R.id.religion);
        cast=findViewById(R.id.cast);

//        SharedPreferences sharedPreferences2 = SubActivity.this.getSharedPreferences("MySharedPref2", MODE_PRIVATE);
//        if (sharedPreferences2 != null) {
//            String name1 = sharedPreferences2.getString("FirstName", null);
//            name.setText(name1);
//            String  memberid = sharedPreferences2.getString("UserID", null);
//            memberId.setText(memberid);
//            String fathername = sharedPreferences2.getString("FatherFirstName", null);
//            father.setText(fathername);
//            String maritalstatus = sharedPreferences2.getString("MaritalStatus", null);
//            marital.setText(maritalstatus);
//            String age1 = sharedPreferences2.getString("DOB", null);
//            age.setText(age1);
//            String religion1 = sharedPreferences2.getString("Religion", null);
//            religion.setText(religion1);
//            String cast1 = sharedPreferences2.getString("SubCaste", null);
//            cast.setText(cast1);
//            userid=sharedPreferences2.getString("ID",null);
//
//        }

        SharedPreferences sharedPreferences2 = SubActivity.this.getSharedPreferences("MySharedPref2", MODE_PRIVATE);

        String httpResponseMsg=sharedPreferences2.getString("login","");
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(httpResponseMsg);

            JSONArray array = jsonObject.getJSONArray("result");



            for (int i = 0; i < array.length(); i++) {
                JSONObject ob = array.getJSONObject(i);
                id=ob.getString("ID");
                strname=ob.getString("FirstName");
                strfather=ob.getString("FatherFirstName");
                strmemberId=ob.getString("UserID");
                strage=ob.getString("DOB");
                strcast=ob.getString("SubCaste");
                strreligion=ob.getString("Religion");
                strmarital=ob.getString("MaritalStatus");

            }

           // Toast.makeText(this, ""+email+"--"+phone, Toast.LENGTH_SHORT).show();
           // Toast.makeText(Myprofile.this, ""+id, Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        SharedPreferences sharedPreferences1=Myprofile.this.getSharedPreferences("profie",MODE_PRIVATE);
//         String Name=sharedPreferences1.getString("name",null);
//
//         String Email=sharedPreferences1.getString("email",null);
//
//         String Phone=sharedPreferences1.getString("phone",null);
//
//

//         if(sharedPreferences1!=null){

//         }
        SharedPreferences sharedPreferences3 = SubActivity.this.getSharedPreferences("ID", MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences3.edit();

        editor.putString("ID",id);
        editor.putString("FirstName",strname);
        editor.putString("FatherFirstName",strfather);
        editor.putString("UserID",strmemberId);
        editor.putString("DOB",strage);
        editor.putString("SubCaste",strcast);
        editor.putString("Religion",strreligion);
        editor.putString("MaritalStatus",strmarital);
        editor.apply();
        name.setText(strname);
        father.setText(strfather);
        memberId.setText(strmemberId);
        age.setText(strage);
        cast.setText(strcast);
        religion.setText(strreligion);
        marital.setText(strmarital);

    }
}