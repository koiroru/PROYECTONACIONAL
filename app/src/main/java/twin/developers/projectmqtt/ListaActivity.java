package twin.developers.projectmqtt;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        FirebaseApp.initializeApp(this); // Si aún no se inicializa automáticamente
        // Obtén una referencia a tu base de datos
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("peliculas");

        // Agrega datos a un nodo específico (por ejemplo, una película)
        String peliculaNombre = "ID_UNICO_DE_PELICULA";
        databaseReference.child(peliculaNombre).setValue("datos_de_la_pelicula");
        // Obtén una referencia a tu base de datos
        databaseReference = FirebaseDatabase.getInstance().getReference("peliculas");

        // Agrega un listener para obtener datos en tiempo real
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Obtiene los datos individuales de cada película
                    String nombre = snapshot.child("nombre").getValue(String.class);
                    String resena = snapshot.child("reseña").getValue(String.class);

                    // Haz algo con los datos obtenidos, como imprimirlos en el log
                    Log.d("Datos de Película", "Nombre: " + nombre + ", Reseña: " + resena);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error Firebase", "Error al leer datos", databaseError.toException());
            }
        });


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

                    nombresResenas.add(nombre + " - " + resena);
            }

            // Mostrar la lista de nombres y reseñas en un ListView
            ListView listView = findViewById(R.id.LVlistaPeliculas);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombresResenas);
            listView.setAdapter(adapter);
        }
    }
}
