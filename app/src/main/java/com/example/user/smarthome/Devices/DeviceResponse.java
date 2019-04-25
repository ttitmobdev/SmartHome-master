package com.example.user.smarthome.Devices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceResponse {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("room_id")
    @Expose
    private Integer room_id;

    @SerializedName("type_id")
    @Expose
    private Integer type_id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("type_name")
    @Expose
    private String type_name;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getType_name() {
        return type_name;
    }
}
