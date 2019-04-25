package com.example.user.smarthome;

import com.example.user.smarthome.Auth.TokenResponse;
import com.example.user.smarthome.Devices.DeviceResponse;
import com.example.user.smarthome.Rooms.OneRoomActivity;
import com.example.user.smarthome.Rooms.OneRoomResponse;
import com.example.user.smarthome.Rooms.RoomResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    @POST ("/smart-home/api/login")
    @FormUrlEncoded
    Call<TokenResponse> signIn(@Field("login")String login, @Field("password")String password);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("/smart-home/api/rooms")
    Call<List<RoomResponse>> getRooms(@Header("Authorization") String token);

    @GET("/smart-home/api/rooms/{id}")
    Call<OneRoomResponse> getInfoAboutRoom (@Header("Authorization") String token,@Path("id") Integer id);

    @GET("/smart-home/api/rooms/{id}/devices")
    Call<List<DeviceResponse>> getInfoAboutDevices (@Header("Authorization") String token, @Path("id") Integer id);
}
