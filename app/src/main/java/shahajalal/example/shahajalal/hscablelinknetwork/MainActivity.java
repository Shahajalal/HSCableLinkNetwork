package shahajalal.example.shahajalal.hscablelinknetwork;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText username,password;
    private Button loginbtn,seebillbtn;
    public static final String URL="http://shahajalal.com/dev/hscable/api.php";
    private InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {

        } else {
            Toast.makeText(MainActivity.this, "আপনার ইন্টারনেট অন করুন", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(
                    Settings.ACTION_WIFI_SETTINGS));
        }

            interstitialAd = new InterstitialAd(this);
            interstitialAd.setAdUnitId("ca-app-pub-5871362448692559/7472997545");
            interstitialAd.loadAd(new AdRequest.Builder().build());
            interstitialAd.setAdListener(new AdListener() {
                                             @Override
                                             public void onAdClosed() {
                                                 Intent intent = new Intent(MainActivity.this, UserPanel.class);
                                                 startActivity(intent);
                                                 interstitialAd.loadAd(new AdRequest.Builder().build());
                                             }
                                         }
            );




        username=findViewById(R.id.adminusername);
        password=findViewById(R.id.adminpassword);
        loginbtn=findViewById(R.id.loginbutton);
        seebillbtn=findViewById(R.id.seebillbutton);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernametext=username.getText().toString();
                String usernamepass=password.getText().toString();
                if(!usernametext.equals("") && !usernamepass.equals("")){
                    gotoAdminPage(usernametext,usernamepass);
                    username.setText("");
                    password.setText("");
                }else{
                    Toast.makeText(MainActivity.this,"সব ঘর পুরন করুন",Toast.LENGTH_SHORT).show();
                }

            }
        });

        seebillbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(interstitialAd.isLoaded()){
                    interstitialAd.show();
                }else{
                    Intent intent=new Intent(MainActivity.this,UserPanel.class);
                    startActivity(intent);
                }


            }
        });
    }

    public void gotoAdminPage(final String user, final String pass){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s=response;
                        String arr[];
                        arr=s.split(" ");
                        if(user.equals(arr[0])&& pass.equals(arr[1])){
                            Intent intent=new Intent(MainActivity.this,AdminPanel.class);
                            startActivity(intent);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"ভুল হচ্ছে",Toast.LENGTH_LONG).show();
                Log.d("Volley", "onErrorResponse: "+error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String > map=new HashMap<>();
                map.put("getoperation","login");
                return map;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
