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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {


    EditText firstName,middleName,lastName,email,phone,password,conformPassword;
    String strFirstName,strMiddleName,strLastName,strEmail,strPhone,strPassword,strConformPassword;
    String strGender,strMonth,strYear,strRelation,strCast,strReligion,strDate;

    Spinner spinnerGender;
    Spinner spinnerMonth;
    Spinner spinnerYear;
    Spinner spinnerRelation;
    Spinner spinnerCast;
    Spinner spinnerReligion;
    Spinner spinnerDate;
    String[] month;

    Button Register;
    String finalResult ;
    String HttpURL = "https://mob.odialagna.com/loginAction.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    ArrayList<String> arr=new ArrayList<>();
    //HashMap<String,String> hashMap = new HashMap<>();
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
   // JsonHttpParse jsonhttpParse = new JsonHttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //getSupportActionBar().hide();

        firstName = findViewById(R.id.enter_Fname);
        middleName = findViewById(R.id.enter_Mname);
        lastName = findViewById(R.id.enter_Lname);
        email = findViewById(R.id.enter_email);
        phone = findViewById(R.id.enter_mobile);
        password = findViewById(R.id.enter_password);
        conformPassword = findViewById(R.id.reEnter_password);

        spinnerGender = findViewById(R.id.spinnerGender);
        spinnerMonth = findViewById(R.id.spinnerMonth);
        spinnerYear = findViewById(R.id.spinnerYear);
        spinnerRelation = findViewById(R.id.spinnerRelation);
        spinnerReligion = findViewById(R.id.spinnerReligion);
        spinnerDate = findViewById(R.id.spinnerDate);
        spinnerCast = findViewById(R.id.spinnerCast);
        papulateSpinnerYear();
        papulateSpinnerMonth();
        papulateSpinnerDate();
        papulateSpinnerGender();
        papulateSpinnerCast();
        papulateSpinnerReligion();
        papulateSpinnerRelation();

        Button button10 = findViewById(R.id.button10);
         Register = findViewById(R.id.btn_register);

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(int3);
            }
        });
    }

    private void papulateSpinnerRelation() {
        ArrayAdapter<String> relationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.spinner_relation));
        relationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRelation.setAdapter(relationAdapter);
    }

    private void papulateSpinnerReligion() {
        ArrayAdapter<String> religionAdapter =new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.spinner_religion));
        religionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerReligion.setAdapter(religionAdapter);
    }

    private void papulateSpinnerCast() {
        ArrayAdapter<String> castAdapter =new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.spinner_cast));
        castAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCast.setAdapter(castAdapter);
    }

    private void papulateSpinnerGender() {
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.spinner_gender));
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);
    }


    private void papulateSpinnerDate() {
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.spinner_date));
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDate.setAdapter(dateAdapter);
    }

    private void papulateSpinnerYear() {
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.spinner_year));
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(yearAdapter);

    }

    private void papulateSpinnerMonth() {

        month = new DateFormatSymbols().getMonths();
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,month);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(monthAdapter);

    }
    public void onClickRegisterBtn(View view) {
        CheckEditTextIsEmptyOrNot();

        if(CheckEditText){

            SharedPreferences sharedPreferences1=RegisterActivity.this.getSharedPreferences("profie",MODE_PRIVATE);
            final SharedPreferences.Editor edit=sharedPreferences1.edit();
            edit.putString("name",strFirstName);
            edit.putString("name",strLastName);
            edit.putString("email",strEmail);
            edit.putString("phone",strPhone);
            edit.apply();

            UserRegisterFunction("reg",strFirstName,strLastName,strDate,strMonth,strYear ,strGender,strRelation,strReligion,strCast,strPhone,strEmail,strPassword,strConformPassword);
        }
        else {

            Toast.makeText(RegisterActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

        }

    }

    private void CheckEditTextIsEmptyOrNot() {
        strFirstName = firstName.getText().toString().trim();
        strLastName = lastName.getText().toString().trim();
        strDate = spinnerDate.toString().trim();
        strMonth = spinnerMonth.toString().trim();
        strYear = spinnerYear.toString().trim();
        strGender = spinnerGender.toString().trim();
        strRelation = spinnerRelation.toString().trim();
        strReligion = spinnerReligion.toString().trim();
        strCast = spinnerCast.toString().trim();
        strPhone = phone.getText().toString().trim();
        strEmail = email.getText().toString().trim();
        strPassword = password.getText().toString().trim();
        strConformPassword = conformPassword.getText().toString().trim();


        if(TextUtils.isEmpty(strFirstName) || TextUtils.isEmpty(strLastName) || TextUtils.isEmpty(strDate) || TextUtils.isEmpty(strGender) || TextUtils.isEmpty(strRelation)  || TextUtils.isEmpty(strReligion) || TextUtils.isEmpty(strCast) || TextUtils.isEmpty(strPhone)  || TextUtils.isEmpty(strEmail) || TextUtils.isEmpty(strPassword)|| TextUtils.isEmpty(strConformPassword))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }

    }
    public void UserRegisterFunction(final String act ,final String FirstName , final String LastName, final String Day, final String Month,final String Year, final String Gender,final String MaritalStatus,final String Religion,final String Caste,final String Mobile,final String Email,final String pass ,final String cpass){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {


        @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(RegisterActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(RegisterActivity.this, httpResponseMsg, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                if(httpResponseMsg.contains("ID")){
                    try {
                        Toast.makeText(RegisterActivity.this, "Registered in successfully !", Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject = new JSONObject(httpResponseMsg);
                        JSONArray result = jsonObject.getJSONArray("{");
                        for (int i=0; i<result.length(); i++ ){
                            JSONObject ob=result.getJSONObject(i);
                            Toast.makeText(RegisterActivity.this, ""+ob, Toast.LENGTH_SHORT).show();



                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                    Toast.makeText(RegisterActivity.this, httpResponseMsg, Toast.LENGTH_SHORT).show();


                }else{
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(httpResponseMsg);
                        String messege = jsonObject.getString("ret");
                        Toast.makeText(RegisterActivity.this, messege, Toast.LENGTH_SHORT).show();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected String doInBackground(String... params) {

                hashMap.put("act",params[0]);
                hashMap.put("FirstName",params[1]);
                hashMap.put("LastName",params[2]);
                hashMap.put("Day",params[3]);
                hashMap.put("Month",params[4]);
                hashMap.put("Year",params[5]);
                hashMap.put("Gender",params[6]);
                hashMap.put("MaritalStatus",params[7]);
                hashMap.put("Religion",params[8]);
                hashMap.put("Caste",params[9]);
                hashMap.put("Mobile",params[10]);
                hashMap.put("Email",params[11]);
                hashMap.put("pass",params[12]);
                hashMap.put("cpass",params[13]);


                finalResult = httpParse.postRequest(hashMap,HttpURL);


                    return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(act,FirstName,LastName,Day,Month,Year,Gender,MaritalStatus,Religion,Caste,Mobile,Email,pass,cpass);
    }

}