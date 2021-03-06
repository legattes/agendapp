package legates.agendapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import legates.agendapp.Models.Consulta;
import legates.agendapp.Models.Convenio;
import legates.agendapp.Models.Especialidade;
import legates.agendapp.Models.Medico;
import legates.agendapp.Models.Paciente;

import static legates.agendapp.ConsultaActivity.consulta;

public class AddConsultaActivity extends AppCompatActivity {
    private ProgressDialog loading;
    private ProgressDialog loading2;
    private Paciente paciente;
    private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
    private Spinner listaPacientes;

    private EditText input_dia;
    private EditText input_hora;

    private Especialidade especialidade;
    private ArrayList<Especialidade> especialidades = new ArrayList<Especialidade>();
    private Spinner listaEspecialidades;

    private Convenio convenio;
    private ArrayList<Convenio> convenios = new ArrayList<Convenio>();
    private Spinner listaConvenios;

    private Medico medico;
    private ArrayList<Medico> medicos = new ArrayList<Medico>();
    private Spinner listaMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consulta);

        listaMedicos = findViewById(R.id.input_consulta_medico);
        listaPacientes = findViewById(R.id.input_consulta_paciente);
        listaEspecialidades = findViewById(R.id.input_consulta_especialidade);
        listaConvenios = findViewById(R.id.input_consulta_convenio);

        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        input_dia = findViewById(R.id.input_consulta_dia);
        input_dia.setInputType(InputType.TYPE_NULL);

        input_dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog date_picker = new DatePickerDialog(AddConsultaActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String data = String.valueOf(year) + "-" + String.valueOf(month) + "-" +
                        String.valueOf(dayOfMonth);
                        input_dia.setText(data);
                    }
                }, year, month, dayOfMonth);

                date_picker.show();
            }
        });

        input_hora = findViewById(R.id.input_consulta_hora);
        input_hora.setInputType(InputType.TYPE_NULL);
        input_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog time_picker = new TimePickerDialog(AddConsultaActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String data = String.valueOf(hourOfDay) + ":" + String.valueOf(minute) + ":" + "00";
                        input_hora.setText(data);
                    }
                }, 10, 12, true);
                time_picker.show();
            }
        });

        new getter().execute();

        listaMedicos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medico = (Medico) listaMedicos.getItemAtPosition(position);
                if(Integer.parseInt(medico.getId()) > 0){
                    AsyncTask<Void, Void, Void> especialidades = new getEspecialidades();
                    especialidades.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btn_salvar_consulta = findViewById(R.id.btn_salvar_consulta);
        btn_salvar_consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medico = (Medico) listaMedicos.getSelectedItem();
                paciente = (Paciente) listaPacientes.getSelectedItem();
                especialidade = (Especialidade) listaEspecialidades.getSelectedItem();
                convenio = (Convenio) listaConvenios.getSelectedItem();

                if(medico == null || paciente == null || convenio == null || especialidade == null || input_dia.getText().toString().equals("") || input_hora.getText().toString().equals("")){
                    Toast.makeText(AddConsultaActivity.this, "É necessário preencher todos os campos", Toast.LENGTH_LONG).show();
                } else {
                    consulta = new Consulta();

                    consulta.setMedico(medico.getId());
                    consulta.setPaciente(paciente.getId());
                    consulta.setEspecialidade(especialidade.getId());
                    consulta.setConvenio(convenio.getId());
                    consulta.setData(input_dia.getText().toString() + " " + input_hora.getText().toString());

                    new addConsulta().execute();
                }
            }
        });

    }

    private class getter extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(AddConsultaActivity.this);
            loading.setMessage("Carregando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AsyncTask<Void, Void, Void> medicos = new getMedicos();
            AsyncTask<Void, Void, Void> pacientes = new getPacientes();
            AsyncTask<Void, Void, Void> convenios = new getConvenios();

            medicos.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            pacientes.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            convenios.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            while(medicos.getStatus() != Status.FINISHED
                    || pacientes.getStatus() != Status.FINISHED
                    || convenios.getStatus() != Status.FINISHED){
                Thread.currentThread();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(loading.isShowing()){
                loading.dismiss();
            }
        }
    }

    private class getMedicos extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            medico = new Medico();
            medicos = medico.get();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            ArrayAdapter<Medico> adapter = new ArrayAdapter<Medico>(AddConsultaActivity.this, android.R.layout.simple_spinner_item, medicos);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            listaMedicos.setAdapter(adapter);
        }
    }

    private class getPacientes extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            paciente = new Paciente();
            pacientes = paciente.get();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            ArrayAdapter<Paciente> adapter = new ArrayAdapter<Paciente>(AddConsultaActivity.this, android.R.layout.simple_spinner_item, pacientes);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            listaPacientes.setAdapter(adapter);
        }
    }

    private class getConvenios extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            convenio = new Convenio();
            convenios = convenio.get();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            ArrayAdapter<Convenio> adapter = new ArrayAdapter<Convenio>(AddConsultaActivity.this, android.R.layout.simple_spinner_item, convenios);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            listaConvenios.setAdapter(adapter);
        }
    }

    private class getEspecialidades extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading2 = new ProgressDialog(AddConsultaActivity.this);
            loading2.setMessage("Carregando especialidades...");
            loading2.setCancelable(false);
            loading2.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            especialidade = new Especialidade();
            especialidades = especialidade.getByMedico(medico.getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(loading2.isShowing()){
                loading2.dismiss();
            }

            ArrayAdapter<Especialidade> adapter = new ArrayAdapter<Especialidade>(AddConsultaActivity.this, android.R.layout.simple_spinner_item, especialidades);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            listaEspecialidades.setAdapter(adapter);
        }
    }

    private class addConsulta extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading2 = new ProgressDialog(AddConsultaActivity.this);
            loading2.setMessage("Salvando...");
            loading2.setCancelable(false);
            loading2.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            if(!consulta.add()){
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

            Toast toast = Toast.makeText(AddConsultaActivity.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT);
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

            Toast toast = Toast.makeText(AddConsultaActivity.this, "Não foi possível cadastrar", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 500);
            toast.show();
            finish();
        }
    }
}
