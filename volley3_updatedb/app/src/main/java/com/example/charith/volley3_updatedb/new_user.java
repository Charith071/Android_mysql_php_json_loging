package com.example.charith.volley3_updatedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class new_user extends AppCompatActivity {

    EditText fullname_txt,age_txt,email_txt,password_txt,cpassword_txt,username_txt;
    RadioGroup radioGroup;
    Button register_btn;
    RadioButton radiobtn;
    private RequestQueue requestQueue;
    private StringRequest register_request;
    private static final String register_url="http://192.168.43.228/prac/Afac/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        fullname_txt=findViewById(R.id.fulnametxt_id);
        email_txt=findViewById(R.id.emailtxt_id);
        age_txt=findViewById(R.id.agetxt_id);
        password_txt=findViewById(R.id.passwordtxt_id);
        cpassword_txt=findViewById(R.id.confirmpasswordtxt_id);
        username_txt=findViewById(R.id.usernametxt_id);
        radioGroup=findViewById(R.id.radiogroup_id);
        register_btn=findViewById(R.id.registerbtn_id);
        requestQueue= Volley.newRequestQueue(getApplicationContext());

        set_registerbtn_listner();
    }
    public void set_registerbtn_listner(){
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_request=new StringRequest(Request.Method.POST, register_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.names().get(0).equals("duplicate_status")){
                                Toast.makeText(getApplicationContext(),jsonObject.getString("duplicate_status"),Toast.LENGTH_LONG).show();
                            }else if (jsonObject.names().get(0).equals("reg_status1")){
                                Toast.makeText(getApplicationContext(),jsonObject.getString("reg_status1"),Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(),jsonObject.getString("reg_status2"),Toast.LENGTH_LONG).show();
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
                        hashMap.put("fullname",fullname_txt.getText().toString());
                        hashMap.put("email",email_txt.getText().toString());
                        hashMap.put("age",age_txt.getText().toString());
                        hashMap.put("password",password_txt.getText().toString());
                        hashMap.put("cpassword",cpassword_txt.getText().toString());
                        hashMap.put("usrname",username_txt.getText().toString());
                        int id=radioGroup.getCheckedRadioButtonId();
                        radiobtn=findViewById(id);
                        hashMap.put("gender",radiobtn.getText().toString());
                        return  hashMap;
                    }
                };
                requestQueue.add(register_request);
            }
        });
    }
}
