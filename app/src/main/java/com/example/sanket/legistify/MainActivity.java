package com.example.sanket.legistify;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private RecyclerView r;
    private Adapter adapter;
    ArrayList<Lawyer> List = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StringBuilder url1= new StringBuilder();
        url1.append("http://legistify.com/lawyers/find/all/all/notary/?page=1");
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest fetch = new JsonArrayRequest(Request.Method.GET, url1.toString(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0;i<response.length();i++)
                    {
                        JSONObject random = response.getJSONObject(i);
                        Lawyer l=new Lawyer();
                        l.setName(random.getString("name"));
                        l.setAddress(random.getString("addr"));
                        l.setPhone(random.getString("phonenum"));
                        l.setField(random.getString("lawfield"));
                        List.add(l);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(fetch);
        r=(RecyclerView)findViewById(R.id.rv);
        r.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Adapter(getApplicationContext(),List);
        r.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
