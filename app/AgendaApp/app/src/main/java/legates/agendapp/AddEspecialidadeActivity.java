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

import legates.agendapp.Models.Especialidade;

public class AddEspecialidadeActivity extends AppCompatActivity {
    private ProgressDialog loading;
    private Especialidade especialidade = new Especialidade();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_especialidade);

        Button btn_salvar = findViewById(R.id.btn_salvar_especialidade);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText especialidade_nome = findViewById(R.id.input_especialidade_nome);

                especialidade.setNome(especialidade_nome.getText().toString());

                new AddEspecialidadeActivity.add().execute();
            }
        });
    }

    private class add extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(AddEspecialidadeActivity.this);
            loading.setMessage("Salvando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(!especialidade.add()){
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

            Toast toast = Toast.makeText(AddEspecialidadeActivity.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT);
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

            Toast toast = Toast.makeText(AddEspecialidadeActivity.this, "Não foi possível cadastrar", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
            finish();
        }
    }
}
