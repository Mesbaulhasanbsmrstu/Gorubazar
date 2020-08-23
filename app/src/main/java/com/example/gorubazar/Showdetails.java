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

public class Showdetails extends AppCompatActivity implements AdapterClass.OnItemClickListener{

    public static final String EXTRA_URL="imageUrl";
    public static final String EXTRA_CREATOR="price";
    public static final String EXTRA_LIKES="description";
    public static final String ID="id";
    public static final String PHONE="phone";
    public static final String DESCRIPTION="description";

        private RecyclerView recyclerView;
        private AdapterClass adapter;
        private RecyclerView.LayoutManager layoutmanager;
        List<Show_element> data;
        private Showapi mAPIService;
        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            super.onStart();
            SessionManagment sessionManagment = new SessionManagment(Showdetails.this);
            int userId = sessionManagment.getSession();

            if (userId != -1) {
                setContentView(R.layout.activity_showdetails);
                //final ArrayList<Card_element>[] data = new ArrayList[]{new ArrayList<>()};
                mAPIService = Apiutlize.getAPIService4();
                recyclerView=findViewById(R.id.show);
                recyclerView.setHasFixedSize(true);
                layoutmanager=new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutmanager);


                Call<List<Show_element>> call = mAPIService.getdata( String.valueOf(userId));

                call.enqueue(new Callback<List<Show_element>>() {
                    @Override
                    public void onResponse(Call<List<Show_element>> call, Response<List<Show_element>> response) {
                        List<Show_element> showresponse=response.body();
   data=showresponse;
                        // data.add(new Card_element("abc","bfg"));
                        //data = new ArrayList<>(Arrays.asList(cardresponse.getCard()));
                        //int l=data.size();
                        //Log.d("data", String.valueOf(l));
                        //Toast.makeText(Recycleviewactivity.this, l, Toast.LENGTH_SHORT).show();
                        adapter=new AdapterClass(Showdetails.this,showresponse);

                       adapter.setOnClickListener(Showdetails.this);
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(Call<List<Show_element>> call, Throwable t) {

                    }


                });


            }
            else {
                Intent intent = new Intent(Showdetails.this, Login.class);
                startActivity(intent);
            }


        }
    public void OnItemClick(int position) {
        Intent detailIntent=new Intent(this,descriptionActivity.class);
        //Show_element currentItem=examplelist.get(position);
        Show_element clickItem=data.get(position);
        detailIntent.putExtra(EXTRA_URL,clickItem.getProduct_image());
        detailIntent.putExtra(EXTRA_CREATOR,clickItem.getProduct_price());
        detailIntent.putExtra(ID,clickItem.getProduct_id());
        detailIntent.putExtra(PHONE,clickItem.getProduct_phone());
        detailIntent.putExtra(DESCRIPTION,clickItem.getDescription());
        // detailIntent.putExtra(EXTRA_LIKES,clickItem.getmLikes());
        // detailIntent.putExtra("imageUrlAll", Images);
        startActivity(detailIntent);

    }

}
