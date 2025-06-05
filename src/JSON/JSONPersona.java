package JSON;

import Classes.Libreria.Productos.Ebook;
import Classes.Persona.GestionPersona;
import Classes.Persona.Personas.Administrador;
import Classes.Persona.Personas.Cliente;
import Classes.Persona.Personas.Empleado;
import Classes.Persona.Personas.Persona;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONPersona {

    // Lectura de Personas
    public static GestionPersona<Persona> mapeoPersonas(String archivo) {
        GestionPersona<Persona> p = new GestionPersona<>();
        List<Persona> personas;

        try {
            JSONObject json = new JSONObject(JSONUtiles.leer(archivo));
            JSONArray jPersonas = json.getJSONArray("personas");
            personas = mapeoListaPersonas(jPersonas);
            p.setPersonas(personas);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return p;
    }

    // Lectura de la lista de personas
    public static List<Persona> mapeoListaPersonas(JSONArray jPersonas) {
        List<Persona> personas = new ArrayList<>();

        for (int i = 0; i < jPersonas.length(); i++) {
            try {
                JSONObject jPersona = jPersonas.getJSONObject(i);
                if (jPersona.getString("tipoPersona").equals("Empleado")) {
                    Empleado e = new Empleado();
                    mapeoPersona(jPersona, e);
                    personas.add(e);
                }

                if (jPersona.getString("tipoPersona").equals("Administrador")) {
                    Administrador e = new Administrador();
                    mapeoPersona(jPersona, e);
                    personas.add(e);
                }

                if (jPersona.getString("tipoPersona").equals("Cliente")) {
                    Cliente e = new Cliente();
                    mapeoPersona(jPersona, e);
                    personas.add(e);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        return personas;
    }

    // Lectura de objeto persona
    public static void mapeoPersona(JSONObject jPersona, Persona persona) {
        try {
            persona.setNombre(jPersona.getString("nombre"));
            persona.setDni(jPersona.getString("dni"));
            persona.setEdad(jPersona.getInt("edad"));
            persona.setPinAcceso(jPersona.getString("pinAcceso"));

            if (persona instanceof Empleado) {
                ((Empleado) persona).setSalario(jPersona.getDouble("salario"));
                ((Empleado) persona).setAdmin(jPersona.getBoolean("admin"));
            }

            if (persona instanceof Administrador) {
                ((Administrador) persona).setSalario(jPersona.getDouble("salario"));
                ((Administrador) persona).setAdmin(jPersona.getBoolean("admin"));
            }

            if (persona instanceof Cliente) {
                JSONArray jLibros = jPersona.getJSONArray("libros");
                List<Ebook> libreria = new ArrayList<>();
                for (int i = 0; i < jLibros.length(); i++) {
                    try {
                        JSONObject jProducto = jLibros.getJSONObject(i);
                        if (jProducto.getString("tipo").equals("Ebook")) {
                            Ebook e = new Ebook();
                            JSONLibreria.mapeoProducto(jProducto, e);
                            libreria.add(e);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                ((Cliente) persona).setLibros(libreria);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    // Escritura de Personas
    public static void escrituraPersonas(GestionPersona<Persona> personas, String archivo) {
        JSONObject p = new JSONObject();
        try {
            JSONArray jPersonas = escrituraListaPersonas(personas.getPersonas());
            p.put("personas", jPersonas);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JSONUtiles.grabar(p, archivo);
    }

    // Escritura de la lista de personas
    public static JSONArray escrituraListaPersonas(List<Persona> listaPersonas) {
        JSONArray jListaPersonas = new JSONArray();

        for (Persona p : listaPersonas) {
            JSONObject jPersona = escrituraPersona(p);
            jListaPersonas.put(jPersona);
        }

        return jListaPersonas;
    }

    // Escritura de objeto persona
    public static JSONObject escrituraPersona(Persona persona) {
        JSONObject p = new JSONObject();

        try {
            p.put("tipoPersona", persona.getTipo());
            p.put("nombre", persona.getNombre());
            p.put("pinAcceso", persona.getPinAcceso());
            p.put("dni", persona.getDni());
            p.put("edad", persona.getEdad());

            if (persona instanceof Administrador) {
                p.put("salario", ((Administrador) persona).getSalario());
                p.put("admin", ((Administrador) persona).isAdmin());
            }

            if (persona instanceof Empleado) {
                p.put("salario", ((Empleado) persona).getSalario());
                p.put("admin", ((Empleado) persona).isAdmin());
            }

            if (persona instanceof Cliente) {
                JSONArray jLibros = new JSONArray();
                for (Ebook e : ((Cliente) persona).getLibros()) {
                    JSONObject jEbook = JSONLibreria.escrituraProducto(e);
                    jLibros.put(jEbook);
                }
                p.put("libros", jLibros);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return p;
    }
}
