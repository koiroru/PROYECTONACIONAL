package twin.developers.projectmqtt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<Pelicula> listaPeliculas;
    private MqttClient mqttClient;
    private EditText AgregarResena;
    private EditText AgregarNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaPeliculas = new ArrayList<>();

        mqttClient = new MqttClient();
        AgregarResena = findViewById(R.id.agregaresena);
        AgregarNombre = findViewById(R.id.agreganombre);

        Button btnEnviarResena = findViewById(R.id.btnagregar);
        btnEnviarResena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resena = AgregarResena.getText().toString();
                mqttClient.publishMessage(resena);
                finish();
            }
        });

        btnEnviarResena.setOnClickListener(new View.OnClickListener() {

            String nombre = AgregarNombre.getText().toString();
            String resena = AgregarResena.getText().toString();
            @Override
            public void onClick(View view) {
                Pelicula nuevaPelicula = new Pelicula(nombre, resena);
                listaPeliculas.add(nuevaPelicula);

                // Limpiar los campos después de agregar la película
                AgregarNombre.getText().clear();
                AgregarResena.getText().clear();
            }
        });

        Button btnVerPeliculas = findViewById(R.id.btnverlista);
        btnVerPeliculas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                intent.putParcelableArrayListExtra("lista_peliculas", new ArrayList<>(listaPeliculas));
                startActivity(intent);


            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mqttClient.disconnect();
    }
}