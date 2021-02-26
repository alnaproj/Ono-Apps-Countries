package com.nadavalon.countriesnadavalon.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nadavalon.countriesnadavalon.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    Context mCtx;
    List<String> borders;
    public CountryAdapter(Context mCtx, List<String> borders)  {
        this.mCtx = mCtx;
        this.borders = borders;
    }

    @NonNull
    @Override
    public CountryAdapter.CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.borders_item, parent, false);
        return new CountryAdapter.CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryViewHolder holder, int position) {
        String border = borders.get(position).toString();
        holder.tvBorder.setText(border);
    }

    @Override
    public int getItemCount() {
        return borders.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView tvBorder;

        public CountryViewHolder(View itemView) {
            super(itemView);

            tvBorder = itemView.findViewById(R.id.tv_border);
        }
    }
}