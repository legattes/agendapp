package legates.agendapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import legates.agendapp.Models.Especialidade;
import legates.agendapp.Models.Medico;

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

    @Override
    protected void onStart() {
        super.onStart();
        new getEspecialidadesByMedico().execute();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        especialidade = (Especialidade) listaEspecialidades.getItemAtPosition(info.position);

        MenuItem remover = menu.add("Remover");
        remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                userDialogBuilder = new AlertDialog.Builder(MedicoEspecialidadeActivity.this);
                userDialogBuilder.setMessage("Deseja remover a especialidade " + especialidade.getNome() + "?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new removeEspecialidade().execute();
                    }
                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                userDialog = userDialogBuilder.create();
                userDialog.show();

                return false;
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
    private class removeEspecialidade extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(MedicoEspecialidadeActivity.this);
            loading.setMessage("Removendo...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            medico_id = intent.getStringExtra("MEDICO_ID");
            Medico medico = new Medico();
            medico.setId(medico_id);
            medico.removeEspecilidade(especialidade.getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(loading.isShowing()){
                loading.dismiss();
            }

            Toast toast = Toast.makeText(MedicoEspecialidadeActivity.this, "Removido com sucesso", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
            recreate();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            if(loading.isShowing()){
                loading.dismiss();
            }

            Toast toast = Toast.makeText(MedicoEspecialidadeActivity.this, "Não foi possível remover", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
        }
    }
}
