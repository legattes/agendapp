package legates.agendapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class AddPacienteActivity extends AppCompatActivity {

    private OkHttpClient cliente = new OkHttpClient();
    private ProgressDialog loading;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private addPacientes task;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paciente);

        Button btn_salvar = findViewById(R.id.btn_salvar_paciente);
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText paciente_nome = findViewById(R.id.input_paciente_nome);
                EditText paciente_cpf = findViewById(R.id.input_paciente_cpf);
                EditText paciente_telefone = findViewById(R.id.input_paciente_telefone);
                EditText paciente_email = findViewById(R.id.input_paciente_email);
                nome = paciente_nome.getText().toString();
                cpf = paciente_cpf.getText().toString();
                telefone = paciente_telefone.getText().toString();
                email = paciente_email.getText().toString();

                task = new addPacientes();
                task.execute();
            }
        });
    }

    private class addPacientes extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            loading = new ProgressDialog(AddPacienteActivity.this);
            loading.setMessage("Salvando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("paciente_nome", nome)
                    .add("paciente_cpf", cpf)
                    .add("paciente_telefone", telefone)
                    .add("paciente_email", email)
                    .build();
            Request request = new Request.Builder()
                    .url("http://agendapp.dx.am/paciente/add.php")
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

            Toast.makeText(AddPacienteActivity.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(AddPacienteActivity.this, "Não foi possível cadastrar", Toast.LENGTH_SHORT).show();
            AddPacienteActivity.this.onDestroy();
        }
    }
}
