package com.example.registro.model;

import java.io.Serializable;

public class gasto implements Serializable {
    private Long id;
    private String nomeGasto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeGasto() {
        return nomeGasto;
    }

    public void setNomeGasto(String nomeGasto) {
        this.nomeGasto = nomeGasto;
    }
}
