package com.judeventures.customer.wajabati;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShowProducts extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private String URL_DATA="http://wajabati.com/api/get_rest_area.php?area=Hiteen";
    private static final String URL_IMAGE="http://wajabati.com/admin_area/rest_images/";
    private List<ListItem> listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String text4="Hello";
        List<ListItem> mL1=new ArrayList<ListItem>();
       GetResults getResults=new GetResults();
        Bundle firstMsg= getIntent().getExtras();
        String msg1=firstMsg.getString("Msg1");
        //Toast.makeText(this,msg1,Toast.LENGTH_LONG).show();
        URL_DATA=msg1;

        try {
            mL1=getResults.execute(URL_DATA).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        listItems = new ArrayList<>();
       for (int i=0;i<10;i++){
            ListItem listItem=new ListItem(
                    "heading"+(i+1),
                    text4,
                    "test"
            );
            listItems.add(listItem);
        }
      //  ListItem listItem= mL1.get(1);
       // Toast.makeText(this,"you clicked "+listItem.getImageUrl(),Toast.LENGTH_LONG).show();
       // adapter= new MyAdapter(listItems,this);
        adapter= new MyAdapter(mL1,this);
        recyclerView.setAdapter(adapter);
    }

   class GetResults extends AsyncTask<String,Void,List>{
        OkHttpClient client=new OkHttpClient();

       @Override
       protected List doInBackground(String... params) {

           OkHttpClient client = new OkHttpClient();

           Request.Builder builder=new Request.Builder();

           builder.url(params[0]);

           Request request=builder.build();
           try {
               Response response=client.newCall(request).execute();
               String s= response.body().string();
               List<ListItem> myList=new ArrayList<ListItem>();

               try {
                   JSONObject obj1= new JSONObject(s);
                   JSONArray arr1= obj1.getJSONArray("restaurants");
                   for (int i=0;i<arr1.length();i++){
                       JSONObject o=arr1.getJSONObject(i);
                       ListItem listItem= new ListItem(
                               o.getString("rest_name"),
                               o.getString("rest_cuisine"),
                               URL_IMAGE+o.getString("rest_log")
                       );
                       myList.add(listItem);

                   }
                   return myList;
               } catch (JSONException e) {
                   e.printStackTrace();
               }


           } catch (IOException e) {
               e.printStackTrace();
           }


           return null;
       }
   }
}
