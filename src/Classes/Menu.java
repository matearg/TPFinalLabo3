package Classes;

import Classes.Libreria.GestionLibreria;
import Classes.Libreria.Productos.*;
import Classes.Persona.GestionPersona;
import Classes.Persona.Personas.Administrador;
import Classes.Persona.Personas.Cliente;
import Classes.Persona.Personas.Empleado;
import Classes.Persona.Personas.Persona;
import Exceptions.ElementoExistenteException;
import JSON.JSONLibreria;
import JSON.JSONPersona;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final static GestionLibreria libreria = JSONLibreria.mapeoLibreria("libreria.json");
    private final static GestionPersona<Persona> personas = JSONPersona.mapeoPersonas("persona.json");

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("--- Libreria Mar Abierto ---");
        do {
            System.out.println(" 1- Login");
            System.out.println(" 0- Salir");
            System.out.print("Ingrese una opcion: ");
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
        int index;
        do {
            System.out.print("Ingrese su Nombre: ");
            String nombre = scanner.nextLine();

            index = personas.buscarNombre(nombre);
        } while (index == -1);

        Persona p = personas.getPersonas().get(index);
        String pin;
        do {
            intentos++;
            System.out.println("Intentos: " + intentos + "/5");
            System.out.print("Ingrese su pin de acceso: ");
            pin = scanner.nextLine();
        } while (intentos <= 5 && !pin.equals(p.getPinAcceso()));

        if (pin.equals(p.getPinAcceso())) {
            if (p instanceof Administrador) {
                menuAdmininstrador();
            }
            if (p instanceof Empleado) {
                menuEmpleado();
            }
            if (p instanceof Cliente) {
                menuCliente((Cliente) p);
            }
        }
    }


    public static void menuAdmininstrador() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("-------------------------------------------");
            System.out.println(" 1- Menu Personas");
            System.out.println(" 2- Menu Libros");
            System.out.println(" 0- Salir");
            System.out.print(" Elija una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuPersonas();
                    break;
                case 2:
                    menuLibros();
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

    public static void menuLibros() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("-------------------------------------------");
            System.out.println(" 1- Ver Libros");
            System.out.println(" 2- Cambiar Estado de Alquiler");
            System.out.println(" 3- Agregar Libro");
            System.out.println(" 0- Salir");
            System.out.print(" Elija una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    libreria.ver();
                    break;
                case 2:
                    libreria.cambiarEstadoAlquiler();
                    break;
                case 3:
                    creaLibro();
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

    public static void creaLibro() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        int index;
        do {
            System.out.println("-------------------------------------");
            System.out.println(" 1- Libro");
            System.out.println(" 2- Revista");
            System.out.println(" 3- Comic");
            System.out.println(" 4- Ebook");
            System.out.println(" 0- Salir");
            System.out.print("Que tipo de producto quiere crear: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    Libro l = new Libro();
                    l.setTipo("Libro");
                    System.out.print("Ingrese el nombre del libro: ");
                    l.setNombre(scanner.nextLine());
                    index = libreria.buscar(l);
                    if (index == -1) {
                        System.out.print("Ingrese la marca: ");
                        l.setMarca(scanner.nextLine());
                        System.out.print("Ingrese el precio: ");
                        l.setPrecio(scanner.nextDouble());
                        System.out.print("Ingrese la cantidad: ");
                        l.setCantidad(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Ingrese el genero: ");
                        l.setGenero(scanner.nextLine());
                        System.out.print("Ingrese anio de publicacion: ");
                        l.setAnioPublicacion(scanner.nextInt());
                        l.setEmbalajes(crearEmbalaje());
                        l.setEspecificaciones(crearEspecificacion());
                        libreria.getLibreria().agregar(l);
                    } else {
                        throw new ElementoExistenteException("El elemento ya existe.");
                    }
                    break;
                case 2:
                    Revista r = new Revista();
                    r.setTipo("Revista");
                    System.out.print("Ingrese el nombre del libro: ");
                    r.setNombre(scanner.nextLine());
                    index = libreria.buscar(r);
                    if (index == -1) {
                        System.out.print("Ingrese la marca: ");
                        r.setMarca(scanner.nextLine());
                        System.out.print("Ingrese el precio: ");
                        r.setPrecio(scanner.nextDouble());
                        System.out.print("Ingrese la cantidad: ");
                        r.setCantidad(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Ingrese frecuencia de publicacion: ");
                        r.setFrecuenciaDePublicacion(scanner.nextLine());
                        System.out.print("Igrese numero de edicion: ");
                        r.setNumeroDeEdicion(scanner.nextLine());
                        r.setEmbalajes(crearEmbalaje());
                        r.setEspecificaciones(crearEspecificacion());
                        libreria.getLibreria().agregar(r);
                    } else {
                        throw new ElementoExistenteException("El elemento ya existe.");
                    }
                    break;
                case 3:
                    Comic c = new Comic();
                    c.setTipo("Comic");
                    System.out.print("Ingrese el nombre del libro: ");
                    c.setNombre(scanner.nextLine());
                    index = libreria.buscar(c);
                    if (index == -1) {
                        System.out.print("Ingrese la marca: ");
                        c.setMarca(scanner.nextLine());
                        System.out.print("Ingrese el precio: ");
                        c.setPrecio(scanner.nextDouble());
                        System.out.print("Ingrese la cantidad: ");
                        c.setCantidad(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Ingrese el universo: ");
                        c.setUniverso(scanner.nextLine());
                        System.out.print("Ingrese el color: ");
                        c.setColor(scanner.nextLine());
                        c.setEmbalajes(crearEmbalaje());
                        c.setEspecificaciones(crearEspecificacion());
                        libreria.getLibreria().agregar(c);
                    } else {
                        throw new ElementoExistenteException("El elemento ya existe.");
                    }
                    break;
                case 4:
                    Ebook e = new Ebook();
                    e.setTipo("Ebook");
                    System.out.print("Ingrese el nombre del libro: ");
                    e.setNombre(scanner.nextLine());
                    index = libreria.buscar(e);
                    if (index == -1) {
                        System.out.print("Ingrese la marca: ");
                        e.setMarca(scanner.nextLine());
                        System.out.print("Ingrese el precio: ");
                        e.setPrecio(scanner.nextDouble());
                        System.out.print("Ingrese la cantidad: ");
                        e.setCantidad(scanner.nextInt());
                        scanner.nextLine();
                        e.setDrm(true);
                        System.out.print("Ingrese el idioma: ");
                        e.setIdioma(scanner.nextLine());
                        e.setAlquilado(false);
                        e.setEmbalajes(crearEmbalaje());
                        e.setEspecificaciones(crearEspecificacion());
                        libreria.getLibreria().agregar(e);
                    } else {
                        throw new ElementoExistenteException("El elemento ya existe.");
                    }
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

    public static void menuPersonas() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("-------------------------------------------");
            System.out.println(" 1- Ver Personas");
            System.out.println(" 2- Eliminar persona por DNI");
            System.out.println(" 3- Cambiar salario de persona por DNI");
            System.out.println(" 4- Agregar Persona");
            System.out.println(" 0- Salir");
            System.out.print(" Elija una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    personas.ver();
                    break;
                case 2:
                    System.out.print("Ingrese el DNI que desea eliminar: ");
                    String dniEliminar = scanner.nextLine();
                    scanner.nextLine();
                    personas.eliminarPorDni(dniEliminar);
                    break;
                case 3:
                    System.out.print("Ingrese el DNI para cambiar su sueldo: ");
                    String dniSueldo = scanner.nextLine();
                    scanner.nextLine();
                    personas.cambiarSueldoPorDni(dniSueldo);
                    break;
                case 4:
                    creaPersona();
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

    public static void creaPersona() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        int index;
        do {
            System.out.println(" 1- Administrador");
            System.out.println(" 2- Empleado");
            System.out.println(" 3- Cliente");
            System.out.println(" 0- Salir");
            System.out.print("Que tipo Usuario quiere agregar: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    Administrador a = new Administrador();
                    a.setTipo("Administrador");
                    System.out.print("Ingrese el DNI: ");
                    a.setDni(scanner.nextLine());
                    index = personas.buscar(a);
                    if (index == -1) {
                        System.out.print("Ingrese el nombre: ");
                        a.setNombre(scanner.nextLine());
                        System.out.print("Ingrese el pin: ");
                        a.setPinAcceso(scanner.nextLine());
                        System.out.print("Ingrese la edad: ");
                        a.setEdad(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Ingrese el salario: ");
                        a.setSalario(scanner.nextDouble());
                        personas.agregar(a);
                    } else {
                        throw new ElementoExistenteException("La persona ya existe.");
                    }
                    break;
                case 2:
                    Empleado e = new Empleado();
                    e.setTipo("Empleado");
                    System.out.print("Ingrese el DNI: ");
                    e.setDni(scanner.nextLine());
                    index = personas.buscar(e);
                    if (index == -1) {
                        System.out.print("Ingrese el nombre: ");
                        e.setNombre(scanner.nextLine());
                        System.out.print("Ingrese el pin: ");
                        e.setPinAcceso(scanner.nextLine());
                        System.out.print("Ingrese la edad: ");
                        e.setEdad(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Ingrese el salario: ");
                        e.setSalario(scanner.nextDouble());
                        personas.agregar(e);
                    } else {
                        throw new ElementoExistenteException("La persona ya existe.");
                    }
                    break;
                case 3:
                    Cliente c = new Cliente();
                    c.setTipo("Cliente");
                    System.out.print("Ingrese el DNI: ");
                    c.setDni(scanner.nextLine());
                    index = personas.buscar(c);
                    if (index == -1) {
                        System.out.print("Ingrese el nombre: ");
                        c.setNombre(scanner.nextLine());
                        System.out.print("Ingrese el pin: ");
                        c.setPinAcceso(scanner.nextLine());
                        System.out.print("Ingrese la edad: ");
                        c.setEdad(scanner.nextInt());
                        scanner.nextLine();
                        personas.agregar(c);
                    } else {
                        throw new ElementoExistenteException("La persona ya existe.");
                    }
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


    public static List<Especificacion> crearEspecificacion() {
        System.out.println("Especificacion:");
        Scanner scan = new Scanner(System.in);
        List<Especificacion> l = new ArrayList<>();
        System.out.println("Ingrese el nombre");
        String nombre = scan.next();
        System.out.println("Ingrese el valor");
        String valor = scan.next();
        l.add(new Especificacion(nombre, valor));
        return l;
    }

    public static List<Embalaje> crearEmbalaje() {
        System.out.println("Embalaje:");
        List<Embalaje> l = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        System.out.println("Ingrese el tipo");
        String tipo = scan.next();
        System.out.println("Ingrese la resistencia");
        String resistencia = scan.next();
        System.out.println("Ingrese las dimensiones");
        String dimensiones = scan.next();

        l.add(new Embalaje(tipo, resistencia, dimensiones));

        return l;
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
                case 2:
                    cliente.verLibros();
                    break;
                case 3:
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
