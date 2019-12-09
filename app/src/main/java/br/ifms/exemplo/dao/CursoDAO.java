package br.ifms.exemplo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ifms.exemplo.model.Curso;

public class CursoDAO {
    private SQLiteDatabase db;
    private Helper helper;
    private static CursoDAO instance;

    public CursoDAO(Context context){
        helper = new Helper(context);
    }

    public void addNovoCurso(Curso curso){
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome",curso.getNome());
        values.put("descricao", curso.getDescricao());

        db.insert("curso",null,values);
    }

    public void removerCurso(Curso curso){


    }

    public List<Curso> getCursos() {
        db = helper.getWritableDatabase();
        StringBuilder query = new StringBuilder();
        query.append("SELECT * ");
        query.append("FROM Curso ");
        Cursor cursor = db.rawQuery(query.toString(),null);

        List<Curso> cursos = new ArrayList<Curso>();

        if(cursor == null){
            return cursos;
        }

        Curso curso;
        while(cursor.moveToNext()){
            curso = new Curso();
            curso.setId(cursor.getInt(0));
            curso.setNome(cursor.getString(1));
            curso.setDescricao(cursor.getString(2));
            cursos.add(curso);
        }
        return cursos;
    }
}
