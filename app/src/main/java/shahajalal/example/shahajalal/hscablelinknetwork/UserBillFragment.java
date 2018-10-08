package shahajalal.example.shahajalal.hscablelinknetwork;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class UserBillFragment extends android.support.v4.app.Fragment {
    public static final String URL="http://shahajalal.com/dev/hscable/api.php";
    private Spinner spinner,spinner2;
    private String spineer1,spninner21;
    private EditText userName,userFatherName,monthlyCharge,givenamount;
    private Button sendBillinfo;
    private static final String[] paths = {"জানুয়ারী", "ফেব্রুয়ারি", "মার্চ","এপ্রিল","মে","জুন","জুলাই","আগস্ট","সেপ্টেম্বর","অক্টোবর","নভেম্বর","ডিসেম্বর"};
    private static final String[] paths1 = {"২০১৮", "২০১৯", "২০২০","২০২১","২০২২","২০২৩","২০২৪","২০২৫"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_bill, container, false);

        userName=v.findViewById(R.id.userbillusernameid);
        userFatherName=v.findViewById(R.id.userbilluserfathernameid);
        monthlyCharge=v.findViewById(R.id.monthlychargeid);
        givenamount=v.findViewById(R.id.givenamountid);
        spinner = v.findViewById(R.id.spinner1);
        spinner2=v.findViewById(R.id.spinner2);
        sendBillinfo=v.findViewById(R.id.uploadbillid);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, paths);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(),
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
                        spineer1=paths[0];
                        break;
                    case 1:
                        spineer1=paths[1];
                        break;
                    case 2:
                        spineer1=paths[2];
                        break;
                    case 3:
                        spineer1=paths[3];
                        break;
                    case 4:
                        spineer1=paths[4];
                        break;
                    case 5:
                        spineer1=paths[5];
                        break;
                    case 6:
                        spineer1=paths[6];
                        break;
                    case 7:
                        spineer1=paths[7];
                        break;
                    case 8:
                        spineer1=paths[8];
                        break;
                    case 9:
                        spineer1=paths[9];
                        break;
                    case 10:
                        spineer1=paths[10];
                        break;
                    case 11:
                        spineer1=paths[11];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        spninner21=paths1[0];
                        break;
                    case 1:

                        spninner21=paths1[1];
                        break;
                    case 2:

                        spninner21=paths1[2];
                        break;
                    case 3:

                        spninner21=paths1[3];
                        break;
                    case 4:

                        spninner21=paths1[4];
                        break;
                    case 5:

                        spninner21=paths1[5];
                        break;
                    case 6:

                        spninner21=paths1[6];
                        break;
                    case 7:

                        spninner21=paths1[7];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sendBillinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String uname= userName.getText().toString();
               String ufathername=userFatherName.getText().toString();
               String monthcharge=monthlyCharge.getText().toString();
               String givenamountt=givenamount.getText().toString();
               if(!uname.equals("") && !ufathername.equals("") && !monthcharge.equals("") && !givenamountt.equals("")) {
                   sendBillinformation(uname, ufathername, monthcharge, givenamountt, spineer1, spninner21);
                   userName.setText("");
                   userFatherName.setText("");
                   monthlyCharge.setText("");
                   givenamount.setText("");
               }else{
                   Toast.makeText(getContext(),"সব ঘর পুরন করুন",Toast.LENGTH_SHORT).show();
               }

            }
        });

        return v;

    }

    public  void sendBillinformation(final String uname, final String ufathername, final String monthcharge, final String givenamountt, final String spin1, final String spin2){
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
                map.put("getoperation","adduserbillinfo");
                map.put("name",uname);
                map.put("father",ufathername);
                map.put("monthcharge",monthcharge);
                map.put("givenamount",givenamountt);
                map.put("monthname",spin1);
                map.put("year",spin2);
                return map;
            }
        };
        Volley.newRequestQueue(this.getContext()).add(stringRequest);
    }

}
