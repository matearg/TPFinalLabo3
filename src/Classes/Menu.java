package Classes;

import Classes.Libreria.GestionLibreria;
import Classes.Persona.GestionPersona;
import Classes.Persona.Personas.Administrador;
import Classes.Persona.Personas.Cliente;
import Classes.Persona.Personas.Empleado;
import Classes.Persona.Personas.Persona;
import JSON.JSONLibreria;
import JSON.JSONPersona;

import java.util.Scanner;

public class Menu {
    private static final GestionLibreria libreria = JSONLibreria.mapeoLibreria("libreria.json");
    private final static GestionPersona<Persona> personas = JSONPersona.mapeoPersonas("persona.json");

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("--- Libreria Mar Abierto ---");
        do {
            System.out.println(" 1- Login");
            System.out.println(" 0- Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    login();
                    break;
                case 0:
                    System.out.println("Adios!");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (opcion != 0);

        JSONLibreria.escrituraLibreria(libreria, "libreria.json");
        JSONPersona.escrituraPersonas(personas, "persona.json");
    }

    public static void login() {
        Scanner scanner = new Scanner(System.in);
        int intentos = 0;
        while (intentos <= 5) {
            intentos++;
            System.out.print("Ingrese su Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese su pin de acceso: ");
            String pin = scanner.nextLine();
            System.out.println("Intentos: " + intentos + "/5");

            for (Persona p : personas.getPersonas()) {
                if (p.getNombre().equals(nombre)) {
                    if (p.getPinAcceso().equals(pin)) {
                        if (p instanceof Administrador) {
                            menuAdmininstrador();
                        }
                        if (p instanceof Empleado) {
                            menuEmpleado();
                        }
                        if (p instanceof Cliente) {
                            menuCliente((Cliente) p);
                        }
                    } else {
                        System.out.println("Pin incorrecto.");
                    }
                } else {
                    System.out.println("Nombre no encontrado.");
                }
            }
        }
    }

    public static void menuAdmininstrador() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("-------------------------------------------");
            System.out.println(" 1- Ver Personas");
            System.out.println(" 2- Ver Libros");
            System.out.println(" 3- Cambiar Estado de Alquiler");
            System.out.println(" 4- Eliminar persona por DNI");
            System.out.println(" 5- Cambiar salario de persona por DNI");
            System.out.println(" 0- Salir");
            System.out.print(" Elija una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    personas.ver();
                    break;
                case 2:
                    libreria.ver();
                    break;
                case 3:
                    libreria.cambiarEstadoAlquiler();
                    break;
                case 4:
                    System.out.print("Ingrese el DNI que desea eliminar: ");
                    String dniEliminar = scanner.nextLine();
                    scanner.nextLine();
                    personas.eliminarPorDni(dniEliminar);
                    break;
                case 5:
                    System.out.print("Ingrese el DNI para cambiar su sueldo: ");
                    String dniSueldo = scanner.nextLine();
                    scanner.nextLine();
                    personas.cambiarSueldoPorDni(dniSueldo);
                    break;
                case 0:
                    System.out.println("Adios!");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (opcion != 0);
    }

    public static void menuEmpleado() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("-------------------------------------------");
            System.out.println(" 1- Ver Personas");
            System.out.println(" 2- Ver Libros");
            System.out.println(" 3- Cambiar Estado de Alquiler");
            System.out.println(" 0- Salir");
            System.out.print(" Elija una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    personas.ver();
                    break;
                case 2:
                    libreria.ver();
                    break;
                case 3:
                    libreria.cambiarEstadoAlquiler();
                    break;
                case 0:
                    System.out.println("Adios!");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (opcion != 0);
    }

    public static void menuCliente(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("-------------------------------------------");
            System.out.println(" 1- Ver Productos");
            System.out.println(" 2- Ver mis Libros");
            System.out.println(" 3- Alquilar un Ebook");
            System.out.println(" 0- Salir");
            System.out.print(" Elija una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    libreria.ver();
                    break;
                case 3:
                    cliente.verLibros();
                    break;
                case 4:
                    cliente.cargarLibro(libreria);
                    break;
                case 0:
                    System.out.println("Adios!");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (opcion != 0);
    }
}
