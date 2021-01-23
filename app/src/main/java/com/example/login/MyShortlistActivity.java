package com.example.login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MyShortlistActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView myshort,soumya;
    String str;
    String HttpURL = "http://mob.odialagna.com/my-shortlisted-api.php";
    MyShortlistActivityAdapter myShortlistActivityAdapter;
    ArrayList<PersonProfile>personProfiles=new ArrayList<PersonProfile>();
    String finalResult ;
    ArrayList<String> arr=new ArrayList<>();
    HashMap<String,String> hashMap = new HashMap<>();
    ProgressDialog progressDialog;
    HttpParse httpParse = new HttpParse();
    JsonHttpParse jsonhttpParse = new JsonHttpParse();
    String logkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shortlist);

        recyclerView=findViewById(R.id.myShortlistRecycler);
        recyclerView.setHasFixedSize(true);
        myshort = findViewById(R.id.myshort);
        soumya = findViewById(R.id.soumya);

        myShortlistActivityAdapter=new MyShortlistActivityAdapter(personProfiles,MyShortlistActivity.this);
        recyclerView.setAdapter(myShortlistActivityAdapter);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MyShortlistActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
//
//       for (int i=0;i<=10;i++){
//           PersonProfile personProfile1=new PersonProfile("ProfilePic","MemberName"
//                   ,"Age","Religion",
//                   "Caste","Qualification","City");
//           personProfiles.add(personProfile1);
//           i++;
//       }


    }

    public void onClickSignInBtn(View view) {
        UserLoginFunction(logkey);
    }
    public void UserLoginFunction(final String Logkey){

        class UserLoginClass extends AsyncTask<String,Void,String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(MyShortlistActivity.this,"Finding Profiles...",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                if(httpResponseMsg!=null){
                    try {

                        JSONObject jsonObject = new JSONObject(httpResponseMsg);

                        JSONArray result = jsonObject.getJSONArray("{");
                        for (int i=0; i<result.length(); i++ ) {
                            JSONObject ob = result.getJSONObject(i);

                            PersonProfile personProfile=new PersonProfile(ob.getString("ProfilePic"),ob.getString("MemberName")
                                    ,ob.getString("Age"),ob.getString("Religion"),
                                    ob.getString("Caste"),ob.getString("Qualification"),ob.getString("City"));
                            personProfiles.add(personProfile);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    recyclerView.setAdapter(myShortlistActivityAdapter);
                    myShortlistActivityAdapter=new MyShortlistActivityAdapter(personProfiles, getApplicationContext());


                }else{
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(httpResponseMsg);
                        String messege = jsonObject.getString("ret");
                        Toast.makeText(MyShortlistActivity.this, "hiii", Toast.LENGTH_SHORT).show();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected String doInBackground(String... params) {

                hashMap.put("LogKey",params[0]);

                finalResult = httpParse.postRequest(hashMap,HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(Logkey);
    }
}