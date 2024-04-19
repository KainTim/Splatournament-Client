package com.kainzt.splatournament_client.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
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
        holder.mIdView.setText(mValues.get(position).getId());
        holder.mContentView.setText(mValues.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Tournament mItem;

        public ViewHolder(FragmentTournamentBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}