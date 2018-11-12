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

import legates.agendapp.Models.Medico;
import legates.agendapp.Models.Paciente;

public class MedicoActivity extends AppCompatActivity {
    private AlertDialog userDialog;
    private AlertDialog.Builder userDialogBuilder;
    private ProgressDialog loading;
    private ArrayList<Medico> medicos;
    private ListView listaMedicos;
    private SwipeRefreshLayout swipe;
    public static Medico medico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico);

        swipe = findViewById(R.id.listMedicosSwipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recreate();
            }
        });

        listaMedicos = findViewById(R.id.listMedicos);
        registerForContextMenu(listaMedicos);

        listaMedicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                medico = (Medico) listaMedicos.getItemAtPosition(position);

                userDialogBuilder = new AlertDialog.Builder(MedicoActivity.this);
                userDialogBuilder.setMessage("Nome: " + medico.getNome() + "\n\n" + "CPF: " + medico.getCpf() + "\n\n" + "CRM: " + medico.getCrm() + "\n\n" + "Telefone: " + medico.getTelefone() + "\n\n" + "E-mail: " + medico.getEmail() + "\n")
                        .setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent editMedicoView = new Intent(MedicoActivity.this, EditMedicoActivity.class);
                                startActivity(editMedicoView);
                            }
                        }).setNegativeButton("Especialidades", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent medicoEspecialidadeView = new Intent(MedicoActivity.this, MedicoEspecialidadeActivity.class);
                        startActivity(medicoEspecialidadeView);
                    }
                });

                userDialog = userDialogBuilder.create();
                userDialog.show();
            }
        });

        Button btn_add = findViewById(R.id.btn_add_medico);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPacientesView = new Intent (MedicoActivity.this, AddMedicoActivity.class);
                startActivity(addPacientesView);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        new getMedicos().execute();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        medico = (Medico) listaMedicos.getItemAtPosition(info.position);

        MenuItem remover = menu.add("Remover");
        remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                userDialogBuilder = new AlertDialog.Builder(MedicoActivity.this);
                userDialogBuilder.setMessage("Deseja remover o médico " + medico.getNome() + "?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new removeMedico().execute();
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

    private class getMedicos extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(MedicoActivity.this);
            loading.setMessage("Carregando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            medico = new Medico();
            medicos = medico.get();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(loading.isShowing()){
                loading.dismiss();
            }

            ArrayAdapter<Medico> adapter = new ArrayAdapter<Medico>(MedicoActivity.this, android.R.layout.simple_list_item_1, medicos);
            listaMedicos.setAdapter(adapter);
        }
    }

    private class removeMedico extends  AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(MedicoActivity.this);
            loading.setMessage("Carregando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(!medico.remove()){
                this.cancel(true);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(loading.isShowing()){
                loading.dismiss();
            }

            Toast toast = Toast.makeText(MedicoActivity.this, "Removido com sucesso", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
            recreate();
        }

        @Override
        protected void onCancelled() {
            if(loading.isShowing()){
                loading.dismiss();
            }
            super.onCancelled();
            Toast toast = Toast.makeText(MedicoActivity.this, "Não foi possível remover", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
        }
    }
}
