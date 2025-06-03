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
    public static GestionPersona<Persona> mapeoPersonas(String archivo) {
        GestionPersona<Persona> p = new GestionPersona<>();
        List<Persona> personas = new ArrayList<>();

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

    public static void mapeoPersona(JSONObject jPersona, Persona persona) {
        try {
            persona.setNombre(jPersona.getString("nombre"));
            persona.setDni(jPersona.getString("dni"));
            persona.setEdad(jPersona.getInt("edad"));

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
}
