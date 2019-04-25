package com.example.user.smarthome.Rooms;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.smarthome.API;
import com.example.user.smarthome.ApiUtils;
import com.example.user.smarthome.Devices.DeviceAdapter;
import com.example.user.smarthome.Devices.DeviceResponse;
import com.example.user.smarthome.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OneRoomActivity extends AppCompatActivity {
    TextView roomName;
    ImageView roomImage;
    Integer roomId;
    private API api;
    String token;
    String name="asd";
    String photo="asdas";
    List<DeviceResponse> deviceResponses;
    RecyclerView rec;
    DeviceAdapter deviceAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_room);
        rec = findViewById(R.id.recDevId);
        roomImage = findViewById(R.id.imageId);
        roomName = findViewById(R.id.textId);


        deviceResponses = new ArrayList<>();



        Intent getId = getIntent();
        roomId = getId.getIntExtra("ID",-1);
        token = getId.getStringExtra("TOKEN");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(layoutManager);

        deviceAdapter = new DeviceAdapter(deviceResponses,context);
        rec.setAdapter(deviceAdapter);

        api = ApiUtils.getAPIService();
        loadRoom(token,roomId);
        loadDevices(token,roomId);
    }

    private void loadDevices(String userToken,Integer id) {
        Call<List<DeviceResponse>> call1 = api.getInfoAboutDevices(userToken,id);
        call1.enqueue(new Callback<List<DeviceResponse>>() {
            @Override
            public void onResponse(Call<List<DeviceResponse>> call, Response<List<DeviceResponse>> response) {
                if (response.isSuccessful()){
                    deviceResponses.addAll(response.body());
                    rec.getAdapter().notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"DEVICES TYT",Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(),"DEVISES NE TYT",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<DeviceResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"DEVICES NE BUDET",Toast.LENGTH_SHORT).show();
                Log.e("DEVISES",t.toString());
            }
        });

    }



    public void loadRoom(String userToken,Integer id){
        Call<OneRoomResponse> call = api.getInfoAboutRoom(userToken,id);
        call.enqueue(new Callback<OneRoomResponse>() {
            @Override
            public void onResponse(Call<OneRoomResponse> call, Response<OneRoomResponse> response) {
                if (response.isSuccessful()){
                   // roomResponseList = response.body();
                    name = response.body().getName();
                    Log.d("NAME", name);
                    photo = response.body().getPhoto();
                    roomName.setText(name);
                    Uri uri = Uri.parse(photo);
                    Picasso.get().load(uri).into(roomImage);
                } else
                    Toast.makeText(getApplicationContext(),"AAAAAA",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<OneRoomResponse> call, Throwable t) {

            }
        });
    }
}
