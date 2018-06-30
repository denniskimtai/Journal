package com.codegreed_devs.journalalc;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

//Recycler view adapter
//Recycler view View holder
public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {

    private Context mCtx;
    private List<Details> detailsList;

    public DetailsAdapter(Context mCtx, List<Details> detailsList) {
        this.mCtx = mCtx;
        this.detailsList = detailsList;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        DetailsViewHolder holder = new DetailsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {

        Details details = detailsList.get(position);
        //bind data to ui elements
        holder.textViewHeading.setText(details.getHeading());
        holder.textViewThought.setText(details.getThought());

    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    class DetailsViewHolder extends RecyclerView.ViewHolder{

        TextView textViewHeading , textViewThought;

        public DetailsViewHolder(View itemView) {
            super(itemView);

            textViewHeading = itemView.findViewById(R.id.textViewHeading);
            textViewThought = itemView.findViewById(R.id.textViewThought);
        }
    }

}
