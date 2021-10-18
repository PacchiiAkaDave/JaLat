package com.example.jalat.db.highscore;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jalat.R;


import java.util.List;


/**
 *
 * @author Jan Weimer
 *
 * */
public class HighscoreAdapter extends RecyclerView.Adapter<HighscoreAdapter.HighscoreHolder> {
    private List<User> users;

    public HighscoreAdapter(List<User> us){
        setUsers(us);
    }

    @NonNull
    @Override
    public HighscoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.highscore_item,parent,false);
        return new HighscoreHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull HighscoreHolder holder, int position) {
        User current = users.get(position);

        holder.textViewNumber.setText(String.valueOf(++position));
        holder.textViewPoints.setText(String.valueOf(current.getScore()));
        holder.textViewName.setText(current.getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users){
        this.users = users;
        notifyDataSetChanged();
    }

    class HighscoreHolder extends RecyclerView.ViewHolder{

        private TextView textViewNumber;
        private TextView textViewPoints;
        private TextView textViewName;

        public HighscoreHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.place);
            textViewPoints = itemView.findViewById(R.id.score);
            textViewName = itemView.findViewById(R.id.name);
        }


    }
}
