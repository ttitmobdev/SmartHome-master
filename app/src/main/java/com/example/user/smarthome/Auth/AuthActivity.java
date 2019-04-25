package com.example.user.smarthome.Auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.smarthome.API;
import com.example.user.smarthome.ApiUtils;
import com.example.user.smarthome.R;
import com.example.user.smarthome.Rooms.RoomsActivity;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {
    EditText loginEditText;
    EditText passwordEditText;
    Button signInBtn;
    private API mApi;
    String userToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginEditText = findViewById(R.id.loginEditTextId);
        passwordEditText = findViewById(R.id.passwordEditTextId);
        signInBtn = findViewById(R.id.signInBtnId);
        mApi = ApiUtils.getAPIService();
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                signIn(login,password);

            }
        });



    }




    private static String token;
    public void signIn(String loginUser,String passwordUser){
        mApi.signIn(loginUser,passwordUser).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()){
                    token = response.body().getToken();

                    userToken = token;
                    Intent suc = new Intent(AuthActivity.this,RoomsActivity.class);
                    suc.putExtra("TOKEN",userToken);
                    startActivity(suc);
                    Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(),"OPYAT NET",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"SHTO TO NE TAK",Toast.LENGTH_LONG).show();
            }
        });
    }

}
