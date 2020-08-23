package com.example.gorubazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {
Button buy,sell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        buy=(Button)findViewById(R.id.buttonbuy);
        sell=(Button)findViewById(R.id.buttonsell);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, Login.class);
                intent.putExtra("sell","sell");
                startActivity(intent);}

        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(FirstActivity.this, Showdetailsclientside.class);
                Intent intent = new Intent(FirstActivity.this,Login.class);
                intent.putExtra("sell","buy");
                startActivity(intent);
            }
        });
    }
}
