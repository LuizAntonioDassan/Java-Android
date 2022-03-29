package com.example.videoyoutube.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import com.example.videoyoutube.R;
import com.example.videoyoutube.adapter.AdapterVideo;
import com.example.videoyoutube.model.Video;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private List<Video> videos = new ArrayList<>();
    private AdapterVideo adapterVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        //searchView = findViewById(R.id.searchView);



        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Youtube");
        setSupportActionBar( toolbar );

        recuperarVideos();
        Log.i("teste" , videos.get(0).getTitulo());
        //RecyclerVideos
        adapterVideo = new AdapterVideo(videos,this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //RecyclerAdapter
        recyclerView.setAdapter(adapterVideo);

    }

    private void recuperarVideos(){

        Video video1 = new Video();
        Video video2 = new Video();

        video1.setTitulo("Aspas amassando Gringo VCT");
        video2.setTitulo("Sacy amassando Gringo VCT");

        videos.add(video1);
        videos.add(video2);

    }


}