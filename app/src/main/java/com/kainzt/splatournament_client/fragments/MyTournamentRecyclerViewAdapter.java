package com.kainzt.splatournament_client.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kainzt.splatournament_client.databinding.FragmentTournamentBinding;
import com.kainzt.splatournament_client.models.Tournament;

import java.util.List;
public class MyTournamentRecyclerViewAdapter extends RecyclerView.Adapter<MyTournamentRecyclerViewAdapter.ViewHolder> {

    private final List<Tournament> mValues;

    public MyTournamentRecyclerViewAdapter(List<Tournament> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentTournamentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvTournamentItemName.setText(mValues.get(position).getName());
        holder.tvTournamentStyle.setText(mValues.get(position).getStyle().toString());
        holder.tvTournamentTeamCounter.setText(String.valueOf(mValues.get(position).getCurrentPlayerCount()));
        holder.tvTournamentCreatedBy.setText(mValues.get(position).getCreatedBy());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvTournamentItemName;
        public final TextView tvTournamentStyle;
        public final TextView tvTournamentTeamCounter;
        public final TextView tvTournamentCreatedBy;
        public final Button btnTournamentEnter;
        public Tournament mItem;

        public ViewHolder(FragmentTournamentBinding binding) {
            super(binding.getRoot());
            tvTournamentItemName = binding.tvTournamentItemName;
            tvTournamentStyle = binding.tvTournamentStyle;
            tvTournamentTeamCounter = binding.tvTournamentTeamCounter;
            tvTournamentCreatedBy = binding.tvTournamentCreatedBy;
            btnTournamentEnter = binding.btnTournamentEnter;
        }

    }
}