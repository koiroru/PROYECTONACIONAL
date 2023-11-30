package twin.developers.projectmqtt;

import android.os.Parcel;
import android.os.Parcelable;

public class Pelicula implements Parcelable {
    private String nombre;
    private String resena;

    public Pelicula(String nombre, String reseña) {
        this.nombre = nombre;
        this.resena = reseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getResena() {
        return resena;
    }

    // Métodos para Parcelable
    protected Pelicula(Parcel in) {
        nombre = in.readString();
        resena = in.readString();
    }

    public static final Creator<Pelicula> CREATOR = new Creator<Pelicula>() {
        @Override
        public Pelicula createFromParcel(Parcel in) {
            return new Pelicula(in);
        }

        @Override
        public Pelicula[] newArray(int size) {
            return new Pelicula[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(resena);
    }
}
