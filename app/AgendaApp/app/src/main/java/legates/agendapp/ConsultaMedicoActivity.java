package legates.agendapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import legates.agendapp.Models.Consulta;

public class ConsultaMedicoActivity extends AppCompatActivity {
    private AlertDialog userDialog;
    private AlertDialog.Builder userDialogBuilder;
    private ProgressDialog loading;
    private ArrayList<Consulta> consultas;
    private ListView listaMedicoConsultas;
    private SwipeRefreshLayout swipe;
    private String medico_id;
    private String consulta_data;
    public static Consulta consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_medico);



        swipe = findViewById(R.id.listConsultasSwipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recreate();
            }
        });

        listaMedicoConsultas = findViewById(R.id.listMedicoConsultas);

        registerForContextMenu(listaMedicoConsultas);

        listaMedicoConsultas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                consulta = (Consulta) listaMedicoConsultas.getItemAtPosition(position);

                userDialogBuilder = new AlertDialog.Builder(ConsultaMedicoActivity.this);
                userDialogBuilder.setMessage("Médico: " + consulta.getMedico() + "\n" + "Paciente: " + consulta.getPaciente() + "\n" + "Dia: " + consulta.getDia() + " às " + consulta.getHora() + "\n" + "Especialidade: " + consulta.getEspecialidade() + "\n" + "Convênio: " + consulta.getConvenio())
                        .setNeutralButton("Remarcar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ConsultaMedicoActivity.this, EditConsultaActivity.class);
                                intent.putExtra("consulta_id", consulta.getId());
                                startActivity(intent);
                            }
                        }).setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // new confirmaConsulta().execute();
                    }
                }).setNegativeButton("Desmarcar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //new removeConsulta().execute();
                    }
                }).setTitle(consulta.getData());

                userDialog = userDialogBuilder.create();
                userDialog.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        new getMedicoConsultas().execute();
    }

    private class getMedicoConsultas extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(ConsultaMedicoActivity.this);
            loading.setMessage("Carregando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            consulta = new Consulta();
            consultas = consulta.getByDataMedico(medico_id, consulta_data);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(loading.isShowing()){
                loading.dismiss();
            }

            ArrayAdapter<Consulta> adapter = new ArrayAdapter<Consulta>(ConsultaMedicoActivity.this, android.R.layout.simple_list_item_1, consultas);
            listaMedicoConsultas.setAdapter(adapter);
        }
    }
}
