package br.ifms.exemplo.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.ifms.exemplo.R;
import br.ifms.exemplo.model.Curso;

public class AdapterPersonalizado extends BaseAdapter {

    private List<Curso> cursos;
    private AppCompatActivity activity;
    private Context contexto;

    public void setCursos(List<Curso> cursos){
        this.cursos = cursos;
    }

    public AdapterPersonalizado(AppCompatActivity activity, Context context){
        this.cursos = new ArrayList<Curso>();
        this.activity = activity;
        this.contexto = context;

    }

    @Override
    public int getCount() {
        return cursos.size();
    }

    @Override
    public Object getItem(int position) {
        return cursos.get(position);
    }

    @Override
    public long getItemId(int position) {
        Curso  curso = cursos.get(position);
        return curso.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity
                .getLayoutInflater()
                .inflate(R.layout.activity_list_cursos_list_item, parent,false);

        final TextView campoNome = view.findViewById(R.id.acitivity_list_cursos_list_item_campo_nome);
        final TextView campoDescricao = view.findViewById(R.id.acitivity_list_cursos_list_item_campo_descricao);
        Button botaoFeito = view.findViewById(R.id.button_feito);



        Curso curso = cursos.get(position);

        campoNome.setText(curso.getNome());
        campoDescricao.setText(curso.getDescricao());

        String[] arr = campoDescricao.getText().toString().split("/");

        String meuFormato = "dd/MM/yyyy";
        SimpleDateFormat simplesData = new SimpleDateFormat(meuFormato, new Locale("pt","BR"));

        SimpleDateFormat sdf = new SimpleDateFormat(meuFormato);

        Calendar c = Calendar.getInstance();


        try {
            Date strDate = sdf.parse(campoDescricao.getText().toString());

            if (strDate.compareTo(c.getTime()) == 0)
            {
                campoNome.setBackgroundColor(Color.YELLOW);
                campoDescricao.setBackgroundColor(Color.YELLOW);
            }
            else if(strDate.compareTo(c.getTime()) <= -1)
            {
                campoNome.setBackgroundColor(Color.RED);
                campoDescricao.setBackgroundColor(Color.RED);

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        botaoFeito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                campoNome.setBackgroundColor(Color.GREEN);
                campoDescricao.setBackgroundColor(Color.GREEN);
            }
        });

        return view;
    }
}
