package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.control.DeleteAufgabeListener;
import com.example.myapp.control.HomeScreenActivity;
import com.example.myapp.model.database.AppDatabase;
import com.example.myapp.model.database.AppDatabaseFactory;
import com.example.myapp.model.database.Aufgaben;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AufgabenRecyclerViewAdapter extends RecyclerView.Adapter<AufgabenRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Aufgaben> aufgaben;
    private DeleteAufgabeListener deleteAufgabeListener;
    public AufgabenRecyclerViewAdapter(Context context, ArrayList<Aufgaben> aufgaben, DeleteAufgabeListener deleteAufgabeListener){
        this.context = context;
        this.aufgaben = aufgaben;
        this.deleteAufgabeListener = deleteAufgabeListener;
    }

    @NonNull
    @Override
    public AufgabenRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_row_dirtymeter, parent, false);
        return new AufgabenRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AufgabenRecyclerViewAdapter.ViewHolder holder, int position) {
        // Befüllen der Elemente des Recycler Views mit den entsprechenden Daten
        holder.textViewRoomName.setText(aufgaben.get(position).getName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(aufgaben.get(position).getDate_last_cleaned() == null){
            holder.textViewLastCleaned.setText("nie geputzt!");
        }else {
            String formattedDateTime = dateFormat.format(aufgaben.get(position).getDate_last_cleaned());
            holder.textViewLastCleaned.setText(String.valueOf(formattedDateTime));
        }

        holder.buttonDeletAufgabe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAufgabeListener.onButtonClicked(aufgaben.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return aufgaben.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewRoomName, textViewLastCleaned;
        FloatingActionButton buttonDeletAufgabe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRoomName = itemView.findViewById(R.id.room_name);
            textViewLastCleaned = itemView.findViewById(R.id.cleaning_date);
            buttonDeletAufgabe = itemView.findViewById(R.id.buttonDeletAufgabe);

        }
    }
}
