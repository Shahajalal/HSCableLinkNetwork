package com.example.shahajalal.hscablelinknetwork;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class UserInformationFragment extends android.support.v4.app.Fragment {

    public static final String URL="http://shahajalal.com/dev/hscable/api.php";
    private EditText username,userfatherName,userVillage;
    private Button pressbtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_user_information, container, false);
        username=v.findViewById(R.id.userusernameid);
        userfatherName=v.findViewById(R.id.userfathernameid);
        userVillage=v.findViewById(R.id.uservillageid);
        pressbtn=v.findViewById(R.id.giveinfobtnid);
        pressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString();
                String ufather=userfatherName.getText().toString();
                String uservillagename=userVillage.getText().toString();
                if(!name.equals("") && !ufather.equals("") && !uservillagename.equals("")) {
                    addToDatabase(name, ufather, uservillagename);
                    username.setText("");
                    userfatherName.setText("");
                    userVillage.setText("");
                }else{
                    Toast.makeText(getContext(),"সব ঘর পুরন করুন",Toast.LENGTH_SHORT).show();
                }

            }
        });
        return v;
    }

    public void addToDatabase(final String name, final String ufather, final String uservillagename){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s=response;
                        if(s.equals("inserted")){
                            Toast.makeText(getActivity(),"যোগ হয়ে গেছে",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(),"যোগ হয় নাই",Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"ভুল হচ্ছে",Toast.LENGTH_SHORT).show();
                Log.d("Volley", "onErrorResponse: "+error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String > map=new HashMap<>();
                map.put("getoperation","adduserinfo");
                map.put("name",name);
                map.put("father",ufather);
                map.put("village",uservillagename);
                return map;
            }
        };
        Volley.newRequestQueue(this.getContext()).add(stringRequest);
    }

}
