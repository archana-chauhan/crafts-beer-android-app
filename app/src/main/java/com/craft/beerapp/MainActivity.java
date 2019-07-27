package com.craft.beerapp;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements BeerAdapter2.ContactsAdapterListener{

    //a list to store all the products
    List<Beer> productList;

    //the recyclerview
    RecyclerView recyclerView;
//    String TAG = "MainActivity";
    String urlGetServerData = "http://starlord.hackerearth.com/beercraft";

    ArrayList<Beer> jobInformation = new ArrayList<Beer>();
    RecyclerView rvJobForYou;
    BeerAdapter rvJobForYouAdapter;
    JSONObject jsonObject ;

    JSONArray jsonArray;
     BeerAdapter2 rvAdapter;

    private static final String TAG = MainActivity.class.getSimpleName();
//    private RecyclerView recyclerView;
    private List<Beer> contactList;
    private BeerAdapter2 mAdapter;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getServerData();


        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView.setHasFixedSize(true);

//        LinearLayoutManager llm = new LinearLayoutManager(mContext);
//        recyclerView.setLayoutManager(llm);

        rvAdapter = new BeerAdapter2(this, jobInformation, this);

        whiteNotificationBar(recyclerView);


        recyclerView.setAdapter(rvAdapter);


//
//        //initializing the productlist
//        productList = new ArrayList<>();
//
//
//        //adding some items to our list
//        productList.add(
//                new Beer(
//                        1,
//                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
//                        "13.3 inch, Silver, 1.35 kg",
//                        4.3,
//                        60000));
//
//        productList.add(
//                new Beer(
//                        1,
//                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
//                        "14 inch, Gray, 1.659 kg",
//                        4.3,
//                        60000));
//
//        productList.add(
//                new Beer(
//                        1,
//                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
//                        "13.3 inch, Silver, 1.35 kg",
//                        4.3,
//                        60000));
//
//        //creating recyclerview adapter
//        BeerAdapter adapter = new BeerAdapter(this, productList);
//
//        //setting adapter to recyclerview
//        recyclerView.setAdapter(adapter);
    }


    private void getServerData() {

        Log.d(TAG, "urlGetServerData----------: "+urlGetServerData);



        RequestQueue queue = Volley.newRequestQueue(this);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlGetServerData, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                        // display response
                        Log.d("Response",  String.valueOf(response.length()));

                        jsonArray = response;



                        if (response.length() > 0) {
                            jobInformation.clear();
                            for (int i = 0; i < response.length(); i++) {

                                Beer beers = new Beer();

                                try {
                                    jsonObject = response.getJSONObject(i);
//
//                                    beers.setAbv(jsonObject.getString("abv"));
//                                    beers.setIbu(jsonObject.getString("ibu"));
//                                    beers.setId(jsonObject.getString("id"));
                                    beers.setName(jsonObject.getString("name"));
                                    beers.setStyle(jsonObject.getString("style"));
//                                    beers.setOunces(jsonObject.getString("ounces"));



//                                    if (!jsonObject.isNull("name")) {
//                                        person.name = jsonObject.getString("name");
//                                    }
//                                    if (!jsonObject.isNull("style")) {
//                                        person.age = jsonObject.getString("style");
//                                    }
                                    jobInformation.add(i, beers);
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            rvAdapter.notifyDataSetChanged();

                        }



//                        Gson gson = new Gson();
//
//                        jobInformation.clear();
//
//                                for (int p=0; p<jsonArray.length(); p++){
//                                    try {
//                                        jsonObject = response.getJSONObject(p);
//
//                                        Log.d(TAG, "jsonObject : " + response.getJSONObject(p).toString());
//
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
////                                    Beer rvJobDetails = gson.fromJson(String.valueOf(jsonObject), Beer.class);
////                                    jobInformation.add(rvJobDetails);
//                                }
////                                rvJobForYouAdapter = new BeerAdapter(getApplicationContext(), jobInformation);
////                            recyclerView.setAdapter(rvJobForYouAdapter);
//
//                        rvJobForYouAdapter = new BeerAdapter(GetDataAdapter1, this);
//                        recyclerView.setAdapter(recyclerViewadapter);


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );



        // add it to the RequestQueue
        queue.add(jsonArrayRequest);


//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlGetServerData,null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.i(TAG, "response: "+response);
//
//                        try {
//                            Gson gson = new Gson();
//                            JSONArray jsonArray = response.getJSONArray("jobs");
//
//                            Log.i(TAG, "jsonarray: "+jsonArray);
//
//                            Log.i(TAG, "jsonarray: " + jsonArray.toString());
//
//                            String str= response.getString("jobs");
//                            Log.d(TAG, str);
//
//
//
//                            jobInformation.clear();
//
//                                for (int p=0; p<jsonArray.length(); p++){
//                                    JSONObject jsonObject = jsonArray.getJSONObject(p);
//
//                                    Beer rvJobDetails = gson.fromJson(String.valueOf(jsonObject), Beer.class);
//                                    jobInformation.add(rvJobDetails);
//                                }
//                                rvJobForYouAdapter = new BeerAdapter(getApplicationContext(), jobInformation);
//                            recyclerView.setAdapter(rvJobForYouAdapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d(TAG,error.toString());
//                    }
//                });
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(jsonObjectRequest);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                rvAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                rvAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

//    @Override
//    public void onContactSelected(Beer contact) {
//        Toast.makeText(getApplicationContext(), "Selected: " + contact.getName() + ", " + contact.getStyle(), Toast.LENGTH_LONG).show();
//    }
//

    @Override
    public void onContactSelected(Beer contact) {

    }
}