package br.ifms.exemplo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {

    public static String DB_NAME = "app_cursos.db";
    public static Integer VERSION = 1;

    public Helper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableCurso(db);
        createTableAluno(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTableCurso(SQLiteDatabase db){
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE curso( ");
        query.append("id INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append("nome TEXT, ");
        query.append("descricao TEXT)");
        db.execSQL(query.toString());
    }

    private void createTableAluno(SQLiteDatabase db){

    }
}
