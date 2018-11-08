package legates.agendapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class PacienteActivity extends AppCompatActivity {

    private ProgressDialog p;
    private ListView listaPacientes;
    ArrayList<String> pacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        pacientes = new ArrayList<>();
        listaPacientes = findViewById(R.id.listPacientes);

        new getPacientes().execute();

        listaPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder window = new AlertDialog.Builder(PacienteActivity.this);
                window.setMessage("olá mundo\n\n\nnome do paciente\n\n\ndocumento");
                window.show();
            }
        });
    }

    private class getPacientes extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
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

                String name = null;
                try {
                    name = c.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pacientes.add(name);
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(p.isShowing()){
                p.dismiss();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(PacienteActivity.this, android.R.layout.simple_list_item_1, pacientes);
            listaPacientes.setAdapter(adapter);
        }

    }
}
