package legates.agendapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pacientes = findViewById(R.id.btn_pacientes);
        pacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getPacientesView = new Intent (MainActivity.this, PacienteActivity.class);
                startActivity(getPacientesView);
            }
        });

        Button medicos = findViewById(R.id.btn_medicos);
        medicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getMedicosView = new Intent (MainActivity.this, MedicoActivity.class);
                startActivity(getMedicosView);
            }
        });

        Button convenios = findViewById(R.id.btn_convenios);
        convenios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getConveniosView = new Intent (MainActivity.this, ConvenioActivity.class);
                startActivity(getConveniosView);
            }
        });

        Button especialidades = findViewById(R.id.btn_especialidades);
        especialidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getEspecialidadesView = new Intent (MainActivity.this, EspecialidadeActivity.class);
                startActivity(getEspecialidadesView);
            }
        });

        Button consultas = findViewById(R.id.btn_consultas);
        consultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getConsultasView = new Intent (MainActivity.this, ConsultaActivity.class);
                startActivity(getConsultasView);
            }
        });
    }
}
