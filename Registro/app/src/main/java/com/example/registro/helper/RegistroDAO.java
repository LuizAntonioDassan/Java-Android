package com.example.registro.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.registro.model.gasto;

import java.util.ArrayList;
import java.util.List;

public class RegistroDAO implements  IRegistroDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public RegistroDAO(Context context) {
        dbhelper db = new dbhelper( context );
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();

    }

    @Override
    public boolean salvar(gasto gasto) {

        ContentValues cv = new ContentValues();
        cv.put("nome",gasto.getNomeGasto());

        try {
            escreve.insert(dbhelper.TABELA_TAREFAS, null, cv);
        }catch (Exception e){
            Log.e("INFO" ,  "Erro ao salvar o Gasto" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(gasto gasto) {
        return false;
    }

    @Override
    public boolean deletar(gasto gasto) {
        return false;
    }

    @Override
    public List<gasto> gastar() {

        List<gasto> gastos  = new ArrayList<>();

        String sql = "SELECT * FROM " + dbhelper.TABELA_TAREFAS + " ;";
        Cursor c = le.rawQuery(sql,null);

        while (c.moveToNext()){
            gasto gasto = new gasto();

            Long id = c.getLong( c.getColumnIndex("id") );
            String nomeGasto = c.getString( c.getColumnIndex("nome") );

            gasto.setId(id);
            gasto.setNomeGasto(nomeGasto);

            gastos.add(gasto);
            Log.i("registroDAO", gasto.getNomeGasto());

        }

        return gastos;
    }
}
