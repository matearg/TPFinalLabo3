package JSON;

import Classes.Libreria.GestionLibreria;
import Classes.Libreria.Libreria;
import Classes.Libreria.Productos.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONLibreria {
    public static GestionLibreria mapeoLibreria(String archivo) {
        GestionLibreria l = new GestionLibreria();
        Libreria<ProductoLibreria> libreria = new Libreria<>();

        try {
            JSONObject json = new JSONObject(JSONUtiles.leer(archivo));
            JSONObject jLibreria = json.getJSONObject("libreria");
            libreria.setNombre(jLibreria.getString("nombre"));
            libreria.setUbicacion(jLibreria.getString("ubicacion"));
            JSONArray jInventario = jLibreria.getJSONArray("inventario");
            libreria.setInventario(mapeoInventario(jInventario));
            l.setLibreria(libreria);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return l;
    }

    public static List<ProductoLibreria> mapeoInventario(JSONArray jInventario) {
        List<ProductoLibreria> libreria = new ArrayList<>();

        for (int i = 0; i < jInventario.length(); i++) {
            try {
                JSONObject jProducto = jInventario.getJSONObject(i);
                if (jProducto.getString("tipo").equals("Libro")) {
                    Libro l = new Libro();
                    mapeoProducto(jProducto, l);
                    libreria.add(l);
                }

                if (jProducto.getString("tipo").equals("Revista")) {
                    Revista r = new Revista();
                    mapeoProducto(jProducto, r);
                    libreria.add(r);
                }

                if (jProducto.getString("tipo").equals("Comic")) {
                    Comic c = new Comic();
                    mapeoProducto(jProducto, c);
                    libreria.add(c);
                }

                if (jProducto.getString("tipo").equals("Ebook")) {
                    Ebook e = new Ebook();
                    mapeoProducto(jProducto, e);
                    libreria.add(e);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        return libreria;
    }

    public static void mapeoProducto(JSONObject jProducto, ProductoLibreria producto) {
        try {
            producto.setNombre(jProducto.getString("nombre"));
            producto.setMarca(jProducto.getString("marca"));
            producto.setPrecio(jProducto.getDouble("precio"));
            producto.setCantidad(jProducto.getInt("cantidadDisponible"));
            if (producto instanceof Libro) {
                ((Libro) producto).setGenero(jProducto.getString("generoLiterario"));
                ((Libro) producto).setAnioPublicacion(jProducto.getInt("anioPublicacion"));
            }

            if (producto instanceof Revista) {
                ((Revista) producto).setFrecuenciaDePublicacion(jProducto.getString("frecuenciaPublicacion"));
                ((Revista) producto).setNumeroDeEdicion(jProducto.getString("numeroEdicion"));
            }

            if (producto instanceof Comic) {
                ((Comic) producto).setColor(jProducto.getString("color"));
                ((Comic) producto).setUniverso(jProducto.getString("universo"));
            }

            if (producto instanceof Ebook) {
                ((Ebook) producto).setAlquilado(jProducto.getBoolean("alqiulado"));
                ((Ebook) producto).setDrm(jProducto.getBoolean("drm"));
                ((Ebook) producto).setIdioma(jProducto.getString("idioma"));
            }

            JSONArray jEspecificaciones = jProducto.getJSONArray("especificaciones");
            List<Especificacion> especificaciones = new ArrayList<>();
            for (int i = 0; i < jEspecificaciones.length(); i++) {
                JSONObject jEspecificacion = jEspecificaciones.getJSONObject(i);
                Especificacion especificacion = new Especificacion();
                especificacion.setNombre(jEspecificacion.getString("nombre"));
                especificacion.setValor(jEspecificacion.getString("valor"));
                especificaciones.add(especificacion);
            }
            producto.setEspecificaciones(especificaciones);

            JSONArray jEmbalajes = jProducto.getJSONArray("embalajesDisponibles");
            List<Embalaje> embalajes = new ArrayList<>();
            for (int i = 0; i < jEmbalajes.length(); i++) {
                JSONObject jEmbalaje = jEmbalajes.getJSONObject(i);
                Embalaje embalaje = new Embalaje();
                embalaje.setTipo(jEmbalaje.getString("tipo"));
                embalaje.setResistencia(jEmbalaje.getString("resistencia"));
                embalaje.setDimensiones(jEmbalaje.getString("dimensiones"));
                embalajes.add(embalaje);
            }
            producto.setEmbalajes(embalajes);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}