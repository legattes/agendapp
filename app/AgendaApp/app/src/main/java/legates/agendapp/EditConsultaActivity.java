package legates.agendapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import legates.agendapp.Models.Consulta;

public class EditConsultaActivity extends AppCompatActivity {
    private ProgressDialog loading;
    private Consulta consulta;
    private EditText input_edit_consulta_dia;
    private EditText input_edit_consulta_hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_consulta);

        Intent intent = getIntent();
        String consulta_id = intent.getStringExtra("consulta_id");

        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        input_edit_consulta_dia = findViewById(R.id.input_edit_consulta_dia);
        input_edit_consulta_dia.setInputType(InputType.TYPE_NULL);
        input_edit_consulta_dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog date_picker = new DatePickerDialog(EditConsultaActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String data = String.valueOf(year) + "-" + String.valueOf(month) + "-" +
                                String.valueOf(dayOfMonth);
                        input_edit_consulta_dia.setText(data);
                    }
                }, year, month, dayOfMonth);

                date_picker.show();
            }
        });

        input_edit_consulta_hora = findViewById(R.id.input_edit_consulta_hora);
        input_edit_consulta_hora.setInputType(InputType.TYPE_NULL);
        input_edit_consulta_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog time_picker = new TimePickerDialog(EditConsultaActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String data = String.valueOf(hourOfDay) + ":" + String.valueOf(minute) + ":" + "00";
                        input_edit_consulta_hora.setText(data);
                    }
                }, 10, 12, true);
                time_picker.show();
            }
        });

        consulta = new Consulta();

        consulta.setId(consulta_id);

        Button btn_edit_consulta_data = findViewById(R.id.btn_edit_consulta_data);

        btn_edit_consulta_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consulta.setData(input_edit_consulta_dia.getText().toString() + " " + input_edit_consulta_hora.getText().toString());
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
