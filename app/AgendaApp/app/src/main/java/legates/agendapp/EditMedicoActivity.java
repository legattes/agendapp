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

import static legates.agendapp.MedicoActivity.medico;

public class EditMedicoActivity extends AppCompatActivity {

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medico);

        EditText editNome = findViewById(R.id.input_edit_medico_nome);
        EditText editTelefone = findViewById(R.id.input_edit_medico_telefone);
        EditText editEmail = findViewById(R.id.input_edit_medico_email);

        editNome.setText(medico.getNome());
        editTelefone.setText(medico.getTelefone());
        editEmail.setText(medico.getEmail());

        Button btn_edit_medico = findViewById(R.id.btn_edit_medico);
        btn_edit_medico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new edit().execute();
            }
        });
    }

    private class edit extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(EditMedicoActivity.this);
            loading.setMessage("Editando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            EditText nome = findViewById(R.id.input_edit_medico_nome);
            EditText telefone = findViewById(R.id.input_edit_medico_telefone);
            EditText email = findViewById(R.id.input_edit_medico_email);

            medico.setNome(nome.getText().toString());
            medico.setTelefone(telefone.getText().toString());
            medico.setEmail(email.getText().toString());

            if(!medico.edit()){
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

            Toast toast = Toast.makeText(EditMedicoActivity.this, "Editado com sucesso.", Toast.LENGTH_SHORT);
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

            Toast toast = Toast.makeText(EditMedicoActivity.this, "Não foi possível editar", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            finish();
        }
    }
}
