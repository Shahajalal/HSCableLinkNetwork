package com.example.shahajalal.hscablelinknetwork;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleTon {
    private  static MySingleTon minstance;
    private RequestQueue requestQueue;
    private static Context mcontext;

    private MySingleTon(Context context){
        mcontext=context;
        requestQueue= getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized MySingleTon getInstance(Context context){
        if (minstance==null){
            minstance=new MySingleTon(context);
        }
        return minstance;
    }

    public<T> void addToRequestQue(Request<T> request){
        getRequestQueue().add(request);
    }
}
