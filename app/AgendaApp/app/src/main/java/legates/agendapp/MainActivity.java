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
    }
}
