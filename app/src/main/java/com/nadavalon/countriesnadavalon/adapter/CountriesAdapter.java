package com.nadavalon.countriesnadavalon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nadavalon.countriesnadavalon.R;
import com.nadavalon.countriesnadavalon.databinding.CountriesBinding;
import com.nadavalon.countriesnadavalon.viewmodel.CountriesViewModel;

import java.util.ArrayList;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(CountriesViewModel item);
    }

    private ArrayList<CountriesViewModel> arrayList;
    private Context mContext;

    //to provide onitemclicklistener to recyclerview
    private final OnItemClickListener listener;
    private LayoutInflater layoutInflater;

    public CountriesAdapter(Context mContext, ArrayList<CountriesViewModel> arrayList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater==null){
            layoutInflater=LayoutInflater.from(parent.getContext());
        }
        CountriesBinding countriesBinding= DataBindingUtil.inflate(layoutInflater, R.layout.countries_item,parent,false);
        return new CountryViewHolder(countriesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(arrayList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        private CountriesBinding countriesBinding;

        public CountryViewHolder(@NonNull CountriesBinding countriesBinding) {
            super(countriesBinding.getRoot());
            this.countriesBinding = countriesBinding;
        }

        public void bind(final CountriesViewModel country, final OnItemClickListener listener) {
            this.countriesBinding.setCountriesmodel(country);
            countriesBinding.executePendingBindings();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(country);
                }
            });
        }
    }
}