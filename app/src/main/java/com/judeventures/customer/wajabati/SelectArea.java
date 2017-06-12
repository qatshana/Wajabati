package com.judeventures.customer.wajabati;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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

public class SelectArea extends AppCompatActivity {
    TextView txtV1;
    Spinner spinner;
    ListView listView1;
    public final String URL_LINK="http://wajabati.com/api/get_all_areas.php";
    public final String URL_Restaurants="http://wajabati.com/api/get_rest_area.php?area=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_area);
        spinner = (Spinner) findViewById(R.id.selectArea);
        listView1=(ListView)findViewById(R.id.listV1);


        String txtV3="hello";

        txtV1=(TextView) findViewById(R.id.txtV1);
        List<String> mL=new ArrayList<String>();
        GetDataList getDataList = new GetDataList();
        try {
            mL=getDataList.execute(URL_LINK).get();
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mL);
            spinner.setAdapter(adapter2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void displayRestaurants(View v){
        String txtV2= String.valueOf(spinner.getSelectedItem());
        //txtV1.setText("Button Clicked " + URL_Restaurants+txtV2);
        //GetData getData= new GetData();
        String text3;
        String url=URL_Restaurants+txtV2;
        Intent i = new Intent(this, ShowProducts.class);
        i.putExtra("Msg1",url);
        startActivity(i);
        /*
        try {
            text3=getData.execute(url).get();
            txtV1.setText(text3);
            Intent i = new Intent(this, ShowProducts.class);
            i.putExtra("Msg1",url);

            startActivity(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        */
    }

    public class GetData extends AsyncTask<String,Void,String>{

        OkHttpClient client= new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {

            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request=builder.build();

            try {
                Response response=client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public class GetDataList extends AsyncTask<String,Void,List> {

        OkHttpClient client= new OkHttpClient();

        @Override
        protected List doInBackground(String... params) {
            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request=builder.build();

            Response response= null;
            try {
                response = client.newCall(request).execute();
                String res=response.body().string();

                try {
                    JSONObject object=new JSONObject(res);
                    JSONArray arr= object.getJSONArray("areas");
                    List<String> myList= new ArrayList<String>();
                    for (int i=0;i<arr.length();i++){
                        JSONObject obj1= arr.getJSONObject(i);
                        myList.add(obj1.getString("name"));
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
