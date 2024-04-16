package com.example.ejemplo1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText txtNumero1, txtNumero2;
    Button btnResultado;
    TextView txtResultado;
    Spinner spnOperacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultado = findViewById(R.id.txtResultado);
        txtNumero1 = findViewById(R.id.txtNumero1);
        txtNumero2 = findViewById(R.id.txtNumero2);
        btnResultado = findViewById(R.id.btnResultado);
        spnOperacion = findViewById(R.id.spnOperacion);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operaciones_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOperacion.setAdapter(adapter);

        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado();
            }
        });
    }

    private void calcularResultado() {
        try {
            double num1 = Double.parseDouble(txtNumero1.getText().toString());
            double num2 = Double.parseDouble(txtNumero2.getText().toString());
            String operacion = spnOperacion.getSelectedItem().toString();
            double resultado = 0;

            switch (operacion) {
                case "Suma":
                    resultado = num1 + num2;
                    break;
                case "Resta":
                    resultado = num1 - num2;
                    break;
                case "Multiplicacion":
                    resultado = num1 * num2;
                    break;
                case "Division":
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        txtResultado.setText("Error: División por cero");
                        return;
                    }
                    break;
            }
            txtResultado.setText(String.format("Resultado: %s", resultado));
        } catch (NumberFormatException e) {
            txtResultado.setText("Error: Entrada no válida");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
