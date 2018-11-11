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

import legates.agendapp.Models.Convenio;

public class ConvenioActivity extends AppCompatActivity {
    private AlertDialog userDialog;
    private AlertDialog.Builder userDialogBuilder;
    private ProgressDialog loading;
    private ArrayList<Convenio> convenios;
    private ListView listaConvenios;
    private SwipeRefreshLayout swipe;
    public static Convenio convenio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenio);

        swipe = findViewById(R.id.listConveniosSwipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recreate();
            }
        });

        listaConvenios = findViewById(R.id.listConvenios);

        registerForContextMenu(listaConvenios);

        listaConvenios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                convenio = (Convenio) listaConvenios.getItemAtPosition(position);

                userDialogBuilder = new AlertDialog.Builder(ConvenioActivity.this);
                userDialogBuilder.setMessage("Nome: " + convenio.getNome() + "\n\n" + "Dia de faturar: " + convenio.getDia_mes_fatura() + "\n")
                        .setNeutralButton("Editar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent editConvenioView = new Intent(ConvenioActivity.this, EditConvenioActivity.class);
                                startActivity(editConvenioView);
                            }
                        });

                userDialog = userDialogBuilder.create();
                userDialog.show();
            }
        });

        Button btn_add = findViewById(R.id.btn_add_convenio);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addConveniosView = new Intent (ConvenioActivity.this, AddConvenioActivity.class);
                startActivity(addConveniosView);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        new getConvenios().execute();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        convenio = (Convenio) listaConvenios.getItemAtPosition(info.position);

        MenuItem remover = menu.add("Remover");
        remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                userDialogBuilder = new AlertDialog.Builder(ConvenioActivity.this);
                userDialogBuilder.setMessage("Deseja remover o convenio " + convenio.getNome() + "?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new removeConvenio().execute();
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

    private class getConvenios extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(ConvenioActivity.this);
            loading.setMessage("Carregando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            convenio = new Convenio();
            convenios = convenio.get();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(loading.isShowing()){
                loading.dismiss();
            }

            ArrayAdapter<Convenio> adapter = new ArrayAdapter<Convenio>(ConvenioActivity.this, android.R.layout.simple_list_item_1, convenios);
            listaConvenios.setAdapter(adapter);
        }
    }

    private class removeConvenio extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(ConvenioActivity.this);
            loading.setMessage("Carregando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(!convenio.remove()){
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

            Toast toast = Toast.makeText(ConvenioActivity.this, "Removido com sucesso", Toast.LENGTH_SHORT);
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
            Toast toast = Toast.makeText(ConvenioActivity.this, "Não foi possível remover", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
        }
    }
}
