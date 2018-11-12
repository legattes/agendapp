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

public class ConsultaActivity extends AppCompatActivity {
    private AlertDialog userDialog;
    private AlertDialog.Builder userDialogBuilder;
    private ProgressDialog loading;
    private ArrayList<Consulta> consultas;
    private ListView listaConsultas;
    private SwipeRefreshLayout swipe;
    public static Consulta consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        swipe = findViewById(R.id.listConsultasSwipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recreate();
            }
        });

        listaConsultas = findViewById(R.id.listConsultas);

        registerForContextMenu(listaConsultas);

        listaConsultas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                consulta = (Consulta) listaConsultas.getItemAtPosition(position);

                userDialogBuilder = new AlertDialog.Builder(ConsultaActivity.this);
                userDialogBuilder.setMessage("Médico: " + consulta.getMedico() + "\n" + "Paciente: " + consulta.getPaciente() + "\n" + "Dia: " + consulta.getDia() + " às " + consulta.getHora() + "\n" + "Especialidade: " + consulta.getEspecialidade() + "\n" + "Convênio: " + consulta.getConvenio())
                    .setNeutralButton("Remarcar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Intent remarcarConsultaView = new Intent(ConsultaActivity.this, RemarcarConsultaActivity.class);
                            //startActivity(remarcarConsultaView);
                        }
                    }).setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setNegativeButton("Desmarcar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setTitle(consulta.getData());

                userDialog = userDialogBuilder.create();
                userDialog.show();
            }
        });

        Button btn_add = findViewById(R.id.btn_add_consulta);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addConsultasView = new Intent (ConsultaActivity.this, AddConsultaActivity.class);
                startActivity(addConsultasView);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        new getConsultas().execute();
    }

    private class getConsultas extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(ConsultaActivity.this);
            loading.setMessage("Carregando...");
            loading.setCancelable(false);
            loading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            consulta = new Consulta();
            consultas = consulta.get();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(loading.isShowing()){
                loading.dismiss();
            }

            ArrayAdapter<Consulta> adapter = new ArrayAdapter<Consulta>(ConsultaActivity.this, android.R.layout.simple_list_item_1, consultas);
            listaConsultas.setAdapter(adapter);
        }
    }
}
