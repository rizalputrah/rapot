package com.example.owl.rapot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

/**
 * Created by OWL on 04/05/2016.
 */
public class RapotAdapter extends RecyclerView.Adapter<RapotAdapter.RapotHolder>{
    private List<Rapot> daftarNilai;
    private LayoutInflater layoutInflater;
    private Context context;

    public class RapotHolder extends RecyclerView.ViewHolder{
        public TextView mapel,nilai;
        public RapotHolder(View itemView) {
            super(itemView);
            mapel = (TextView) itemView.findViewById(R.id.text_mapel);
            nilai = (TextView) itemView.findViewById(R.id.text_nilai);
        }
    }

    public RapotAdapter(Context context, List<Rapot> daftarNilai){
        this.daftarNilai = daftarNilai;
        this.context = context;
    }

    @Override
    public RapotHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);
        RapotHolder viewHolder = new RapotHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RapotHolder holder, int position) {
        Rapot rapot = daftarNilai.get(position);
        holder.mapel.setText(rapot.getMapel());
        holder.nilai.setText(rapot.getNilai());
    }



    @Override
    public int getItemCount() {
        return daftarNilai.size();
    }


}
