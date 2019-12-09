package br.ifms.exemplo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.ifms.exemplo.R;
import br.ifms.exemplo.adapters.AdapterPersonalizado;
import br.ifms.exemplo.dao.CursoDAO;

public class ListCursosActivity extends AppCompatActivity {
    private CursoDAO dao;
    private ListView listView;
    private AdapterPersonalizado adapterPersonalizado;
    private FloatingActionButton botaoNovoCurso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Cursos");
        setContentView(R.layout.activity_list_cursos);
        inicializarDAO();
        inicializarReferencias();
        inicializarListView();
        inicializarAcoes();
    }

    private void inicializarDAO() {
        dao = new CursoDAO(getBaseContext());
    }

    private void inicializarReferencias() {
        listView = findViewById(R.id.activity_list_cursos_listview);
        botaoNovoCurso = findViewById(R.id.activity_list_cursos_botao_novo_curso);
    }

    private void inicializarListView() {
        adapterPersonalizado = new AdapterPersonalizado(this, getApplicationContext());
        listView.setAdapter(adapterPersonalizado);


    }

    private void inicializarAcoes() {
        botaoNovoCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListCursosActivity.this, FormCursoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterPersonalizado.setCursos(dao.getCursos());
    }
}
