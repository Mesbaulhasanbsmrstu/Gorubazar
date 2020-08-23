package com.example.gorubazar;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

//import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Addproduct extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 1;
    private Button selectimg, btnUpload, logout;
    private ImageView img;
    EditText product_price, phone, description;
    private MyApi mAPIService;
    private static final int PICK_IMAGE_REQUEST = 1,CAMERA_REQUEST=1;

    final int IMAGE_REQUEST_CODE = 999;
    private ProgressBar progressBar;
    private Uri filepath;
    private Bitmap bitmap;
    private ProgressBar pd;
    String type = "null";
    int check = 0;
    RadioButton r1, r2, r3, r4, r5, r6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onStart();
        SessionManagment sessionManagment = new SessionManagment(Addproduct.this);
        int userId = sessionManagment.getSession();

        if (userId != -1) {
            setContentView(R.layout.activity_addproduct);
            selectimg = (Button) findViewById(R.id.ubuttonadd);
            logout = (Button) findViewById(R.id.ubuttonlogout);
            img = (ImageView) findViewById(R.id.image);
            product_price = (EditText) findViewById(R.id.price);
            phone = (EditText) findViewById(R.id.phone);
            description = (EditText) findViewById(R.id.description);
            btnUpload = (Button) findViewById(R.id.ubuttonupload);
            r1 = (RadioButton) findViewById(R.id.radio_id1);
            r2 = (RadioButton) findViewById(R.id.radio_id2);
            r3 = (RadioButton) findViewById(R.id.radio_id3);
            r4 = (RadioButton) findViewById(R.id.radio_id4);
            r5 = (RadioButton) findViewById(R.id.radio_id5);
            r6 = (RadioButton) findViewById(R.id.radio_id6);
            mAPIService = Apiutlize.getAPIService();
            //pd=(ProgressBar)findViewById(R.id.progressbar);
            btnUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadImage();

                }
            });
            r1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radio1();

                }
            });
            r2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radio2();

                }
            });
            r3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radio3();

                }
            });
            r4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radio4();

                }
            });
            r5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radio5();

                }
            });
            r6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radio6();

                }
            });

            //progressBar=(ProgressBar)findViewById(R.id.progressbar);

            selectimg.setOnClickListener(new View.OnClickListener() {

                // @Override
                public void onClick(View v) {
                    check=1;


                //check=1;
                ActivityCompat.requestPermissions(Addproduct.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA},IMAGE_REQUEST_CODE);
                    //ActivityCompat.requestPermissions(Addproduct.this,new String[] {Manifest.permission.CAMERA},CAMERA_REQUEST);
imageselect();
                    }

                // Toast.makeText(Login.this, "yes", Toast.LENGTH_SHORT).show();
            });
        } else {
            Intent intent = new Intent(Addproduct.this, Login.class);
            startActivity(intent);
        }


    }
public void imageselect()
{
    final CharSequence[] items={"Camera", "Gallery","Cancle"};
AlertDialog.Builder builder=new AlertDialog.Builder(Addproduct.this);
builder.setTitle("Add Image");
builder.setItems(items, new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int i) {
        if(items[i].equals("Camera"))
        {
Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
startActivityForResult(intent,CAMERA_REQUEST);
        }
        else if(items[i].equals("Gallery"))
        {
            Intent intent=new Intent(new Intent(Intent.ACTION_PICK));
            intent.setType("image/*");

            startActivityForResult(Intent.createChooser(intent,"select image"),IMAGE_REQUEST_CODE);

        }
        else if(items[i].equals("Cancle"))
        {
            dialog.dismiss();
        }
    }
});
builder.show();
}
@Override
public void onActivityResult(int requestCode,int resultCode, Intent data )
{
    super.onActivityResult(requestCode,resultCode,data);
    if(resultCode==Activity.RESULT_OK)
    {
        if(requestCode==CAMERA_REQUEST)
        {
            Bundle bundle=data.getExtras();
            bitmap=(Bitmap)bundle.get("data");
            img.setImageBitmap(bitmap);

        }
        else if(requestCode==IMAGE_REQUEST_CODE)
        {
            filepath=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}

    /* @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         if (requestCode==IMAGE_REQUEST_CODE){
             if (grantResults.length>0&& grantResults[0]== PackageManager.PERMISSION_GRANTED){
                 Intent intent=new Intent(new Intent(Intent.ACTION_PICK));
                 intent.setType("image/*");

                 startActivityForResult(Intent.createChooser(intent,"select image"),IMAGE_REQUEST_CODE);

             }
         }
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (requestCode==IMAGE_REQUEST_CODE && resultCode==RESULT_OK && data!=null){
             filepath=data.getData();
             try {
                 InputStream inputStream=getContentResolver().openInputStream(filepath);
                 bitmap= BitmapFactory.decodeStream(inputStream);
                 img.setImageBitmap(bitmap);
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }
         }
         super.onActivityResult(requestCode, resultCode, data);
     }*/
    private String imgToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgbytes = byteArrayOutputStream.toByteArray();
        String encodeimg = Base64.encodeToString(imgbytes, Base64.DEFAULT);
        return encodeimg;
    }

    private void uploadImage() {
        if (check == 0) {
            Toast.makeText(this, "ছবি সিলেক্ট করতে হবে ", Toast.LENGTH_SHORT).show();
            return;
        }
        String imgdata = imgToString(bitmap);


        if (product_price.getText().toString().isEmpty()) {
            product_price.setError("পশুর দাম দিতে হবে");
            product_price.requestFocus();
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
        if (type.equals("null")) {
            Toast.makeText(this, "পশু সিলেক্ট করতে হবে ", Toast.LENGTH_SHORT).show();
            return;
        }
        String price = product_price.getText().toString();
        String product_phone = phone.getText().toString();
        String product_description = description.getText().toString();
        SessionManagment sessionManagment = new SessionManagment(Addproduct.this);
        String userId = String.valueOf(sessionManagment.getSession());
        Call<ResponseBody> call=mAPIService.insertdata(imgdata,price,product_phone,product_description,type,userId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                startActivity(new Intent(Addproduct.this,MainActivity.class));
                finish();

                try {
                    String hi=response.body().string();
                    Toast.makeText(Addproduct.this,hi,Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Addproduct.this,"fail",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void radio1() {
        type = "goru";
    }

    private void radio2() {
        type = "chagol";
    }

    private void radio3() {
        type = "mohis";
    }

    private void radio4() {
        type = "vera";
    }

    private void radio5() {
        type = "utt";
    }

    private void radio6() {
        type = "dubma";
    }


                }








