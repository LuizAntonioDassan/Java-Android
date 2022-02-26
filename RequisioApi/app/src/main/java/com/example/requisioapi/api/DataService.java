package com.example.requisioapi.api;



import com.example.requisioapi.model.Fotos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("/photos/")
    Call<List<Fotos>> recuperarFotos();

}
