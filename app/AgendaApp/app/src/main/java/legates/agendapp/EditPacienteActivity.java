package legates.agendapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditPacienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_paciente);
        Intent intent = getIntent();

        int pacienteId = intent.getIntExtra("PACIENTE_ID", 0);


    }
}
