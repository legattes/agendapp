package legates.agendapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import legates.agendapp.Models.Especialidade;

public class MedicoEspecialidadeActivity extends AppCompatActivity {
    private AlertDialog userDialog;
    private AlertDialog.Builder userDialogBuilder;
    private ProgressDialog loading;
    private ListView listaEspecialidades;
    private ArrayList<Especialidade> especialidades;
    private SwipeRefreshLayout swipe;
    private Intent intent;
    private String medico_id;
    public static Especialidade especialidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_especialidade);

        swipe = findViewById(R.id.listMedicosEspecialidadesSwipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recreate();
            }
        });

        intent = getIntent();

        listaEspecialidades = findViewById(R.id.listMedicosEspecialidades);

        registerForContextMenu(listaEspecialidades);

        new getEspecialidadesByMedico().execute();

        Button btn_add = findViewById(R.id.btn_add_medico_especialidade);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addMedicoEspecialidadesView = new Intent (MedicoEspecialidadeActivity.this, AddMedicoEspecialidadeActivity.class);
                addMedicoEspecialidadesView.putExtra("MEDICO_ID", medico_id);
                startActivity(addMedicoEspecialidadesView);
            }
        });
    }

    private class getEspecialidadesByMedico extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(MedicoEspecialidadeActivity.this);
            loading.setMessage("Carregando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            medico_id = intent.getStringExtra("MEDICO_ID");
            especialidade = new Especialidade();
            especialidades = especialidade.getByMedico(medico_id);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(loading.isShowing()){
                loading.dismiss();
            }

            ArrayAdapter<Especialidade> adapter = new ArrayAdapter<Especialidade>(MedicoEspecialidadeActivity.this, android.R.layout.simple_list_item_1, especialidades);
            listaEspecialidades.setAdapter(adapter);
        }
    }
}
