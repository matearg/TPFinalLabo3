package JSON;

import Classes.Libreria.GestionLibreria;
import Classes.Libreria.Libreria;
import Classes.Libreria.Productos.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Manejo de archivos JSON de librerias y productos
public class JSONLibreria {
    // Lectura de libreria
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

    // Lectura de inventario de productos
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

    // Lectura de productoLibreria
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

    // Escritura libreria
    public static void escrituraLibreria(GestionLibreria libreria, String archivo) {
        JSONObject jLibreria = new JSONObject();
        JSONObject l = new JSONObject();
        try {
            jLibreria.put("nombre", libreria.getLibreria().getNombre());
            jLibreria.put("ubicacoin", libreria.getLibreria().getUbicacion());
            JSONArray jInventario = escrituraInventario(libreria.getLibreria().getInventario());
            jLibreria.put("inventario", jInventario);
            l.put("libreria", jLibreria);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JSONUtiles.grabar(l, archivo);
    }

    // Escitura de inventario de productos
    public static JSONArray escrituraInventario(List<ProductoLibreria> inventario) {
        JSONArray jInventario = new JSONArray();

        for (ProductoLibreria p : inventario) {
            JSONObject jProducto = escrituraProducto(p);
            jInventario.put(jProducto);
        }

        return jInventario;
    }

    // Escritura de productoLibreria
    public static JSONObject escrituraProducto(ProductoLibreria producto) {
        JSONObject o = new JSONObject();

        try {
            o.put("tipo", producto.getTipo());
            o.put("nombre", producto.getNombre());
            o.put("marca", producto.getMarca());
            o.put("precio", producto.getPrecio());
            o.put("cantidadDisponible", producto.getCantidad());

            JSONArray jEspecificaciones = new JSONArray();
            for (Especificacion e : producto.getEspecificaciones()) {
                JSONObject jEspecificacion = new JSONObject();
                jEspecificacion.put("nombre", e.getNombre());
                jEspecificacion.put("valor", e.getValor());
                jEspecificaciones.put(jEspecificacion);
            }
            o.put("especificaciones", jEspecificaciones);

            JSONArray jEmbalajes = new JSONArray();
            for (Embalaje e : producto.getEmbalajes()) {
                JSONObject jEmbalaje = new JSONObject();
                jEmbalaje.put("tipo", e.getTipo());
                jEmbalaje.put("resistencia", e.getResistencia());
                jEmbalaje.put("dimensiones", e.getDimensiones());
                jEmbalajes.put(jEmbalaje);
            }
            o.put("embalajesDisponibles", jEmbalajes);

            if (producto instanceof Libro) {
                o.put("generoLiterario", ((Libro) producto).getGenero());
                o.put("anioPublicacion", ((Libro) producto).getAnioPublicacion());
            }

            if (producto instanceof Revista) {
                o.put("frecuenciaPublicacion", ((Revista) producto).getFrecuenciaDePublicacion());
                o.put("numeroEdicion", ((Revista) producto).getNumeroDeEdicion());
            }

            if (producto instanceof Comic) {
                o.put("universo", ((Comic) producto).getUniverso());
                o.put("color", ((Comic) producto).getColor());
            }

            if (producto instanceof Ebook) {
                o.put("alquilado", ((Ebook) producto).isAlquilado());
                o.put("drm", ((Ebook) producto).isDrm());
                o.put("idioma", ((Ebook) producto).getIdioma());
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return o;
    }
}