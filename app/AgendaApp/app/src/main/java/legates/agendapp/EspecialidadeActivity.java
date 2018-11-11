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

public class EspecialidadeActivity extends AppCompatActivity {
    private AlertDialog userDialog;
    private AlertDialog.Builder userDialogBuilder;
    private ProgressDialog loading;
    private ArrayList<Especialidade> especialidades;
    private ListView listaEspecialidades;
    private SwipeRefreshLayout swipe;
    public static Especialidade especialidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialidade);

        swipe = findViewById(R.id.listEspecialidadesSwipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recreate();
            }
        });

        listaEspecialidades = findViewById(R.id.listEspecialidadess);
        registerForContextMenu(listaEspecialidades);

        listaEspecialidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                especialidade = (Especialidade) listaEspecialidades.getItemAtPosition(position);

                userDialogBuilder = new AlertDialog.Builder(EspecialidadeActivity.this);
                userDialogBuilder.setMessage("Nome: " + especialidade.getNome() + "\n")
                        .setNeutralButton("Editar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent editEspecialidadeView = new Intent(EspecialidadeActivity.this, EditEspecialidadeActivity.class);
                                startActivity(editEspecialidadeView);
                            }
                        });

                userDialog = userDialogBuilder.create();
                userDialog.show();
            }
        });

        Button btn_add = findViewById(R.id.btn_add_especialidade);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addEspecialidadeView = new Intent (EspecialidadeActivity.this, AddEspecialidadeActivity.class);
                startActivity(addEspecialidadeView);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        new getEspecialidades().execute();
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
                userDialogBuilder = new AlertDialog.Builder(EspecialidadeActivity.this);
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

    private class getEspecialidades extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(EspecialidadeActivity.this);
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

            ArrayAdapter<Especialidade> adapter = new ArrayAdapter<Especialidade>(EspecialidadeActivity.this, android.R.layout.simple_list_item_1, especialidades);
            listaEspecialidades.setAdapter(adapter);
        }
    }

    private class removeEspecialidade extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(EspecialidadeActivity.this);
            loading.setMessage("Carregando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(!especialidade.remove()){
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

            Toast toast = Toast.makeText(EspecialidadeActivity.this, "Removido com sucesso", Toast.LENGTH_SHORT);
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
            Toast toast = Toast.makeText(EspecialidadeActivity.this, "Não foi possível remover", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
        }
    }
}
