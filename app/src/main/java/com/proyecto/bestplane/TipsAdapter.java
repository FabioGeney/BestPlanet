package com.proyecto.bestplane;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {
    private List<String> tips;
    private int layout;
    private OnItemClickListener itemClickListener;


    public TipsAdapter(List<String> tips, int layout, OnItemClickListener itemClickListener) {
        this.tips = tips;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(tips.get(i), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tip = itemView.findViewById(R.id.tip);

        }
        public void bind(final String mensaje, final OnItemClickListener itemClickListener){
            this.tip.setText(mensaje);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.OnItemClick(mensaje, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener{

        void OnItemClick(String mensaje, int position);
    }
}
