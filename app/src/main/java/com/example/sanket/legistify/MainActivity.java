package com.example.sanket.legistify;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;



public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private RecyclerView r;
    private CustomAdapter ad;
    private Spinner s, c, f;
    String[] a;
    ArrayList<Lawyer> List = new ArrayList<>();
    LinearLayoutManager lay = new LinearLayoutManager(this);
    private String city;
    private String field;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s = (Spinner) findViewById(R.id.spinner);
        f = (Spinner) findViewById(R.id.spinner1);
        c = (Spinner) findViewById(R.id.spinner2);
        s.setOnItemSelectedListener(this);
        f.setOnItemSelectedListener(this);
        c.setOnItemSelectedListener(this);
        r = (RecyclerView) findViewById(R.id.rv);
        r.setLayoutManager(lay);
        ad = new CustomAdapter(getApplicationContext(), List);
        r.setAdapter(ad);
        JsonArrayRequest fetch = new JsonArrayRequest(Request.Method.GET,"http://legistify.com/lawyers/find/all/all/notary/?page=1", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject random = response.getJSONObject(i);
                        Lawyer l = new Lawyer();
                        l.setName(random.getString("name"));
                        l.setAddress(random.getString("addr"));
                        l.setPhone(random.getString("phonenum"));
                        l.setField(random.getString("lawfield"));
                        List.add(l);
                    }
                    ad.notifyDataSetChanged();
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
        Volley.newRequestQueue(this).add(fetch);

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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner:
                switch (position) {
                    case 0:
                        a = new String[]{"All"};
                        break;
                    case 1:
                        a = new String[]{"Delhi"};
                        break;
                    case 2:
                        a = new String[]{"Goa"};
                        break;
                    case 3:
                        a = new String[]{"Ahemdabad"};
                        break;
                    case 4:
                        a = new String[]{"Gurgaon", "Chandigarh"};
                        break;
                    case 5:
                        a = new String[]{"Bangalore"};
                        break;
                    case 6:
                        a = new String[]{"Indore"};
                        break;
                    case 7:
                        a = new String[]{"Mumbai"};
                        break;
                    case 8:
                        a = new String[]{"Jaipur"};
                        break;
                    case 9:
                        a = new String[]{"Chennai", "Coimbatore"};
                        break;
                    case 10:
                        a = new String[]{"Hyderabad"};
                        break;
                    case 11:
                        a = new String[]{"Kolkata"};
                        break;
                }
                ArrayAdapter<String> cit = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, a);
                cit.notifyDataSetChanged();
                c.setAdapter(cit);
                break;
            case R.id.spinner1:
                String fi = f.getSelectedItem().toString();
                fi.replace(" ", "%20");
                field=fi;
                break;
            case R.id.spinner2:
                city=c.getSelectedItem().toString();
                setUrl(city,field);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void performRequest(String url){
        r = (RecyclerView) findViewById(R.id.rv);
        r.setLayoutManager(lay);
        ad = new CustomAdapter(getApplicationContext(), List);
        r.setAdapter(ad);
        JsonArrayRequest fetch = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject random = response.getJSONObject(i);
                            Lawyer l = new Lawyer();
                            l.setName(random.getString("name"));
                            l.setAddress(random.getString("addr"));
                            l.setPhone(random.getString("phonenum"));
                            l.setField(random.getString("lawfield"));
                            List.add(l);
                        }
                        ad.notifyDataSetChanged();
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
            Volley.newRequestQueue(this).add(fetch);

        }
    public void setUrl(String a,String b)
    {
        String url=("http://legistify.com/lawyers/find/all/all/notary/?page=1"+a+b);
        performRequest(url);
    }
    }




