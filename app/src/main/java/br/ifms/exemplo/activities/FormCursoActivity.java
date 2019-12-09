package br.ifms.exemplo.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ifms.exemplo.R;
import br.ifms.exemplo.dao.CursoDAO;
import br.ifms.exemplo.model.Curso;

public class FormCursoActivity extends AppCompatActivity {

    private Button botaoSalvar;
    private EditText campoNome;
    private EditText campoDescricao;
    private CursoDAO dao;
    private Calendar meuCalendario;
    private DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Novo Curso");
        setContentView(R.layout.activity_form_curso);
        inicializarDAO();
        inicializarReferencias();
        inicializarAcoes();
    }

    private void inicializarDAO(){
        dao = new CursoDAO(getBaseContext());
    }

    private void inicializarReferencias(){
        botaoSalvar = findViewById(R.id.activity_form_curso_botao_salvar);
        campoNome = findViewById(R.id.activity_form_curso_campo_nome);
        campoDescricao = findViewById(R.id.activity_form_curso_campo_descricao);

        meuCalendario = Calendar.getInstance();

         date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {

                meuCalendario.set(Calendar.YEAR, ano);
                meuCalendario.set(Calendar.MONTH, mes);
                meuCalendario.set(Calendar.DAY_OF_MONTH, dia);

                atualizarValorEditTextComDataAtual();
            }
        };

        campoDescricao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                new DatePickerDialog(FormCursoActivity.this, date, meuCalendario.get(Calendar.YEAR),
                        meuCalendario.get(Calendar.MONTH), meuCalendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void inicializarAcoes(){
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Curso curso = new Curso();
                curso.setNome(campoNome.getText().toString());
                curso.setDescricao(campoDescricao.getText().toString());
                dao.addNovoCurso(curso);
                Toast toast = Toast.makeText(FormCursoActivity.this,"Salvo!",Toast.LENGTH_LONG);
                toast.show();
                finish();
            }
        });
    }

    private void atualizarValorEditTextComDataAtual(){

        String meuFormato = "dd/MM/yyyy";
        SimpleDateFormat simplesData = new SimpleDateFormat(meuFormato, new Locale("pt","BR"));

        campoDescricao.setText(simplesData.format(meuCalendario.getTime()));
    }
}
