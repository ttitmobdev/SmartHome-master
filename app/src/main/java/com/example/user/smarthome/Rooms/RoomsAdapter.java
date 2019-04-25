package com.example.user.smarthome.Rooms;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.smarthome.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder> {
   private List<RoomResponse> roomResponseList;
   private Context context;
   private OnItemClickListener mListener;

    public RoomsAdapter(List<RoomResponse> roomResponseList, Context context) {
        this.roomResponseList = roomResponseList;
        this.context = context;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_room,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.text.setText(roomResponseList.get(position).getName());
        Uri uri = Uri.parse(roomResponseList.get(position).getPhoto());
        Picasso.get().load(uri)
                .into(holder.image);

    }
    @Override
    public int getItemCount(){
        if (roomResponseList!=null) {
            return roomResponseList.size();
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;
        TextView id;
        LinearLayout layout;
        public ViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener!=null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mListener.onItemCLick((position));
                        }
                    }
                }
            });
            layout = itemView.findViewById(R.id.layout);
            image = (ImageView) itemView.findViewById(R.id.imageId);
            text = (TextView) itemView.findViewById(R.id.nameId);
            id = (TextView) itemView.findViewById(R.id.Id);

        }
    }

    public interface OnItemClickListener{
        void onItemCLick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

}
