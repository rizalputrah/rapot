package com.example.owl.rapot.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.owl.rapot.R;
import com.example.owl.rapot.model.Rapot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by OWL on 04/05/2016.
 */
public class RapotAdapter extends RecyclerView.Adapter<RapotAdapter.ViewHolder>{
    private ArrayList<com.example.owl.rapot.model.Result> result;
    private CardView card;

    public RapotAdapter(ArrayList<com.example.owl.rapot.model.Result> result) {
        this.result = result;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mapel,nilai;
        public ViewHolder(View itemView) {
            super(itemView);
            mapel = (TextView) itemView.findViewById(R.id.mapel);
            nilai = (TextView) itemView.findViewById(R.id.nilai);

        }
    }


    @Override
    public RapotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mapel.setText(result.get(position).getNilai());
        holder.nilai.setText(result.get(position).getMapel());

    }



    @Override
    public int getItemCount() {
        return result.size();
    }


}
