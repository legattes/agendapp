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

import java.io.Serializable;
import java.util.ArrayList;

import legates.agendapp.Models.Paciente;

public class PacienteActivity extends AppCompatActivity {

    AlertDialog userDialog;
    AlertDialog.Builder userDialogBuilder;
    private ProgressDialog loading;
    private ListView listaPacientes;
    ArrayList<Paciente> pacientes;
    public static Paciente paciente;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        listaPacientes = findViewById(R.id.listPacientes);

        registerForContextMenu(listaPacientes);

        listaPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                paciente = (Paciente) listaPacientes.getItemAtPosition(position);

                userDialogBuilder = new AlertDialog.Builder(PacienteActivity.this);
                userDialogBuilder.setMessage(paciente.getNome() + "\n\n" + paciente.getCpf() + "\n\n" + paciente.getTelefone() + "\n\n" + paciente.getEmail() + "\n")
                        .setNeutralButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent editPacienteView = new Intent(PacienteActivity.this, EditPacienteActivity.class);
                        startActivity(editPacienteView);
                    }
                });

                userDialog = userDialogBuilder.create();
                userDialog.show();
            }
        });

        Button btn_add = findViewById(R.id.btn_add_paciente);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPacientesView = new Intent (PacienteActivity.this, AddPacienteActivity.class);
                startActivity(addPacientesView);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        new getPacientes().execute();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        paciente = (Paciente) listaPacientes.getItemAtPosition(info.position);

        MenuItem remover = menu.add("Remover");
        remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                userDialogBuilder = new AlertDialog.Builder(PacienteActivity.this);
                userDialogBuilder.setMessage("Deseja remover o paciente " + paciente.getNome() + "?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new removePaciente().execute();
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

    private class getPacientes extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(PacienteActivity.this);
            loading.setMessage("Carregando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            paciente = new Paciente();
            pacientes = paciente.getPacientes();
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(loading.isShowing()){
                loading.dismiss();
            }

            ArrayAdapter<Paciente> adapter = new ArrayAdapter<Paciente>(PacienteActivity.this, android.R.layout.simple_list_item_1, pacientes);
            listaPacientes.setAdapter(adapter);
        }

    }

    private class removePaciente extends  AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(PacienteActivity.this);
            loading.setMessage("Removendo...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(!paciente.removePaciente()){
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

            Toast toast = Toast.makeText(PacienteActivity.this, "Removido com sucesso", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            recreate();
        }

        @Override
        protected void onCancelled() {
            if(loading.isShowing()){
                loading.dismiss();
            }
            super.onCancelled();
            Toast toast = Toast.makeText(PacienteActivity.this, "Não foi possível remover", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

}
