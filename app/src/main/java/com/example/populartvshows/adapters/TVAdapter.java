package com.example.populartvshows.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.populartvshows.R;
import com.example.populartvshows.models.Tv;

import org.w3c.dom.Text;

import java.util.List;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.MyViewHolder> {
    private List<Tv> dataset;
    private LayoutInflater layoutInflater;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name;
        public TextView txt_overview;

        public MyViewHolder(View view) {
            super(view);
            txt_name = view.findViewById(R.id.txt_name);
            txt_overview = view.findViewById(R.id.txt_overview);
        }
    }

    public TVAdapter(List<Tv> dataset) {
        this.dataset = dataset;
    }

    public TVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tv_recycler_view, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_name.setText(dataset.get(position).getName());
        holder.txt_overview.setText(dataset.get(position).getOverview());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
