package legates.agendapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static legates.agendapp.PacienteActivity.paciente;

public class EditPacienteActivity extends AppCompatActivity {
    ProgressDialog loading;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_paciente);

        EditText editNome = findViewById(R.id.input_edit_paciente_nome);
        EditText editTelefone = findViewById(R.id.input_edit_paciente_telefone);
        EditText editEmail = findViewById(R.id.input_edit_paciente_email);

        editNome.setText(paciente.getNome());
        editTelefone.setText(paciente.getTelefone());
        editEmail.setText(paciente.getEmail());

        Button btn_edit_paciente = findViewById(R.id.btn_edit_paciente);
        btn_edit_paciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new editPaciente().execute();
            }
        });
    }

    private class editPaciente extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(EditPacienteActivity.this);
            loading.setMessage("Editando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            EditText nome = findViewById(R.id.input_edit_paciente_nome);
            EditText telefone = findViewById(R.id.input_edit_paciente_telefone);
            EditText email = findViewById(R.id.input_edit_paciente_email);

            paciente.setNome(nome.getText().toString());
            paciente.setTelefone(telefone.getText().toString());
            paciente.setEmail(email.getText().toString());

            if(!paciente.editPaciente()){
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

            Toast toast = Toast.makeText(EditPacienteActivity.this, "Editado com sucesso.", Toast.LENGTH_SHORT);
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

            Toast toast = Toast.makeText(EditPacienteActivity.this, "Não foi possível editar", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            finish();
        }
    }
}
