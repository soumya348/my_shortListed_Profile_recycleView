package com.example.login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText phoneNo;
    Button forgtPhone;
    String HttpURL = "https://mob.odialagna.com/loginAction.php";
    String strPhoneNo;
    String finalResult ;
    Boolean CheckEditText ;
    ArrayList<String> arr=new ArrayList<>();
    HashMap<String,String> hashMap = new HashMap<>();
    ProgressDialog progressDialog;
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        phoneNo = findViewById(R.id.ForgPassPhone);
        forgtPhone = findViewById(R.id.forgtPhone);

    }
    public void SubmmitClick(View view) {

        strPhoneNo=phoneNo.getText().toString().trim();

        if(strPhoneNo.length()!=0){
            forgetPassword("fpass",strPhoneNo);
        }else{
            Snackbar.make(view, " Please Enter Phone number ..", Snackbar.LENGTH_LONG)
                    .setActionTextColor(getResources().getColor(android.R.color.holo_green_dark))
                    .show();

        }

    }
    private void forgetPassword(String act, final String mobile) {

        class UserLoginClass extends AsyncTask<String,Void,String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(ForgotPasswordActivity.this,"Loading...",null,true,true);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                JSONObject jsonObject2 = null;
                try {
                    jsonObject2 = new JSONObject(httpResponseMsg);
                    String status = jsonObject2.getString("status");

                    if(status.equals("ID")){
                        Intent intent =new Intent(ForgotPasswordActivity.this,RegisterActivity.class);
                        intent.putExtra("mobile",""+mobile);
                        startActivity(intent);

                        String msg=jsonObject2.getString("msg");

                        Toast.makeText(ForgotPasswordActivity.this, ""+msg, Toast.LENGTH_LONG).show();



                    }else{

                        String messege = jsonObject2.getString("msg");
                        Toast.makeText(ForgotPasswordActivity.this, messege, Toast.LENGTH_SHORT).show();

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected String doInBackground(String... params) {

                hashMap.put("act",params[0]);
                hashMap.put("mobile",params[1]);
                finalResult = httpParse.postRequest(hashMap,HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(act,mobile);


    }
    public void loginClick(View view){
        Intent intent=new Intent(ForgotPasswordActivity.this,MainActivity.class);
        startActivity(intent);
    }
}