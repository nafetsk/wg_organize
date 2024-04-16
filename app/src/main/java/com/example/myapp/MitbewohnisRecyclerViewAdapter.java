package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MitbewohnisRecyclerViewAdapter extends RecyclerView.Adapter<MitbewohnisRecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<MitbewohniModel> mitbewohniModels;
    public MitbewohnisRecyclerViewAdapter(Context context, ArrayList<MitbewohniModel> mitbewohniModels){
        this.context = context;
        this.mitbewohniModels = mitbewohniModels;

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
        holder.textViewName.setText(mitbewohniModels.get(position).getMitbewohniName());
        holder.numberOfRings.setText(String.valueOf(mitbewohniModels.get(position).getNumberOfRings()));
    }

    @Override
    public int getItemCount() {
        return mitbewohniModels.size();
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
