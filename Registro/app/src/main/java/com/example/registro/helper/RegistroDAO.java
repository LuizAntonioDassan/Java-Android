package com.example.registro.helper;

import com.example.registro.model.gasto;

public class RegistroDAO implements  IRegistroDAO{

    public RegistroDAO() {
    }

    @Override
    public boolean salvar(gasto gasto) {
        return false;
    }

    @Override
    public boolean atualizar(gasto gasto) {
        return false;
    }

    @Override
    public boolean deletar(gasto gasto) {
        return false;
    }
}
