package com.example.registro.helper;

import com.example.registro.model.gasto;

import java.util.List;

public interface IRegistroDAO {

    public boolean salvar(gasto gasto);
    public boolean atualizar(gasto gasto);
    public boolean deletar(gasto gasto);
    public List<gasto> gastar();


}
