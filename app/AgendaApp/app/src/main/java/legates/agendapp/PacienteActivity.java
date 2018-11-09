package legates.agendapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import legates.agendapp.Models.Paciente;

public class PacienteActivity extends AppCompatActivity {

    private ProgressDialog p;
    private OkHttpClient cliente = new OkHttpClient();
    private ProgressDialog loading;
    private ListView listaPacientes;
    ArrayList<Paciente> pacientes;
    Paciente paciente;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        listaPacientes = findViewById(R.id.listPacientes);

        registerForContextMenu(listaPacientes);

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
        pacientes = new ArrayList<>();

        new getPacientes().execute();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem remover = menu.add("Remover");
        remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                paciente = (Paciente) listaPacientes.getItemAtPosition(info.position);

                new removePacientes().execute();
                return false;
            }
        });

        MenuItem editar = menu.add("Editar");
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                paciente = (Paciente) listaPacientes.getItemAtPosition(info.position);

                Intent editPacienteView = new Intent(PacienteActivity.this, EditPacienteActivity.class);
                editPacienteView.putExtra("PACIENTE_ID", paciente.getId());
                startActivity(editPacienteView);
                return false;
            }
        });
    }

    private class getPacientes extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(PacienteActivity.this);
            p.setMessage("Carregando...");
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url("http://agendapp.dx.am/paciente/list.php/").build();

            Response response = null;

            try{
                response = client.newCall(request).execute();
            } catch (IOException e){
                e.printStackTrace();
            }

            JSONObject reader = new JSONObject();
            try {
                reader = new JSONObject(response.body().string());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONArray json = new JSONArray();
            try {
                json = reader.getJSONArray("pacientes");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < json.length(); i++){
                JSONObject c = new JSONObject();

                try {
                    c = json.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Paciente paciente = new Paciente();

                try {
                    paciente.setId(c.getString("id"));
                    paciente.setNome(c.getString("nome"));
                    paciente.setCpf(c.getString("cpf"));
                    paciente.setTelefone(c.getString("telefone"));
                    paciente.setEmail(c.getString("email"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pacientes.add(paciente);
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(p.isShowing()){
                p.dismiss();
            }

            ArrayAdapter<Paciente> adapter = new ArrayAdapter<Paciente>(PacienteActivity.this, android.R.layout.simple_list_item_1, pacientes);
            listaPacientes.setAdapter(adapter);
        }

    }

    private class removePacientes extends  AsyncTask<Void, Void, Void>{

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
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("paciente_id", paciente.getId())
                    .build();
            Request request = new Request.Builder()
                    .url("http://agendapp.dx.am/paciente/remove.php")
                    .method("POST",requestBody).header("Content-Length", "0").build();
            try {
                Response response = cliente.newCall(request).execute();
                if(response.headers().get("Response-Code").equals("420")){
                    if(loading.isShowing()){
                        loading.dismiss();
                    }
                    this.cancel(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(loading.isShowing()){
                loading.dismiss();
            }

            Toast.makeText(PacienteActivity.this, "Removido com sucesso", Toast.LENGTH_SHORT).show();
            recreate();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(PacienteActivity.this, "Não foi possível remover", Toast.LENGTH_SHORT).show();
            recreate();
        }
    }

}
