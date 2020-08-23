package com.example.gorubazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.gorubazar.Showdetailsclientside1.EXTRA_CREATOR;
import static com.example.gorubazar.Showdetails.EXTRA_LIKES;
import static com.example.gorubazar.Showdetailsclientside1.EXTRA_URL;
import static com.example.gorubazar.Showdetailsclientside1.ID;
import static com.example.gorubazar.Showdetailsclientside1.PHONE;
import static com.example.gorubazar.Showdetailsclientside1.DESCRIPTION;
public class Descriptionclientside extends AppCompatActivity {
    TextView price1,description1,phone1;
    ImageView imageView;
    Button contact;
    Deleteapi mAPIService;
    public static final String PRICE = "price";

    public static final String Location=null;
    public static final String PRODUCT_ID="id";
    public static final String PRODUCT_PHONE="phone";
    public static final String PRODUCT_DESCRIPTION="description";
    public static final String IMAGE="image";
    String product_location;
    public  String product_price,product_phone,id,product_description,product_image;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriptionclientside);
        Intent intent = getIntent();
        mAPIService=Apiutlize.getAPIService6();
        String  imageUrl = intent.getStringExtra(EXTRA_URL);
        String price = intent.getStringExtra(EXTRA_CREATOR);
        id=intent.getStringExtra(ID);
        String phone=intent.getStringExtra(PHONE);
        String description=intent.getStringExtra(DESCRIPTION);
        product_phone=phone;
        product_description=description;
        product_price=price;
        product_image=imageUrl;

        price1=(TextView)findViewById(R.id.price);
        description1=(TextView)findViewById(R.id.decrptn);
       // phone1=(TextView)findViewById(R.id.phone);
        imageView=(ImageView)findViewById(R.id.imageView);
       contact=(Button)findViewById(R.id.buttoncontact);
        //delete=(Button)findViewById(R.id.buttondlt);

        price1.setText(product_price);
        description1.setText(product_description);
        //phone1.setText(product_phone);

        // Picasso.with().load(imageUrl).fit().centerInside().into(imageView);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());




    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }

}

