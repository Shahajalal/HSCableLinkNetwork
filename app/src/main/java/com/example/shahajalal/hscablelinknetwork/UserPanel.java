package com.example.shahajalal.hscablelinknetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UserPanel extends AppCompatActivity {

    public static final String URL="http://shahajalal.com/dev/hscable/api.php";
    private EditText username,userfathername;
    private TextView textView;
    private Button btn,btn1;
    private String spin1,spin2;
    private Spinner spinner,spinner2;
    private static final String[] paths = {"জানুয়ারী", "ফেব্রুয়ারি", "মার্চ","এপ্রিল","মে","জুন","জুলাই","আগস্ট","সেপ্টেম্বর","অক্টোবর","নভেম্বর","ডিসেম্বর"};
    private static final String[] paths1 = {"২০১৮", "২০১৯", "২০২০","২০২১","২০২২","২০২৩","২০২৪","২০২৫"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);

        username=findViewById(R.id.showforusernameid);
        textView=findViewById(R.id.seeresulttxtid);
        userfathername=findViewById(R.id.showforuserfathernameid);
        btn=findViewById(R.id.userseebtn);
        btn1=findViewById(R.id.totalbillbtnid);


        spinner = findViewById(R.id.spinner3);
        spinner2=findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, paths);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, paths1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        spin1=paths[0];
                        break;
                    case 1:

                        spin1=paths[1];
                        break;
                    case 2:

                        spin1=paths[2];
                        break;
                    case 3:

                        spin1=paths[3];
                        break;
                    case 4:

                        spin1=paths[4];
                        break;
                    case 5:

                        spin1=paths[5];
                        break;
                    case 6:

                        spin1=paths[6];
                        break;
                    case 7:

                        spin1=paths[7];
                        break;
                    case 8:

                        spin1=paths[8];
                        break;
                    case 9:

                        spin1=paths[0];
                        break;
                    case 10:

                        spin1=paths[9];
                        break;
                    case 11:

                        spin1=paths[10];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spin1=paths[0];

            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        spin2=paths1[0];
                        break;
                    case 1:

                        spin2=paths1[1];
                        break;
                    case 2:

                        spin2=paths1[2];
                        break;
                    case 3:

                        spin2=paths1[3];
                        break;
                    case 4:

                        spin2=paths1[4];
                        break;
                    case 5:

                        spin2=paths1[5];
                        break;
                    case 6:

                        spin2=paths1[6];
                        break;
                    case 7:

                        spin2=paths1[7];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spin2=paths1[0];

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString();
                String userfather=userfathername.getText().toString();
                if(!name.equals("") && !userfather.equals("")){
                    searchvalue(name,userfather,spin1,spin2);
                }else{
                    Toast.makeText(getApplicationContext(),"সব ঘর পুরন করুন",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString();
                String userfather=userfathername.getText().toString();
                if(!name.equals("") && !userfather.equals("")) {
                    totalbill(name, userfather);
                }else{
                    Toast.makeText(getApplicationContext(),"সব ঘর পুরন করুন",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void searchvalue(final String name, final String userfather, final String sp1, final String sp2){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s=response;
                        if(s.equals("not")){
                            textView.setText("আপনার বিল এখনও যোগ হই নাই");
                        }else{
                            textView.setText("আপনার কাছে পাওয়া যাবে = "+s+" টাকা");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserPanel.this,"ভুল হচ্ছে",Toast.LENGTH_SHORT).show();
                Log.d("Volley", "onErrorResponse: "+error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String > map=new HashMap<>();
                map.put("getoperation","seeuserbill");
                map.put("name",name);
                map.put("father",userfather);
                map.put("monthname",sp1);
                map.put("year",sp2);
                return map;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void totalbill(final String name, final String father){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s=response;
                        if(s.equals("not")){
                            textView.setText("আপনার বিল এখনও যোগ হই নাই");
                        }else{
                            textView.setText("আপনার কাছে পাওয়া যাবে = "+s+" টাকা");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserPanel.this,"ভুল হচ্ছে",Toast.LENGTH_SHORT).show();
                Log.d("Volley", "onErrorResponse: "+error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String > map=new HashMap<>();
                map.put("getoperation","totalbill");
                map.put("name",name);
                map.put("father",father);
                return map;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
