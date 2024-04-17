package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.model.RoomModel;

import java.util.ArrayList;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomRecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<RoomModel> roomModels;
    public RoomRecyclerViewAdapter(Context context, ArrayList<RoomModel> roomModels){
        this.context = context;
        this.roomModels = roomModels;

    }

    @NonNull
    @Override
    public RoomRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_row_dirtymeter, parent, false);
        return new RoomRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomRecyclerViewAdapter.ViewHolder holder, int position) {
        // Befüllen der Elemente des Recycler Views mit den entsprechenden Daten
        holder.textViewRoomName.setText(roomModels.get(position).getRoomName());
        holder.textViewLastCleaned.setText(String.valueOf(roomModels.get(position).getDateLastCleaned()));
    }

    @Override
    public int getItemCount() {
        return roomModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewRoomName, textViewLastCleaned;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRoomName = itemView.findViewById(R.id.room_name);
            textViewLastCleaned = itemView.findViewById(R.id.cleaning_date);
        }
    }
}
