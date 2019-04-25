package com.example.user.smarthome.Devices;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.smarthome.R;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {
    private List<DeviceResponse> deviceResponses;
    private Context context;

    public DeviceAdapter(List<DeviceResponse> deviceResponses,Context context) {
        this.deviceResponses = deviceResponses;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.devices_info,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.type.setText(deviceResponses.get(i).getType_name());
        viewHolder.name.setText(deviceResponses.get(i).getName());
        viewHolder.value.setText(deviceResponses.get(i).getValue());
    }

    @Override
    public int getItemCount() {
        if (deviceResponses!=null){
            return deviceResponses.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView type;
        TextView name;
        TextView value;

        public ViewHolder(View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.typeDevId);
            name = itemView.findViewById(R.id.nameDevId);
            value = itemView.findViewById(R.id.valueDevId);
        }
    }
}
