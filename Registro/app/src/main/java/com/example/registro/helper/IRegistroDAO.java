package com.example.registro.helper;

import com.example.registro.model.gasto;

public interface IRegistroDAO {

    public boolean salvar(gasto gasto);
    public boolean atualizar(gasto gasto);
    public boolean deletar(gasto gasto);
    public<gasto> gastar();


}
