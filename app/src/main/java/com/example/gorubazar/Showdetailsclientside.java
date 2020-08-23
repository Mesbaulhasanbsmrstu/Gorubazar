package com.example.gorubazar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Showdetailsclientside extends AppCompatActivity implements AdapterClassforclient1.OnItemClickListener{


    public static final String ID="id";
    public static final String NAME="name";


    private RecyclerView recyclerView;
    private AdapterClassforclient1 adapter;
    private RecyclerView.LayoutManager layoutmanager;
    List<Show_elementforclient> data;
    private Showapiforclient mAPIService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.onStart();
        SessionManagment sessionManagment = new SessionManagment(Showdetailsclientside.this);
        int userId = sessionManagment.getSession();

        if (userId != -1) {
            setContentView(R.layout.activity_showdetailsclientside);
            //final ArrayList<Card_element>[] data = new ArrayList[]{new ArrayList<>()};
            mAPIService = Apiutlize.getAPIService8();
            recyclerView = findViewById(R.id.show);
            recyclerView.setHasFixedSize(true);
            layoutmanager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutmanager);


            Call<List<Show_elementforclient>> call = mAPIService.getdata1("magura");

            call.enqueue(new Callback<List<Show_elementforclient>>() {
                @Override
                public void onResponse(Call<List<Show_elementforclient>> call, Response<List<Show_elementforclient>> response) {
                    List<Show_elementforclient> showresponse = response.body();
                    data = showresponse;
                    // data.add(new Card_element("abc","bfg"));
                    //data = new ArrayList<>(Arrays.asList(cardresponse.getCard()));
                    //int l=data.size();
                    //Log.d("data", String.valueOf(l));
                    //Toast.makeText(Recycleviewactivity.this, l, Toast.LENGTH_SHORT).show();
                   adapter = new AdapterClassforclient1(Showdetailsclientside.this, showresponse);
                    //Toast.makeText(Showdetailsclientside.this, "success", Toast.LENGTH_SHORT).show();
                    adapter.setOnClickListener(Showdetailsclientside.this);
                    recyclerView.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<List<Show_elementforclient>> call, Throwable t) {
                    Toast.makeText(Showdetailsclientside.this, "fail", Toast.LENGTH_SHORT).show();
                }


            });


        }

    }
   public void OnItemClick(int position) {
        Intent detailIntent=new Intent(this,Showdetailsclientside1.class);

        Show_elementforclient clickItem=data.get(position);

        detailIntent.putExtra(ID,clickItem.getSeller_id());

        startActivity(detailIntent);

    }

}
