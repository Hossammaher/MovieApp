package com.example.movie.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.pojo.TrailerModel;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private Context mContext;
    private List<TrailerModel> TrailerModelList = new ArrayList<>();

    TrailerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trailer_item, viewGroup, false);

        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TrailerViewHolder viewHolder, int i) {
        viewHolder.Trailer_name.setText(TrailerModelList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return TrailerModelList.size();
    }

    public void setList(List<TrailerModel> TrailerModelList) {
        this.TrailerModelList = TrailerModelList;
        notifyDataSetChanged();
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder {

        TextView Trailer_name;

        TrailerViewHolder(View view) {
            super(view);
            Trailer_name = view.findViewById(R.id.Trailer_name);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        TrailerModel clicked = TrailerModelList.get(pos);
                        String video_id = clicked.getKey();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video_id));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);

                    }
                }
            });
        }
    }
}