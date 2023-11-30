package twin.developers.projectmqtt;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        ListView listViewPeliculas = findViewById(R.id.LVlistaPeliculas);

        // Obtener la lista de películas pasada desde MainActivity
        List<Pelicula> listaPeliculas;
        listaPeliculas = getIntent().getParcelableArrayListExtra("lista_peliculas");

        for (Pelicula pelicula : listaPeliculas) {
            Log.d("Datos de Película", "Nombre: " + pelicula.getNombre() + ", Reseña: " + pelicula.getResena());
        }


        if (listaPeliculas != null) {
            List<String> nombresResenas = new ArrayList<>();
            for (Pelicula pelicula : listaPeliculas) {
                String nombre = pelicula.getNombre();
                String resena = pelicula.getResena();

                // Verificar si el nombre o la reseña son nulos o vacíos
                if (nombre != null && !nombre.isEmpty() && resena != null && !resena.isEmpty()) {
                    nombresResenas.add(nombre + " - " + resena);
                } else {
                    nombresResenas.add("Datos de película incompletos");
                    // Otra opción: nombresResenas.add("Nombre: " + nombre + ", Reseña: " + reseña);
                }
            }

            // Mostrar la lista de nombres y reseñas en un ListView
            ListView listView = findViewById(R.id.LVlistaPeliculas);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombresResenas);
            listView.setAdapter(adapter);
        }
    }
}
