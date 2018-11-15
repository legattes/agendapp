package legates.agendapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;

import legates.agendapp.Models.Medico;

public class getConsultaMedicoActivity extends AppCompatActivity {
    private Medico medico;
    private ArrayList<Medico> medicos = new ArrayList<Medico>();
    private Spinner listaMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_consulta_medico);

        listaMedicos = findViewById(R.id.input_consulta_medico);
    }
}
