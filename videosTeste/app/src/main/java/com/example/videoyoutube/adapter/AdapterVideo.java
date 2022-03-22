package com.example.videoyoutube.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoyoutube.model.Video;

import java.util.ArrayList;
import java.util.List;

public class AdapterVideo  extends RecyclerView.Adapter<AdapterVideo.MyViewHolder> {

    private List<Video> videos = new ArrayList<>();
    private Context context;

    public AdapterVideo(List<Video> videos, Context context) {
        this.videos = videos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;
        TextView descricao;
        TextView data;
        TextView capa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
