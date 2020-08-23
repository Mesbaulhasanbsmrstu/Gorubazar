package com.example.gorubazar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.gorubazar.descriptionActivity.PRICE;
import static com.example.gorubazar.descriptionActivity.PRODUCT_PHONE;
import static com.example.gorubazar.descriptionActivity.PRODUCT_DESCRIPTION;
import static com.example.gorubazar.descriptionActivity.IMAGE;
import static com.example.gorubazar.descriptionActivity.PRODUCT_ID;
public class EditActivity extends AppCompatActivity {
    EditText price, description, phone;
    ImageView image;
    String id;
    public int check = 0;
    private Updateapi mAPIService;
    private Editapi mAPIService1;
    Button edit, image_edit;
    private static final int PICK_IMAGE_REQUEST = 1;
    final int IMAGE_REQUEST_CODE = 999;
    private Bitmap bitmap;
    private Uri filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        mAPIService = Apiutlize.getAPIService5();
        mAPIService1 = Apiutlize.getAPIService7();
        String product_price = intent.getStringExtra(PRICE);
        String product_description = intent.getStringExtra(PRODUCT_DESCRIPTION);
        String product_phone = intent.getStringExtra(PRODUCT_PHONE);
        String product_image = intent.getStringExtra(IMAGE);
        //   String  id=intent.getStringExtra(PRODUCT_ID);
        id = intent.getStringExtra("mes");
        image = (ImageView) findViewById(R.id.imageView);
        price = (EditText) findViewById(R.id.price);
        description = (EditText) findViewById(R.id.description);
        phone = (EditText) findViewById(R.id.phone);
        edit = (Button) findViewById(R.id.edit);
        image_edit = (Button) findViewById(R.id.ubuttonupdate);

        Picasso.with(this).load(product_image).fit().centerInside().into(image);
        price.setText(product_price);
        description.setText(product_description);
        phone.setText(product_phone);

        //Toast.makeText(EditActivity.this, product_price, Toast.LENGTH_SHORT).show();

        image_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check = 1;
                ActivityCompat.requestPermissions(EditActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == IMAGE_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent1 = new Intent(new Intent(Intent.ACTION_PICK));
                intent1.setType("image/*");

                startActivityForResult(Intent.createChooser(intent1, "select image"), IMAGE_REQUEST_CODE);

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String imgToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgbytes = byteArrayOutputStream.toByteArray();
        String encodeimg = Base64.encodeToString(imgbytes, Base64.DEFAULT);
        return encodeimg;
    }


    public void upload() {

        if (price.getText().toString().isEmpty()) {
            price.setError("পশুর দাম দিতে হবে");
            price.requestFocus();
            return;
        }
        if (phone.getText().toString().isEmpty()) {
            phone.setError("ফোন নাম্বার দিতে হবে");
            phone.requestFocus();
            return;
        }
        if (!(phone.getText().toString().length() == 11)) {
            phone.setError("ভ্যালিড ফোন নাম্বার দিতে হবে");
            phone.requestFocus();
            return;
        }
        if (!(phone.getText().toString().charAt(0) == '0' && phone.getText().toString().charAt(1) == '1')) {
            phone.setError("ভ্যালিড ফোন নাম্বার দিতে হবে");
            phone.requestFocus();
            return;
        }
        if (description.getText().toString().isEmpty()) {
            description.setError("পশুর বর্ণনা দিতে হবে");
            description.requestFocus();
            return;
        }
        try {
            sendPost(price.getText().toString(), phone.getText().toString(), description.getText().toString(), id);
        } catch (IOException e) {

        }


    }

    public void sendPost(String price, String phone, String description, String id) throws IOException {
        if (check == 0) {
            //final String p=password;
            int product_id = Integer.valueOf(id);
            // UpdatBody body=new UpdatBody(product_id,price);
            Call<UpdatBody> call = mAPIService.update(product_id, price, phone, description);

            call.enqueue(new Callback<UpdatBody>() {
                @Override
                public void onResponse(Call<UpdatBody> call, Response<UpdatBody> response) {

                    UpdatBody updateresponse = response.body();


                    Toast.makeText(EditActivity.this, updateresponse.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditActivity.this, Showdetails.class);
                    startActivity(intent);

                }

                @Override
                public void onFailure(Call<UpdatBody> call, Throwable t) {

                    Toast.makeText(EditActivity.this, "fuck", Toast.LENGTH_SHORT).show();

                }
            });

        } else if (check == 1) {
            String imgdata = imgToString(bitmap);
            int product_id = Integer.valueOf(id);
            // UpdatBody body=new UpdatBody(product_id,price);
            Call<UpdatBody> call = mAPIService1.editdata(product_id, price, phone, description,imgdata);

            call.enqueue(new Callback<UpdatBody>() {
                @Override
                public void onResponse(Call<UpdatBody> call, Response<UpdatBody> response) {

                    UpdatBody updateresponse = response.body();


                    Toast.makeText(EditActivity.this, updateresponse.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditActivity.this, Showdetails.class);
                    startActivity(intent);

                }

                @Override
                public void onFailure(Call<UpdatBody> call, Throwable t) {

                    Toast.makeText(EditActivity.this, "fuck", Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
}
