package legates.agendapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import legates.agendapp.Models.Consulta;

public class EditConsultaActivity extends AppCompatActivity {
    private ProgressDialog loading;
    private Consulta consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_consulta);

        Intent intent = getIntent();
        String consulta_id = intent.getStringExtra("consulta_id");

        final EditText input_edit_consulta_data = findViewById(R.id.input_edit_consulta_data);

        consulta = new Consulta();

        consulta.setId(consulta_id);

        Button btn_edit_consulta_data = findViewById(R.id.btn_edit_consulta_data);

        btn_edit_consulta_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consulta.setData(input_edit_consulta_data.getText().toString());
                new remarcaConsulta().execute();
            }
        });
    }

    private class remarcaConsulta extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(EditConsultaActivity.this);
            loading.setMessage("Remarcando...");
            loading.setCancelable(false);
            loading.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {

            if(!consulta.remarcar()){
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

            Toast toast = Toast.makeText(EditConsultaActivity.this, "Remarcardo com sucesso", Toast.LENGTH_SHORT);
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

            Toast toast = Toast.makeText(EditConsultaActivity.this, "Não foi possível remarcar", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
            finish();
        }
    }
}
