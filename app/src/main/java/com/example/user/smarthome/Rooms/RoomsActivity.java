package com.example.user.smarthome.Rooms;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.smarthome.API;
import com.example.user.smarthome.ApiUtils;
import com.example.user.smarthome.R;
import com.example.user.smarthome.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomsActivity extends AppCompatActivity implements RoomsAdapter.OnItemClickListener {
    String token;
     RecyclerView recyclerView;
    private API api;
    RoomsAdapter adapter;
    Integer id;
    private Context context;
    String userToken;

     List<RoomResponse> roomResponsesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        roomResponsesList = new ArrayList<>();

        recyclerView = findViewById(R.id.recView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RoomsAdapter(roomResponsesList,context);
        recyclerView.setAdapter(adapter);

        Intent getToken = getIntent();
        token = getToken.getStringExtra("TOKEN");
        Log.e("TOKEN","sdsd"+token);
        userToken = "Bearer " + token;
        api = ApiUtils.getAPIService();
        loadRooms(userToken);

    }

   public void loadRooms(String token) {
        Call<List<RoomResponse>>  call = api.getRooms(""+token);
        call.enqueue(new Callback<List<RoomResponse>>() {
            @Override
            public void onResponse(Call<List<RoomResponse>> call, Response<List<RoomResponse>> response) {
                if (response.isSuccessful()) {
                    roomResponsesList.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                    adapter.setOnItemClickListener(RoomsActivity.this);
                }else
                    Toast.makeText(getApplicationContext(),"FAIL",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<RoomResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemCLick(int position) {
        Toast.makeText(getApplicationContext(),"VSE NORM"+roomResponsesList.get(position).getId().toString(),Toast.LENGTH_SHORT).show();
        Intent goRoom = new Intent(this,OneRoomActivity.class);
        RoomResponse clicked = roomResponsesList.get(position);
        goRoom.putExtra("ID",clicked.getId());
        goRoom.putExtra("TOKEN",userToken);
        startActivity(goRoom);
    }
}
