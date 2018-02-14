package com.keya.fetch_data_network;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {
    ArrayList<HashMap<String , String>> listItem;
    HashMap<String,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listItem=new ArrayList<HashMap<String,String>>();

        RequestQueue queue= Volley.newRequestQueue(this);
        String jsonString="http://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, jsonString, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try{
                    JSONArray array= jsonObject.getJSONArray("worldpopulation");
                    for(int i=0;i<array.length();i++){
                      JSONObject obj= array.getJSONObject(i);
                        String country=  obj.getString("country");
                        String population= obj.getString("rank");
                        int rank=obj.getInt("");
                        map=new HashMap<String,String>();
                        map.put("country",country);
                        map.put("population",population);
                        map.put("rank",""+rank);
                        listItem.add(map);
                          }
            }
                catch (JSONException e){
                }
                populateListView(listItem);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request);
    }

    public void populateListView(ArrayList<HashMap<String,String>> list){
        ListAdapter adapter=new SimpleAdapter(this,list,R.layout.list_item,new String[]{"country","population","rank"},new int []{R.id.textView,R.id.textView2,R.id.textView3});
        setListAdapter(adapter);
    }
}

