package shahajalal.example.shahajalal.hscablelinknetwork;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeeUserActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    List<Contact> list;
    public static final String URL="http://shahajalal.com/dev/hscable/api.php";
    private RecyclerView recyclerView;
    RecyclerAdapterForUserInfo adapter;
    RecyclerView.LayoutManager layoutManager;
    android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_user);
        toolbar=findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.recyclerviewid);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        getIngormation();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem menuItem=menu.findItem(R.id.searchactionid);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    private void getIngormation(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder=new GsonBuilder();
                Gson gson=builder.create();
                Log.d("Response", response);

                list= Arrays.asList(gson.fromJson(response,Contact[].class));

                adapter=new RecyclerAdapterForUserInfo(list,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SeeUserActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                Log.d("volley", error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String > map=new HashMap<>();
                map.put("getoperation","seeuser");
                return map;
            }
        };
        MySingleTon.getInstance(this).addToRequestQue(stringRequest);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        try {
            s = s.toLowerCase();
            ArrayList<Contact> newList = new ArrayList<>();
            for (Contact contact : list) {
                String name = contact.getName().toLowerCase();
                if (name.contains(s)) {
                    newList.add(contact);
                }

            }
            adapter.setFilter(newList);
        }catch (Exception e){
            Toast.makeText(SeeUserActivity.this,"লোড হচ্ছে",Toast.LENGTH_LONG).show();
        }
        return true;
    }
}

