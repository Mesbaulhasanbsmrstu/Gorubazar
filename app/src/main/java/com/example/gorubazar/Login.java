package com.example.gorubazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText  phone,password;
    Button login,register;
    private Loginapi mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        super.onStart();
        SessionManagment sessionManagment = new SessionManagment(Login.this);
        int userId = sessionManagment.getSession();
        Intent intent1=getIntent();
        String s=intent1.getStringExtra("sell");

        if (userId != -1 && s.equals("sell")) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            // Toast.makeText(Login.this, "yes", Toast.LENGTH_SHORT).show();
        }else if(userId != -1 && s.equals("buy")){
            Intent intent = new Intent(Login.this, Showdetailsclientside.class);
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_login);
            phone = (EditText) findViewById(R.id.uphone);
            password = (EditText) findViewById(R.id.upassword);

            login = (Button) findViewById(R.id.ubuttonlogin);
            register = (Button) findViewById(R.id.ubuttonregister);
            mAPIService = Apiutlize.getAPIService1();
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Registration.class);

                    startActivity(intent);
                }
            });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (phone.getText().toString().isEmpty()) {
                        phone.setError("PhoneNumber is required");
                        phone.requestFocus();
                        return;
                    }
                    if (password.getText().toString().isEmpty()) {
                        password.setError("Password is required");
                        password.requestFocus();
                        return;
                    }
                    try {
                        sendPost(phone.getText().toString(), password.getText().toString());
                        finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }
    public void sendPost(String phone,  String password) throws IOException {
        final String p=password;
        Call<Loginresponse> call = mAPIService.getStringScalar( phone, password );

        call.enqueue(new Callback<Loginresponse>() {
            @Override
            public void onResponse(Call<Loginresponse> call, Response<Loginresponse> response) {
                Loginresponse loginresponse=response.body();
                String msg="successfull";
                String responsemsg=loginresponse.getMessage();
                if(responsemsg.equals(msg)) {
                    User user=new User(loginresponse.getId(),loginresponse.getPhone());
                    SessionManagment sessionManagment=new SessionManagment(Login.this) ;
                    sessionManagment.saveSession(user);
                //    Intent intent = new Intent(Login.this, MainActivity.class);
               //     startActivity(intent);

                    // Toast.makeText(Login.this, loginresponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Login.this, loginresponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Loginresponse> call, Throwable t) {

                Toast.makeText(Login.this, p, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
