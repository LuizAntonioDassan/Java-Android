package com.example.requisioapi.api;

import com.example.requisioapi.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("{cep}/json/")
    Call<CEP> recuperarCep(@Path("cep") String Cep);

}
