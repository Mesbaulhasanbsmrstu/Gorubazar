package com.example.gorubazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button add,logout,show,client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionManagment sessionManagment = new SessionManagment(MainActivity.this);
        int userId = sessionManagment.getSession();

        if (userId != -1) {
            setContentView(R.layout.activity_main);
            add = (Button) findViewById(R.id.ubuttonadd);
            logout = (Button) findViewById(R.id.ubuttonlogout);
            show = (Button) findViewById(R.id.ubuttonshow);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Addproduct.class);
                    startActivity(intent);
                }
            });
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SessionManagment sessionManagment = new SessionManagment(MainActivity.this);
                    sessionManagment.removeSession();

                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
            });
            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(MainActivity.this, Showdetails.class);
                    startActivity(intent);
                }
            });
        }
        // Toast.makeText(Login.this, "yes", Toast.LENGTH_SHORT).show();

        else {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    }
}
