package shahajalal.example.shahajalal.hscablelinknetwork;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanel extends AppCompatActivity {

    private Button userinfobtn,userbilbtn,seeuserbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        userinfobtn=findViewById(R.id.userinfobtnid);
        userbilbtn=findViewById(R.id.userbillbtnid);
        seeuserbtn=findViewById(R.id.seeuserbtnid);
        seeuserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminPanel.this,SeeUserActivity.class);
                startActivity(intent);
            }
        });
        userinfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new UserInformationFragment());
            }
        });

        userbilbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new UserBillFragment());
            }
        });
    }
    public void setFragment(Fragment f){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame,f);
        fragmentTransaction.commit();
    }
}
