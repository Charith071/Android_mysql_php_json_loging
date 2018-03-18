package com.example.charith.volley3_updatedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText username_txt,password_txt;
    Button log_btn;
    TextView newuser_txt;
   private RequestQueue requestQueue;
   private StringRequest logrequest,updaterequest;
   private static final String logurl="http://192.168.43.228/prac/Afac/loging.php";
   private static final String updateurl="http://192.168.43.228/prac/Afac/loging.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username_txt=findViewById(R.id.usernametxt_id);
        password_txt=findViewById(R.id.pwdtxt_id);
        log_btn=findViewById(R.id.logbtn_id);
        newuser_txt=findViewById(R.id.newusertxt_id);
        requestQueue= Volley.newRequestQueue(getApplicationContext());

        set_login_btn_listner();
        set_new_user_registration_listner();

    }
    public void set_new_user_registration_listner(){
        newuser_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),new_user.class);
                startActivity(intent);
            }
        });
    }

    public void set_login_btn_listner(){
        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logrequest=new StringRequest(Request.Method.POST, logurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.names().get(0).equals("logstatus_success")){
                                Toast.makeText(getApplicationContext(),jsonObject.getString("logstatus_success"),Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                                startActivity(intent);

                            }else {
                                Toast.makeText(getApplicationContext(),jsonObject.getString("logstatus_fail"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap=new HashMap<String, String>();
                        hashMap.put("username",username_txt.getText().toString());
                        hashMap.put("password",password_txt.getText().toString());
                        return hashMap;
                    }
                };
                requestQueue.add(logrequest);
            }
        });
    }
}
