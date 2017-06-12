package com.judeventures.customer.wajabati;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button btn1;
    ImageView img1;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      img1=(ImageView) findViewById(R.id.image1);
      btn1=(Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
    if(v==btn1){
        //Intent i = new Intent(this, ShowProducts.class);
        Intent i = new Intent(this,SelectArea.class);
        startActivity(i);
    }

    }

}
