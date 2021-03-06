package com.atu.diplomas.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atu.diplomas.R;

import java.util.ArrayList;

public class ConferenceAdapter extends RecyclerView.Adapter<ConferenceAdapter.ViewHolder> {
    private ArrayList<ConferenceData> arrayList;
    private Context context;
    public ConferenceAdapter(ArrayList<ConferenceData> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public ConferenceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_conf, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConferenceAdapter.ViewHolder holder, int position) {
        ConferenceData data = arrayList.get(position);
        holder.NameTV.setText(data.getNameConf());
        holder.LocationTV.setText(data.getLocationConf());
        holder.DateTV.setText(data.getDateConf());
        holder.NameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ConferenceActivity.class);
                intent.putExtra("name", data.getNameConf());
                intent.putExtra("date", data.getDateConf());
                intent.putExtra("location", data.getLocationConf());
                intent.putExtra("languages", data.getLanguageConf());
                intent.putExtra("forma", data.getFormaConf());
                intent.putExtra("text", data.getTextConf());
                intent.putExtra("organizer", data.getOrganizatorConf());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView NameTV;
        private TextView LocationTV;
        private TextView DateTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NameTV = itemView.findViewById(R.id.idTVConfName);
            LocationTV = itemView.findViewById(R.id.idTVLocation);
            DateTV = itemView.findViewById(R.id.idTVDate);
        }
    }
}
