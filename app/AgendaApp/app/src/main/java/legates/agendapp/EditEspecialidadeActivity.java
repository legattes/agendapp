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

import static legates.agendapp.EspecialidadeActivity.especialidade;

public class EditEspecialidadeActivity extends AppCompatActivity {
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_especialidade);

        EditText editNome = findViewById(R.id.input_edit_especialidade_nome);

        editNome.setText(especialidade.getNome());

        Button btn_edit_especialidade = findViewById(R.id.btn_edit_especialidade);
        btn_edit_especialidade.setOnClickListener(new View.OnClickListener() {
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
            loading = new ProgressDialog(EditEspecialidadeActivity.this);
            loading.setMessage("Editando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            EditText nome = findViewById(R.id.input_edit_especialidade_nome);

            especialidade.setNome(nome.getText().toString());

            if(!especialidade.edit()){
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

            Toast toast = Toast.makeText(EditEspecialidadeActivity.this, "Editado com sucesso.", Toast.LENGTH_SHORT);
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

            Toast toast = Toast.makeText(EditEspecialidadeActivity.this, "Não foi possível editar", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
            finish();
        }
    }
}
