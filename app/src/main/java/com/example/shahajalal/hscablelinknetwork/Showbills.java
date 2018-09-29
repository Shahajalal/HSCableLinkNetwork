package com.example.shahajalal.hscablelinknetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Showbills extends AppCompatActivity {

    String name,father;
    public static final String URL="http://shahajalal.com/dev/hscable/api.php";
    private RecyclerView recyclerView;
    RecyclerAdapterForUserbill adapter;
    RecyclerView.LayoutManager layoutManager;
    android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showbills);
        toolbar=findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);
        name=getIntent().getStringExtra("name");
        father=getIntent().getStringExtra("father");
        recyclerView=findViewById(R.id.recyclerviewid1);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        getIngormation();

    }

    private void getIngormation(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder=new GsonBuilder();
                Gson gson=builder.create();
                Log.d("Response", response);

                List<ContactForBill>list= Arrays.asList(gson.fromJson(response,ContactForBill[].class));

                adapter=new RecyclerAdapterForUserbill(list);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Showbills.this,error.toString(),Toast.LENGTH_LONG).show();
                Log.d("volley", error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String > map=new HashMap<>();
                map.put("getoperation","showbillrecycler");
                map.put("name",name);
                map.put("father",father);
                return map;
            }
        };
        MySingleTon.getInstance(this).addToRequestQue(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_mode,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteidmenu:
                deletefunction();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void  deletefunction(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("deleted")) {
                    Toast.makeText(Showbills.this, "মুছে গেছে", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Showbills.this, SeeUserActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Showbills.this,"মুছে নাই",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Showbills.this,error.toString(),Toast.LENGTH_LONG).show();
                Log.d("volley", error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String > map=new HashMap<>();
                map.put("getoperation","delete");
                map.put("name",name);
                map.put("father",father);
                return map;
            }
        };
        MySingleTon.getInstance(this).addToRequestQue(stringRequest);
    }
}

