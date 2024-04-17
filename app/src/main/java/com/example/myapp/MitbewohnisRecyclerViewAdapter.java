package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.model.database.Mitbewohni;

import java.util.ArrayList;

public class MitbewohnisRecyclerViewAdapter extends RecyclerView.Adapter<MitbewohnisRecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Mitbewohni> mitbewohnis;
    public MitbewohnisRecyclerViewAdapter(Context context, ArrayList<Mitbewohni> mitbewohnis){
        this.context = context;
        this.mitbewohnis = mitbewohnis;

    }

    @NonNull
    @Override
    public MitbewohnisRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_row_mitbewohni, parent, false);
        return new MitbewohnisRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MitbewohnisRecyclerViewAdapter.ViewHolder holder, int position) {
        // Befüllen der Elemente des Recycler Views mit den entsprechenden Daten
        holder.textViewName.setText(mitbewohnis.get(position).getName());
        holder.numberOfRings.setText(String.valueOf(mitbewohnis.get(position).getNumber_of_rings()));
    }

    @Override
    public int getItemCount() {
        return mitbewohnis.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName, numberOfRings;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.mitbewohni_name);
            numberOfRings = itemView.findViewById(R.id.number_of_rings);
        }
    }
}
