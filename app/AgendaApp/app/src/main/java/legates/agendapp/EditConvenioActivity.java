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

import static legates.agendapp.ConvenioActivity.convenio;

public class EditConvenioActivity extends AppCompatActivity {
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_convenio);

        EditText editNome = findViewById(R.id.input_edit_convenio_nome);
        EditText editDia_mes_fatura = findViewById(R.id.input_edit_convenio_dia_mes_fatura);

        editNome.setText(convenio.getNome());
        editDia_mes_fatura.setText(convenio.getDia_mes_fatura());

        Button btn_edit_convenio = findViewById(R.id.btn_edit_convenio);
        btn_edit_convenio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new edit().execute();
            }
        });
    }

    private class edit extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(EditConvenioActivity.this);
            loading.setMessage("Editando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            EditText nome = findViewById(R.id.input_edit_convenio_nome);
            EditText dia_mes_fatura = findViewById(R.id.input_edit_convenio_dia_mes_fatura);

            convenio.setNome(nome.getText().toString());
            convenio.setDia_mes_fatura(dia_mes_fatura.getText().toString());

            if(!convenio.edit()){
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

            Toast toast = Toast.makeText(EditConvenioActivity.this, "Editado com sucesso.", Toast.LENGTH_SHORT);
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

            Toast toast = Toast.makeText(EditConvenioActivity.this, "Não foi possível editar", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
            finish();
        }
    }
}
