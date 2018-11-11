package legates.agendapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import legates.agendapp.Models.Medico;


public class AddMedicoActivity extends AppCompatActivity {
    private ProgressDialog loading;
    private Medico medico = new Medico();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medico);

        Button btn_salvar = findViewById(R.id.btn_salvar_medico);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText medico_nome = findViewById(R.id.input_medico_nome);
                EditText medico_cpf = findViewById(R.id.input_medico_cpf);
                EditText medico_crm = findViewById(R.id.input_medico_crm);
                EditText medico_telefone = findViewById(R.id.input_medico_telefone);
                EditText medico_email = findViewById(R.id.input_medico_email);

                medico.setNome(medico_nome.getText().toString());
                medico.setCpf(medico_cpf.getText().toString());
                medico.setCrm(medico_crm.getText().toString());
                medico.setTelefone(medico_telefone.getText().toString());
                medico.setEmail(medico_email.getText().toString());

                new AddMedicoActivity.add().execute();
            }
        });
    }

    private class add extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(AddMedicoActivity.this);
            loading.setMessage("Salvando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(!medico.add()){
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

            Toast toast = Toast.makeText(AddMedicoActivity.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT);
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

            Toast toast = Toast.makeText(AddMedicoActivity.this, "Não foi possível cadastrar", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
            finish();
        }
    }
}
