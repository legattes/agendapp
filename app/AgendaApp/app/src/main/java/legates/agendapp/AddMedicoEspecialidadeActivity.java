package legates.agendapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import legates.agendapp.Models.Especialidade;
import legates.agendapp.Models.Medico;

public class AddMedicoEspecialidadeActivity extends AppCompatActivity {
    private ProgressDialog loading;
    private Especialidade especialidade;
    private ArrayList<Especialidade> especialidades = new ArrayList<Especialidade>();
    private Spinner listaEspecialidades;
    private String medico_id;
    private Medico medico = new Medico();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medico_especialidade);

        Intent intent = getIntent();
        medico_id = intent.getStringExtra("MEDICO_ID");

        listaEspecialidades = findViewById(R.id.input_medico_especialidade);
        medico.setId(medico_id);

        new getEspecialidades().execute();

    }

    private class addMedicoEspecialidade extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(AddMedicoEspecialidadeActivity.this);
            loading.setMessage("Salvando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            /*if(!medico.addEspecialidade()){
                this.cancel(true);
            }*/
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(loading.isShowing()){
                loading.dismiss();
            }

            Toast toast = Toast.makeText(AddMedicoEspecialidadeActivity.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
            finish();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            if(loading.isShowing()){
                loading.dismiss();
            }

            Toast toast = Toast.makeText(AddMedicoEspecialidadeActivity.this, "Não foi possível cadastrar", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
            finish();
        }
    }

    private class getEspecialidades extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(AddMedicoEspecialidadeActivity.this);
            loading.setMessage("Carregando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            especialidade = new Especialidade();
            especialidades = especialidade.get();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(loading.isShowing()){
                loading.dismiss();
            }

            ArrayAdapter<Especialidade> adapter = new ArrayAdapter<Especialidade>(AddMedicoEspecialidadeActivity.this, android.R.layout.simple_spinner_item, especialidades);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            listaEspecialidades.setAdapter(adapter);
        }
    }
}
