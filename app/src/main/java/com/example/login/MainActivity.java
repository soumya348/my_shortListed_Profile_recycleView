package com.example.login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    String HttpURL = "https://mob.odialagna.com/loginAction.php";
    Button signin;
    String stremail, strpassword;
    String finalResult ;
    Boolean CheckEditText ;
    ArrayList<String> arr=new ArrayList<>();
    HashMap<String,String> hashMap = new HashMap<>();
    ProgressDialog progressDialog;
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.emailcontainer);
        password=findViewById(R.id.passwordcontainer);
        signin=findViewById(R.id.loginbtn);

        Button button8 = findViewById(R.id.button);

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(int3);
            }
        });

    }

    public void onClickSignInBtn(View view) {
        CheckEditTextIsEmptyOrNot();

        if(CheckEditText){

            UserLoginFunction("login",stremail,strpassword);


        }
        else {

            Toast.makeText(MainActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

        }

    }
    public void CheckEditTextIsEmptyOrNot(){

        stremail = email.getText().toString().trim();
        strpassword = password.getText().toString().trim();

        if(TextUtils.isEmpty(stremail) || TextUtils.isEmpty(strpassword))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }


    public void UserLoginFunction(final String act,final String username, final String password){

        class UserLoginClass extends AsyncTask<String,Void,String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(MainActivity.this,"Authenticating User...",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                if(httpResponseMsg.contains("ID")){
                    try {
                        Toast.makeText(MainActivity.this, "Logged in successfully !", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,MyShortlistActivity.class);
                        startActivity(intent);

                        JSONObject jsonObject = new JSONObject(httpResponseMsg);
                        String LogKey=jsonObject.getString("LogKey");
                        String UserID=jsonObject.getString("UserID");
                        // this is how we use sharedpref to save data
                        SharedPreferences sharedPreferences=MainActivity.this.getSharedPreferences("logkey",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("logkey",LogKey);
                        editor.putString("UserID",UserID);

                        editor.apply();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }else{
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(httpResponseMsg);
                        String messege = jsonObject.getString("ret");
                        Toast.makeText(MainActivity.this, messege, Toast.LENGTH_SHORT).show();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected String doInBackground(String... params) {

                hashMap.put("act",params[0]);
                hashMap.put("username",params[1]);
                hashMap.put("password",params[2]);
                finalResult = httpParse.postRequest(hashMap,HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(act,username,password);
    }
    public void ForgotPasswordClick(View view){
        Intent intent=new Intent(MainActivity.this,ForgotPasswordActivity.class);
        startActivity(intent);
    }

}