package legates.agendapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import legates.agendapp.Models.Paciente;

public class AddPacienteActivity extends AppCompatActivity {
;
    private ProgressDialog loading;
    private Paciente paciente = new Paciente();
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

                paciente.setNome(paciente_nome.getText().toString());
                paciente.setCpf(paciente_cpf.getText().toString());
                paciente.setTelefone(paciente_telefone.getText().toString());
                paciente.setEmail(paciente_email.getText().toString());

                new add().execute();
            }
        });
    }

    private class add extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(AddPacienteActivity.this);
            loading.setMessage("Salvando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(!paciente.addPaciente()){
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

            Toast toast = Toast.makeText(AddPacienteActivity.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            finish();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            if(loading.isShowing()){
                loading.dismiss();
            }

            Toast toast = Toast.makeText(AddPacienteActivity.this, "Não foi possível cadastrar", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            finish();
        }
    }
}
